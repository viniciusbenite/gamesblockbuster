package com.blockbuster.gamesblockbuster.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class GamePlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @OneToOne
    private Game game;
    @OneToOne
    private Platform platform;
    private int quantity;
    private int borrowedQuantity;
}
