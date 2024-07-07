package com.Al_ahly_momkn.NotificationTool.Entity;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "system_table")
public class System {
    @Id
    private String ip;
    private String name;
    private String password;
    private Role role;
}
