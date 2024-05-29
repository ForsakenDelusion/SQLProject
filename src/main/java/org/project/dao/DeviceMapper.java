package org.project.dao;

import org.apache.ibatis.annotations.*;
import org.project.entity.Device;

import java.util.List;

public interface DeviceMapper {
    @Results({
            @Result(column = "Did", property = "device_id"),
            @Result(column = "Dname", property = "device_name"),
            @Result(column = "Dstatus", property = "device_status"),
            @Result(column = "Dprice", property = "device_price"),
            @Result(column = "Ddate", property = "device_purchase_date")
    })
    @Select("select * from Device ORDER BY Did DESC Limit 10")
    List<Device> getNewestDevice();


    @Select("select distinct Dname from Device where Dstatus = '空闲中'")
    List<String> getIdleDeviceName();

    @Update("insert into BR(BRUid,BRDid) VALUES (#{Uid},#{Did})")
    void borrowDevice(@Param("Uid") String Uid, @Param("Did") String Did);

    @Select("select Did from Device where Dname = #{Dname} and Dstatus='空闲中' ORDER BY Did ASC LIMIT 1")
    String getOldestDeviceByName(String Dname);

    @Results({
            @Result(column = "Did",property = "device_id"),
            @Result(column = "Dname", property = "device_name"),
            @Result(column = "Dstatus", property = "device_status")
    })
    @Select("select Did,Dname,Dstatus from Device")
    List<Device> getDeviceStatus();

    @Update("update Device set Dstatus = '空闲中' where Did = #{Did}")
    void setDeviceIdle(String Did);

    @Update("update Device set Dstatus = '使用中' where Did = #{Did}")
    void setDeviceUse(String Did);

    @Update("update Device set Dstatus = '维修中' where Did = #{Did}")
    void setDeviceMaintain(String Did);

    @Update("update Maintain set MFid = #{Fid} where MDid = #{MDid}")
    void setFixerId(@Param("Fid") String Fid,@Param("MDid") String MDid);
}
