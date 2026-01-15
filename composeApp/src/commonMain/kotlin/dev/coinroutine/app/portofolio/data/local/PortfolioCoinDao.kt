package dev.coinroutine.app.portofolio.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioCoinDao {

    //Singkatan dari "Update or Insert". Jika data dengan id yang sama sudah ada, ia akan diupdate. Jika belum ada, ia akan ditambah baru.
    @Upsert
    suspend fun insert(portfolioCoinEntity: PortfolioCoinEntity)

    //ORDER BY timestamp DESC: Mengurutkan koin yang dimiliki dari yang paling baru ditambahkan.
    @Query("SELECT * FROM PortfolioCoinEntity ORDER BY timestamp DESC")
    suspend fun getAllOwnedCoins() : Flow<List<PortfolioCoinEntity>>

    @Query("SELECT * FROM PortfolioCoinEntity WHERE coinId = :coinId")
    suspend fun getCoinById(coinId: String) : PortfolioCoinEntity?

    //Menghapus langsung berdasarkan kriteria yang kamu tulis di SQL.
    @Query("DELETE FROM PortfolioCoinEntity WHERE coinId = :coinId")
    suspend fun deletePortfolioItem(coinId: String)

}