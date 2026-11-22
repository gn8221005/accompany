package com.accompany.business.service.impl;

import com.accompany.business.domain.entity.ChatConversation;
import com.accompany.business.mapper.ChatConversationMapper;
import com.accompany.business.service.IChatConversationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天会话表 服务实现类
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Service
public class ChatConversationServiceImpl extends ServiceImpl<ChatConversationMapper, ChatConversation> implements IChatConversationService {

}
