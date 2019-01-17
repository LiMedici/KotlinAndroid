package com.medici.kotlin.common

import android.content.Context
import java.lang.IllegalArgumentException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preference<T>(val context:Context,val name:String,val default:T,val prefName:String= "default")
    :ReadWriteProperty<Any?,T>{

    private val pref by lazy {
        context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name,value)
    }

    private fun findPreference(key:String):T{
        return when(default){
            is Boolean -> pref.getBoolean(key,default)
            is Float -> pref.getFloat(name,default)
            is Int -> pref.getInt(name,default)
            is Long -> pref.getLong(name,default)
            is String -> pref.getString(name,default)
            else -> throw IllegalArgumentException("UnSupported type.")
        } as T
    }

    private fun putPreference(key:String,value:T){
        with(pref.edit()){
            when(value){
                is Boolean -> putBoolean(key,value)
                is Float -> putFloat(key,value)
                is Int -> putInt(key,value)
                is Long -> putLong(key,value)
                is String -> putString(key,value)
                else -> throw IllegalArgumentException("UnSupported type.")
            }
        }.apply()
    }
}