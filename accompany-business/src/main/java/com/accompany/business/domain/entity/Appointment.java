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
 * 预约表
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("appointment")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long userId;
    private Integer hospitalItemId;
    private LocalDateTime serviceTime;
    private LocalDateTime serviceActualEndTime;
    private LocalDateTime serviceReservedEndTime;
    private Long patientId;
    private String phone;
    private String medicalContent;
    private String demand;
    private Integer status;
    private Long itemId;
    private String itemName;
    private String itemImage;
    private String itemDescription;
    private String itemServiceContent;
    private String itemHospitalName;
    private String itemRelation;
    private String itemPatientName;
    private String itemPatientPhone;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private LocalDateTime createTime;
    private Integer deleted;
    private LocalDateTime updateTime;


}
