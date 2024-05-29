package org.project.service;

import jakarta.servlet.http.HttpSession;
import org.project.entity.Device;
import org.project.entity.Fixer;
import org.project.entity.MaintainDevice;

import java.util.List;

public interface AdminService {
    boolean auth(String Aid,String Apassword, HttpSession session );

    List<Device> getDeviceStatus();

    void setDeviceIdle(String Did);

    void setDeviceUse(String Did);

    void setDeviceMaintain(String Did);

    void setDeviceFixer(String Fname,String Did);

    List<Fixer> getAllFixer();

    void sellDevice(String Did);

    String getDeviceStatusById(String Did);

    void buyDevice(String Dname,String Dprice);

    List<MaintainDevice> getAllMaintainDevice();

    void changefixer(String Did,String Fname);

    String getOldestFixerIdByName(String Fname);
}
