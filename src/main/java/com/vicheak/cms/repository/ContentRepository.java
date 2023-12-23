package com.vicheak.cms.repository;

import com.vicheak.cms.model.Content;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContentRepository {

    @Select("""
                SELECT * 
                FROM contents
                WHERE is_deleted = false
            """)
    @Results(value = {
            @Result(property = "isDeleted", column = "is_deleted"),
            @Result(property = "createdAt", column = "created_at")
    })
    List<Content> select();

    @Insert("""
                INSERT INTO contents 
                (uuid, slug, title, description, thumbnail, keyword, is_deleted, created_at, category_id)
                VALUES (#{c.uuid}, #{c.slug}, #{c.title}, #{c.description}, #{c.thumbnail}, #{c.keyword},
                #{c.isDeleted}, #{c.createdAt}, #{catId})
            """)
    void insert(@Param("c") Content content, @Param("catId") Integer categoryId);

    @Delete("DELETE FROM contents WHERE id = #{id}")
    boolean deleteById(@Param("id") Integer id);

    @Update("""
            UPDATE contents
            SET is_deleted = #{isDeleted}
            WHERE id = #{id}
            """)
    boolean updateIsDeletedById(@Param("id") Integer id,
                                @Param("isDeleted") Boolean isDeleted);

}
