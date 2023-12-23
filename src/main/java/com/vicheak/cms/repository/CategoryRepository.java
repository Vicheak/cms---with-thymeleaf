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
                WHERE is_deleted = false
            """)
    @Result(property = "isDeleted", column = "is_deleted")
    List<Category> select();

    @Insert("""
                INSERT INTO categories (name, is_deleted)
                VALUES (#{c.name}, #{c.isDeleted})
            """)
    void insert(@Param("c") Category category);

}
