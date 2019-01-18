package com.medici.kotlin.common.base

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

abstract class BaseActivity<out Presenter:BasePresenter<IView<Presenter>>> : AppCompatActivity(),IView<Presenter>{
    override val presenter: Presenter

    init {
        presenter = initPresenter()
        presenter.view = this
    }

    private fun initPresenterKt():Presenter{
        sequence {
            var thisClass : KClass<*> = this@BaseActivity::class
            while (true){
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure?:break
            }
        }.flatMap{ it ->
            it.flatMap{it.arguments}.asSequence()
        }.first{
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class)?:false
        }.let{
            return it.type!!.jvmErasure.primaryConstructor!!.call() as Presenter
        }
    }

    private fun initPresenter():Presenter{
        sequence {
            var thisClass : Class<*> = this@BaseActivity::class.java
            while (true){
                yield(thisClass.genericSuperclass)
                thisClass = thisClass.superclass?:break
            }
        }.filter{
            it is ParameterizedType
        }.flatMap{ it ->
            (it as ParameterizedType).actualTypeArguments.asSequence()
        }.first{
            it is Class<*> && IPresenter::class.java.isAssignableFrom(it)
        }.let{
            return (it as Class<Presenter>).newInstance()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        presenter.onConfigurationChanged(newConfig)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    class OtherFragment : BaseActivity<OtherPresenter>(){
        override fun onResume() {
            super.onResume()
            println("onResume Called")
        }
    }
    class OtherPresenter : BasePresenter<OtherFragment>()
}