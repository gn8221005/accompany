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
 * 陪诊师熟悉的医院中间表结构
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("accompany_familiar_hospital")
public class AccompanyFamiliarHospital implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long accompanyingMedicalAttendantId;
    private Long hospitalId;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
