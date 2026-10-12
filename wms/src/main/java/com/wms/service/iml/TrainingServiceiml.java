package com.wms.service.iml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.mapper.TrainingMapper;
import com.wms.service.TrainingService;
import com.wms.entity.Training;
import org.springframework.stereotype.Service;

 @Service
public class TrainingServiceiml extends ServiceImpl<TrainingMapper, Training> implements TrainingService {
}
