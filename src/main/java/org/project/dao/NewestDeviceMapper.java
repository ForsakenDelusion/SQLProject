package org.project.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.project.entity.DeviceStatus;
import org.project.entity.NewestDevice;

import java.util.List;

public interface NewestDeviceMapper {
    @Results({
            @Result(column = "Did", property = "device_id"),
            @Result(column = "Dname", property = "device_name"),
            @Result(column = "Dstatus", property = "device_status"),
            @Result(column = "Dprice", property = "device_price"),
            @Result(column = "Ddate", property = "device_purchase_date")
    })
    @Select("select * from Device ORDER BY Did DESC Limit 10")
    List<NewestDevice> getNewestDevice();
}
