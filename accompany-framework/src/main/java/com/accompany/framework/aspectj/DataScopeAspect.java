package com.accompany.framework.aspectj;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.accompany.common.annotation.DataScope;
import com.accompany.common.constant.UserConstants;
import com.accompany.common.core.domain.BaseEntity;
import com.accompany.common.core.domain.entity.SysRole;
import com.accompany.common.core.domain.entity.SysUser;
import com.accompany.common.core.domain.model.LoginUser;
import com.accompany.common.core.text.Convert;
import com.accompany.common.exception.ServiceException;
import com.accompany.common.utils.SecurityUtils;
import com.accompany.common.utils.StringUtils;
import com.accompany.framework.security.context.PermissionContextHolder;
import com.accompany.system.service.ISysMenuService;
import com.accompany.system.service.ISysUserService;

/** 若依数据范围过滤；每次查询重新读取权限，避免旧会话保留已收紧的数据范围。 */
@Aspect
@Component
public class DataScopeAspect
{
    public static final String DATA_SCOPE = "dataScope";

    @Autowired
    @Lazy
    private ISysUserService userService;

    @Autowired
    @Lazy
    private ISysMenuService menuService;

    @Before("@annotation(controllerDataScope)")
    public void doBefore(JoinPoint point, DataScope controllerDataScope)
    {
        if (point.getArgs().length == 0 || !(point.getArgs()[0] instanceof BaseEntity))
        {
            throw new ServiceException("数据范围查询参数无效");
        }
        BaseEntity query = (BaseEntity) point.getArgs()[0];
        // The request must never supply its own SQL fragment; fail closed until a scope is resolved.
        query.getParams().put(DATA_SCOPE, " AND (1 = 0)");
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (loginUser == null || loginUser.getUserId() == null)
        {
            return;
        }
        // selectUserById is intentionally not scoped: it only reloads the authenticated identity.
        SysUser currentUser = userService.selectUserById(loginUser.getUserId());
        if (currentUser == null || !"0".equals(currentUser.getStatus()) || !"0".equals(currentUser.getDelFlag()))
        {
            return;
        }
        if (currentUser.isAdmin())
        {
            query.getParams().put(DATA_SCOPE, "");
            return;
        }
        if (currentUser.getRoles() != null)
        {
            for (SysRole role : currentUser.getRoles())
            {
                if (role != null && role.getRoleId() != null)
                {
                    role.setPermissions(menuService.selectMenuPermsByRoleId(role.getRoleId()));
                }
            }
        }
        String permission = StringUtils.defaultIfEmpty(controllerDataScope.permission(), PermissionContextHolder.getContext());
        query.getParams().put(DATA_SCOPE, buildScopeSql(currentUser, controllerDataScope.userAlias(),
                controllerDataScope.deptAlias(), controllerDataScope.userField(), controllerDataScope.deptField(), permission));
    }

    /** Build SQL only from trusted annotation aliases and database Long identifiers. */
    static String buildScopeSql(SysUser user, String userAlias, String deptAlias, String userField, String deptField, String permission)
    {
        if (user == null || user.getRoles() == null || !identifier(deptAlias) || !identifier(deptField)
                || !identifier(userField) || (StringUtils.isNotBlank(userAlias) && !identifier(userAlias)))
        {
            return " AND (1 = 0)";
        }
        List<String> clauses = new ArrayList<>();
        for (SysRole role : user.getRoles())
        {
            if (role == null || !StringUtils.equals(role.getStatus(), UserConstants.ROLE_NORMAL)
                    || !"0".equals(role.getDelFlag()) || !positive(role.getRoleId())
                    || (StringUtils.isNotEmpty(permission)
                        && !StringUtils.containsAny(role.getPermissions(), Convert.toStrArray(permission))))
            {
                continue;
            }
            String scope = role.getDataScope();
            if ("1".equals(scope))
            {
                return "";
            }
            if ("2".equals(scope))
            {
                clauses.add(deptAlias + "." + deptField + " IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = " + role.getRoleId() + " )");
            }
            else if ("3".equals(scope) && positive(user.getDeptId()))
            {
                clauses.add(deptAlias + "." + deptField + " = " + user.getDeptId());
            }
            else if ("4".equals(scope) && positive(user.getDeptId()))
            {
                clauses.add(deptAlias + "." + deptField + " IN ( SELECT dept_id FROM sys_dept WHERE dept_id = "
                        + user.getDeptId() + " OR find_in_set( " + user.getDeptId() + ", ancestors ) )");
            }
            else if ("5".equals(scope) && StringUtils.isNotBlank(userAlias) && positive(user.getUserId()))
            {
                clauses.add(userAlias + "." + userField + " = " + user.getUserId());
            }
        }
        return clauses.isEmpty() ? " AND (1 = 0)" : " AND (" + clauses.stream().collect(Collectors.joining(" OR ")) + ")";
    }

    private static boolean identifier(String value)
    {
        return value != null && value.matches("[A-Za-z_][A-Za-z0-9_]*");
    }

    private static boolean positive(Long id)
    {
        return id != null && id > 0;
    }
}
