package com.kaido.repository.db.handler.base;

import com.kaido.repository.db.entity.base.SysRoleResource;
import com.kaido.repository.db.mapper.base.SysRoleResourceMapper;
import com.you.meet.nice.tk_mybatis.handler.TKHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysRoleResourceHandler extends TKHandler<SysRoleResourceMapper, SysRoleResource> {

    public List<Integer> getUserRoleResourceIds(Integer userId) {
        return this.baseMapper.getUserRoleResourceIds(userId);
    }

}
