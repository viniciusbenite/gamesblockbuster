package com.blockbuster.gamesblockbuster.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcreteGame {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private GamePlatform game;

    private String status; // rented - available


}
