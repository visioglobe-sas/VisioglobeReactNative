package com.visioglobe;

import android.util.Log;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.visioglobe.visiomoveessential.enums.VMEViewMode;
import com.visioglobe.visiomoveessential.models.VMECameraHeading;
import com.visioglobe.visiomoveessential.models.VMECameraPitch;
import com.visioglobe.visiomoveessential.models.VMECameraUpdate;
import com.visioglobe.visiomoveessential.models.VMECameraUpdateBuilder;
import com.visioglobe.visiomoveessential.models.VMEPosition;
import com.visioglobe.visiomoveessential.models.VMESceneContext;

import java.util.ArrayList;

public class UtilsType {

    public VMECameraHeading readableMapToCameraHeading(ReadableMap lHeading){
        VMECameraHeading heading = VMECameraHeading.newCurrent();
        if (lHeading.getBoolean("current")){
            heading = VMECameraHeading.newCurrent();
        }
        else if (lHeading.getType("heading") == ReadableType.String){
            heading = VMECameraHeading.newPoiID(String.valueOf(lHeading.getType("heading")));
        } else if (lHeading.getType("heading") == ReadableType.Number) {
            heading = VMECameraHeading.newHeading(lHeading.getDouble("poiID"));
        }
        return heading;
    }

    public VMECameraPitch readableMapToCameraPitch(ReadableMap lPitch){
        VMECameraPitch pitch = VMECameraPitch.newDefaultPitch();
        if (lPitch.hasKey("type")) {
            if (lPitch.getInt("type") == 0) {
                pitch = VMECameraPitch.newCurrent();
                return pitch;
            } else if (lPitch.getInt("type") == 0) {
                return pitch;
            }
        }
        else if (lPitch.hasKey("pitch")){
            pitch = VMECameraPitch.newPitch(lPitch.getDouble("pitch"));
        }
        return pitch;
    }

    public VMECameraUpdate readableMapToCamera(ReadableMap lCameraarray){
        VMECameraUpdateBuilder builder = new VMECameraUpdateBuilder();
        VMECameraHeading heading = readableMapToCameraHeading(lCameraarray.getMap("heading"));
        builder.setHeading(heading);
        builder.setPaddingBottom(lCameraarray.getInt("paddingBottom"));
        builder.setPaddingLeft(lCameraarray.getInt("paddingLeft"));
        builder.setPaddingRight(lCameraarray.getInt("paddingRight"));
        builder.setPaddingTop(lCameraarray.getInt("paddingTop"));
        builder.setPitch(readableMapToCameraPitch(lCameraarray.getMap("pitch")));

        ArrayList<Object> targets = new ArrayList<>();
        for (Object element: lCameraarray.getArray("targets").toArrayList()) {
            if (element instanceof String) targets.add(element);
            else if (element instanceof ReadableMap){
                targets.add(readableMapToPosition((ReadableMap) element));
            }
        }

        builder.setTargets(targets);
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
        Log.d("CC", "readableMapToCamera: " + builder);
        return cameraUpdate;
    }

    public VMEPosition readableMapToPosition(ReadableMap positionMap){
        VMESceneContext scene;
        ReadableMap inputSceneMap = positionMap.getMap("scene");
        if (inputSceneMap != null) {
            scene = new VMESceneContext(inputSceneMap.getString("buildingID"),inputSceneMap.getString("floorID"));
        }
        else scene = new VMESceneContext();
        VMEPosition position = new VMEPosition(positionMap.getDouble("latitude"),positionMap.getDouble("longitude"),positionMap.getDouble("altitude"),scene);
        return position;
    }
}
