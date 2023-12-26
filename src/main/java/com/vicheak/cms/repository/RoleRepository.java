package com.vicheak.cms.repository;

import com.vicheak.cms.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleRepository {

    @Select("SELECT * FROM roles")
    List<Role> select();

}
