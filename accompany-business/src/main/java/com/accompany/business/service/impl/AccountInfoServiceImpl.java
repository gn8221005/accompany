package com.accompany.business.service.impl;

import com.accompany.business.domain.entity.AccountInfo;
import com.accompany.business.mapper.AccountInfoMapper;
import com.accompany.business.service.IAccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户信息表 服务实现类
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements IAccountInfoService {

}
