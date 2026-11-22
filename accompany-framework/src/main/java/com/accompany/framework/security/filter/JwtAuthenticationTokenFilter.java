package com.accompany.framework.security.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.accompany.common.constant.Constants;
import com.accompany.common.core.domain.entity.SysRole;
import com.accompany.common.core.domain.entity.SysUser;
import com.accompany.common.core.domain.model.LoginUser;
import com.accompany.common.utils.SecurityUtils;
import com.accompany.common.utils.StringUtils;
import com.accompany.framework.web.service.TokenService;
import com.accompany.framework.web.service.SysPermissionService;
import com.accompany.system.service.ISysUserService;

/**
 * token过滤器 验证token有效性
 * 
 * @author accompany
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
        {
            try
            {
                // The token identifies the session, not an enduring authorization snapshot.
                SysUser current = loginUser.getUserId() == null ? null : userService.selectUserById(loginUser.getUserId());
                if (current != null && Objects.equals(loginUser.getUserId(), current.getUserId())
                        && "0".equals(current.getStatus()) && "0".equals(current.getDelFlag()))
                {
                    List<SysRole> roles = current.getRoles() == null ? Collections.emptyList() : current.getRoles().stream()
                            .filter(role -> role != null && role.getRoleId() != null && role.getRoleId() > 0
                                    && "0".equals(role.getStatus()) && "0".equals(role.getDelFlag())
                                    && StringUtils.isNotBlank(role.getRoleKey())
                                    && (current.isAdmin() || (!role.isAdmin() && !Constants.SUPER_ADMIN.equals(role.getRoleKey()))))
                            .collect(Collectors.toList());
                    current.setRoles(roles);
                    // Do not use getMenuPermission's user-level fallback for an empty
                    // role list: it can reintroduce permissions from deleted roles.
                    Set<String> permissions = current.isAdmin() || !roles.isEmpty()
                            ? permissionService.getMenuPermission(current) : Collections.emptySet();
                    loginUser.setUser(current);
                    loginUser.setPermissions(permissions == null ? Collections.emptySet() : permissions);
                    // Preserve the existing expiry policy; do not write/extend the
                    // cached session merely to refresh its authorization snapshot.
                    tokenService.verifyToken(loginUser);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            catch (Exception e)
            {
                SecurityContextHolder.clearContext();
                log.warn("当前认证信息无法核验，拒绝请求");
                response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
