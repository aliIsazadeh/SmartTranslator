package com.esum.database.dataProvider

import com.esum.database.dao.ProfileDao
import com.esum.database.entity.ProfileEntity
import javax.inject.Inject

class ProfileDataProvider @Inject constructor(private val profileDao: ProfileDao) {

    fun getProfile() = profileDao.getProfile()

    fun getAllProfile() = profileDao.getAllProfile()

    suspend fun insertProfile(profileEntity: ProfileEntity) : Long  =  profileDao.insertProfile(profileEntity)
    suspend fun updateProfile(profileEntity: ProfileEntity) = profileDao.updateProfile(profileEntity)
    suspend fun deleteProfile(profileEntity: ProfileEntity) = profileDao.deleteProfile(profileEntity)
    suspend fun deleteProfileById(id : Long) = profileDao.deleteProfileById(id)


}