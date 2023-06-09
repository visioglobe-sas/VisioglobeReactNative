package com.rtnmapview

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

class MapViewPackage : ReactPackage {
  override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> =
    listOf(MapViewManager(reactContext))
    
  override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> =
    emptyList()
}