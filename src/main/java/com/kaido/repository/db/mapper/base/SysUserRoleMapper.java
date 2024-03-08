package com.kaido.repository.db.mapper.base;

import com.kaido.repository.db.entity.base.SysUserRole;
import com.you.meet.nice.tk_mybatis.mapper.TKMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends TKMapper<SysUserRole> {

    int batchInsert(@Param("list") List<SysUserRole> userRoles);

}
