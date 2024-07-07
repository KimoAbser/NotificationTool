package com.Al_ahly_momkn.NotificationTool.Repository;

import com.Al_ahly_momkn.NotificationTool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
    @Query(value = "select * from user where mobile_number=?1",nativeQuery = true)
    User findByMobileNumber(String receiverMobile);

    @Query(value = "SELECT * FROM user u " +
            "WHERE (:ip IS NULL OR u.system_ip = :ip) " +
            "AND (:organization IS NULL OR u.organization = :organization) " +
            "AND (:subOrganization IS NULL OR u.sub_organization = :subOrganization) " +
            "AND (:department IS NULL OR u.department = :department) " +
            "AND (:academicYear IS NULL OR u.academic_year = :academicYear)",
            nativeQuery = true)
    List<User> findByIdentifiers(String ip,String organization, String subOrganization, String department, String academicYear);
}
