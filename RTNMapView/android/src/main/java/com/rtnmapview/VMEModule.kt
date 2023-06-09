
package com.reactlibrary;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.bridge.Dynamic;

import com.visioglobe.visiomoveessential.VMEMapView;
import com.visioglobe.visiomoveessential.models.VMESegment;
import com.visioglobe.visiomoveessential.models.VMEPosition;
import com.visioglobe.visiomoveessential.models.VMESceneContext;

import android.util.Log;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VMEModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public VMEModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "VMEModule";
    }

    @ReactMethod
    public void computeRoute(final int tag,final ReadableMap info, final ReadableMap options, final Promise promise) {
        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);

        if (info.hasKey("origin") == false || info.hasKey("destinations") == false) {
            promise.reject("You should provide the origin");
            return;
        }
        boolean drawRouteOnMap = options.hasKey("drawRouteOnMap") ? options.getBoolean("drawRouteOnMap") : true;
        boolean accessibility = options.hasKey("accessibility") ? options.getBoolean("accessibility") : false;
        final boolean walkTimeService = options.hasKey("_walkTime") ? true : false;
        final boolean showHeaderNavigation = options.hasKey("showHeaderNavigation") ? options.getBoolean("showHeaderNavigation") : true;
        uiManager.addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nvhm) {
                MapView view = (MapView) nvhm.resolveView(tag);
                if (view == null) {
                    promise.reject("VMEMapView component not found");
                    return;
                }
                if (view.mMapView == null) {
                    promise.reject("map view instance is not valid");
                    return;
                }
                if (walkTimeService == false) {
                    view.mMapView.setNavigationHeaderViewVisible(showHeaderNavigation);
                }
                Dynamic origin = info.getDynamic("origin");
                List<Object> destinationsArray = info.getArray("destinations").toArrayList();
                List<Object> dests = new ArrayList<Object>();
                for (Object d : destinationsArray) {
                if (d instanceof String) {
                    dests.add(d);
                } else {
                    HashMap map = (HashMap) d;
                    HashMap scene = (HashMap) map.get("scene");
                    VMEPosition post = new VMEPosition(
                        (double) map.get("latitude"),
                        (double) map.get("longitude"),
                        (double) map.get("altitude"),
                        new VMESceneContext((String)scene.get("buildingId"), (String)scene.get("floorId")));
                    dests.add(post);
                }
            }

                view.computeRouteFromOriginToDests(origin, dests, 0, accessibility, drawRouteOnMap, promise);
            }
        });
    }

    @ReactMethod
    public void navigateToSegment(final int tag, final int index, final Promise promise) {
        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nvhm) {
                MapView view = (MapView) nvhm.resolveView(tag);
                if (view == null) {
                    promise.reject("VMEMapView component not found");
                    return;
                }
                view.navigateToSegment(index);
            }
        });
    }

    @ReactMethod
    public void recenterMap(final int tag) {
        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nvhm) {
                MapView view = (MapView) nvhm.resolveView(tag);
                if (view == null) {
                    return;
                }
                view.recenterMap();
            }
        });
    }

    @ReactMethod
    public void focusMapOn(final int tag, final ReadableMap params, final Promise promise) {
        final ReactApplicationContext context = getReactApplicationContext();
        if (params.hasKey("place") == false) {
            promise.reject("You should provide the origin");
            return;
        }
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nvhm) {
                MapView view = (MapView) nvhm.resolveView(tag);
                if (view == null) {
                    return;
                }
                Dynamic place = params.getDynamic("place");
                view.focusMapOn(place);
            }
        });
    }

    @ReactMethod
    public void startLocationTracker(final int tag) {
        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nvhm) {
                MapView view =(MapView) nvhm.resolveView(tag);
                view.startLocationTracker(context);
            }
        });
    }

    @ReactMethod
    public void stopLocationTracker(final int tag) {
        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        uiManager.addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nvhm) {
                MapView view =(MapView) nvhm.resolveView(tag);
                view.stopLocationTracker();
            }
        });
    }

    @ReactMethod
    public void getNearestLocation(final int tag, final ReadableMap position, final Promise promise) {
        final ReactApplicationContext context = getReactApplicationContext();
        UIManagerModule uiManager = context.getNativeModule(UIManagerModule.class);
        if (position.hasKey("latitude") == false || position.hasKey("longitude") == false || position.hasKey("altitude") == false || position.hasKey("scene") == false || position.getMap("scene").hasKey("buildingId") == false || position.getMap("scene").hasKey("floorId") == false) {
            promise.reject("The passed position argument doesn't confirm to type VMEPosition");
            return;
        }
        final VMEPosition post = new VMEPosition(
                position.getDouble("latitude"),
        position.getDouble("longitude"),
        position.getDouble("altitude"),
        new VMESceneContext(position.getMap("scene").getString("buildingId"), position.getMap("scene").getString("floorId")));
        uiManager.addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nvhm) {
                MapView view =(MapView) nvhm.resolveView(tag);
                view.getNearestLocation(post, promise);
            }
        });
    }

}