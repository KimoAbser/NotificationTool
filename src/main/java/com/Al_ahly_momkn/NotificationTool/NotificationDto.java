package com.Al_ahly_momkn.NotificationTool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationDto {
    private Long id;
    private String title;
    private String body;
    private LocalDateTime effectiveDate;
    private String SenderName;
    private String userMobileNumber;
}
