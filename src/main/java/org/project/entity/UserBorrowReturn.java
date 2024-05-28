package org.project.entity;

import lombok.Data;

@Data
public class UserBorrowReturn {
    long borrow_id;
    long borrow_device_id;
    String borrow_device_name;
    String borrow_date;
}
