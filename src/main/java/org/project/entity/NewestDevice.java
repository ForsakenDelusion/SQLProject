package org.project.entity;

import lombok.Data;
import java.util.Date;

@Data
public class NewestDevice {
    long device_id;
    String device_name;
    DeviceStatus device_status;
    double device_price;
    String device_purchase_date;
}
