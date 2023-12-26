package com.vicheak.cms.repository;

import com.vicheak.cms.model.Role;
import com.vicheak.cms.model.User;
import com.vicheak.cms.repository.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRepository {

    @SelectProvider(UserProvider.class)
    @Results(id = "userResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "displayName", column = "display_name"),
            @Result(property = "isDeleted", column = "is_deleted"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "roles", column = "id",
                    many = @Many(select = "selectUserRoles"))
    })
    List<User> select();

    @SelectProvider(UserProvider.class)
    List<Role> selectUserRoles(@Param("userId") Integer userId);

    @InsertProvider(UserProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(@Param("u") User user);

    @InsertProvider(UserProvider.class)
    boolean insertUserRoles(@Param("userId") Integer userId,
                            @Param("roleId") Integer roleId);

}
