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
 * 服务预定须知表结构
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("service_reservation_notice")
public class ServiceReservationNotice implements Serializable {

    private static final long serialVersionUID = 1L;
    private String appointmentTime;
    private LocalDateTime acceptOrderTime;
    private String serviceDesc;
    private String serviceContent;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
