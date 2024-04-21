package io.github.zhoujunlin94.kaido.repository.db.mapper.base;

import io.github.zhoujunlin94.kaido.repository.db.entity.base.SysRoleResource;
import io.github.zhoujunlin94.meet.tk_mybatis.mapper.TKMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleResourceMapper extends TKMapper<SysRoleResource> {

    List<Integer> getUserRoleResourceIds(@Param("userId") Integer userId);

}