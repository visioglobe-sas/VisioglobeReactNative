package com.rtnmapview

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.RTNMapViewManagerInterface


@ReactModule(name = MapViewManager.NAME)
class MapViewManager (context: ReactApplicationContext) : SimpleViewManager<MapView>(),
    RTNMapViewManagerInterface<MapView> {
    override fun getName(): String {
        return NAME
    }

    override fun getDelegate(): ViewManagerDelegate<MapView> = delegate

    override fun createViewInstance(p0: ThemedReactContext): MapView {
        return MapView(context = p0, this);
    }

    companion object {
        const val NAME = "RTNMapViewImage"
    }

    @ReactProp(name = "filePath")
    override fun setFilePath(view: MapView?, value: String?) {
        if (value != null) {    
        }

    }

    @ReactProp(name = "mapPath")
    override fun setMapPath(view: MapView?, value: String?) {
        if (value != null) {
            view?.setMapPath(value)
        }
    }

    @ReactProp(name = "mapSecretCode")
    override fun setMapSecretCode(view: MapView?, value: Int) {
        if (value != null) {
            view?.setMapSecretCode(value)
        }
    }

    @ReactProp(name = "mapHash")
    override fun setMapHash(view: MapView?, value: String?) {
        if (value != null) {
            view?.setMapHash(value)
        }
    }

    @ReactProp(name = "mapServerUrl")
    override fun setMapServerUrl(view: MapView?, value: String?) {
        if (value != null) {
            view?.setMapServerUrl(value)
        }
    }

}