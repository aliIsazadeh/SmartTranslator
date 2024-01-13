package com.esum.network.drscription.dataProviders

import android.util.Log
import com.esum.common.lagnuage.Languages
import com.esum.network.description.dataproviders.DescriptionDataProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.math.log

@HiltAndroidTest
class DescriptionDataProviderTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var provider: DescriptionDataProvider

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun testDescription() : Unit = runBlocking(){

        try{
            val response = provider.getDescription(Languages.English, word = "you")
            Log.d("DescriptionDataProviderTest", "testDescription: ${response.body()?.toString()} ", )
            assert(response.isSuccessful)
        }catch(e : Exception) {
            Log.e("DescriptionDataProviderTest", "testDescription: ${e.message}", )
        }

    }

}