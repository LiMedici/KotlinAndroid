package com.medici.kotlin

import com.medici.kotlin.common.Preference

object Settings{

    var email:String by Preference(AppContext,"email","")
    var password:String by Preference(AppContext,"password","")

}