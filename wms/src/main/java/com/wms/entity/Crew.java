package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@TableName("crew")
public class Crew {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String crewNo;
    private String name;
    private String gender;

    @Pattern(regexp = "^$|^\\d{17}[0-9Xx]$", message = "身份证号必须是 18 位")
    private String idCard;

    @Pattern(regexp = "^$|^\\d{11}$", message = "手机号必须是 11 位数字")
    private String phone;

    private String position;
    private String status;
    private String photo;
    private LocalDateTime createTime;
}
