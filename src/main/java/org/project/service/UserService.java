package org.project.service;

import jakarta.servlet.http.HttpSession;

public interface UserService {

    boolean auth(String Uid,String Upassword, HttpSession session );
}
