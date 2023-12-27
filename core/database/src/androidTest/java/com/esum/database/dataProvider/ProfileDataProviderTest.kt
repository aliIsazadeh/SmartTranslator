package com.esum.database.dataProvider

import android.util.Log
import com.esum.database.entity.ProfileEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
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
        profileEntity= ProfileEntity(name = "ali", username = "ali", email = "isazadhali@gmail.com" , phoneNumber = "09355452735")
    }

    @Test
    fun getProfile() {
    }

    @Test
    fun insertProfile(): Unit = runBlocking {

        try {
            val id = provider.insertProfile(profileEntity)
            assert(id> -1)


        } catch (e: Exception) {
            Log.e(TAG, "insertProfile: ${e.message}")
        }

    }

    @Test
    fun updateProfile() {
    }

    @Test
    fun deleteProfile() {
    }

    @Test
    fun deleteProfileById() {
    }
}