package com.rtnmapview

import android.view.LayoutInflater
import android.widget.FrameLayout
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.uimanager.ThemedReactContext
import com.visioglobe.visiomoveessential.VMEMapController
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder
import com.visioglobe.visiomoveessential.VMEMapView
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener
import java.io.File
import java.io.FileOutputStream
import java.util.ArrayList




class MapView(context: ThemedReactContext, mapViewManager: MapViewManager) : FrameLayout(context) {
    /*private val lifecycleListener: LifecycleEventListener? = null
    private val manager: MapViewManager? = null
    private val view = this
    private val context: ThemedReactContext? = null
    private val segments: List<VMESegment> = java.util.ArrayList<VMESegment>()
    var directions: WritableMap = Arguments.createMap()
    private val coordinatesGetter: CurrentCoordinatesGetter? = null
    private val mIsPlaceMarkerDisplayed = false
    private val shouldCameraFollow = true*/
  var mMapView : VMEMapView? = null
  var mMapController : VMEMapController = VMEMapController(context, VMEMapControllerBuilder())
  var manager : MapViewManager? = mapViewManager
  var filePath : String = "shizuru_regular.ttf"
  private val context: ThemedReactContext? = null
  private val mLifeCycleListener: VMELifeCycleListener = object : VMELifeCycleListener() {
        fun mapDidInitializeEngine(mapView: VMEMapView?) {
            val lFilePath: String? = extractFromAssetsAndGetFilePath(filePath)
            if (lFilePath != null) {
                mMapController.setMapFont(lFilePath)
            }
        }

        fun mapDidGainFocus(mapView: VMEMapView?) {}
        fun mapDidLoad(mapView: VMEMapView?) {
            var lifecycleListener = object : LifecycleEventListener {
                override fun onHostResume() {
                    mMapController.onResume()
                }

                override fun onHostPause() {
                    mMapController.onPause()
                }

                override fun onHostDestroy() {
                    mMapController.unloadMapData()
                    mMapController.unloadMapView()
                }
            }
            context.addLifecycleEventListener(lifecycleListener)
            //val args: WritableMap = extractMapInfo()
            //manager.pushEvent(context, view, "onMapDidLoad", args)
        }

      //fun mapDidDisplayRoute(mapView: VMEMapView?, routeResult: VMERouteResult) {
          //val args: WritableMap = Arguments.createMap()
          //segments = routeResult.getSegments()
          //args.putArray("route", constructSegmentsForm(routeResult.getSegments()))
          //manager.pushEvent(context, view, "onDidDisplayRoute", args)
      //}
    }

  init {
      val inflater = LayoutInflater.from(context)
      mMapView = inflater.inflate(R.layout.map_view, this, true) as VMEMapView?
      mMapView?.let { mMapController!!.loadMapData() }
      mMapController.setLifeCycleListener(mLifeCycleListener)
      mMapView?.let { mMapController!!.loadMapView(it) }
      this.addView(mMapView)
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
            mMapController.mapSecretCode = value
        }
    }

    fun setMapHash(value: String?) {
        if (value != null) {
            mMapController.mapHash = value
        }
    }

    fun setMapServerUrl(value: String?) {
        if (value != null) {
            mMapController.mapServerUrl = value
        }
    }

    //Helpers

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
                paths.pushMap(path)
            }
            segment.putArray("paths", paths)
            arr.pushMap(segment)
        }
        return arr
    }

    private WritableMap extractMapInfo() {
        VMEMapDescriptor vmeMapDescriptor = mMapView.getCachedMapDescriptor(mMapView.getMapHash());
        if (vmeMapDescriptor == null) return Arguments.createMap();
        HashMap<String,String> data = new HashMap<>();
        data.put("Version", VMEMapView.getVersion());
        data.put("MinDataSDKVersion", VMEMapView.getMinDataSDKVersion());
        data.put("DataSDKVersion", VMEMapView.getDataSDKVersion());
        data.put("MapId", vmeMapDescriptor.getID());
        data.put("MapName", vmeMapDescriptor.getName());
        data.put("MapSDKType", vmeMapDescriptor.getSDKType());
        data.put("MapSDKVersion", vmeMapDescriptor.getSDKVersion() );
        data.put("MapSDKMinVersion", vmeMapDescriptor.getSDKMinVersion());
        data.put("Target", vmeMapDescriptor.getTarget());
        data.put("ZipFile", vmeMapDescriptor.getZipFile());
        data.put("SecretCode", vmeMapDescriptor.getSecretCode() + "");
        data.put("CustomDataHash", vmeMapDescriptor.getCustomDataHash());
        data.put("Timestamp", vmeMapDescriptor.getTimestamp() + "");
        data.put("ExpiryDate",vmeMapDescriptor.getExpiryDate());
        WritableMap map = Arguments.createMap();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            map.putString(entry.getKey(), entry.getValue());
        }
        WritableMap mapInfo = Arguments.createMap();
        mapInfo.putMap("MapInfo", map);
        return mapInfo;
  }
 */

}
