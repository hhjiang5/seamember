package com.wms.service.iml;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.mapper.ContractMapper;
import com.wms.service.ContractService;
import com.wms.entity.Contract;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceiml extends ServiceImpl<ContractMapper,Contract> implements ContractService {
}
