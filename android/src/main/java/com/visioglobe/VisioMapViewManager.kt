package com.visioglobe

import android.os.Build
import android.util.Log
import android.view.Choreographer
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableType
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.UIManagerModule
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.uimanager.events.EventDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VisioMapViewManager(var reactContext: ReactApplicationContext) :
    ViewGroupManager<FrameLayout>() {
    val COMMAND_CREATE = 1
    val COMMAND_SET_POIS = 2
    val COMMAND_RESET_POIS_COLOR = 3
    val COMMAND_SET_POIS_COLOR = 4
    val COMMAND_COMPUTE_ROUTE = 5
    val COMMAND_GET_POI_POSITION = 6
    val COMMAND_GET_VERSION = 7
    val COMMAND_ANIMATE_CAMERA = 8
    val COMMAND_GET_CAMERA_CONTEXT = 9
    val COMMAND_UPDATE_CAMERA = 10
    val COMMAND_ANIMATE_SCENE = 11
    val COMMAND_UPDATE_SCENE = 12
    val COMMAND_CREATE_LOCATION_FROM_LOCATION = 13
    val COMMAND_CREATE_POSITION_FROM_LOCATION = 14
    val COMMAND_GET_LOCATION_TRACKING_MODE = 15
    val COMMAND_SET_LOCATION_TRACKING_MODE = 16
    val COMMAND_GET_LOCATION_TRACKING_BUTTON_TOGGLE_MODES = 17
    val COMMAND_SET_LOCATION_TRACKING_BUTTON_TOGGLE_MODES = 18
    val COMMAND_GET_NAVIGATION_HEADER_VIEW_VISIBLE = 19
    val COMMAND_SET_NAVIGATION_HEADER_VIEW_VISIBLE = 20
    val COMMAND_GET_SELECTOR_VIEW_VISIBLE = 21
    val COMMAND_REMOVE_POI = 22
    val COMMAND_REMOVE_POIS = 23
    val COMMAND_GET_CATEGORY = 24
    val COMMAND_GET_POI = 25
    val COMMAND_GET_POI_BOUNDING_POSITIONS = 26
    val COMMAND_QUERY_ALL_CATEGORY_IDS = 27
    val COMMAND_QUERY_ALL_POI_IDS = 28
    val COMMAND_QUERY_POIS = 29
    val COMMAND_RESET_POI_COLOR = 30
    val COMMAND_SET_POI_SIZE = 31
    val COMMAND_SET_POIS_SIZE = 32
    val COMMAND_SET_POI_POSITION = 33
    val COMMAND_SET_POIS_POSITION = 34
    private val COMMAND_SHOW_POI_INFO = 35
    private val COMMAND_SET_CATEGORIES = 36
    private val COMMAND_UNLOAD_MAP_DATA = 37
    private val COMMAND_UNLOAD_MAP_VIEW = 38
    private val COMMAND_LOAD_MAP_VIEW = 39
    private val COMMAND_SHOW_SEARCH_VIEW_WITH_TITLE = 40
    private var reactNativeViewId = 0
    private val propWidth = 0
    private val propHeight = 0
    private var propMapHash: String? = null
    private var propMapPath: String? = null
    private var propSecret = 0
    private var propListeners: ReadableArray? = null
    private var promptToDownload = false
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    override fun getName(): String {
        return REACT_CLASS
    }

    /**
     * Return a FrameLayout which will later hold the Fragment
     */
    public override fun createViewInstance(reactContext: ThemedReactContext): FrameLayout {
        return FrameLayout(reactContext)
    }

    /**
     * Map the "create" command to an integer
     */
    override fun getCommandsMap(): Map<String, Int>? {
        val commands: MutableMap<String, Int> = HashMap()
        commands["create"] = COMMAND_CREATE
        commands["setPois"] = COMMAND_SET_POIS
        commands["resetPoisColor"] = COMMAND_RESET_POIS_COLOR
        commands["setPoisColor"] = COMMAND_SET_POIS_COLOR
        commands["computeRoute"] = COMMAND_COMPUTE_ROUTE
        commands["getPoiPosition"] = COMMAND_GET_POI_POSITION
        commands["getVersion"] = COMMAND_GET_VERSION
        commands["animateCamera"] = COMMAND_ANIMATE_CAMERA
        commands["getCameraContext"] = COMMAND_GET_CAMERA_CONTEXT
        commands["updateCamera"] = COMMAND_UPDATE_CAMERA
        commands["animateScene"] = COMMAND_ANIMATE_SCENE
        commands["updateScene"] = COMMAND_UPDATE_SCENE
        commands["createLocationFromLocation"] = COMMAND_CREATE_LOCATION_FROM_LOCATION
        commands["createPositionFromLocation"] = COMMAND_CREATE_POSITION_FROM_LOCATION
        commands["getLocationTrackingMode"] = COMMAND_GET_LOCATION_TRACKING_MODE
        commands["setLocationTrackingMode"] = COMMAND_SET_LOCATION_TRACKING_MODE
        commands["getLocationTrackingButtonToggleModes"] =
            COMMAND_GET_LOCATION_TRACKING_BUTTON_TOGGLE_MODES
        commands["setLocationTrackingButtonToggleModes"] =
            COMMAND_SET_LOCATION_TRACKING_BUTTON_TOGGLE_MODES
        commands["getNavigationHeaderViewVisible"] = COMMAND_GET_NAVIGATION_HEADER_VIEW_VISIBLE
        commands["setNavigationHeaderViewVisible"] = COMMAND_SET_NAVIGATION_HEADER_VIEW_VISIBLE
        commands["getSelectorViewVisible"] = COMMAND_GET_SELECTOR_VIEW_VISIBLE
        commands["removePoi"] = COMMAND_REMOVE_POI
        commands["removePois"] = COMMAND_REMOVE_POIS
        commands["getCategory"] = COMMAND_GET_CATEGORY
        commands["getPoi"] = COMMAND_GET_POI
        commands["getPoiBoundingPositions"] = COMMAND_GET_POI_BOUNDING_POSITIONS
        commands["queryAllCategoryIDs"] = COMMAND_QUERY_ALL_CATEGORY_IDS
        commands["queryAllPoiIDs"] = COMMAND_QUERY_ALL_POI_IDS
        commands["queryPois"] = COMMAND_QUERY_POIS
        commands["resetPoiColor"] = COMMAND_RESET_POI_COLOR
        commands["setPoiSize"] = COMMAND_SET_POI_SIZE
        commands["setPoisSize"] = COMMAND_SET_POIS_SIZE
        commands["setPoiPosition"] = COMMAND_SET_POI_POSITION
        commands["setPoisPosition"] = COMMAND_SET_POIS_POSITION
        commands["showPoiInfo"] = COMMAND_SHOW_POI_INFO
        commands["setCategories"] = COMMAND_SET_CATEGORIES
        commands["unloadMapData"] = COMMAND_UNLOAD_MAP_DATA
        commands["unloadMapView"] = COMMAND_UNLOAD_MAP_VIEW
        commands["loadMapView"] = COMMAND_LOAD_MAP_VIEW
        commands["showSearchViewWithTitle"] = COMMAND_SHOW_SEARCH_VIEW_WITH_TITLE
        return commands
    }

    override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Map<String, String>>? {
        return MapBuilder.of<String, Map<String, String>>(
            VisioGetReturnedEvent.EVENT_NAME,
            MapBuilder.of("registrationName", "onDataReturned"),
            VisioMapLoadedEvent.EVENT_NAME,
            MapBuilder.of("registrationName","onMapLoaded")
        )
    }

    /**
     * Handle "create" command (called from JS) and call createFragment method
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun receiveCommand(
        root: FrameLayout,
        commandId: String,
        args: ReadableArray?
    ) {
        Log.d("COMMAND", "NEW COMMAND RECEIVED")
        Log.d("COMMAND", commandId)
        Log.d("COMMAND", args.toString())
        super.receiveCommand(root, commandId, args)
        // int commandIdInt = Integer.parseInt(commandId);
        val context = reactContext
        val activity = reactContext.currentActivity as FragmentActivity?
        val eventDispatcher = context.getNativeModule(
            UIManagerModule::class.java
        )!!.eventDispatcher as EventDispatcher
        val myFragment =
            activity!!.supportFragmentManager.findFragmentById(reactNativeViewId) as VisioFragment?
        when (commandId) {
            "1" -> {
                reactNativeViewId = args!!.getInt(0)
                createFragment(root, reactNativeViewId, coroutineScope, eventDispatcher)
            }

            "setPois" -> {
                val data = args!!.getString(0)
                myFragment!!.setPois(data)
            }

            "setPoisColor" -> {
                val poiIDs = args!!.getArray(0)
                val poiIDList = ArrayList<String>()
                var i = 0
                while (i < poiIDs.size()) {
                    if (poiIDs.getType(i) == ReadableType.String) {
                        poiIDList.add(poiIDs.getString(i))
                    }
                    i++
                }
                myFragment!!.setPoisColor(poiIDList)
            }

            "resetPoisColor" -> myFragment!!.resetPoisColor()
            "computeRoute" -> {
                val routeInfo = args!!.getMap(0)
                myFragment!!.computeRoute(routeInfo)
            }

            "getPoiPosition" -> {
                val poi = args!!.getString(0)
                myFragment!!.getPoiPosition(poi)
            }

            "setSelectorViewVisible" -> {
                val visible = args!!.getBoolean(0)
                myFragment!!.setSelectorViewVisible(visible)
            }

            /*"getVersion" -> {
                val requestId = args!!.getDouble(0)
                myFragment!!.version
            }

            "getMinDataSDKVersion" -> myFragment!!.minDataSDKVersion*/

            "unloadMapData" -> myFragment!!.unloadMapData()

            "unloadMapView" -> myFragment!!.unloadMapView()

            "loadMapView" -> myFragment!!.loadMapView()

            "setExcludedAttributes" -> {
                val excluded = args!!.getArray(0)
                myFragment!!.setExcludedAttributes(excluded)
            }

            "setExcludedModalities" -> {
                val excludedMod = args!!.getArray(0)
                myFragment!!.setExcludedModalities(excludedMod)
            }

            "setLocationTrackingButtonToggleModes" -> {
                val locationtracking = args!!.getArray(0)
                myFragment!!.setLocationTrackingButtonToggleModes(locationtracking)
            }

            "setNavigationHeaderViewVisible" -> {
                val navviewbool = args!!.getBoolean(0)
                myFragment!!.setNavigationHeaderViewVisible(navviewbool)
            }

            "setCompassHeadingMarkerVisible" -> {
                val compassmarkerbool = args!!.getBoolean(0)
                myFragment!!.setCompassHeadingMarkerVisible(compassmarkerbool)
            }

            "showPoiInfo" -> {
                val poiid = args!!.getString(0)
                myFragment!!.showPoiInfo(poiid)
            }

            "setStatisticsLog" -> {
                val statlogbool = args!!.getBoolean(0)
                myFragment!!.setStatisticsLog(statlogbool)
            }

            "setStatisticsLogCamera" -> {
                val camlogbool = args!!.getBoolean(0)
                myFragment!!.setStatisticsLogCamera(camlogbool)
            }

            "setStatisticsLogInterest" -> {
                val interestlogbool = args!!.getBoolean(0)
                myFragment!!.setStatisticsLogInterest(interestlogbool)
            }

            "setStatisticsLogLocation" -> {
                val statloglogbool = args!!.getBoolean(0)
                myFragment!!.setStatisticsLogLocation(statloglogbool)
            }

            /*"setStatisticsTrackedPoiIDs" -> {
                val statpoiids = args!!.getArray(0)
                myFragment!!.setStatisticsTrackedPoiIDs(statpoiids)
            }*/

            "setCompass" -> {
                val compassbool = args!!.getBoolean(0)
                myFragment!!.setCompass(compassbool)
            }

            "animateCamera" -> {
                val cameraarray = args!!.getMap(0)
                val duration = args.getInt(1)
                myFragment!!.animateCamera(cameraarray, duration)
                val cameraupdate = args.getMap(0)
                myFragment.updateCamera(cameraupdate)
                val poiId = args.getString(0)
                myFragment.getPoi(poiId)
                run {}
            }

            "updateCamera" -> {
                val cameraupdate = args!!.getMap(0)
                myFragment!!.updateCamera(cameraupdate)
                val poiId = args.getString(0)
                myFragment.getPoi(poiId)
                run {}
            }

            "getPoi" -> {
                val poiId = args?.getString(1);
                val result = myFragment!!.getPoi(poiId)?.let { UtilsType().vMEPoiToVMPoi(it) };
                val requestId = args?.getInt(0);
                //Log.d("CC", "receiveCommand:$result")
                coroutineScope.launch {
                    launch(Dispatchers.Main) {
                        eventDispatcher.dispatchEvent(requestId?.let {
                            VisioGetReturnedEvent(
                                reactNativeViewId,
                                it, "getPoi $result"
                            )
                        })
                    }
                }
                run {}
            }

            "removePois" -> {
                val pois = args!!.getArray(0)
                myFragment!!.removePois(pois)

            }

            "updateLocation" -> {
                val location = args?.getMap(0);
                if (location != null) {
                    myFragment!!.updateLocation(location)
                }
            }

            "showSearchViewWithTitle" -> {
                val title = args?.getString(0);
                if (title != null) {
                    myFragment!!.showSearchViewWithTitle(title)
                }
            }

            else -> {}
        }
    }

    /*
  @ReactPropGroup(names = {"width", "height"}, customType = "Style")
  public void setStyle(FrameLayout view, int index, Integer value) {
    if (index == 0) {
      propWidth = value;
    }

    if (index == 1) {
      propHeight = value;
    }
  }
  */
    @ReactProp(name = "mapHash")
    fun setMapHash(view: FrameLayout?, value: String?) {
        propMapHash = value
    }

    @ReactProp(name = "mapPath")
    fun setMapPath(view: FrameLayout?, value: String?) {
        propMapPath = value
    }

    @ReactProp(name = "mapSecret")
    fun setMapSecret(view: FrameLayout?, value: Int) {
        propSecret = value
    }

    @ReactProp(name = "listeners")
    fun setListeners(view: FrameLayout?, values: ReadableArray?) {
        propListeners = values
    }

    @ReactProp(name = "promptToDownload")
    fun setPromptToDownload(view: FrameLayout?, value: Boolean) {
        promptToDownload = value
    }

    /**
     * Replace your React Native view with a custom fragment
     */
    fun createFragment(root: FrameLayout, reactNativeViewId: Int, coroutineScope: CoroutineScope, eventDispatcher : EventDispatcher) {
        val parentView = root.findViewById<View>(reactNativeViewId) as ViewGroup
        setupLayout(parentView)
        Log.d("VisioMapViewManager", "====> CALLED")
        val myFragment =
            propMapHash?.let { propMapPath?.let { it1 ->
                propListeners?.let { it2 ->
                    VisioFragment(it,
                        it1, propSecret, it2, promptToDownload,coroutineScope, eventDispatcher, reactNativeViewId)
                }
            } }
        val activity = reactContext.currentActivity as FragmentActivity?
        if (myFragment != null) {
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(reactNativeViewId, myFragment, reactNativeViewId.toString())
                .commit()
        }
    }

    fun setupLayout(view: View) {
        Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {
            override fun doFrame(frameTimeNanos: Long) {
                manuallyLayoutChildren(view)
                view.viewTreeObserver.dispatchOnGlobalLayout()
                Choreographer.getInstance().postFrameCallback(this)
            }
        })
    }

    /**
     * Layout all children properly
     */
    fun manuallyLayoutChildren(view: View) {
        // propWidth and propHeight coming from react-native props
        //int width = propWidth;
        //int height = propHeight;
        val width = view.width
        val height = view.height
        view.measure(
            View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        )
        view.layout(0, 0, width, height)
    }

    companion object {
        const val REACT_CLASS = "VisioMapViewManager"
    }
}