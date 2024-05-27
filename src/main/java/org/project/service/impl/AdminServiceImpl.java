package org.project.service.impl;

import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.project.dao.UserMapper;
import org.project.entity.Admin;
import org.project.service.AdminService;
import org.project.utils.MybatisUtil;

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
}
