package org.project.dao;

import org.apache.ibatis.annotations.*;
import org.project.entity.Admin;
import org.project.entity.Fixer;
import org.project.entity.User;
import org.project.entity.UserBorrowReturn;

import java.util.List;

public interface UserMapper {

    @Select("select * from User where Uid = #{Uid} and Upassword = #{Upassword}")
    User getUser(@Param("Uid") String Uid,@Param("Upassword") String Upassword);

    @Select("select * from Admin where Aid = #{Aid} and Apassword = #{Apassword}")
    Admin getAdmin(@Param("Aid") String Aid, @Param("Apassword") String Apassword);

    @Select("select * from Fixer where Fid = #{Fid} and Fpassword = #{Fpassword}")
    Fixer getFixer(@Param("Fid") String Fid, @Param("Fpassword") String Fpassword);

    @Select("select * from Fixer")
    List<Fixer> getAllFixer();

    @Results({
            @Result(column = "BRid", property = "borrow_id"),
            @Result(column = "Did", property = "borrow_device_id"),
            @Result(column = "Dname", property = "borrow_device_name"),
            @Result(column = "BRbdate", property = "borrow_date")
    })
    @Select("select BRid,Did,Dname,BR.BRbdate from Device,BR where BRUid = #{Uid} and BRDid = Did and BRrdate IS NULL")
    List<UserBorrowReturn> getUserBorrowReturn(Long Uid);

    @Update("update Device set Dstatus = '空闲中' where Did = #{Did}")
    void returnDevice(String Did);

    @Select("select Fid from Fixer where Fname = #{Fname} ORDER BY Fid ASC LIMIT 1")
    String getFidByFname(String Fname);
}
