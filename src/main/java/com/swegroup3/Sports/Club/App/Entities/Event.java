package com.swegroup3.Sports.Club.App.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // @ManyToMany(mappedBy = "events")
    // private Set<User> users =new HashSet<>();

}
