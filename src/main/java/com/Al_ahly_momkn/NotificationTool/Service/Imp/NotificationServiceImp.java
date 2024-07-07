package com.Al_ahly_momkn.NotificationTool.Service.Imp;

import com.Al_ahly_momkn.NotificationTool.Entity.Notification;
import com.Al_ahly_momkn.NotificationTool.Entity.System;
import com.Al_ahly_momkn.NotificationTool.Entity.User;
import com.Al_ahly_momkn.NotificationTool.Model.*;
import com.Al_ahly_momkn.NotificationTool.NotificationDto;
import com.Al_ahly_momkn.NotificationTool.Repository.NotificationRepo;
import com.Al_ahly_momkn.NotificationTool.Repository.SystemRepo;
import com.Al_ahly_momkn.NotificationTool.Repository.UserRepo;
import com.Al_ahly_momkn.NotificationTool.Service.NotificationService;
import com.Al_ahly_momkn.NotificationTool.Service.SystemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {

    private final UserRepo userRepo;
    private final SystemRepo systemRepo;
    private final NotificationRepo notificationRepo;
    private final SystemService systemService;
    private final PasswordEncoder passwordEncoder;
    Logger logger= LoggerFactory.getLogger(Logger.class);
    @Override
    public ResponseEntity<String> sendSingleNotification(NotificationModel notificationModel) {
        System system=systemRepo.findByIp(notificationModel.getSenderIp());
        if (system==null|| !passwordEncoder.matches(notificationModel.getSenderPassword(),system.getPassword())){
            return ResponseEntity.badRequest().body("Sender ip or Sender password Not correct");
        }
        User user =userRepo.findByMobileNumber(notificationModel.getReceiverMobile());
        if (user==null){
            return ResponseEntity.badRequest().body("User Not found with number"
                    +notificationModel.getReceiverMobile());
        }


        LocalDateTime now= LocalDateTime.now();
        if (notificationModel.getEffectiveDate().plusMinutes(1).isBefore(now)){
            return ResponseEntity.badRequest().body("the EffectiveDate Must be now or in the future");
        }

        Notification notification=new Notification();

        notification.setTitle(notificationModel.getTitle());
        notification.setBody(notificationModel.getBody());
        notification.setCode("Text");
        notification.setEffectiveDate(notificationModel.getEffectiveDate());
        notification.setOpened(false);
        notification.setSender(system);
        notification.setUser(user);
        notificationRepo.save(notification);
        return ResponseEntity.ok("Notification Send Successfully");
    }

    @Override
    public ResponseEntity<String> sendBulkNotification(BulkNotifications bulkNotifications) {
        System system=systemRepo.findByIp(bulkNotifications.getSenderIp());
        if (system==null|| !passwordEncoder.matches(bulkNotifications.getSenderPassword(),system.getPassword())){
            return ResponseEntity.badRequest().body("Sender ip or Sender password Not correct");
        }
        logger.info(bulkNotifications.getNotifications().toString());
        LocalDateTime now= LocalDateTime.now();
        if (bulkNotifications.getEffectiveDate().plusMinutes(1).isBefore(now)){
            return ResponseEntity.badRequest().body("the EffectiveDate Must be now or in the future");
        }
        List<String> invalidNumbers=new ArrayList<>();
        for (SimpleNotification simpleNotification:bulkNotifications.getNotifications()){
            User user =userRepo.findByMobileNumber(simpleNotification.getReceiverMobile());
            if (user==null){
                invalidNumbers.add(simpleNotification.getReceiverMobile());
            }
        }
        if (!invalidNumbers.isEmpty()){
            return ResponseEntity.badRequest().body("There Are invalid Mobile Numbers \n"+invalidNumbers);
        }

        List<Notification> notifications=new ArrayList<>();

        for (SimpleNotification simpleNotification:bulkNotifications.getNotifications()){
            User user =userRepo.findByMobileNumber(simpleNotification.getReceiverMobile());
            Notification notification=new Notification();
            notification.setOpened(false);
            notification.setSender(system);
            notification.setEffectiveDate(bulkNotifications.getEffectiveDate());
            notification.setUser(user);
            notification.setTitle(simpleNotification.getTitle());
            notification.setBody(simpleNotification.getBody());
            notification.setCode("Text");
            notifications.add(notification);


        }
        notificationRepo.saveAll(notifications);
        return ResponseEntity.ok("Notifications Send Successfully");

    }

    @Override
    public ResponseEntity<List<NotificationDto>> retrieveAllNotification(RetrieveNotificationModel retrieveNotificationModel) {
        System system=systemRepo.findByIp(retrieveNotificationModel.getIp());
        if (system==null|| !passwordEncoder.matches(retrieveNotificationModel.getPassword(),system.getPassword())){
            return ResponseEntity.badRequest().body(null);
        }
        User user =userRepo.findByMobileNumber(retrieveNotificationModel.getMobileNumber());
        if (user==null){
            return ResponseEntity.badRequest().body(null);
        }
        List<Notification> notifications=notificationRepo.findAllByMobileNumber(retrieveNotificationModel.getMobileNumber());
        List<NotificationDto> notificationDtos=new ArrayList<>();
        for (Notification notification:notifications){
            notificationDtos.add(new NotificationDto(notification.getId(), notification.getTitle(),notification.getBody()
                    ,notification.getEffectiveDate(),notification.getSender().getName(),notification.getUser().getMobileNumber()));
        }
        return ResponseEntity.ok(notificationDtos);
    }
    @Override
    public ResponseEntity<String> sendBroadCastNotification(BroadCastNotificationModel broadCast) {

        System system=systemRepo.findByIp(broadCast.getSenderIp());
        if (system==null|| !passwordEncoder.matches(broadCast.getSenderPassword(),system.getPassword())){
            return ResponseEntity.badRequest().body("Sender ip or Sender password Not correct");
        }
        LocalDateTime now= LocalDateTime.now();
        if (broadCast.getEffectiveDate().plusMinutes(1).isBefore(now)){
            return ResponseEntity.badRequest().body("the EffectiveDate Must be now or in the future");
        }
        List<User> users=userRepo.findByIdentifiers(broadCast.getReceiverIp(), broadCast.getOrganization(),broadCast.getSubOrganization()
                                    ,broadCast.getDepartment(),broadCast.getAcademicYear());
        logger.info((String.valueOf( users.size())));
        for(User user:users){
            Notification notification=new Notification() ;
            notification.setCode("Text");
            notification.setTitle(broadCast.getTitle());
            notification.setBody(broadCast.getBody());
            notification.setEffectiveDate(broadCast.getEffectiveDate());
            notification.setSender(system);
            notification.setOpened(false);
            notification.setUser(user);
            notificationRepo.save(notification);
        }
        return ResponseEntity.ok("The BroadCast Notification Sent Successfully");
    }
    @Override
    public ResponseEntity<List<NotificationDto>> makeReport() {
        List<Notification> LastNotification=notificationRepo.findLastNotifications();
        List<NotificationDto> notificationDtos=new ArrayList<>();
        for (Notification notification:LastNotification){
            notificationDtos.add(new NotificationDto(notification.getId(), notification.getTitle(),notification.getBody()
                    ,notification.getEffectiveDate(),notification.getSender().getName(),notification.getUser().getMobileNumber()));
        }
        return ResponseEntity.ok(notificationDtos);
          }
}
