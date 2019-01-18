package com.medici.kotlin.common.base

import android.content.res.Configuration
import android.os.Bundle

interface ILifecycle{

    fun onCreate(saveInstanceState:Bundle?)

    fun onSaveInstanceState(outState:Bundle)

    fun onViewStateRestored(saveInstanceState:Bundle?)

    fun onConfigurationChanged(newConfig: Configuration?)

    fun onDestroy()

    fun onStart()

    fun onStop()

    fun onResume()

    fun onPause()
}