package io.github.zhoujunlin94.kaido.repository.db.handler.base;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import io.github.zhoujunlin94.kaido.dto.sa.SysResourcePageParamDTO;
import io.github.zhoujunlin94.kaido.repository.db.entity.base.SysResource;
import io.github.zhoujunlin94.kaido.repository.db.mapper.base.SysResourceMapper;
import io.github.zhoujunlin94.meet.tk_mybatis.handler.TKHandler;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.List;
import java.util.Objects;

@Repository
public class SysResourceHandler extends TKHandler<SysResourceMapper, SysResource> {

    public List<SysResource> getResource(List<Integer> resourceIds) {
        if (CollUtil.isEmpty(resourceIds)) {
            return Lists.newArrayList();
        }
        Weekend<SysResource> weekend = thisWeekend();
        weekend.weekendCriteria().andIn(SysResource::getId, resourceIds)
                .andEqualTo(SysResource::getResourceStatus, Boolean.TRUE);
        return baseMapper.selectByExample(weekend);
    }

    public List<SysResource> selectByParam(SysResourcePageParamDTO paramDTO) {
        Weekend<SysResource> weekend = thisWeekend();
        WeekendCriteria<SysResource, Object> weekendCriteria = weekend.weekendCriteria();
        if (StrUtil.isNotBlank(paramDTO.getResourceCode())) {
            weekendCriteria.andLike(SysResource::getResourceCode, likeString(paramDTO.getResourceCode()));
        }
        if (StrUtil.isNotBlank(paramDTO.getResourceName())) {
            weekendCriteria.andLike(SysResource::getResourceName, likeString(paramDTO.getResourceName()));
        }
        if (Objects.nonNull(paramDTO.getResourceType())) {
            weekendCriteria.andEqualTo(SysResource::getResourceType, paramDTO.getResourceType());
        }
        if (Objects.nonNull(paramDTO.getResourceParent())) {
            weekendCriteria.andEqualTo(SysResource::getResourceParent, paramDTO.getResourceParent());
        }
        if (Objects.nonNull(paramDTO.getResourceStatus())) {
            weekendCriteria.andEqualTo(SysResource::getResourceStatus, paramDTO.getResourceStatus());
        }
        weekend.orderBy("id").desc();
        return baseMapper.selectByExample(weekend);
    }

}
