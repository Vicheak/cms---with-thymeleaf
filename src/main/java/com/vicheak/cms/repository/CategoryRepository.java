package com.vicheak.cms.repository;

import com.vicheak.cms.model.Category;
import com.vicheak.cms.repository.provider.CategoryProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface CategoryRepository {

    /*@Select("""
                SELECT * 
                FROM categories
            """)*/
    //@Select("SELECT * FROM func_all_categories(#{status})")
    @SelectProvider(CategoryProvider.class)
    @Results(id = "categoryResultMap", value = {
            @Result(property = "isDeleted", column = "is_deleted")
    })
    List<Category> select(@Param("status") Boolean isDeleted);

    //@Select("SELECT * FROM categories WHERE id = #{id}")
    @SelectProvider(CategoryProvider.class)
    @ResultMap(value = "categoryResultMap")
    Optional<Category> selectById(@Param("id") Integer id);

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

    @Update("""
            UPDATE categories
            SET name = #{c.name},
            is_deleted = #{c.isDeleted}
            WHERE id = #{c.id}
            """)
    boolean update(@Param("c") Category category);

}
