package com.accompany.business.service.impl;

import com.accompany.business.domain.entity.ChatMemorySetting;
import com.accompany.business.mapper.ChatMemorySettingMapper;
import com.accompany.business.service.IChatMemorySettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天长期记忆设置表 服务实现类
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Service
public class ChatMemorySettingServiceImpl extends ServiceImpl<ChatMemorySettingMapper, ChatMemorySetting> implements IChatMemorySettingService {

}
