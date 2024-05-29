package org.project.service;

import org.project.entity.Device;

import java.util.List;

public interface IndexService {
    List<Device> getNewestDeviceList();

    int getDeviceCount();

    int getUsingDeviceCount();

    int getMaintainDeviceCount();


}
