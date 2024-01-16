package com.visioglobe

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableType
import com.facebook.react.bridge.WritableMap
import com.visioglobe.visiomoveessential.VMEMapController
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder
import com.visioglobe.visiomoveessential.VMEMapView
import com.visioglobe.visiomoveessential.callbacks.VMEComputeRouteCallback
import com.visioglobe.visiomoveessential.enums.VMELocationTrackingMode
import com.visioglobe.visiomoveessential.enums.VMERouteDestinationsOrder
import com.visioglobe.visiomoveessential.enums.VMERouteRequestType
import com.visioglobe.visiomoveessential.enums.VMEViewMode
import com.visioglobe.visiomoveessential.listeners.VMEBuildingListener
import com.visioglobe.visiomoveessential.listeners.VMECameraListener
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener
import com.visioglobe.visiomoveessential.listeners.VMELocationTrackingModeListener
import com.visioglobe.visiomoveessential.listeners.VMEMapListener
import com.visioglobe.visiomoveessential.listeners.VMEPoiListener
import com.visioglobe.visiomoveessential.models.VMEPoi
import com.visioglobe.visiomoveessential.models.VMEPosition
import com.visioglobe.visiomoveessential.models.VMERouteRequest
import com.visioglobe.visiomoveessential.models.VMERouteResult
import com.visioglobe.visiomoveessential.models.VMESceneContext
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.util.Random
import kotlin.jvm.internal.Intrinsics

