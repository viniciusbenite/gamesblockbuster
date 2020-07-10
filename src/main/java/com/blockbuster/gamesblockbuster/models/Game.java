package com.blockbuster.gamesblockbuster.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Game {

    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String genre;
    private int releaseYear;
    private int totalQuantity;
    private int borrowedQuantity;

    @OneToMany
    private List<Platform> platforms;

}
