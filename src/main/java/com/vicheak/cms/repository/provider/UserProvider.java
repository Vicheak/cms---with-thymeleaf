package com.vicheak.cms.repository.provider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider implements ProviderMethodResolver {

    public String select() {
        return new SQL() {{
            SELECT("*");
            FROM("users");
        }}.toString();
    }

    public String selectUserRoles(){
        return new SQL(){{
            SELECT("r.id, r.name");
            FROM("roles AS r");
            INNER_JOIN("users_roles AS ur ON r.id = ur.role_id");
            WHERE("ur.user_id = #{userId}");
        }}.toString();
    }

}
