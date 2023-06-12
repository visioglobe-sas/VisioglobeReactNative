package com.rtnmapview

import android.util.Log
import android.view.Choreographer
import android.view.View
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.RTNMapViewManagerDelegate
import com.facebook.react.viewmanagers.RTNMapViewManagerInterface
import com.visioglobe.visiomoveessential.enums.VMERouteDestinationsOrder
import com.visioglobe.visiomoveessential.enums.VMERouteRequestType


@ReactModule(name = MapViewManager.NAME)
class MapViewManager (context: ReactApplicationContext) : SimpleViewManager<MapView>(),
    RTNMapViewManagerInterface<MapView> {

    private val delegate = RTNMapViewManagerDelegate(this)
    override fun getName(): String {
        return NAME
    }

    override fun getDelegate(): ViewManagerDelegate<MapView> = delegate

    override fun createViewInstance(p0: ThemedReactContext): MapView {
        return MapView(context = p0)
    }

    companion object {
        const val NAME = "RTNMapView"
    }

    @ReactProp(name = "filePath")
    override fun setFilePath(view: MapView?, value: String?) {
        if (value != null) {
        }

    }

    @ReactProp(name = "mapPath")
    override fun setMapPath(view: MapView?, value: String?) {
        if (value != null) {
            view?.mMapController?.mapPath = value
        }
    }

    @ReactProp(name = "mapSecretCode")
    override fun setMapSecretCode(view: MapView?, value: Int) {
        if (value != null) {
            view?.mMapController?.mapSecretCode = value
        }
    }

    @ReactProp(name = "mapHash")
    override fun setMapHash(view: MapView?, value: String?) {
        if (value != null) {
            Log.d("cc", "notinit : $value")
            view?.mMapController?.mapHash = value
        }
    }

    @ReactProp(name = "mapServerUrl")
    override fun setMapServerUrl(view: MapView?, value: String?) {
        if (value != null) {
            view?.mMapController?.mapServerUrl = value
        }
    }

    @ReactMethod fun routeRequest(view: MapView?, requestType : VMERouteRequestType, destinationsOrder: VMERouteDestinationsOrder, isAccessible : Boolean) {
        view?.routeRequest(requestType, destinationsOrder, isAccessible)
    }

    @ReactMethod fun addDestination(view: MapView?, poiID : String) {
        view?.addDestination(poiID)
    }

    @ReactMethod fun setOrigin(view: MapView?, poiID : String) {
        view?.setOrigin(poiID)
    }

}