package com.medici.kotlin.common.base

interface IPresenter<out View:IView<IPresenter<View>>> : ILifecycle{
    val view:View
}

interface IView<out Presenter:IPresenter<IView<Presenter>>> : ILifecycle{
    val presenter:Presenter
}