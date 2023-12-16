package com.kaido.repository.db.mapper.base;

import com.kaido.repository.db.entity.base.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends tk.mybatis.mapper.common.Mapper<SysUserRole> {

    int batchInsert(@Param("list") List<SysUserRole> userRoles);

}
