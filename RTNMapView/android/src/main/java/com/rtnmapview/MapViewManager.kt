package com.rtnmapview

import android.util.Log
import android.view.Choreographer
import android.view.View
import com.facebook.react.bridge.*
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.uimanager.annotations.ReactPropGroup
import com.facebook.react.viewmanagers.RTNMapViewManagerDelegate
import com.facebook.react.viewmanagers.RTNMapViewManagerInterface
import com.visioglobe.visiomoveessential.VMEMapController
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder
import com.visioglobe.visiomoveessential.enums.VMERouteDestinationsOrder
import com.visioglobe.visiomoveessential.enums.VMERouteRequestType
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener
import com.visioglobe.visiomoveessential.models.VMEMapDescriptor


@ReactModule(name = MapViewManager.NAME)
class MapViewManager (context: ReactApplicationContext) : SimpleViewManager<MapView>(),
    RTNMapViewManagerInterface<MapView> {

    var contexte =  context
    private lateinit var mMapController: VMEMapController
    val builder = VMEMapControllerBuilder()

    private val delegate = RTNMapViewManagerDelegate(this)
    override fun getName(): String {
        return NAME
    }

    override fun getDelegate(): ViewManagerDelegate<MapView> = delegate

    override fun createViewInstance(p0: ThemedReactContext): MapView {
        mMapController = VMEMapController(p0, builder)
        return MapView(context = p0)
    }

    companion object {
        const val NAME = "RTNMapView"
        private const val LOAD_VIEW = 1
        private const val LOAD_DATA = 2
    }

    @ReactProp(name = "filePath")
    override fun setFilePath(view: MapView?, value: String?) {
        if (value != null) {
        }

    }

    @ReactProp(name = "mapPath")
    override fun setMapPath(view: MapView?, value: String?) {
        if (value != null) {
            builder.mapPath = value
        }
    }

    @ReactProp(name = "mapSecretCode")
    override fun setMapSecretCode(view: MapView?, value: Int) {
        if (value != null) {
            builder.mapSecretCode = value
        }
    }

    @ReactProp(name = "mapHash")
    override fun setMapHash(view: MapView?, value: String?) {
        if (value != null) {
            builder.mapHash = value
        }
    }

    @ReactProp(name = "mapServerUrl")
    override fun setMapServerUrl(view: MapView?, value: String?) {
        if (value != null) {
            builder.mapServerURL = value
        }
    }

    @ReactProp(name = "style")
    fun setstyle(view: MapView?, value: String?) {
        Log.d("ccstring", "setstyle: $value")

    }

    private val mLifeCycleListener: VMELifeCycleListener = object : VMELifeCycleListener() {
        override fun mapDidInitializeEngine() {
            super.mapDidInitializeEngine()
        }

        override fun mapDataDidLoad() {
            super.mapDataDidLoad()
            //mMapController.loadMapView(mMapView!!)
        }

        override fun mapViewDidLoad() {
            super.mapViewDidLoad()
        }

        override fun mapDidGainFocus() {
            super.mapDidGainFocus()
        }
    }

    private fun loadMapView(mapView: MapView){
        Log.d("ici", "loadMapView: tni tempa ")
        mMapController.setLifeCycleListener(mLifeCycleListener)
        mMapController.loadMapView(mapView.mMapView!!)
    }

    private fun loadMapData(){
        mMapController = VMEMapController(contexte, builder)
        mMapController.loadMapData()
    }

    private fun unloadMapData() {
        mMapController.unloadMapData()
    }

    private fun unloadMapView() {
        mMapController.unloadMapView()
    }


    override fun getCommandsMap() = mapOf("loadMapView" to LOAD_VIEW, "loadMapData" to LOAD_DATA)

    override fun receiveCommand(
        view: MapView, commandId: String?, args: ReadableArray?
    ) {
        //super.receiveCommand(view, commandId, args)
        when (commandId) {
            "loadMapView" -> {
                Log.d("cc2", "receiveCommand: v2 ")
                loadMapView(view)
            }
            "loadMapData" -> {
                Log.d("cc2", "receiveCommand: v3 ")
                loadMapData()
            }
            "unloadMapData" -> {
                Log.d("cc2", "receiveCommand: v4 ")
                unloadMapData()
            }
            "unloadMapView" -> {
                Log.d("cc2", "receiveCommand: v5 ")
                unloadMapView()
            }
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


    private fun extractMapInfo(): WritableMap {
        val vmeMapDescriptor: VMEMapDescriptor = mMapController.getCachedMapDescriptor(
            mMapController.mapHash
        )
            ?: return Arguments.createMap()
        val data: HashMap<String, String> = HashMap()
        data["version"] = "VMEssential2-Beta3"
        data["MinDataSDKVersion"] = mMapController.minDataSDKVersion
        data["DataSDKVersion"] = mMapController.dataSDKVersion
        data["MapId"] = vmeMapDescriptor.id
        data["MapName"] = vmeMapDescriptor.name
        data["MapSDKType"] = vmeMapDescriptor.sDKType
        data["MapSDKVersion"] = vmeMapDescriptor.sdkVersion
        data["MapSDKMinVersion"] = vmeMapDescriptor.sDKMinVersion
        data["Target"] = vmeMapDescriptor.target
        data["ZipFile"] = vmeMapDescriptor.zipFile
        data["SecretCode"] = vmeMapDescriptor.secretCode.toString()
        data["CustomDataHash"] = vmeMapDescriptor.mCustomDataHash
        data["Timestamp"] = vmeMapDescriptor.timestamp.toString()
        data["ExpiryDate"] = vmeMapDescriptor.expiryDate
        val map: WritableMap = Arguments.createMap()
        for (entry in data) {
            map.putString(entry.key, entry.value)
        }
        val mapInfo: WritableMap = Arguments.createMap()
        mapInfo.putMap("MapInfo", map)
        return mapInfo
    }
}