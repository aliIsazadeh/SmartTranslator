package com.esum.database.dataProvider

import android.util.Log
import com.esum.database.entity.ProfileEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.UUID
import javax.inject.Inject

@HiltAndroidTest
class ProfileDataProviderTest {

    val TAG = "ProfileDataProviderTest"


    @get:Rule
    var hilt = HiltAndroidRule(this)

    @Inject
    lateinit var provider: ProfileDataProvider


    lateinit var profileEntity: ProfileEntity

    @Before
    fun setUp() {
        hilt.inject()
        profileEntity = ProfileEntity(
            name = "ali",
            username = "ali",
            email = "isazadhali@gmail.com",
            phoneNumber = "09355452735",
            id = UUID.randomUUID()
        )
    }



    @Test
    fun insertProfile(): Unit = runBlocking {

        try {
            val id = provider.insertProfile(profileEntity)
            assert(id > -1)
        } catch (e: Exception) {
            assertEquals(e.message.toString(), 1, 0)
            Log.e(TAG, "insertProfile: ${e.message}")
        }
    }

    @Test
    fun updateProfile(): Unit = runBlocking {
        try {
            val id = provider.insertProfile(profileEntity)

            var updateEntity: ProfileEntity? = null

            updateEntity = provider.getProfile().first().copy(email = "isazadeh")

            provider.updateProfile(updateEntity)

            val updatedValue = provider.getProfile().first()

            assertEquals(updateEntity, updatedValue)

        } catch (e: Exception) {
            assertEquals(e.message.toString(), 1, 0)
            Log.e(TAG, "insertProfile: ${e.message}")
        }
    }

    @Test
    fun deleteProfile(): Unit = runBlocking {
        try {

            val id = provider.insertProfile(profileEntity)
            val deleteEntity = provider.getProfile().first()
            provider.deleteProfile(deleteEntity)
            val deleted: ProfileEntity? = provider.getProfile().firstOrNull()

            assertEquals(null, deleted)


        } catch (e: Exception) {
            assertEquals(e.message.toString(), 1, 0)
            Log.e(TAG, "insertProfile: ${e.message}")
        }


    }


}