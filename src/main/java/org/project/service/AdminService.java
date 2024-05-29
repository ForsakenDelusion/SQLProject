package org.project.service;

import jakarta.servlet.http.HttpSession;
import org.project.entity.Device;
import org.project.entity.Fixer;

import java.util.List;

public interface AdminService {
    boolean auth(String Aid,String Apassword, HttpSession session );

    List<Device> getDeviceStatus();

    void setDeviceIdle(String Did);

    void setDeviceUse(String Did);

    void setDeviceMaintain(String Did);

    void setDeviceFixer(String Fname,String Did);

    List<Fixer> getAllFixer();
}
