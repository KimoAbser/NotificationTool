package com.Al_ahly_momkn.NotificationTool.Service.Imp;

import com.Al_ahly_momkn.NotificationTool.Entity.System;
import com.Al_ahly_momkn.NotificationTool.Repository.SystemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final SystemRepo systemRepo;
    @Override
    public UserDetails loadUserByUsername(String ip) throws UsernameNotFoundException {

        System system=systemRepo.findByIp(ip);
        if (system==null){
            throw new UsernameNotFoundException("Invalid System Ip or Password");
        }
        return new CustomUserDetails(system.getIp(),system.getRole(),system.getPassword());
    }
}
