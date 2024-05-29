package org.project.entity;

import lombok.Data;

@Data
public class MaintainDevice {
    long maintain_device_id;
    String maintain_device_name;
    String fixer_name;
    long fixer_id;
    long fixer_tele;
}
