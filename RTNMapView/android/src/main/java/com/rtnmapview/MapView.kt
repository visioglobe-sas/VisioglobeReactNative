package com.rtnmapview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.visioglobe.visiomoveessential.VMEMapController
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder
import com.visioglobe.visiomoveessential.VMEMapView
import com.visioglobe.visiomoveessential.enums.VMERouteDestinationsOrder
import com.visioglobe.visiomoveessential.enums.VMERouteRequestType
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener
import com.visioglobe.visiomoveessential.models.VMEMapDescriptor
import com.visioglobe.visiomoveessential.models.VMERouteRequest
import java.io.File
import java.io.FileOutputStream


class MapView(context: ThemedReactContext) : FrameLayout(context) {
    private var rView: View? = null
    private var sdkInfo: WritableMap? = null
    /*private val lifecycleListener: LifecycleEventListener? = null
    private val manager: MapViewManager? = null
    private val view = this
    private val context: ThemedReactContext? = null
    private val segments: List<VMESegment> = java.util.ArrayList<VMESegment>()
    var directions: WritableMap = Arguments.createMap()
    private val coordinatesGetter: CurrentCoordinatesGetter? = null
    private val mIsPlaceMarkerDisplayed = false
    private val shouldCameraFollow = true*/
    private var mMapView : VMEMapView? = null
    lateinit var mMapController: VMEMapController
    val builder = VMEMapControllerBuilder()
    //var filePath : String = "shizuru_regular.ttf" NOT USEFUL NOW
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

  init {
      val inflater = LayoutInflater.from(context)
      rView = inflater.inflate(R.layout.map_view, this)
      mMapView = rView?.findViewById<View>(R.id.map) as VMEMapView
      builder.mapHash = "dev-m219a3bb03e5be89ce238a54e088aab2eb0d9b736"
      mMapController = VMEMapController(context,builder)
      mMapController.setLifeCycleListener(mLifeCycleListener)
      mMapController.loadMapData()
      mMapController.loadMapView(mMapView!!)
  }



    private fun extractFromAssetsAndGetFilePath(pFileName: String): String? {
        val ctx = getContext()
        if (ctx != null) {
            val f = File(getContext().cacheDir.toString() + "/" + pFileName)
            if (!f.exists()) {
                try {
                    val `is` = getContext().assets.open(pFileName)
                    val size = `is`.available()
                    val buffer = ByteArray(size)
                    `is`.read(buffer)
                    `is`.close()
                    val fos = FileOutputStream(f)
                    fos.write(buffer)
                    fos.close()
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }
            }
            return f.path
        }
        return null
    }

    fun setMapPath(value: String?) {
        if (value != null) {
            mMapController.mapPath = value
        }
    }

    fun setMapSecretCode(value: Int) {
        if (value != null) {
            builder.mapSecretCode = value
        }
    }

    fun setMapHash(value: String?) {
        if (value != null) {
            builder.mapHash = value
        }
    }

    fun setMapServerUrl(value: String?) {
        if (value != null) {
            mMapController.mapServerUrl = value
        }
    }


    /* ROUTING */
    fun routeRequest(requestType: VMERouteRequestType, destinationsOrder: VMERouteDestinationsOrder, accessible: Boolean): VMERouteRequest {
        return VMERouteRequest(requestType, destinationsOrder, accessible)
    }

    fun addDestination(poiID: String) {
        return addDestination(poiID)
    }

    fun setOrigin(poiID: String){
        return setOrigin(poiID)
    }

    //fun routeResult()



    //Helpers

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


/*    private fun constructSegmentsForm(segments: List<VMESegment>): WritableArray? {
        val arr: WritableArray = Arguments.createArray()
        for (lSegment in segments) {
            val segment: WritableMap = Arguments.createMap()
            segment.putString("maneuverType", lSegment.getManeuverType() + "")
            segment.putDouble("duration", lSegment.getDuration())
            segment.putDouble("length", lSegment.getLength())
            segment.putString("floorTransitionType", lSegment.getFloorTransitionType() + "")
            segment.putString("floorTransitionId", lSegment.getFloorTransitionId())
            val paths: WritableArray = Arguments.createArray()
            for (vmePosition in lSegment.getPath()) {
                val path: WritableMap = Arguments.createMap()
                path.putDouble("latitude", vmePosition.getLatitude())
                path.putDouble("longitude", vmePosition.getLongitude())
                path.putDouble("altitude", vmePosition.getAltitude())
                path.putString("buildingId", vmePosition.getScene().getBuildingID())
                path.putString("floorId", vmePosition.getScene().getFloorID())
            }
            segment.putArray("paths", paths)
            arr.pushMap(segment)
        }
        return arr
    }
    */

}
