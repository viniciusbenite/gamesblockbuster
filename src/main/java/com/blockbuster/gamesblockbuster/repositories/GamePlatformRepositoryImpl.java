package com.blockbuster.gamesblockbuster.repositories;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;


@Repository
@Transactional
public class GamePlatformRepositoryImpl implements GamePlatformRepositoryCustom {

    /*
    *   Custom queries
    */

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void updateBorrowedQuantity(long id) {
//        This uses specific SQL dialect. Spring can't validate before
        System.out.println("UPDATED CALLED");
        Query query = entityManager.createNativeQuery("update game_platform set borrowed_quantity=borrowed_quantity+1 where id='" + id + "';");
        query.executeUpdate();
        System.out.println("UPDATED DONE");
    }

    @Override
    public void updatedReturnQuantity(long id) {
        System.out.println("RETURN CALLED");
        Query query = entityManager.createNativeQuery("update game_platform set borrowed_quantity=borrowed_quantity-1 where id='" + id + "';");
        query.executeUpdate();
        System.out.println("RETURN DONE");
    }
}
