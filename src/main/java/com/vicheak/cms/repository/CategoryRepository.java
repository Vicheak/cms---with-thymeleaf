package com.vicheak.cms.repository;

import com.vicheak.cms.model.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryRepository {

    @Select("""
                SELECT * 
                FROM categories
            """)
    @Result(property = "isDeleted", column = "is_deleted")
    List<Category> select();

    @Insert("""
                INSERT INTO categories (name, is_deleted)
                VALUES (#{c.name}, #{c.isDeleted})
            """)
    void insert(@Param("c") Category category);

    @Update("""
                UPDATE categories
                SET is_deleted = #{isDeleted}
                WHERE id = #{id}
            """)
    boolean updateIsDeletedById(@Param("id") Integer id,
                                @Param("isDeleted") Boolean isDeleted);

}
