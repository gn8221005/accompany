package com.accompany.business.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 接单表
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("take_order")
public class TakeOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long orderId;
    private Long accompanyingMedicalAttendantId;
    private Integer expectedIncome;
    private Integer status;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private LocalDateTime createTime;
    private Integer deleted;
    private LocalDateTime updateTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
    private Long activeOrder;


}
