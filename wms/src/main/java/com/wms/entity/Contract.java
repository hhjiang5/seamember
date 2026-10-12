package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@TableName("contract")
public class Contract {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long crewId;
    private String contractType;
    private String contractNo;
    private LocalDate signDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String signCompany;
    private BigDecimal salary;
    private String currency;
    private String status;
    private Integer deleteFlag;
}
