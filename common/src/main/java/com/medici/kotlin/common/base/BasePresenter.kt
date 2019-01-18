package com.medici.kotlin.common.base

import android.content.res.Configuration
import android.os.Bundle

abstract class BasePresenter<out View:IView<BasePresenter<View>>> : IPresenter<View>{
    override lateinit var view: @UnsafeVariance View

    override fun onCreate(saveInstanceState: Bundle?) = Unit
    override fun onSaveInstanceState(outState: Bundle) = Unit
    override fun onViewStateRestored(saveInstanceState: Bundle?) = Unit
    override fun onConfigurationChanged(newConfig: Configuration?) = Unit
    override fun onStart() = Unit
    override fun onStop() = Unit
    override fun onResume() = Unit
    override fun onPause() = Unit
    override fun onDestroy() = Unit
}
