package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@TableName("training")
public class Training {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long crewId;
    private String courseName;
    private String trainType;
    private String trainOrg;
    private LocalDate trainStart;
    private LocalDate trainEnd;
    private Integer trainHours;
    private BigDecimal score;
    private Integer passFlag;
    private Integer deleteFlag;
}
