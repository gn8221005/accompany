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
 * 医院
 * </p>
 *
 * @author gn
 * @since 2026-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hospital")
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;
    private String avatar;
    private String name;
    private String level;
    private String type;
    private String introduction;
    private String address;
    private Float longitude;
    private Float latitude;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long cityId;
    private LocalDateTime createTime;
    private Integer deleted;
    private LocalDateTime updateTime;


}
