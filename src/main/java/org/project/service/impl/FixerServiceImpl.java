package org.project.service.impl;

import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.project.dao.UserMapper;
import org.project.entity.Fixer;
import org.project.entity.User;
import org.project.service.FixerService;
import org.project.utils.MybatisUtil;

public class FixerServiceImpl implements FixerService {

    @Override
    public boolean auth(String Fid, String Fpassword, HttpSession session) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()){
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Fixer fixer = userMapper.getFixer(Fid,Fpassword);
            if(fixer == null) return false;
            session.setAttribute("fixer", fixer);//如果User不为空，就给一个session
            return true;
        }
    }
}
