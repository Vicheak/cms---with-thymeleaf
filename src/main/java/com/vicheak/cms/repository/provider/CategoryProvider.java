package com.vicheak.cms.repository.provider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class CategoryProvider implements ProviderMethodResolver {

    public String select(){
        return new SQL(){{
            SELECT("*");
            FROM("func_all_categories(#{status})");
        }}.toString();
    }

    public String selectById(){
        return new SQL(){{
            SELECT("*");
            FROM("categories");
            WHERE("id = #{id}");
        }}.toString();
    }

}
