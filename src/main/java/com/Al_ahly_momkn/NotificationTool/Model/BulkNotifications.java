package com.Al_ahly_momkn.NotificationTool.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BulkNotifications {
    private String senderIp;
    private String senderPassword;
    List<SimpleNotification> notifications;
    private LocalDateTime effectiveDate;
}
