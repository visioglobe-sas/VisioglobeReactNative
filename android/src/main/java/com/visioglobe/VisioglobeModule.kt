package com.visioglobe

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableType
import com.facebook.react.bridge.WritableArray
import com.facebook.react.bridge.WritableMap
import com.facebook.react.module.annotations.ReactModule
import com.visioglobe.VisioglobeModule

@ReactModule(name = VisioglobeModule.NAME)
class VisioglobeModule(reactContext: ReactApplicationContext?) :
    ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String {
        return NAME
    }

    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    fun multiply(a: Double, b: Double, promise: Promise) {
        promise.resolve(a * b)
    }

    companion object {
        const val NAME = "Visioglobe"
        fun toMap(readableMap: ReadableMap?): Map<String, Any?> {
            val map: MutableMap<String, Any?> = HashMap()
            val iterator = readableMap!!.keySetIterator()
            while (iterator.hasNextKey()) {
                val key = iterator.nextKey()
                val type = readableMap.getType(key)
                when (type) {
                    ReadableType.Null -> map[key] = null
                    ReadableType.Boolean -> map[key] = readableMap.getBoolean(key)
                    ReadableType.Number -> map[key] = readableMap.getDouble(key)
                    ReadableType.String -> map[key] = readableMap.getString(key)
                    ReadableType.Map -> map[key] = toMap(readableMap.getMap(key))
                    ReadableType.Array -> map[key] = toArray(
                        readableMap.getArray(key)
                    )
                }
            }
            return map
        }

        fun toArray(readableArray: ReadableArray?): Array<Any?> {
            val array = arrayOfNulls<Any>(readableArray!!.size())
            for (i in 0 until readableArray.size()) {
                val type = readableArray.getType(i)
                when (type) {
                    ReadableType.Null -> array[i] = null
                    ReadableType.Boolean -> array[i] = readableArray.getBoolean(i)
                    ReadableType.Number -> array[i] = readableArray.getDouble(i)
                    ReadableType.String -> array[i] = readableArray.getString(i)
                    ReadableType.Map -> array[i] = toMap(
                        readableArray.getMap(i)
                    )

                    ReadableType.Array -> array[i] = toArray(
                        readableArray.getArray(i)
                    )
                }
            }
            return array
        }

        fun toWritableMap(map: Map<String?, Any?>): WritableMap {
            val writableMap = Arguments.createMap()
            val iterator: Iterator<*> = map.entries.iterator()
            while (iterator.hasNext()) {
                val (key, value) = iterator.next() as Map.Entry<*, *>
                if (value == null) {
                    writableMap.putNull((key as String?)!!)
                } else if (value is Boolean) {
                    writableMap.putBoolean((key as String?)!!, (value as Boolean?)!!)
                } else if (value is Double) {
                    writableMap.putDouble((key as String?)!!, (value as Double?)!!)
                } else if (value is Int) {
                    writableMap.putInt((key as String?)!!, (value as Int?)!!)
                } else if (value is String) {
                    writableMap.putString((key as String?)!!, value as String?)
                } else if (value is Map<*, *>) {
                    writableMap.putMap(
                        (key as String?)!!,
                        toWritableMap(value as Map<String?, Any?>)
                    )
                } else if (value.javaClass != null && value.javaClass.isArray) {
                    writableMap.putArray((key as String?)!!, toWritableArray(value as Array<Any?>?))
                }
            }
            return writableMap
        }

        fun toWritableArray(array: Array<Any?>?): WritableArray {
            val writableArray = Arguments.createArray()
            for (i in array!!.indices) {
                val value = array[i]
                if (value == null) {
                    writableArray.pushNull()
                }
                if (value is Boolean) {
                    writableArray.pushBoolean((value as Boolean?)!!)
                }
                if (value is Double) {
                    writableArray.pushDouble((value as Double?)!!)
                }
                if (value is Int) {
                    writableArray.pushInt((value as Int?)!!)
                }
                if (value is String) {
                    writableArray.pushString(value as String?)
                }
                if (value is Map<*, *>) {
                    writableArray.pushMap(toWritableMap(value as Map<String?, Any?>))
                }
                if (value!!.javaClass.isArray) {
                    writableArray.pushArray(toWritableArray(value as Array<Any?>?))
                }
            }
            return writableArray
        }
    }
}