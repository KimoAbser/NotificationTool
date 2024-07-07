package com.Al_ahly_momkn.NotificationTool.Service.Imp;

import com.Al_ahly_momkn.NotificationTool.Entity.Role;
import com.Al_ahly_momkn.NotificationTool.Entity.System;
import com.Al_ahly_momkn.NotificationTool.Model.LoginModel;
import com.Al_ahly_momkn.NotificationTool.Model.SystemModel;
import com.Al_ahly_momkn.NotificationTool.Repository.SystemRepo;
import com.Al_ahly_momkn.NotificationTool.Security.JwtService;
import com.Al_ahly_momkn.NotificationTool.Service.SystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemServiceImp implements SystemService {
    private final SystemRepo systemRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public System CurrantSystem(String token){
        return null;
    }
    @Override
    public ResponseEntity<String> login(LoginModel loginModel) {
        System system=systemRepo.findByIp(loginModel.getIp());
        // Check if user exists and credentials are correct
        if (system == null || !passwordEncoder.matches(loginModel.getPassword(), system.getPassword())) {
            // Return 400 status code with "Bad request" message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request: Invalid Ip or Password");
        }
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginModel.getIp(),
                            loginModel.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            // Authentication failed due to reasons other than bad credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
        // User authenticated, generate JWT token
        var jwt = jwtService.generateToken(new CustomUserDetails(system.getIp(), system.getRole(),system.getPassword()));
        return ResponseEntity.ok(jwt);
    }
    @Override
    public ResponseEntity<String> addSystem(SystemModel systemModel) {
        if (systemRepo.findByIp(systemModel.getIp())!=null){
           return ResponseEntity.badRequest().body("There is System with same ip");
        }
        if (systemModel.getPassword()==null || systemModel.getName()==null){
            return ResponseEntity.badRequest().body("Invalid Data entered");
        }
        System system=new System();
        system.setIp(systemModel.getIp());
        system.setName(systemModel.getName());
        system.setPassword(passwordEncoder.encode(systemModel.getPassword()));
        system.setRole(Role.SYSTEM);
        systemRepo.save(system);
        return ResponseEntity.ok("System Added Successfully");
    }

}
