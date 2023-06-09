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

class MapView(context: ThemedReactContext, mapViewManager: MapViewManager) : FrameLayout(context) {
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
        }
    }

  init {
      val inflater = LayoutInflater.from(context)
      mMapView = inflater.inflate(R.layout.map_view, this, true) as VMEMapView?
      mMapView?.let { mMapController!!.loadMapData() }
      mMapController.setLifeCycleListener(mLifeCycleListener)
      mMapView?.let { mMapController!!.loadMapView(it) }
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

  //constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
  //}
}
