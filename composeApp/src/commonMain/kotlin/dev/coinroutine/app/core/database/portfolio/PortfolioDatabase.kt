package dev.coinroutine.app.core.database.portfolio

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.coinroutine.app.portofolio.data.local.PortfolioCoinDao
import dev.coinroutine.app.portofolio.data.local.PortfolioCoinEntity
import dev.coinroutine.app.portofolio.data.local.UserBalanceDao
import dev.coinroutine.app.portofolio.data.local.UserBalanceEntity

@Database(entities = [PortfolioCoinEntity::class, UserBalanceEntity::class], version = 2)
abstract class PortfolioDatabase : RoomDatabase() {

    abstract fun portfolioCoinDao(): PortfolioCoinDao
    abstract fun userBalanceDao(): UserBalanceDao

}