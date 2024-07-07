package com.Al_ahly_momkn.NotificationTool.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RetrieveNotificationModel {
    private String mobileNumber;
    private String ip;
    private String password;

}
