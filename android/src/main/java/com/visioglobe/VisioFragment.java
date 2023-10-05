package com.visioglobe;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// replace with your view's import
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.visioglobe.visiomoveessential.VMEMapController;
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder;
import com.visioglobe.visiomoveessential.VMEMapView;
import com.visioglobe.visiomoveessential.callbacks.VMEAnimationCallback;
import com.visioglobe.visiomoveessential.callbacks.VMEComputeRouteCallback;
import com.visioglobe.visiomoveessential.enums.VMELocationTrackingMode;
import com.visioglobe.visiomoveessential.enums.VMERouteDestinationsOrder;
import com.visioglobe.visiomoveessential.enums.VMERouteRequestType;
import com.visioglobe.visiomoveessential.enums.VMEViewMode;
import com.visioglobe.visiomoveessential.listeners.VMEBuildingListener;
import com.visioglobe.visiomoveessential.listeners.VMECameraListener;
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener;
import com.visioglobe.visiomoveessential.listeners.VMELocationTrackingModeListener;
import com.visioglobe.visiomoveessential.listeners.VMEMapListener;
import com.visioglobe.visiomoveessential.listeners.VMEPoiListener;
import com.visioglobe.visiomoveessential.models.VMECameraHeading;
import com.visioglobe.visiomoveessential.models.VMECameraPitch;
import com.visioglobe.visiomoveessential.models.VMECameraUpdate;
import com.visioglobe.visiomoveessential.models.VMECameraUpdateBuilder;
import com.visioglobe.visiomoveessential.models.VMEPosition;
import com.visioglobe.visiomoveessential.models.VMERouteRequest;
import com.visioglobe.visiomoveessential.models.VMERouteResult;
import com.visioglobe.visiomoveessential.models.VMESceneContext;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import kotlin.jvm.internal.Intrinsics;

public class VisioFragment extends Fragment {

  private static final String TAG = "VisioFragment";
  private ViewGroup mFragment;

  private VMEMapController mMapController;
  private VMEMapView mMapView;

  private String mMapHash;
  private String mMapPath;
  private int mMapSecret;
  private ReadableArray mMapListeners;
  private boolean mPromptToDownload;

  private Boolean routingEnabled = false;

  public VisioFragment(String hash, String path, int secret, ReadableArray listeners, boolean promptToDownload) {
    this.mMapHash = hash;
    this.mMapPath = path;
    this.mMapSecret = secret;
    this.mMapListeners = listeners;
    this.mPromptToDownload = promptToDownload;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
    Log.d(TAG, "====> Present Visioglobe");
    Intrinsics.checkNotNullParameter(inflater, "inflater");
    Log.d("TAGTAG", "====> Inflater is set" + parent.getHeight());
    if (this.mMapView == null) {
      this.mFragment = (VMEMapView) inflater.inflate(R.layout.map_view_sample, parent, false);
      VMEMapControllerBuilder builder = new VMEMapControllerBuilder();
      if (this.mFragment == null) {
        Log.d(TAG, "====> mFragment is null");
      }
      this.mMapView = this.mFragment.findViewById(com.visioglobe.visiomoveessential.R.id.map_view);
      if (this.mMapView == null) {
        Log.d(TAG, "====> mMapView is null");
      }
      VMEMapController mController;
      try {
        mController = this.mMapController;
        Intrinsics.checkNotNull(mController);
        // mController.deleteCachedMap("asset://map_bundle_theme.zip");
      } catch (Exception var6) {
      }

      builder.setMapHash(mMapHash);
      builder.setMapSecretCode(mMapSecret);
      builder.setPromptUserToDownloadMap(mPromptToDownload);
      Context context = this.getContext();
      Intrinsics.checkNotNullExpressionValue(context, "requireContext()");
      this.mMapController = new VMEMapController(context, builder);
      mController = this.mMapController;
      Intrinsics.checkNotNull(mController);
      Log.d(TAG, "====> Set life cycle listener");
      mController.setLifeCycleListener(this.mLifeCycleListener);
      //Log.d(TAG, "====> Set life cycle listener success");
      //mController = this.mMapController;
      //Intrinsics.checkNotNull(mController);
      Log.d(TAG, "====> Set Map View");
      VMEMapView mapView = this.mMapView;
      Intrinsics.checkNotNull(mapView);
      Log.d(TAG, "====> Load map view");
      //mController.loadMapView(mapView);
      mController = this.mMapController;
      Intrinsics.checkNotNull(mController);
      Log.d(TAG, "====> Load map data");
      //mController.loadMapData();
      for (int i=0; i<mMapListeners.size(); ++i) {
        String listener = mMapListeners.getString(i);
        switch (listener) {
          case "buildingListener":
            Log.d(TAG, "====> BUILDING LISTENER");
            mController.setBuildingListener(new VMEBuildingListener() {
              @Override
              public boolean mapDidSelectBuilding(@NonNull String buildingID, @Nullable VMEPosition position) {
                return super.mapDidSelectBuilding(buildingID, position);
              }
            });
          case "cameraListener":
            Log.d(TAG, "====> CAMERA LISTENER");
            mController.setCameraListener(new VMECameraListener() {
              @Override
              public void mapCameraDidMove() {
                super.mapCameraDidMove();
              }
            });
          case "mapListener":
            Log.d(TAG, "====> MAP LISTENER");
            mController.setMapListener(new VMEMapListener() {
              @Override
              public void mapDidReceiveTapGesture(@Nullable VMEPosition position) {
                super.mapDidReceiveTapGesture(position);
              }

              @Override
              public void mapSceneDidUpdate(@Nullable VMESceneContext scene, @Nullable VMEViewMode viewMode) {
                super.mapSceneDidUpdate(scene, viewMode);
              }
            });
          case "locationtrackingmodeListener":
            Log.d(TAG, "====> LOCATION TRACKING MODE LISTENER");
            mController.setLocationTrackingModeListener(new VMELocationTrackingModeListener() {
              @Override
              public void mapDidUpdateLocationTrackingMode(@Nullable VMELocationTrackingMode locationTrackingMode) {
                super.mapDidUpdateLocationTrackingMode(locationTrackingMode);
              }
            });
          case "poiListener":
            Log.d(TAG, "====> POI LISTENER");
            mController.setPoiListener(new VMEPoiListener() {
              @Override
              public boolean mapDidSelectPoi(@Nullable String poiID, @Nullable VMEPosition position) {
                return super.mapDidSelectPoi(poiID, position);
              }
            });
          default:
            Log.d(TAG, "====> ERREUR LISTENER");
        }
      }


      }

    View view = this.mMapView;
    return view;
  }

