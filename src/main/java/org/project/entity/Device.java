package org.project.entity;

import lombok.Data;

@Data
public class Device {
    long device_id;
    String device_name;
    DeviceStatus device_status;
    double device_price;
    String device_purchase_date;
}
