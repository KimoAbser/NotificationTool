package com.Al_ahly_momkn.NotificationTool.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BroadCastNotificationModel {
    private String title;
    private String body;
    private String senderIp;
    private String senderPassword;
    private String organization;
    private String subOrganization;
    private String department;
    private String academicYear;
    private LocalDateTime effectiveDate;
    private String receiverIp;

}
