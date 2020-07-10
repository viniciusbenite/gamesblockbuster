package com.blockbuster.gamesblockbuster.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Requests {

    @Id
    @GeneratedValue
    private long id;

    private boolean status; // true: approved, false: denied
    private boolean returned; // true: returned, false: product with user
    private Date requestDate;
    private int numberOfDays;
    private int extraDays;

    @OneToOne
    private User requestOwner;
    @OneToOne
    private GameInstance requestedGame;
}
