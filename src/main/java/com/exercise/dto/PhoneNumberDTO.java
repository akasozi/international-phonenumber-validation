package com.exercise.dto;

import com.exercise.common.PhoneNumberStatus;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
public class PhoneNumberDTO {
    private String phoneNumber;
    private PhoneNumberStatus status;

    public PhoneNumberDTO() {

    }

    public PhoneNumberDTO(String phoneNumber, PhoneNumberStatus status) {
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumberStatus getStatus() {
        return status;
    }

    public void setStatus(PhoneNumberStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "PhoneNumberDTO{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                '}';
    }
}
