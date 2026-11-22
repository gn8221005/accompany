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
 * 陪诊师
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("accompanying_medical_attendant")
public class AccompanyingMedicalAttendant implements Serializable {

    private static final long serialVersionUID = 1L;
    private String avatar;
    private String username;
    private String account;
    private String password;
    private String openId;
    private Integer sex;
    private Integer age;
    private String phone;
    private String realName;
    private String idCard;
    private String serviceCityName;
    private String experienceDesc;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private LocalDateTime createTime;
    private Integer deleted;
    private LocalDateTime updateTime;
    private Integer status;


}
