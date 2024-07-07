package com.Al_ahly_momkn.NotificationTool.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    private String mobileNumber;
    @ManyToOne()
    @JoinColumn(name = "system_ip",referencedColumnName = "ip")
    private System system;
    private String organization;
    private String subOrganization;
    private String department;
    private String academicYear;
}
