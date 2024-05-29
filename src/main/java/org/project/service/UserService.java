package org.project.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.project.entity.UserBorrowReturn;

import java.util.List;

public interface UserService {

    boolean auth(String Uid,String Upassword, HttpSession session );

    boolean userBanUrl(HttpServletRequest req);

    void borrowDevice(String Uid,String Did);

    String getOldestDeviceByName(String Dname);

    void returnDevice(String Did);

    List<UserBorrowReturn> getUserBorrowReturnDeviceList(Long Uid);

    List<String> getIdleDeviceName();
}
