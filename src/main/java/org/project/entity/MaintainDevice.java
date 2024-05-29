package org.project.entity;

import lombok.Data;

@Data
public class MaintainDevice {
    long maintain_id;
    long maintain_device_id;
    String maintain_device_name;
    String maintain_begin_date;
    String fixer_name;
    long fixer_id;
    long fixer_tele;
}
