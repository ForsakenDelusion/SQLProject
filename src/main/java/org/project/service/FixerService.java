package org.project.service;

import jakarta.servlet.http.HttpSession;

public interface FixerService {
    boolean auth(String Fid,String Fpassword, HttpSession session );
}
