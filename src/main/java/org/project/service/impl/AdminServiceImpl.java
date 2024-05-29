package org.project.service.impl;

import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.project.dao.DeviceMapper;
import org.project.dao.UserMapper;
import org.project.entity.Admin;
import org.project.entity.Device;
import org.project.entity.Fixer;
import org.project.entity.MaintainDevice;
import org.project.service.AdminService;
import org.project.utils.MybatisUtil;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean auth(String Aid, String Apassword, HttpSession session) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Admin admin = userMapper.getAdmin(Aid, Apassword);
            if(admin == null) return false;
            session.setAttribute("admin", admin);//如果Admin不为空，就给一个session
            return true;
        }
    }

    @Override
    public List<Device> getDeviceStatus() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper userMapper = sqlSession.getMapper(DeviceMapper.class);
            return userMapper.getDeviceStatus();
        }
    }

    @Override
    public void setDeviceIdle(String Did) {

        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            mapper.setDeviceIdle(Did);
        }

    }

    @Override
    public void setDeviceUse(String Did) {

        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            mapper.setDeviceUse(Did);
        }

    }

    @Override
    public void setDeviceMaintain(String Did) {

        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            mapper.setDeviceMaintain(Did);
        }

    }

    @Override
    public void setDeviceFixer(String Fname,String Did) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper deviceMapper = sqlSession.getMapper(DeviceMapper.class);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            deviceMapper.setFixerId(userMapper.getFidByFname(Fname),Did);
        }
    }

    @Override
    public List<Fixer> getAllFixer() {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getAllFixer();
        }
    }

    @Override
    public void sellDevice(String Did) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.sellDevice(Did);
        }
    }

    @Override
    public String getDeviceStatusById(String Did) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return  mapper.getDeviceStautsById(Did);
        }
    }

    @Override
    public void buyDevice(String Dname, String Dprice) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.buyDevice(Dname,Dprice);
        }
    }

    @Override
    public List<MaintainDevice> getAllMaintainDevice() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getMaintainDevice();
        }
    }

    @Override
    public void changefixer(String Did, String Fname) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            mapper.changeDeviceFixer(Did,mapper.getOldestFixerIdByName(Fname));
        }
    }

    @Override
    public String getOldestFixerIdByName(String Fname) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            return mapper.getOldestFixerIdByName(Fname);
        }
    }
}
