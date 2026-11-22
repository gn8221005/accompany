package com.accompany.business.service.impl;

import com.accompany.business.domain.entity.OrderRecord;
import com.accompany.business.mapper.OrderRecordMapper;
import com.accompany.business.service.IOrderRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Service
public class OrderRecordServiceImpl extends ServiceImpl<OrderRecordMapper, OrderRecord> implements IOrderRecordService {

}
