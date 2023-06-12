package com.rtnmapview

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.UIBlock
import com.facebook.react.uimanager.UIManagerModule
import com.visioglobe.visiomoveessential.models.VMEPosition
import com.visioglobe.visiomoveessential.models.VMESceneContext

class VMEModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String {
        return "VMEModule"
    }

    @ReactMethod
    fun computeRoute(tag: Int, info: ReadableMap, options: ReadableMap, promise: Promise) {
        val context = reactApplicationContext
        val uiManager = context.getNativeModule(UIManagerModule::class.java)
        if (info.hasKey("origin") == false || info.hasKey("destinations") == false) {
            promise.reject("You should provide the origin")
            return
        }
        val drawRouteOnMap = if (options.hasKey("drawRouteOnMap")) options.getBoolean("drawRouteOnMap") else true
        val accessibility = if (options.hasKey("accessibility")) options.getBoolean("accessibility") else false
        val walkTimeService = if (options.hasKey("_walkTime")) true else false
        val showHeaderNavigation = if (options.hasKey("showHeaderNavigation")) options.getBoolean("showHeaderNavigation") else true
        uiManager!!.addUIBlock(UIBlock { nvhm ->
            val view = nvhm.resolveView(tag) as MapView
            if (view == null) {
                promise.reject("VMEMapView component not found")
                return@UIBlock
            }
            if (view.mMapView == null) {
                promise.reject("map view instance is not valid")
                return@UIBlock
            }
            if (walkTimeService == false) {
                view.mMapView.setNavigationHeaderViewVisible(showHeaderNavigation)
            }
            val origin = info.getDynamic("origin")
            val destinationsArray: List<Any> = info.getArray("destinations")!!.toArrayList()
            val dests: MutableList<Any> = ArrayList()
            for (d in destinationsArray) {
                if (d is String) {
                    dests.add(d)
                } else {
                    val map = d as HashMap<*, *>
                    val scene = map["scene"] as HashMap<*, *>?
                    val post = VMEPosition(
                            map["latitude"] as Double,
                            map["longitude"] as Double,
                            map["altitude"] as Double,
                            VMESceneContext((scene!!["buildingId"] as String?)!!, (scene["floorId"] as String?)!!))
                    dests.add(post)
                }
            }
            view.computeRouteFromOriginToDests(origin, dests, 0, accessibility, drawRouteOnMap, promise)
        })
    }

    @ReactMethod
    fun navigateToSegment(tag: Int, index: Int, promise: Promise) {
        val context = reactApplicationContext
        val uiManager = context.getNativeModule(UIManagerModule::class.java)
        uiManager!!.addUIBlock(UIBlock { nvhm ->
            val view = nvhm.resolveView(tag) as MapView
            if (view == null) {
                promise.reject("VMEMapView component not found")
                return@UIBlock
            }
            view.navigateToSegment(index)
        })
    }

    @ReactMethod
    fun recenterMap(tag: Int) {
        val context = reactApplicationContext
        val uiManager = context.getNativeModule(UIManagerModule::class.java)
        uiManager!!.addUIBlock(UIBlock { nvhm ->
            val view = nvhm.resolveView(tag) as MapView ?: return@UIBlock
            view.recenterMap()
        })
    }

    @ReactMethod
    fun focusMapOn(tag: Int, params: ReadableMap, promise: Promise) {
        val context = reactApplicationContext
        if (params.hasKey("place") == false) {
            promise.reject("You should provide the origin")
            return
        }
        val uiManager = context.getNativeModule(UIManagerModule::class.java)
        uiManager!!.addUIBlock(UIBlock { nvhm ->
            val view = nvhm.resolveView(tag) as MapView ?: return@UIBlock
            val place = params.getDynamic("place")
            view.focusMapOn(place)
        })
    }

    @ReactMethod
    fun startLocationTracker(tag: Int) {
        val context = reactApplicationContext
        val uiManager = context.getNativeModule(UIManagerModule::class.java)
        uiManager!!.addUIBlock { nvhm ->
            val view = nvhm.resolveView(tag) as MapView
            view.startLocationTracker(context)
        }
    }

    @ReactMethod
    fun stopLocationTracker(tag: Int) {
        val context = reactApplicationContext
        val uiManager = context.getNativeModule(UIManagerModule::class.java)
        uiManager!!.addUIBlock { nvhm ->
            val view = nvhm.resolveView(tag) as MapView
            view.stopLocationTracker()
        }
    }

    @ReactMethod
    fun getNearestLocation(tag: Int, position: ReadableMap, promise: Promise) {
        val context = reactApplicationContext
        val uiManager = context.getNativeModule(UIManagerModule::class.java)
        if (position.hasKey("latitude") == false || position.hasKey("longitude") == false || position.hasKey("altitude") == false || position.hasKey("scene") == false || position.getMap("scene")!!.hasKey("buildingId") == false || position.getMap("scene")!!.hasKey("floorId") == false) {
            promise.reject("The passed position argument doesn't confirm to type VMEPosition")
            return
        }
        val post = VMEPosition(
                position.getDouble("latitude"),
                position.getDouble("longitude"),
                position.getDouble("altitude"),
                VMESceneContext(position.getMap("scene")!!.getString("buildingId")!!, position.getMap("scene")!!.getString("floorId")!!))
        uiManager!!.addUIBlock { nvhm ->
            val view = nvhm.resolveView(tag) as MapView
            view.getNearestLocation(post, promise)
        }
    }
}