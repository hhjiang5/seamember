package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDate;

@Data
@TableName("dispatch")
public class Dispatch {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long crewId;
    private String shipName;
    private LocalDate onboardDate;
    private LocalDate offboardDate;
    private String status;
    private String remark;
    private Integer deleteFlag;
}
