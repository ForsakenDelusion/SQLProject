package org.project.service;

import org.project.entity.Device;

import java.util.List;

public interface NewestDeviceService {
    List<Device> getNewestDeviceList();
}
