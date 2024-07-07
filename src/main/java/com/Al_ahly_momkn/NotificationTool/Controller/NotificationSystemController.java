package com.Al_ahly_momkn.NotificationTool.Controller;

import com.Al_ahly_momkn.NotificationTool.Entity.Notification;
import com.Al_ahly_momkn.NotificationTool.Model.BroadCastNotificationModel;
import com.Al_ahly_momkn.NotificationTool.NotificationDto;
import com.Al_ahly_momkn.NotificationTool.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/NotificationSystem")
public class NotificationSystemController {

    private final NotificationService notificationService;

    @PostMapping("/BroadCastNotification")
    public ResponseEntity<String> SendBrodCastNotification(@RequestBody BroadCastNotificationModel broadCastNotificationModel){
        return notificationService.sendBroadCastNotification(broadCastNotificationModel);
    }
    @GetMapping("/Report")
    public ResponseEntity<List<NotificationDto>> makeReport(){
        return notificationService.makeReport();
    }






}
