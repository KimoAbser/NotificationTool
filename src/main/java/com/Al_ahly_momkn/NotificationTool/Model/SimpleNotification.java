package com.Al_ahly_momkn.NotificationTool.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleNotification {
    private String title;
    private String body;
    private String receiverMobile;
    private Long amount;

}
