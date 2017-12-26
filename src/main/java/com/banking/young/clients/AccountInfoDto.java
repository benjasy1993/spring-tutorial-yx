package com.banking.young.clients;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class AccountInfoDto {

    private String firstName;

    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/New_York")
    private Date dob;

    private java.util.Date createdDate;

    private boolean assigned;

    private String pinNum;

    private String username;

    @Override
    public String toString() {
        return "AccountInfoDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", createdDate=" + createdDate +
                ", assigned=" + assigned +
                ", pinNum='" + pinNum + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @JsonIgnore
    public java.util.Date getCreatedDate() {
        return createdDate;
    }

    @JsonIgnore
    public boolean isAssigned() {
        return assigned;
    }

    @JsonIgnore
    public String getPinNum() {
        return pinNum;
    }

    @JsonSetter("createdTime")
    public void setCreatedDate(java.util.Date createdDate) {
        this.createdDate = createdDate;
    }

    @JsonSetter("assigned")
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    @JsonSetter("pinNum")
    public void setPinNum(String pinNum) {
        this.pinNum = pinNum;
    }
}
