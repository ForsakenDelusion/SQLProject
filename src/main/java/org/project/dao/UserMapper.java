package org.project.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.project.entity.Admin;
import org.project.entity.User;

public interface UserMapper {

    @Select("select * from User where Uid = #{Uid} and Upassword = #{Upassword}")
    User getUser(@Param("Uid") String Uid,@Param("Upassword") String Upassword);

    @Select("select * from Admin where Aid = #{Aid} and Apassword = #{Apassword}")
    Admin getAdmin(@Param("Aid") String Aid, @Param("Apassword") String Apassword);

}
