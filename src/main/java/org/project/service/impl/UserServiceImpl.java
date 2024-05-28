package org.project.service.impl;

import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.project.dao.NewestDeviceMapper;
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
    public List<UserBorrowReturn> getUserBorrowReturnDeviceList(Long Uid) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.getUserBorrowReturn(Uid);
        }
    }

}
