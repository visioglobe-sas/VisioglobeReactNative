package com.visioglobe;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.fragment.app.Fragment;

// replace with your view's import
import com.visioglobe.visiomoveessential.VMEMapController;
import com.visioglobe.visiomoveessential.VMEMapControllerBuilder;
import com.visioglobe.visiomoveessential.VMEMapView;
import com.visioglobe.visiomoveessential.callbacks.VMEComputeRouteCallback;
import com.visioglobe.visiomoveessential.enums.VMERouteDestinationsOrder;
import com.visioglobe.visiomoveessential.enums.VMERouteRequestType;
import com.visioglobe.visiomoveessential.listeners.VMELifeCycleListener;
import com.visioglobe.visiomoveessential.models.VMERouteRequest;
import com.visioglobe.visiomoveessential.models.VMERouteResult;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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

  private Boolean routingEnabled = false;

  public VisioFragment(String hash, String path, int secret) {
    this.mMapHash = hash;
    this.mMapPath = path;
    this.mMapSecret = secret;
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
      mController.loadMapView(mapView);
      mController = this.mMapController;
      Intrinsics.checkNotNull(mController);
      Log.d(TAG, "====> Load map data");
      mController.loadMapData();
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
  public void setLocationTrackingButtonToggleModes(){}
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
}
