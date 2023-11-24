package com.visioglobe;

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
    public VMECameraUpdate readableMapToCamera(ReadableMap lCameraarray){
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
