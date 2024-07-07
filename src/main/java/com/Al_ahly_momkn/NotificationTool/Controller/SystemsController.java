package com.Al_ahly_momkn.NotificationTool.Controller;

import com.Al_ahly_momkn.NotificationTool.Entity.Notification;
import com.Al_ahly_momkn.NotificationTool.Model.BulkNotifications;
import com.Al_ahly_momkn.NotificationTool.Model.NotificationModel;
import com.Al_ahly_momkn.NotificationTool.Model.RetrieveNotificationModel;
import com.Al_ahly_momkn.NotificationTool.NotificationDto;
import com.Al_ahly_momkn.NotificationTool.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/System")
@RequiredArgsConstructor
public class SystemsController {

    private final NotificationService notificationService;

    @PostMapping("/SingleNotification")
    public ResponseEntity<String> sandSingleNotification(@RequestBody NotificationModel notificationModel){
        return notificationService.sendSingleNotification(notificationModel);
    }
    @PostMapping("/BulkNotification")
    public ResponseEntity<String> sendBulkNotification(@RequestBody BulkNotifications bulkNotifications){
        return notificationService.sendBulkNotification(bulkNotifications);
    }
    @PostMapping("/Notification")
    public ResponseEntity<List<NotificationDto>>retrieveUserNotification(@RequestBody RetrieveNotificationModel retrieveNotificationModel){
        return notificationService.retrieveAllNotification(retrieveNotificationModel);
    }




}
