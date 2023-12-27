package com.esum.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.esum.database.entity.CardEntity
import com.esum.database.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Query("SELECT * FROM CARD_TABLE")
    fun getAllCards() : Flow<List<CardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE ,entity = CardEntity::class)
    suspend fun insertCard(cardEntity: CardEntity) : Long

    @Update(entity = CardEntity::class)
    suspend fun updateCard(entity: CardEntity)

    @Query("SELECT * FROM CARD_TABLE WHERE id = :id")
    fun getCardById(id : Long) : Flow<CardEntity>

    @Query("Delete From card_table Where id = :id")
    suspend fun deleteCardById(id : Long)

    @Delete(entity = CardEntity::class)
    suspend fun deleteCard(cardEntity: CardEntity)

}