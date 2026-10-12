package com.wms.service.iml;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.mapper.CertificateMapper;
import com.wms.service.CertificateService;
import com.wms.entity.Certificate;
import org.springframework.stereotype.Service;

@Service

public class CertificateServiceiml extends ServiceImpl<CertificateMapper,Certificate> implements CertificateService {
}
