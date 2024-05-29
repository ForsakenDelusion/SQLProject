package org.project.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.project.dao.DeviceMapper;
import org.project.entity.User;
import org.project.dao.UserMapper;
import org.project.entity.UserBorrowReturn;
import org.project.service.UserService;
import org.project.utils.MybatisUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public boolean auth(String Uid, String Upassword, HttpSession session) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser(Uid,Upassword);
            if(user == null) return false;
            session.setAttribute("user", user);//如果User不为空，就给一个session
            return true;
        }
    }

    @Override
    public boolean userBanUrl(HttpServletRequest req) {//禁止用户访问的网页
        String url = req.getRequestURI();
        return !url.endsWith("project/status") && !url.endsWith("project/setidle") && !url.endsWith("project/setmaintain") && !url.endsWith("project/setuse");
    }

    @Override
    public void borrowDevice(String Uid,String Did) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            mapper.borrowDevice(Uid,Did);
        }
    }

    @Override
    public String getOldestDeviceByName(String Dname) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            return mapper.getOldestDeviceByName(Dname);
        }
    }

    @Override
    public void returnDevice(String Did) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.returnDevice(Did);
        }
    }



    @Override
    public List<UserBorrowReturn> getUserBorrowReturnDeviceList(Long Uid) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getUserBorrowReturn(Uid);
        }
    }

    public List<String> getIdleDeviceName() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            return mapper.getIdleDeviceName();
        }
    }

    @Override
    public int getUserCount() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getUserCount();
        }
    }

}
