package org.project.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.project.dao.NewestDeviceMapper;
import org.project.entity.NewestDevice;
import org.project.service.NewestDeviceService;
import org.project.utils.MybatisUtil;

import java.util.List;

public class NewestDeviceServiceImpl implements NewestDeviceService {

    @Override
    public List<NewestDevice> getNewestDeviceList() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            NewestDeviceMapper mapper = sqlSession.getMapper(NewestDeviceMapper.class);
            return mapper.getNewestDevice();
        }
    }
}
