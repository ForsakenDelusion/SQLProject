package org.project.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.project.dao.DeviceMapper;
import org.project.entity.Device;
import org.project.service.IndexService;
import org.project.utils.MybatisUtil;

import java.util.List;

public class IndexServiceImpl implements IndexService {

    @Override
    public List<Device> getNewestDeviceList() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            return mapper.getNewestDevice();
        }
    }

    @Override
    public int getDeviceCount() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            return mapper.getDeviceCount();
        }
    }

    @Override
    public int getUsingDeviceCount() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            return mapper.getUsingDeviceCount();
        }
    }

    @Override
    public int getMaintainDeviceCount() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            return mapper.getMaintainDevice().size();
        }
    }
}
