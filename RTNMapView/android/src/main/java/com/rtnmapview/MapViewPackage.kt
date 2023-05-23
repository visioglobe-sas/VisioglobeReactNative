package com.rtnmapview

import com.facebook.react.TurboReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider

class MapViewPackage : TurboReactPackage() {
  override fun getModule(name: String?, reactContext: ReactApplicationContext): NativeModule? =
   if (name == MapViewModule.NAME) {
     MapViewModule(reactContext)
   } else {
     null
   }

  override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
   mapOf(
     MapViewModule.NAME to ReactModuleInfo(
       MapViewModule.NAME,
       MapViewModule.NAME,
       false, // canOverrideExistingModule
       false, // needsEagerInit
       true, // hasConstants
       false, // isCxxModule
       true // isTurboModule
     )
   )
 }

}