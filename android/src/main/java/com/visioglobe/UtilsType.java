package com.visioglobe;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.visioglobe.visiomoveessential.enums.VMEViewMode;
import com.visioglobe.visiomoveessential.models.VMECameraHeading;
import com.visioglobe.visiomoveessential.models.VMECameraPitch;
import com.visioglobe.visiomoveessential.models.VMECameraUpdate;
import com.visioglobe.visiomoveessential.models.VMECameraUpdateBuilder;

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
        return cameraUpdate;
    }
}
