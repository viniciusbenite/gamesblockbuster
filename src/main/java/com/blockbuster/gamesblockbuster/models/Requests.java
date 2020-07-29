package com.blockbuster.gamesblockbuster.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Requests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String status; // 'approved', 'denied' or 'pending'
    private Date requestDate;
    private int numberOfDays;
    private int extraDays;

    @OneToOne(targetEntity = User.class)
    private User requestOwner;
    @OneToOne(targetEntity = ConcreteGame.class)
    private ConcreteGame requestedGame;
}
