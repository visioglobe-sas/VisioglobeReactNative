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
    var mMapView : VMEMapView? = null
    //var filePath : String = "shizuru_regular.ttf" NOT USEFUL NOW

  init {
      val inflater = LayoutInflater.from(context)
      rView = inflater.inflate(R.layout.map_view, this)
      mMapView = rView?.findViewById<View>(R.id.map) as VMEMapView
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
