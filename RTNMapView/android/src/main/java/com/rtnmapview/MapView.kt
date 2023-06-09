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

    //fun routeResult(

}
