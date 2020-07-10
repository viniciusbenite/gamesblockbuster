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

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String lastName;
    private String firstName;
    private String mail;
    private boolean type; // true: admin, false: common user

    @OneToMany
    private List<Requests> userRequests;

    public boolean getType() { return this.type; }
}