  private final VMELifeCycleListener mLifeCycleListener = (VMELifeCycleListener)(new VMELifeCycleListener() {
    public void mapDidInitializeEngine() {
      String lFilePath = VisioFragment.this.extractFromAssetsAndGetFilePath();
      CharSequence var2 = (CharSequence)lFilePath;
      VMEMapController controller;
      if (var2.length() != 0) {
        controller = VisioFragment.this.mMapController;
        Intrinsics.checkNotNull(controller);
        controller.setMapFont(lFilePath);
      } else {
        controller = VisioFragment.this.mMapController;
        Intrinsics.checkNotNull(controller);
        controller.setMapFont("shizuru_regular.ttf");
      }
    }

    public void mapDataDidLoad(@NotNull JSONObject mapVenueInfo) {
      Log.d(TAG, "====> MAP DATA DID LOAD");
      Intrinsics.checkNotNullParameter(mapVenueInfo, "mapVenueInfo");
      super.mapDataDidLoad(mapVenueInfo);
      VisioFragment.this.mMapController.loadMapView(VisioFragment.this.mMapView);
    }

    public void mapViewDidLoad() {
      super.mapViewDidLoad();
    }

    public void mapDidGainFocus() {
      super.mapDidGainFocus();
    }
  });

  Random rand = new Random();

  // Method to generate a random color
  @ColorInt
  public int randomColor() {
    return Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
  }


  public void customFunctionToCall() {
    Log.d("REF", "====> CUSTOM FUNCTION FROM FRAGMENT");

  }

  public void setExcludedAttributes(ReadableArray value) {
    Log.d("REF", "==> SET EXCLUDED ATTRIBUTES" + (List<String>)value);
    mMapController.setExcludedAttributes((List<String>) value);
  }

  public void setExcludedModalities(ReadableArray value) {
    Log.d("REF", "==> SET EXCLUDED MODALITIES" + (List<String>)value);
    mMapController.setExcludedModalities((List<String>)value);
  }

  /**public void setLocationTrackingButtonToggleModes(ReadableArray value) {
    Log.d("REF", "==> SET LOCATION TRACKING BUTTON TOGGLE MODES");
    mMapController.setLocationTrackingButtonToggleModes(value);
  }**/

  public void setNavigationHeaderViewVisible(boolean value) {
    Log.d("REF", "==> SET NAVIGATION HEADER VIEW VISIBLE");
    mMapController.setNavigationHeaderViewVisible(value);
  }

  public void setCompassHeadingMarkerVisible(boolean value) {
    Log.d("REF", "==> SET COMPASS HEADING MARKER VISIBLE");
    mMapController.setCompassHeadingMarkerVisible(value);
  }

