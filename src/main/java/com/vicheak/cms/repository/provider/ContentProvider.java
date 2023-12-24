package com.vicheak.cms.repository.provider;

import com.vicheak.cms.model.Content;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ContentProvider implements ProviderMethodResolver {

    public String buildSelectSql(){
        return new SQL(){{
            SELECT("*");
            FROM("contents");
            WHERE("is_deleted = false");
            ORDER_BY("created_at DESC");
        }}.toString();
    }

    public String selectById(){
        return new SQL(){{
            SELECT("*");
            FROM("contents");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String insert(@Param("c") Content content){
        return new SQL(){{
            INSERT_INTO("contents");
            VALUES("uuid", "#{c.uuid}");
            VALUES("slug", "#{c.slug}");
            VALUES("title", "#{c.title}");
            VALUES("description", "#{c.description}");

            if(!content.getThumbnail().isBlank()){
                VALUES("thumbnail", "#{c.thumbnail}");
            }

            if(!content.getEditor().isBlank()) {
                VALUES("editor", "#{c.editor}");
            }

            VALUES("keyword", "#{c.keyword}");
            VALUES("is_deleted", "#{c.isDeleted}");
            VALUES("created_at", "#{c.createdAt}");
            VALUES("category_id", "#{c.category.id}");
        }}.toString();
    }

    public String deleteById(){
        return new SQL(){{
            DELETE_FROM("contents");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String updateIsDeletedById(){
        return new SQL(){{
            UPDATE("contents");
            SET("is_deleted = #{isDeleted}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String update(){
        return new SQL(){{
            UPDATE("contents");
            SET("title = #{c.title}");
            SET("slug = #{c.slug}");
            SET("keyword = #{c.keyword}");
            SET("description = #{c.description}");
            SET("thumbnail = #{c.thumbnail}");
            SET("editor = #{c.editor}");
            SET("is_deleted = #{c.isDeleted}");
            SET("category_id = #{c.category.id}");
            WHERE("id = #{c.id}");
        }}.toString();
    }

}
