package com.blockbuster.gamesblockbuster.repositories;

public interface GamePlatformRepositoryCustom {
    void updateBorrowedQuantity(long id);
    void updatedReturnQuantity(long id);
}

