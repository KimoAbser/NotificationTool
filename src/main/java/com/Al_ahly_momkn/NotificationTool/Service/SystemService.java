package com.Al_ahly_momkn.NotificationTool.Service;

import com.Al_ahly_momkn.NotificationTool.Entity.System;
import com.Al_ahly_momkn.NotificationTool.Model.LoginModel;
import com.Al_ahly_momkn.NotificationTool.Model.SystemModel;
import org.springframework.http.ResponseEntity;

public interface SystemService {
    public System CurrantSystem(String token);

    ResponseEntity<String> login(LoginModel loginModel);

    ResponseEntity<String> addSystem(SystemModel systemModel);
}
