package com.accompany.business.service.impl;

import com.accompany.business.domain.entity.Item;
import com.accompany.business.mapper.ItemMapper;
import com.accompany.business.service.IItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目 服务实现类
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

}
