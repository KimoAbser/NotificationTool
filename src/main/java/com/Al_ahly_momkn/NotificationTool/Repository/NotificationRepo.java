package com.Al_ahly_momkn.NotificationTool.Repository;

import com.Al_ahly_momkn.NotificationTool.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Long> {
    @Query(value = "select  * from notification where user_number=?1",nativeQuery = true)
    List<Notification> findAllByMobileNumber(String number);

    @Query(value = "SELECT * FROM notification ORDER BY effective_date DESC", nativeQuery = true)
    List<Notification> findLastNotifications();

    List<Notification> findAllByOrderByEffectiveDateDesc();
}
