package org.project.service;

import jakarta.servlet.http.HttpSession;

public interface AdminService {
    boolean auth(String Aid,String Apassword, HttpSession session );
}
