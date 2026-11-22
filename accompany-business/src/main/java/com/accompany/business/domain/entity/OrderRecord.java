package com.accompany.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_record")
public class OrderRecord implements Serializable {

    private static final long serialVersionUID = 1L;
    private String orderNo;
    private Long userId;
    private Long appointmentId;
    private Long hospitalId;
    private Integer orderStatus;
    private Integer productPrice;
    private Integer productQuantity;
    private Integer totalAmount;
    private Integer payAmount;
    private Integer payStatus;
    private LocalDateTime payExpireTime;
    private LocalDateTime payTime;
    private Integer refundStatus;
    private LocalDateTime refundTime;
    private String remark;
    private String openId;
    private String transactionId;
    private String refundNo;
    private Integer refundAmount;
    private String refundId;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private LocalDateTime createTime;
    private Integer deleted;
    private LocalDateTime updateTime;
    private Integer expectedIncome;
    private Integer takeStatus;


}
