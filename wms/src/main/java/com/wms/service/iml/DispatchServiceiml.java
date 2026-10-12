package com.wms.service.iml;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.mapper.DispatchMapper;
import com.wms.service.DispatchService;
import com.wms.entity.Dispatch;
import org.springframework.stereotype.Service;

@Service
public class DispatchServiceiml extends ServiceImpl<DispatchMapper,Dispatch> implements DispatchService{
}
