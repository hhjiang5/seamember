package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDate;

@Data
@TableName("certificate")
public class Certificate {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long crewId;
    private String certName;
    private String certNo;
    private String certType;
    private LocalDate issueDate;
    private LocalDate expireDate;
    private String issueOrg;
    private Integer deleteFlag;
}
