package com.wms.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.entity.Crew;
import com.wms.mapper.CrewMapper;
import com.wms.service.CrewService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CrewServiceiml extends ServiceImpl<CrewMapper, Crew> implements CrewService {
    @Override
    public boolean save(Crew crew) {
        checkDuplicate(crew);
        return super.save(crew);
    }

    @Override
    public boolean updateById(Crew crew) {
        checkDuplicate(crew);
        return super.updateById(crew);
    }

    // 检查重复
    private void checkDuplicate(Crew crew) {
        // 工号唯一
        if (StringUtils.hasText(crew.getCrewNo())) {
            Long cnt = (long) this.count(new QueryWrapper<Crew>()
                    .eq("crew_no", crew.getCrewNo())
                    .ne(crew.getId() != null, "id", crew.getId()));
            if (cnt > 0) {
                throw new RuntimeException("工号已存在：" + crew.getCrewNo());
            }
        }
        // 身份证唯一
        if (StringUtils.hasText(crew.getIdCard())) {
            Long cnt = (long) this.count(new QueryWrapper<Crew>()
                    .eq("id_card", crew.getIdCard())
                    .ne(crew.getId() != null, "id", crew.getId()));
            if (cnt > 0) {
                throw new RuntimeException("身份证号已存在：" + crew.getIdCard());
            }
        }
        // 手机号唯一（可选，看业务是否需要）
        if (StringUtils.hasText(crew.getPhone())) {
            Long cnt = (long) this.count(new QueryWrapper<Crew>()
                    .eq("phone", crew.getPhone())
                    .ne(crew.getId() != null, "id", crew.getId()));
            if (cnt > 0) {
                throw new RuntimeException("手机号已存在：" + crew.getPhone());
            }
        }
    }
}