  public void showPoiInfo(String poiID) {
    Log.d("REF", "==> SHOW POI INFO");
    mMapController.showPoiInfo(poiID);
  }

  public void setStatisticsLog(boolean value) {
    Log.d("REF", "==> SET STATISTICS LOG");
    mMapController.setStatisticsLog(value);
  }

  public void setStatisticsLogCamera(boolean value) {
    Log.d("REF", "==> SET STATISTICS Log CAMERA");
    mMapController.setStatisticsLogCamera(value);
  }

  public void setStatisticsLogInterest(boolean value) {
    Log.d("REF", "==> SET STATISTICS LOG INTEREST");
    mMapController.setStatisticsLogInterest(value);
  }

  public void setStatisticsLogLocation(boolean value) {
    Log.d("REF", "==> SET STATISTICS LOG LOCATION");
    mMapController.setStatisticsLogLocation(value);
  }

  public void setStatisticsTrackedPoiIDs(ReadableArray value) {
    Log.d("REF", "==> SET STATISTICS TRACKED POI IDs"+ (ArrayList<String>) value);
    mMapController.setStatisticsTrackedPoiIDs((ArrayList<String>) value);
  }

  public void setCompass(boolean enabled) {
    Log.d("REF", "==> SET COMPASS");
    mMapController.setCompassHeadingMarkerVisible(enabled);
  }

  public void setLocationTrackingButtonToggleModes(ReadableArray value){
    Log.d("REF", "==> SET COMPASS");
    mMapController.setLocationTrackingButtonToggleModes((ArrayList<VMELocationTrackingMode>) value);
  }

  public void animateCamera(){}
  public void getCameraContext(){}
  public void updateCamera(){}
  public void animateScene(){}
  public void updateScene(){}
  public void createLocationFromLocation(){}
  public void createPositionFromLocation(){}
  public void getLocationTrackingMode(){}
  public void setLocationTrackingMode(){}
  public void getLocationTrackingButtonToggleModes(){}
  public void getNavigationHeaderViewVisible(){}
  public void setNavigationHeaderViewVisible(){}
  public void getSelectorViewVisible(){}
  public void removePoi(){}
  public void removePois(){}
  public void getCategory(){}
  public void getPoi(){}
  public void getPoiBoundingPositions(){}
  public void queryAllCategoryIDs(){}
  public void queryAllPoiIDs(){}
  public void queryPois(){}
  public void resetPoiColor(){}
  public void setPoiSize(){}
  public void setPoisSize(){}
  public void setPoiPosition(){}
  public void setPoisPosition(){}
  public void showPoiInfo(){}
  public void setCategories(){}
  public String getVersion(){
    Log.d("REF", "====> GET VERSION ");
    return mMapController.getDataSDKVersion();
  }

  public String getMinDataSDKVersion(){
    Log.d("REF", "====> GET MIN VERSION ");
    return mMapController.getMinDataSDKVersion();
  }

  public void setSelectorViewVisible(boolean visible){
    Log.d("REF", "====> SET SELECTOR VIEW ");
    mMapController.setSelectorViewVisible(visible);
  }

  public void unloadMapData() {
    Log.d("REF", "====> UNLOAD MAP DATA ");
    mMapController.unloadMapData();
  }

  public void unloadMapView() {
    Log.d("REF", "====> UNLOAD MAP VIEW ");
    mMapController.unloadMapView();
  }

  public void setPois(String data) {
    Log.d("REF", "====> SET POIS FROM FRAGMENT");
    mMapController.setPois(data);
  }

  public void setPoisColor(ArrayList<String> poiIDs) {
    Log.d("REF", "====> SET POIS COLORS FROM FRAGMENT");
    int randomColor = randomColor();
    HashMap<String, Integer> poiToColor = new HashMap<String, Integer>();

    for (String poiID : poiIDs) {
      poiToColor.put(poiID, randomColor);
    }
    mMapController.setPoisColor(poiToColor);
  }

  public void resetPoisColor() {
    Log.d("REF", "====> RESET POIS FROM FRAGMENT");
    var lPoiIDs = mMapController.queryAllPoiIDs();
    mMapController.resetPoisColor(lPoiIDs);
  }
  private VMEComputeRouteCallback mRouteCallback = new VMEComputeRouteCallback() {
    @Override
    public boolean computeRouteDidFinish(VMERouteRequest routeRequest, VMERouteResult routeResult) {
      String lRouteDescription = String.format(
        "computeRouteDidFinish, duration: %.0fmins and length: %.0fm",
        routeResult.getDuration() / 60,
        routeResult.getLength()
      );
      Log.i(TAG, lRouteDescription);
      routingEnabled = true;
      return true;
    }

    @Override
    public void computeRouteDidFail(VMERouteRequest routeRequest, String error) {
      String lRouteDescription = String.format("computeRouteDidFail, Error: %s", error);
      Log.i(TAG, lRouteDescription);
      routingEnabled = false;
    }
  };


