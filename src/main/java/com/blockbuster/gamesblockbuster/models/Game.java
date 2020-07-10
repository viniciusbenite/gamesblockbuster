package com.blockbuster.gamesblockbuster.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String genre;
    private int releaseYear;
    private int totalQuantity;
    private int borrowedQuantity;

    @ManyToMany(targetEntity = Platform.class)
    private Set<Platform> platforms;

}
