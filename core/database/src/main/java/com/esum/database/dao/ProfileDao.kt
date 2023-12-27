package com.esum.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.esum.database.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("Select * From profile_table")
    fun getProfile(): Flow<ProfileEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = ProfileEntity::class)
    suspend fun insertProfile(profileEntity: ProfileEntity) : Long

    @Update(entity = ProfileEntity::class)
    suspend fun updateProfile(profileEntity: ProfileEntity)

    @Query("delete From card_table where id = :id")
    suspend fun deleteProfileById(id : Long)

    @Delete(entity = ProfileEntity::class )
    suspend fun deleteProfile(profileEntity: ProfileEntity)

}