package com.accompany.business.service.impl;

import com.accompany.business.domain.entity.ChatMemoryOutbox;
import com.accompany.business.mapper.ChatMemoryOutboxMapper;
import com.accompany.business.service.IChatMemoryOutboxService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天长期记忆投递表 服务实现类
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Service
public class ChatMemoryOutboxServiceImpl extends ServiceImpl<ChatMemoryOutboxMapper, ChatMemoryOutbox> implements IChatMemoryOutboxService {

}
