package com.esum.network

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


class TestRunner :AndroidJUnitRunner()  {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application = Instrumentation.newApplication(HiltTestApplication::class.java, context)

}