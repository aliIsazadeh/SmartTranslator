package com.esum.network

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

class TestRunner :AndroidJUnitRunner()  {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, className, context)
    }

}