// replace with your view's import
class VisioFragment(
    private val mMapHash: String,
    private val mMapPath: String,
    private val mMapSecret: Int,
    private val mMapListeners: ReadableArray,
    private val mPromptToDownload: Boolean
) : Fragment() {
    private var mFragment: ViewGroup? = null
    private var mMapController: VMEMapController? = null
    private var mMapView: VMEMapView? = null
    private val routingEnabled = false
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "====> Present Visioglobe")
        Intrinsics.checkNotNullParameter(inflater, "inflater")
        Log.d("TAGTAG", "====> Inflater is set" + parent!!.height)
        if (mMapView == null) {
            mFragment =
                inflater.inflate(R.layout.map_view_sample, parent, false) as VMEMapView
            val builder = VMEMapControllerBuilder()
            if (mFragment == null) {
                Log.d(TAG, "====> mFragment is null")
            }
            mMapView =
                mFragment!!.findViewById(com.visioglobe.visiomoveessential.R.id.map_view)
            if (mMapView == null) {
                Log.d(TAG, "====> mMapView is null")
            }
            var mController: VMEMapController?
            try {
                mController = mMapController
                Intrinsics.checkNotNull(mController)
                // mController.deleteCachedMap("asset://map_bundle_theme.zip");
            } catch (var6: Exception) {
            }
            builder.mapHash = mMapHash
            builder.mapSecretCode = mMapSecret
            builder.promptUserToDownloadMap = mPromptToDownload
            val context = this.context
            Intrinsics.checkNotNullExpressionValue(context, "requireContext()")
            mMapController = VMEMapController(context!!, builder)
            mController = mMapController
            Intrinsics.checkNotNull(mController)
            Log.d(TAG, "====> Set life cycle listener")
            mController!!.setLifeCycleListener(mLifeCycleListener)
            //Log.d(TAG, "====> Set life cycle listener success");
            //mController = this.mMapController;
            //Intrinsics.checkNotNull(mController);
            Log.d(TAG, "====> Set Map View")
            val mapView = mMapView
            Intrinsics.checkNotNull(mapView)
            Log.d(TAG, "====> Load map view")
            //mController.loadMapView(mapView);
            mController = mMapController
            Intrinsics.checkNotNull(mController)
            Log.d(TAG, "====> Load map data")
            //mController.loadMapData();
            for (i in 0 until mMapListeners.size()) {
                val listener = mMapListeners.getString(i)
                when (listener) {
                    "buildingListener" -> {
                        Log.d(TAG, "====> BUILDING LISTENER")
                        mController!!.setBuildingListener(object : VMEBuildingListener() {
                            override fun mapDidSelectBuilding(
                                buildingID: String,
                                position: VMEPosition?
                            ): Boolean {
                                return super.mapDidSelectBuilding(buildingID, position)
                            }
                        })
                        Log.d(TAG, "====> CAMERA LISTENER")
                        mController.setCameraListener(object : VMECameraListener() {
                            override fun mapCameraDidMove() {
                                super.mapCameraDidMove()
                            }
                        })
                        Log.d(TAG, "====> MAP LISTENER")
                        mController.setMapListener(object : VMEMapListener() {
                            override fun mapDidReceiveTapGesture(position: VMEPosition?) {
                                super.mapDidReceiveTapGesture(position)
                            }

                            override fun mapSceneDidUpdate(
                                scene: VMESceneContext?,
                                viewMode: VMEViewMode?
                            ) {
                                super.mapSceneDidUpdate(scene, viewMode)
                            }
                        })
                        Log.d(
                            TAG,
                            "====> LOCATION TRACKING MODE LISTENER"
                        )
                        mController.setLocationTrackingModeListener(object :
                            VMELocationTrackingModeListener() {
                            override fun mapDidUpdateLocationTrackingMode(locationTrackingMode: VMELocationTrackingMode?) {
                                super.mapDidUpdateLocationTrackingMode(locationTrackingMode)
                            }
                        })
                        Log.d(TAG, "====> POI LISTENER")
                        mController.setPoiListener(object : VMEPoiListener() {
                            override fun mapDidSelectPoi(
                                poiID: String?,
                                position: VMEPosition?
                            ): Boolean {
                                return super.mapDidSelectPoi(poiID, position)
                            }
                        })
                        Log.d(TAG, "====> SETTING LISTENERS")
                    }

                    "cameraListener" -> {
                        Log.d(TAG, "====> CAMERA LISTENER")
                        mController!!.setCameraListener(object : VMECameraListener() {
                            override fun mapCameraDidMove() {
                                super.mapCameraDidMove()
                            }
                        })
                        Log.d(TAG, "====> MAP LISTENER")
                        mController.setMapListener(object : VMEMapListener() {
                            override fun mapDidReceiveTapGesture(position: VMEPosition?) {
                                super.mapDidReceiveTapGesture(position)
                            }

                            override fun mapSceneDidUpdate(
                                scene: VMESceneContext?,
                                viewMode: VMEViewMode?
                            ) {
                                super.mapSceneDidUpdate(scene, viewMode)
                            }
                        })
                        Log.d(
                            TAG,
                            "====> LOCATION TRACKING MODE LISTENER"
                        )
                        mController.setLocationTrackingModeListener(object :
                            VMELocationTrackingModeListener() {
                            override fun mapDidUpdateLocationTrackingMode(locationTrackingMode: VMELocationTrackingMode?) {
                                super.mapDidUpdateLocationTrackingMode(locationTrackingMode)
                            }
                        })
                        Log.d(TAG, "====> POI LISTENER")
                        mController.setPoiListener(object : VMEPoiListener() {
                            override fun mapDidSelectPoi(
                                poiID: String?,
                                position: VMEPosition?
                            ): Boolean {
                                return super.mapDidSelectPoi(poiID, position)
                            }
                        })
                        Log.d(TAG, "====> SETTING LISTENERS")
                    }

                    "mapListener" -> {
                        Log.d(TAG, "====> MAP LISTENER")
                        mController!!.setMapListener(object : VMEMapListener() {
                            override fun mapDidReceiveTapGesture(position: VMEPosition?) {
                                super.mapDidReceiveTapGesture(position)
                            }

                            override fun mapSceneDidUpdate(
                                scene: VMESceneContext?,
                                viewMode: VMEViewMode?
                            ) {
                                super.mapSceneDidUpdate(scene, viewMode)
                            }
                        })
                        Log.d(
                            TAG,
                            "====> LOCATION TRACKING MODE LISTENER"
                        )
                        mController.setLocationTrackingModeListener(object :
                            VMELocationTrackingModeListener() {
                            override fun mapDidUpdateLocationTrackingMode(locationTrackingMode: VMELocationTrackingMode?) {
                                super.mapDidUpdateLocationTrackingMode(locationTrackingMode)
                            }
                        })
                        Log.d(TAG, "====> POI LISTENER")
                        mController.setPoiListener(object : VMEPoiListener() {
                            override fun mapDidSelectPoi(
                                poiID: String?,
                                position: VMEPosition?
                            ): Boolean {
                                return super.mapDidSelectPoi(poiID, position)
                            }
                        })
                        Log.d(TAG, "====> SETTING LISTENERS")
                    }

                    "locationtrackingmodeListener" -> {
                        Log.d(
                            TAG,
                            "====> LOCATION TRACKING MODE LISTENER"
                        )
                        mController!!.setLocationTrackingModeListener(object :
                            VMELocationTrackingModeListener() {
                            override fun mapDidUpdateLocationTrackingMode(locationTrackingMode: VMELocationTrackingMode?) {
                                super.mapDidUpdateLocationTrackingMode(locationTrackingMode)
                            }
                        })
                        Log.d(TAG, "====> POI LISTENER")
                        mController.setPoiListener(object : VMEPoiListener() {
                            override fun mapDidSelectPoi(
                                poiID: String?,
                                position: VMEPosition?
                            ): Boolean {
                                return super.mapDidSelectPoi(poiID, position)
                            }
                        })
                        Log.d(TAG, "====> SETTING LISTENERS")
                    }

                    "poiListener" -> {
                        Log.d(TAG, "====> POI LISTENER")
                        mController!!.setPoiListener(object : VMEPoiListener() {
                            override fun mapDidSelectPoi(
                                poiID: String?,
                                position: VMEPosition?
                            ): Boolean {
                                return super.mapDidSelectPoi(poiID, position)
                            }
                        })
                        Log.d(TAG, "====> SETTING LISTENERS")
                    }

                    else -> Log.d(
                        TAG,
                        "====> SETTING LISTENERS"
                    )
                }
            }
        }
        return mMapView
    }

    private val mLifeCycleListener = object : VMELifeCycleListener() {
        override fun mapDidInitializeEngine() {
            val lFilePath = extractFromAssetsAndGetFilePath()
            val var2 = lFilePath as CharSequence
            val controller: VMEMapController?
            if (var2.length != 0) {
                controller = mMapController
                Intrinsics.checkNotNull(controller)
                controller!!.setMapFont(lFilePath)
            } else {
                controller = mMapController
                Intrinsics.checkNotNull(controller)
                controller!!.setMapFont("shizuru_regular.ttf")
            }
        }

        override fun mapDataDidLoad(mapVenueInfo: JSONObject) {
            Log.d(TAG, "====> MAP DATA DID LOAD")
            Intrinsics.checkNotNullParameter(mapVenueInfo, "mapVenueInfo")
            super.mapDataDidLoad(mapVenueInfo)
            mMapController!!.loadMapView(mMapView!!)
        }

        override fun mapViewDidLoad() {
            super.mapViewDidLoad()
        }

        override fun mapDidGainFocus() {
            super.mapDidGainFocus()
        }
    } as VMELifeCycleListener
    var rand = Random()

    // Method to generate a random color
    @ColorInt
    fun randomColor(): Int {
        return Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))
    }

    fun customFunctionToCall() {
        Log.d("REF", "====> CUSTOM FUNCTION FROM FRAGMENT")
    }

    fun setExcludedAttributes(value: ReadableArray) {
        Log.d("REF", "==> SET EXCLUDED ATTRIBUTES" + value as List<String?>)
        mMapController!!.setExcludedAttributes(value as List<String>)
    }

    fun setExcludedModalities(value: ReadableArray) {
        Log.d("REF", "==> SET EXCLUDED MODALITIES" + value as List<String?>)
        mMapController!!.setExcludedModalities(value as List<String>)
    }

    /**public void setLocationTrackingButtonToggleModes(ReadableArray value) {
     * Log.d("REF", "==> SET LOCATION TRACKING BUTTON TOGGLE MODES");
     * mMapController.setLocationTrackingButtonToggleModes(value);
     * } */
    fun setNavigationHeaderViewVisible(value: Boolean) {
        Log.d("REF", "==> SET NAVIGATION HEADER VIEW VISIBLE")
        mMapController!!.navigationHeaderViewVisible = value
    }

    fun setCompassHeadingMarkerVisible(value: Boolean) {
        Log.d("REF", "==> SET COMPASS HEADING MARKER VISIBLE")
        mMapController!!.compassHeadingMarkerVisible = value
    }

    fun showPoiInfo(poiID: String?) {
        Log.d("REF", "==> SHOW POI INFO")
        mMapController!!.showPoiInfo(poiID.toString())
    }

    fun updateLocation(location: ReadableMap){
        Log.d("REF", "==> UPDATE LOCATION")
        val loc = UtilsType().readableMapToLocation(location!!)
        mMapController!!.updateLocation(loc)
    }

    fun setStatisticsLog(value: Boolean) {
        Log.d("REF", "==> SET STATISTICS LOG")
        mMapController!!.isStatisticsLog = value
    }

    fun setStatisticsLogCamera(value: Boolean) {
        Log.d("REF", "==> SET STATISTICS Log CAMERA")
        mMapController!!.isStatisticsLogCamera = value
    }

    fun setStatisticsLogInterest(value: Boolean) {
        Log.d("REF", "==> SET STATISTICS LOG INTEREST")
        mMapController!!.isStatisticsLogInterest = value
    }

    fun setStatisticsLogLocation(value: Boolean) {
        Log.d("REF", "==> SET STATISTICS LOG LOCATION")
        mMapController!!.isStatisticsLogLocation = value
    }

    /*fun setStatisticsTrackedPoiIDs(value: ReadableArray) {
        Log.d("REF", "==> SET STATISTICS TRACKED POI IDs" + value as ArrayList<String?>)
        mMapController!!.statisticsTrackedPoiIDs = value
    }*/

    fun setCompass(enabled: Boolean) {
        Log.d("REF", "==> SET COMPASS")
        mMapController!!.compassHeadingMarkerVisible = enabled
    }

    fun showSearchViewWithTitle(value: String){
        mMapController!!.showSearchViewWithTitle(value,null)

    }

    fun setLocationTrackingButtonToggleModes(value: ReadableArray?) {
        Log.d("REF", "==> SET COMPASS")
        mMapController!!.locationTrackingButtonToggleModes =
            value as ArrayList<VMELocationTrackingMode>
    }

    fun animateCamera() {}
    val cameraContext: Unit
        get() {}

    fun updateCamera(cameraupdate: ReadableMap?) {
        val update = UtilsType().readableMapToCamera(cameraupdate!!)
        Log.d("cc", "updateCamera: $update")
        mMapController!!.updateCamera(update)
    }

    fun animateScene() {}
    fun updateScene() {}
    fun createLocationFromLocation() {}
    fun createPositionFromLocation() {}
    val locationTrackingMode: Unit
        get() {}

    fun setLocationTrackingMode() {}
    val locationTrackingButtonToggleModes: Unit
        get() {}
    val navigationHeaderViewVisible: Unit
        get() {}

    fun setNavigationHeaderViewVisible() {}
    val selectorViewVisible: Unit
        get() {}

    fun removePoi() {}
    fun removePois(pois: ReadableArray): List<Boolean> {
        var i = 0
        var poisList : MutableList<String> = mutableListOf()
        while (i < pois.size()){
            poisList.add(pois.getString(i))
            i ++
        }
        return mMapController!!.removePois(poisList);
    }
    val category: Unit
        get() {}

    fun getPoi(poiID: String?): VMEPoi? {
        return mMapController!!.getPoi(poiID!!)
    }

    val poiBoundingPositions: Unit
        get() {}

    fun queryAllCategoryIDs() {}
    fun queryAllPoiIDs() {}
    fun queryPois() {}
    fun resetPoiColor() {}
    fun setPoiSize() {}
    fun setPoisSize() {}
    fun setPoiPosition() {}
    fun setPoisPosition() {}
    fun setCategories() {}

    /*@get:ReactMethod
    val version: Promise
        get() {
            Log.d("REF", "====> GET VERSION ")
            val promise: Promise = object : Promise {
                override fun resolve(o: Any?) {}
                override fun reject(s: String, s1: String) {}
                override fun reject(s: String, throwable: Throwable) {}
                override fun reject(s: String, s1: String, throwable: Throwable) {}
                override fun reject(throwable: Throwable) {}
                override fun reject(throwable: Throwable, writableMap: WritableMap) {}
                override fun reject(s: String, writableMap: WritableMap) {}
                override fun reject(s: String, throwable: Throwable, writableMap: WritableMap) {}
                override fun reject(s: String, s1: String, writableMap: WritableMap) {}
                override fun reject(
                    s: String,
                    s1: String,
                    throwable: Throwable,
                    writableMap: WritableMap
                ) {
                }

                override fun reject(s: String) {}
            }
            if (mMapController!!.dataSDKVersion is String) {
                promise.resolve(mMapController!!.dataSDKVersion)
            } else promise.reject("error on SDK version")
            return promise
        }
    val minDataSDKVersion: String
        get() {
            Log.d("REF", "====> GET MIN VERSION ")
            return mMapController!!.minDataSDKVersion
        }*/

    fun setSelectorViewVisible(visible: Boolean) {
        Log.d("REF", "====> SET SELECTOR VIEW ")
        mMapController!!.selectorViewVisible = visible
    }

    fun unloadMapData() {
        Log.d("REF", "====> UNLOAD MAP DATA ")
        mMapController!!.unloadMapData()
    }

    fun unloadMapView() {
        Log.d("REF", "====> UNLOAD MAP VIEW ")
        mMapController!!.unloadMapView()
    }

    fun loadMapView() {
        Log.d("REF", "====> RELOAD MAP VIEW ")
        mMapController!!.loadMapView(mMapView!!)
    }

    fun setPois(data: String?) {
        Log.d("REF", "====> SET POIS FROM FRAGMENT")
        mMapController!!.setPois(data!!)
    }

    fun setPoisColor(poiIDs: ArrayList<String>) {
        Log.d("REF", "====> SET POIS COLORS FROM FRAGMENT")
        val randomColor = randomColor()
        val poiToColor = HashMap<String, Int>()
        for (poiID in poiIDs) {
            poiToColor[poiID] = randomColor
        }
        mMapController!!.setPoisColor(poiToColor)
    }

    fun resetPoisColor() {
        Log.d("REF", "====> RESET POIS FROM FRAGMENT")
        val lPoiIDs = mMapController!!.queryAllPoiIDs()
        mMapController!!.resetPoisColor(lPoiIDs)
    }

    private val mRouteCallback: VMEComputeRouteCallback = object : VMEComputeRouteCallback {
        override fun computeRouteDidFinish(
            vmeRouteRequest: VMERouteRequest,
            vmeRouteResult: VMERouteResult
        ): Boolean {
            return true
        }

        override fun computeRouteDidFail(vmeRouteRequest: VMERouteRequest, s: String?) {}
    }

    //UTILS
    fun computeRoute(lRouteInfo: ReadableMap) {
        val animateAllRoute = lRouteInfo.getBoolean("animateAllRoute")
        val routeDestinationsOrderNumber = lRouteInfo.getDouble("destinationsOrder")
        var routeDestinationsOrder: VMERouteDestinationsOrder? = null
        if (routeDestinationsOrderNumber == 0.0) {
            routeDestinationsOrder = VMERouteDestinationsOrder.CLOSEST
        }
        if (routeDestinationsOrderNumber == 1.0) {
            routeDestinationsOrder = VMERouteDestinationsOrder.IN_ORDER
        }
        if (routeDestinationsOrderNumber == 2.0) {
            routeDestinationsOrder = VMERouteDestinationsOrder.OPTIMAL
        }
        if (routeDestinationsOrderNumber == 3.0) {
            routeDestinationsOrder = VMERouteDestinationsOrder.OPTIMAL_FINISH_ON_LAST
        }
        val isAccessible = lRouteInfo.getBoolean("isAccessible")
        val destinations = lRouteInfo.getArray("destinations")
        val requestTypeNumber = lRouteInfo.getDouble("requestType")
        var requestType: VMERouteRequestType? = null
        if (requestTypeNumber == 0.0) {
            requestType = VMERouteRequestType.FASTEST
        }
        if (requestTypeNumber == 1.0) {
            requestType = VMERouteRequestType.SHORTEST
        }
        assert(destinations != null)
        val destinationsArray = ArrayList<Any>()
        for (i in 0 until destinations!!.size()) {
            val element = destinations.getString(i)
            destinationsArray.add(element)
        }
        assert(requestType != null)
        assert(routeDestinationsOrder != null)
        val routeRequest = VMERouteRequest(requestType!!, routeDestinationsOrder!!, isAccessible)
        if (lRouteInfo.getType("origin") == ReadableType.String) {
            var origin: String = lRouteInfo.getString("origin").toString()
            routeRequest.setOrigin(origin);
        } else {
            var scene = VMESceneContext()
            if (lRouteInfo.getMap("origin")!!.toHashMap().containsKey("scene")) {
                scene = VMESceneContext(
                    lRouteInfo.getMap("origin")!!.getMap("scene")!!.getString("buildingID")!!,
                    lRouteInfo.getMap("origin")!!.getMap("scene")!!.getString("FloorID")!!
                )
            }
            var origin: VMEPosition = VMEPosition(
                lRouteInfo.getMap("origin")!!.getDouble("latitude"),
                lRouteInfo.getMap("origin")!!.getDouble("longitude"),
                lRouteInfo.getMap("origin")!!
                    .getDouble("altitude"),
                scene
            )
            routeRequest.setOrigin(origin);
        }
        routeRequest.addDestinations(destinationsArray)
        mMapController!!.computeRoute(routeRequest, mRouteCallback)
    }

    fun getPoiPosition(poi: String?) {
        Log.d("REF", "====> COMPUTE ROUTE FROM FRAGMENT")
        mMapController!!.getPoiPosition(poi!!)
    }

    private fun extractFromAssetsAndGetFilePath(): String {
        val var10002 = StringBuilder()
        val var10003 = this.context
        Intrinsics.checkNotNullExpressionValue(var10003, "requireContext()")
        val f = File(
            var10002.append(var10003!!.cacheDir.toString()).append("/")
                .append("artifika_regular.ttf").toString()
        )
        if (!f.exists()) {
            try {
                val var10000 = this.context
                Intrinsics.checkNotNullExpressionValue(
                    var10000,
                    "requireContext()"
                )
                val var7 = var10000!!.assets.open("artifika_regular.ttf")
                Intrinsics.checkNotNullExpressionValue(
                    var7,
                    "requireContext().assets.…n(\"artifika_regular.ttf\")"
                )
                val size = var7.available()
                val buffer = ByteArray(size)
                var7.read(buffer)
                var7.close()
                val fos = FileOutputStream(f)
                fos.write(buffer)
                fos.close()
            } catch (var6: Exception) {
                // throw (Throwable)(new RuntimeException((Throwable)var6));
            }
        }
        val var8 = f.path
        Intrinsics.checkNotNullExpressionValue(var8, "f.path")
        return var8
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // do any logic that should happen in an `onCreate` method, e.g:
        // customView.onCreate(savedInstanceState);
        mMapController!!.loadMapData()
    }

    override fun onPause() {
        super.onPause()
        // do any logic that should happen in an `onPause` method
        // e.g.: customView.onPause();
    }

    override fun onResume() {
        super.onResume()
        // do any logic that should happen in an `onResume` method
        // e.g.: customView.onResume();
    }

    override fun onDestroy() {
        super.onDestroy()
        // do any logic that should happen in an `onDestroy` method
        // e.g.: customView.onDestroy();
    }

    fun animateCamera(lCameraarray: ReadableMap?, duration: Int) {
        val cameraUpdate = UtilsType().readableMapToCamera(lCameraarray!!)
        mMapController!!.animateCamera(cameraUpdate, duration.toFloat(), null)
    }

    companion object {
        private const val TAG = "VisioFragment"
    }
}