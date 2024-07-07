package com.Al_ahly_momkn.NotificationTool.Model;

import com.Al_ahly_momkn.NotificationTool.Entity.System;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    private String mobileNumber;
    private String  ip;
    private String makerPassword;
    private String organization;
    private String subOrganization;
    private String department;
    private String academicYear;


}
