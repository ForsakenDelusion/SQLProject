package org.project.service;

import jakarta.servlet.http.HttpSession;
import org.project.entity.UserBorrowReturn;

import java.util.List;

public interface UserService {

    boolean auth(String Uid,String Upassword, HttpSession session );

    List<UserBorrowReturn> getUserBorrowReturnDeviceList(Long Uid);
}
