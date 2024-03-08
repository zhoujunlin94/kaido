package com.kaido.repository.db.mapper.base;

import com.kaido.repository.db.entity.base.SysRoleResource;
import com.you.meet.nice.tk_mybatis.mapper.TKMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleResourceMapper extends TKMapper<SysRoleResource> {

    List<Integer> getUserRoleResourceIds(@Param("userId") Integer userId);

    int batchInsert(@Param("list") List<SysRoleResource> roleResources);

}