package com.Al_ahly_momkn.NotificationTool.Service.Imp;

import com.Al_ahly_momkn.NotificationTool.Entity.System;
import com.Al_ahly_momkn.NotificationTool.Entity.User;
import com.Al_ahly_momkn.NotificationTool.Model.UserModel;
import com.Al_ahly_momkn.NotificationTool.Repository.SystemRepo;
import com.Al_ahly_momkn.NotificationTool.Repository.UserRepo;
import com.Al_ahly_momkn.NotificationTool.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp  implements UserService {
    private final UserRepo userRepo;
    private final SystemRepo systemRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<String> addUser(UserModel userModel) {
        System system= systemRepo.findByIp(userModel.getIp());
        if (system==null){
            return ResponseEntity.badRequest().body("Sender ip or Sender password Not correct");
        }
        if (userModel.getMobileNumber()==null){
            return ResponseEntity.badRequest().body("Enter a Mobile Number");
        }
        if (userRepo.findByMobileNumber(userModel.getMobileNumber())!=null){
            return ResponseEntity.badRequest().body("This Mobile Number is already exist");
        }
        User user=new User();
        user.setMobileNumber(userModel.getMobileNumber());
        user.setSystem(system);
        user.setOrganization(userModel.getOrganization());
        user.setSubOrganization(user.getSubOrganization());
        user.setDepartment(userModel.getDepartment());
        user.setAcademicYear(userModel.getAcademicYear());
        userRepo.save(user);
        return ResponseEntity.ok("User Added Successfully");
    }
}
