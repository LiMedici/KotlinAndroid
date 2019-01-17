package com.medici.kotlin.common

import java.io.File
import java.io.FileInputStream
import java.net.URL
import java.util.*
import kotlin.Exception
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class PropertiesDelegate(private val  path:String){
    private lateinit var url:URL

    private val properties:Properties by lazy {
        val prop = Properties()
        url = try{
            javaClass.getResourceAsStream(path).use{
                prop.load(it)
            }
            javaClass.getResource(path)
        }catch (e:Exception){
            try{
                ClassLoader.getSystemClassLoader().getResourceAsStream(path).use{
                    prop.load(it)
                }
                ClassLoader.getSystemClassLoader().getResource(path)
            }catch (e:Exception){
                FileInputStream(path).use {
                    prop.load(it)
                }

                URL("file://${File(path).canonicalPath}")
            }
        }

        prop
    }

    operator fun <T> getValue(thisRef:Any,property:KProperty<*>):T{
        val value = properties[property.name]
        val classOft = property.returnType.classifier as KClass<*>
        return when{
            Boolean::class == classOft -> value.toString().toBoolean()
            Number::class.java.isAssignableFrom(classOft.java) ->{
                classOft.javaObjectType.getDeclaredMethod("parse${classOft.simpleName}",String::class.java)
            }
            else -> value
        } as T
    }

    operator fun <T> setValue(thisRef:Any,property: KProperty<*>,value:T){
        properties[property.name] = value.toString()
        File(url.toURI()).outputStream().use{
            properties.store(it,"")
        }
    }
}

abstract class AbsProperties(path:String){
    protected val prop = PropertiesDelegate(path)
}