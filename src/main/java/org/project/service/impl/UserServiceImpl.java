package org.project.service.impl;

import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.project.entity.User;
import org.project.dao.UserMapper;
import org.project.service.UserService;
import org.project.utils.MybatisUtil;

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
}
