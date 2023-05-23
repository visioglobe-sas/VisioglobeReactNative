package com.rtnmapview

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.rtnmapview.RTNMapViewNativeComponentSpec
import com.visioglobe.visiomoveessential.VMEMapController
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder
import com.visioglobe.visiomoveessential.VMEMapView

class MapViewModule(reactContext: ReactApplicationContext) : RTNMapViewNativeComponentSpec(reactContext) {

  private var mapController : VMEMapController = VMEMapController(reactContext, VMEMapControllerBuilder())

  private var mapView : VMEMapView? = MapView(reactContext).mMapView

  override fun getName() = NAME

  override fun init(mapPath: String? , mapSecretCode : Double?, mapHash : String?, mapServerUrl : String? , promptUserToDlMap : Boolean?){
    var builder = VMEMapControllerBuilder()
    if (mapPath != null) {
      builder.mapPath = mapPath
    }
    if (mapHash != null) {
      builder.mapHash = mapHash
    }
    if (mapSecretCode != null) {
      builder.mapSecretCode = mapSecretCode.toInt()
    }
    if (mapServerUrl != null){
      builder.mapServerURL = mapServerUrl
    }
    if (promptUserToDlMap != null) {
      builder.promptUserToDownloadMap = promptUserToDlMap
    }
    mapController = VMEMapController(reactApplicationContext, VMEMapControllerBuilder())
    Log.d("MMM", "init: ${mapController.mapPath}")
  }

  override fun loadView() {
    mapView?.let { mapController.loadMapView(it) }

  }

  override fun loadData() {
    mapController.loadMapData()
  }


  companion object {
    const val NAME = "RTNMapView"
  }
}