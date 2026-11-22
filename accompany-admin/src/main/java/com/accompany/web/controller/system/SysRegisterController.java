package com.accompany.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.accompany.common.core.controller.BaseController;
import com.accompany.common.core.domain.AjaxResult;
import com.accompany.common.core.domain.model.RegisterBody;
import com.accompany.common.utils.StringUtils;
import com.accompany.framework.web.service.SysRegisterService;
import com.accompany.system.service.ISysConfigService;

/**
 * 注册验证
 * 
 * @author accompany
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
