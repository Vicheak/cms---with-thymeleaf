package com.vicheak.cms.repository;

import com.vicheak.cms.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
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

}
