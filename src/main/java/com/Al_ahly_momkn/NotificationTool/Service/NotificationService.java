package com.Al_ahly_momkn.NotificationTool.Service;

import com.Al_ahly_momkn.NotificationTool.Model.BroadCastNotificationModel;
import com.Al_ahly_momkn.NotificationTool.Model.BulkNotifications;
import com.Al_ahly_momkn.NotificationTool.Model.NotificationModel;
import com.Al_ahly_momkn.NotificationTool.Model.RetrieveNotificationModel;
import com.Al_ahly_momkn.NotificationTool.NotificationDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotificationService {
    ResponseEntity<String> sendSingleNotification(NotificationModel notificationModel);

    ResponseEntity<String> sendBulkNotification(BulkNotifications bulkNotifications);

    ResponseEntity<List<NotificationDto>> retrieveAllNotification(RetrieveNotificationModel number);

    ResponseEntity<String> sendBroadCastNotification(BroadCastNotificationModel broadCastNotificationModel);

    ResponseEntity<List<NotificationDto>> makeReport();
}
