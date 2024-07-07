package com.Al_ahly_momkn.NotificationTool.Repository;

import com.Al_ahly_momkn.NotificationTool.Entity.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepo extends JpaRepository<System,String> {

    @Query(value = "select  * from system_table where ip=?1",nativeQuery = true)
    System findByIp(String senderIp);

}
