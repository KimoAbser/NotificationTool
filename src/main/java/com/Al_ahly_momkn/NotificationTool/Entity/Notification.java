package com.Al_ahly_momkn.NotificationTool.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @ManyToOne
    @JoinColumn(name = "sender",referencedColumnName = "ip")
    @JsonIgnore
    private System Sender;

    private String body;
    private String title;
    private Long amount;

    @ManyToOne
    @JoinColumn(name = "user_number")
    @JsonIgnore
    private User user;

    private LocalDateTime effectiveDate;

    private Boolean opened;


}
