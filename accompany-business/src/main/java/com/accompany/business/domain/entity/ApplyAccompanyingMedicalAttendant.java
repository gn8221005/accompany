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
 * 申请陪诊师表
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("apply_accompanying_medical_attendant")
public class ApplyAccompanyingMedicalAttendant implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long accompanyingMedicalAttendantId;
    private String avatar;
    private Integer sex;
    private Integer age;
    private String phone;
    private String realName;
    private String idCard;
    private String frontImage;
    private String backImage;
    private Integer status;
    private Long serviceCityId;
    private String serviceCityName;
    private String experienceDesc;
    private String familiarHospitalId;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String checkOpinion;
    private String checkUser;
    private LocalDateTime checkTime;
    private LocalDateTime createTime;
    private Integer deleted;
    private LocalDateTime updateTime;


}
