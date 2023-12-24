package com.vicheak.cms.repository;

import com.vicheak.cms.model.Category;
import com.vicheak.cms.model.Content;
import com.vicheak.cms.repository.provider.ContentProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface ContentRepository {

//    @Select("""
//                SELECT *
//                FROM contents
//                WHERE is_deleted = false
//            """)
    //@SelectProvider(type = ContentProvider.class, method = "buildSelectSql")
    @Select("SELECT * FROM view_all_contents") //call view from database server
    @Results(id = "contentResultMap", value = {
            @Result(property = "isDeleted", column = "is_deleted"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "category", column = "category_id",
                    one = @One(select = "selectContentCategory"))
    })
    List<Content> select();

    @Select("SELECT * FROM categories WHERE id = #{categoryId}")
    @Result(property = "isDeleted", column = "is_deleted")
    Category selectContentCategory(@Param("categoryId") Integer categoryId);

    //@Select("SELECT * FROM contents WHERE id = #{id}")
    @SelectProvider(ContentProvider.class)
    @ResultMap(value = "contentResultMap")
    Optional<Content> selectById(@Param("id") Integer id);

//    @Insert("""
//                INSERT INTO contents
//                (uuid, slug, title, description, thumbnail, editor, keyword, is_deleted, created_at, category_id)
//                VALUES (#{c.uuid}, #{c.slug}, #{c.title}, #{c.description}, #{c.thumbnail}, #{c.editor}, #{c.keyword},
//                #{c.isDeleted}, #{c.createdAt}, #{c.category.id})
//            """)
    @InsertProvider(ContentProvider.class)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("c") Content content);

    //@Delete("DELETE FROM contents WHERE id = #{id}")
    @DeleteProvider(ContentProvider.class)
    boolean deleteById(@Param("id") Integer id);

//    @Update("""
//            UPDATE contents
//            SET is_deleted = #{isDeleted}
//            WHERE id = #{id}
//            """)
    @UpdateProvider(ContentProvider.class)
    boolean updateIsDeletedById(@Param("id") Integer id,
                                @Param("isDeleted") Boolean isDeleted);

//    @Update("""
//            UPDATE contents
//            SET title = #{c.title},
//            slug = #{c.slug},
//            keyword = #{c.keyword},
//            description = #{c.description},
//            thumbnail = #{c.thumbnail},
//            editor = #{c.editor},
//            is_deleted = #{c.isDeleted},
//            category_id = #{c.category.id}
//            WHERE id = #{c.id}
//            """)
    @UpdateProvider(ContentProvider.class)
    boolean update(@Param("c") Content content);

}
