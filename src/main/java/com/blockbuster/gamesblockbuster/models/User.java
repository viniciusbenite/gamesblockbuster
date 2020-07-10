package com.blockbuster.gamesblockbuster.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String lastName;
    private String firstName;
    private String mail;
    private boolean type; // true: admin, false: common user

    @OneToMany(targetEntity = Requests.class)
    private Set<Requests> userRequests;

    public boolean getType() { return this.type; }
}