  public void computeRoute(String origin, ArrayList<String> destinations, Boolean optimize) {
    VMERouteDestinationsOrder destinationsOrder = optimize ? VMERouteDestinationsOrder.OPTIMAL : VMERouteDestinationsOrder.IN_ORDER;
    VMERouteRequest routeRequest = new VMERouteRequest(VMERouteRequestType.FASTEST, destinationsOrder);
    routeRequest.setOrigin(origin);
    routeRequest.addDestinations(destinations);
    Log.d("REF", "====> COMPUTE ROUTE FROM FRAGMENT");
    mMapController.computeRoute(routeRequest, mRouteCallback);
  }

  public void getPoiPosition(String poi) {
    Log.d("REF", "====> COMPUTE ROUTE FROM FRAGMENT");
    mMapController.getPoiPosition(poi);
  }

  private final String extractFromAssetsAndGetFilePath() {
    StringBuilder var10002 = new StringBuilder();
    Context var10003 = this.getContext();
    Intrinsics.checkNotNullExpressionValue(var10003, "requireContext()");
    File f = new File(var10002.append(var10003.getCacheDir().toString()).append("/").append("artifika_regular.ttf").toString());
    if (!f.exists()) {
      try {
        Context var10000 = this.getContext();
        Intrinsics.checkNotNullExpressionValue(var10000, "requireContext()");
        InputStream var7 = var10000.getAssets().open("artifika_regular.ttf");
        Intrinsics.checkNotNullExpressionValue(var7, "requireContext().assets.…n(\"artifika_regular.ttf\")");
        InputStream is = var7;
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(buffer);
        fos.close();
      } catch (Exception var6) {
        // throw (Throwable)(new RuntimeException((Throwable)var6));
      }
    }

    String var8 = f.getPath();
    Intrinsics.checkNotNullExpressionValue(var8, "f.path");
    return var8;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // do any logic that should happen in an `onCreate` method, e.g:
    // customView.onCreate(savedInstanceState);
    mMapController.loadMapData();
  }

  @Override
  public void onPause() {
    super.onPause();
    // do any logic that should happen in an `onPause` method
    // e.g.: customView.onPause();
  }

  @Override
  public void onResume() {
    super.onResume();
    // do any logic that should happen in an `onResume` method
    // e.g.: customView.onResume();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    // do any logic that should happen in an `onDestroy` method
    // e.g.: customView.onDestroy();
  }

  public void animateCamera(ReadableMap lCameraarray, int duration) {
    VMECameraUpdateBuilder builder = new VMECameraUpdateBuilder();
    if ((lCameraarray.getMap("heading")).getBoolean("current")) {
      VMECameraHeading heading = VMECameraHeading.newCurrent();
      builder.setHeading(heading);
    }
    else {
      if ((lCameraarray.getMap("heading")).getType("heading") == ReadableType.String) {
        VMECameraHeading heading = VMECameraHeading.newPoiID((lCameraarray.getMap("heading")).getString("poiID"));
        builder.setHeading(heading);
      } else if ((lCameraarray.getMap("heading")).getType("heading") == ReadableType.Number) {
        VMECameraHeading heading = VMECameraHeading.newHeading((lCameraarray.getMap("heading")).getDouble("poiID"));
        builder.setHeading(heading);
      }
    }
    builder.setPaddingBottom(lCameraarray.getInt("paddingBottom"));
    builder.setPaddingLeft(lCameraarray.getInt("paddingLeft"));
    builder.setPaddingRight(lCameraarray.getInt("paddingRight"));
    builder.setPaddingTop(lCameraarray.getInt("paddingTop"));
    builder.setPitch(VMECameraPitch.newPitch(lCameraarray.getMap("pitch").getDouble("pitch")));
    builder.setTargets(lCameraarray.getArray("targetPOIs").toArrayList());
    if (lCameraarray.getDouble("viewMode") == 0){
      builder.setViewMode(VMEViewMode.FLOOR);
    }
    if (lCameraarray.getDouble("viewMode") == 1){
      builder.setViewMode(VMEViewMode.GLOBAL);
    }
    if (lCameraarray.getDouble("viewMode") == 2){
      builder.setViewMode(VMEViewMode.UNKNOWN);
    }
    VMECameraUpdate cameraUpdate = new VMECameraUpdate(builder);
    mMapController.animateCamera(cameraUpdate,duration, null);
  }
}
