package com.esum.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.esum.database.entity.CardEntity
import com.esum.database.entity.model.ActiveCardsCount
import com.esum.database.entity.relations.CardWithLanguages
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface CardDao {

    @Transaction
    @Query("SELECT * FROM CARD_TABLE")
    fun getAllCards(): Flow<List<CardWithLanguages>>

    @Transaction
    @Query("SELECT * FROM CARD_TABLE WHERE id = :id")
    fun getCardWithDescriptionById(id: UUID): Flow<List<CardWithLanguages>>


    @Query("Select active , Count(*) as count from card_table group by active")
    fun getActivesCount(): Flow<List<ActiveCardsCount>>

    @Transaction
    @Query(
        "select *  from card_table as ct " +
                " where  ct.active and (ct.update_date <= strftime( '%Y%m%d' ,DATE('now'))" +
                " or ct.update_date is null or ct.update_date ='' ) "
    )
    fun getReviewCards(): Flow<List<CardWithLanguages>>

    @Insert(entity = CardEntity::class)
    suspend fun insertCard(cardEntity: CardEntity): Long

    @Update(entity = CardEntity::class)
    suspend fun updateCard(entity: CardEntity)

    @Transaction
    @Query("SELECT * FROM CARD_TABLE WHERE id = :id")
    fun getCardById(id: UUID): Flow<CardWithLanguages?>

    @Query("Delete From card_table Where id = :id")
    suspend fun deleteCardById(id: UUID)

    @Delete(entity = CardEntity::class)
    suspend fun deleteCard(cardEntity: CardEntity)

}