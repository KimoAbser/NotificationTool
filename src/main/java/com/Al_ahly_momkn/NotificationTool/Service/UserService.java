package com.Al_ahly_momkn.NotificationTool.Service;

import com.Al_ahly_momkn.NotificationTool.Model.UserModel;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<String> addUser(UserModel userModel);
}
