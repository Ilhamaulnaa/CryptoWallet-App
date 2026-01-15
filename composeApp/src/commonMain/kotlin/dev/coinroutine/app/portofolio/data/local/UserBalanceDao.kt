package dev.coinroutine.app.portofolio.data.local

import androidx.room.Query
import androidx.room.Upsert

interface UserBalanceDao {

    @Upsert
    suspend fun insertBalance(userBalanceEntity: UserBalanceEntity)

    @Query("SELECT cashBalance FROM UserBalanceEntity WHERE id = 1")
    suspend fun getCashBalance() : Double?

    @Query("UPDATE USERBALANCEENTITY SET cashBalance = :newBalance WHERE id = 1")
    suspend fun updateCashBalance(newBalance: Double)

}