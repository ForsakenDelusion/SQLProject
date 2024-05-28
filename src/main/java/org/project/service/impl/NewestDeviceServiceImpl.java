package org.project.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.project.dao.DeviceMapper;
import org.project.entity.Device;
import org.project.service.NewestDeviceService;
import org.project.utils.MybatisUtil;

import java.util.List;

public class NewestDeviceServiceImpl implements NewestDeviceService {

    @Override
    public List<Device> getNewestDeviceList() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            return mapper.getNewestDevice();
        }
    }
}
