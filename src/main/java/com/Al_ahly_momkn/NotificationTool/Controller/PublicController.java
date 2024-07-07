package com.Al_ahly_momkn.NotificationTool.Controller;

import com.Al_ahly_momkn.NotificationTool.Model.LoginModel;
import com.Al_ahly_momkn.NotificationTool.Model.SystemModel;
import com.Al_ahly_momkn.NotificationTool.Model.UserModel;
import com.Al_ahly_momkn.NotificationTool.Service.SystemService;
import com.Al_ahly_momkn.NotificationTool.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {
    private final SystemService systemService;
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginModel loginModel){
        return systemService.login(loginModel);
    }
    @PostMapping("/UserRegister")
    public ResponseEntity<String> UserRegister(@RequestBody UserModel userModel){
        return userService.addUser(userModel);
    }

//    @PostMapping("/SystemRegister")
//    public ResponseEntity<String> SystemRegister(@RequestBody SystemModel systemModel){
//        return systemService.addSystem(systemModel);
//    }

}
