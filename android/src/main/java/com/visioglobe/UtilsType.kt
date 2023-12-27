package com.visioglobe

import android.util.Log
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableType
import com.facebook.react.bridge.WritableMap
import com.visioglobe.visiomoveessential.enums.VMEViewMode
import com.visioglobe.visiomoveessential.models.VMECameraHeading
import com.visioglobe.visiomoveessential.models.VMECameraPitch
import com.visioglobe.visiomoveessential.models.VMECameraUpdate
import com.visioglobe.visiomoveessential.models.VMECameraUpdateBuilder
import com.visioglobe.visiomoveessential.models.VMEPoi
import com.visioglobe.visiomoveessential.models.VMEPosition
import com.visioglobe.visiomoveessential.models.VMESceneContext
import java.util.Dictionary

class UtilsType {
    fun readableMapToCameraHeading(lHeading: ReadableMap?): VMECameraHeading {
        var heading = VMECameraHeading.newCurrent()
        if (lHeading!!.getBoolean("current")) {
            heading = VMECameraHeading.newCurrent()
        } else if (lHeading.getType("heading") == ReadableType.String) {
            heading = VMECameraHeading.newPoiID(lHeading.getType("heading").toString())
        } else if (lHeading.getType("heading") == ReadableType.Number) {
            heading = VMECameraHeading.newHeading(lHeading.getDouble("poiID"))
        }
        return heading
    }

    fun readableMapToCameraPitch(lPitch: ReadableMap?): VMECameraPitch {
        var pitch = VMECameraPitch.newDefaultPitch()
        if (lPitch!!.hasKey("type")) {
            if (lPitch.getInt("type") == 0) {
                pitch = VMECameraPitch.newCurrent()
                return pitch
            } else if (lPitch.getInt("type") == 1) {
                return pitch
            }
        } else if (lPitch.hasKey("pitch")) {
            pitch = VMECameraPitch.newPitch(lPitch.getDouble("pitch"))
        }
        return pitch
    }

    fun readableMapToCamera(lCameraarray: ReadableMap): VMECameraUpdate {
        val builder = VMECameraUpdateBuilder()
        val heading = readableMapToCameraHeading(lCameraarray.getMap("heading"))
        builder.setHeading(heading)
        builder.setPaddingBottom(lCameraarray.getInt("paddingBottom"))
        builder.setPaddingLeft(lCameraarray.getInt("paddingLeft"))
        builder.setPaddingRight(lCameraarray.getInt("paddingRight"))
        builder.setPaddingTop(lCameraarray.getInt("paddingTop"))
        builder.setPitch(readableMapToCameraPitch(lCameraarray.getMap("pitch")))
        val targets = ArrayList<Any>()
        for (element in lCameraarray.getArray("targets")!!.toArrayList()) {
            if (element is String) targets.add(element) else if (element is ReadableMap) {
                targets.add(readableMapToPosition(element))
            }
        }
        builder.setTargets(targets)
        if (lCameraarray.getDouble("viewMode") == 0.0) {
            builder.setViewMode(VMEViewMode.FLOOR)
        }
        if (lCameraarray.getDouble("viewMode") == 1.0) {
            builder.setViewMode(VMEViewMode.GLOBAL)
        }
        if (lCameraarray.getDouble("viewMode") == 2.0) {
            builder.setViewMode(VMEViewMode.UNKNOWN)
        }
        val cameraUpdate = VMECameraUpdate(builder)
        Log.d("CC", "readableMapToCamera: $builder")
        return cameraUpdate
    }

    fun readableMapToPosition(positionMap: ReadableMap): VMEPosition {
        val scene: VMESceneContext
        val inputSceneMap = positionMap.getMap("scene")
        scene = if (inputSceneMap != null) {
            VMESceneContext(
                inputSceneMap.getString("buildingID")!!,
                inputSceneMap.getString("floorID")!!
            )
        } else VMESceneContext()
        return VMEPosition(
            positionMap.getDouble("latitude"),
            positionMap.getDouble("longitude"),
            positionMap.getDouble("altitude"),
            scene
        )
    }

    fun vMEPoiToVMPoi(poi : VMEPoi): MutableMap<String,Any>{
        var result = mutableMapOf<String,Any>();
        result["altitudeMode"] = poi.altitudeMode.name
        result["htmlDescription"] = poi.htmlDescription
        result["name"] = poi.name
        result["imageURL"] = poi.imageURL.toString()
        result["id"] = poi.id
        result["icon"] = poi.icon
        result["categories"] = poi.categories
        result["position"] = mutableMapOf<String,Any>("altitude" to poi.position.altitude, "longitude" to poi.position.longitude, "latitude" to poi.position.latitude,"scene" to
                mutableMapOf<String,Any>("buildingID" to poi.position.scene.buildingID, "floorID" to poi.position.scene.floorID));
        result["size"] = mutableMapOf("scale" to poi.size.scale, "constantSizeDistant" to poi.size.sizeDistance)
        result["orientation"] = poi.orientation
        result["displayMode"] = poi.displayMode.name
        result["anchorMode"] = poi.anchorMode.name
        return result
    }
}