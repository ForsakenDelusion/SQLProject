package org.project.dao;

import org.apache.ibatis.annotations.*;
import org.project.entity.*;

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

    @Select("select count(*) from User")
    int getUserCount();

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

    @Delete("delete from Device where Did = #{Did}")
    void sellDevice(String Did);

    @Select("select Device.Dstatus from Device where Did = #{Did}")
    String getDeviceStautsById(String Did);

    @Insert("insert into Device(Dname,Dprice) values(#{Dname},#{Dprice})")
    void buyDevice(@Param("Dname")String Dname,@Param("Dprice")String Dprice);

    @Results({
            @Result(column = "Did", property = "maintain_device_id"),
            @Result(column = "Dname", property = "maintain_device_name"),
            @Result(column = "Fname", property = "fixer_name"),
            @Result(column = "Fid", property = "fixer_id"),
            @Result(column = "Ftele", property = "fixer_tele")
    })
    @Select("select * from Device,Maintain,Fixer where Did = MDid and MFid = Fid and MdateEnd is NULL")
    List<MaintainDevice> getMaintainDevice();

    @Results({
            @Result(column = "Mid", property = "maintain_id"),
            @Result(column = "Did", property = "maintain_device_id"),
            @Result(column = "Dname", property = "maintain_device_name"),
            @Result(column = "MdateBegin", property = "maintain_begin_date"),
    })
    @Select("select * from Device,Maintain,Fixer where Did = MDid and MFid = Fid and Fid = #{Fid} and MdateEnd is NULL")
    List<MaintainDevice> getMaintainDeviceByFixerId(String Fid);
}
