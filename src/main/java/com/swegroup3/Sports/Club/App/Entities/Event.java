package com.swegroup3.Sports.Club.App.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location;
    private Long max_amount_participants;

    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;

    @Transient
    private String dateTimeStartStr;
    @Transient
    private String dateTimeEndStr;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User user;

    private Timestamp timestamp;

    @PrePersist
    public void setTimestamp() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
