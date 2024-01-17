package com.visioglobe

import android.util.Log
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableType
import com.visioglobe.visiomoveessential.enums.VMEPoiAltitudeMode
import com.visioglobe.visiomoveessential.enums.VMEPoiAnchorMode
import com.visioglobe.visiomoveessential.enums.VMEPoiDisplayMode
import com.visioglobe.visiomoveessential.enums.VMEViewMode
import com.visioglobe.visiomoveessential.models.VMECameraHeading
import com.visioglobe.visiomoveessential.models.VMECameraPitch
import com.visioglobe.visiomoveessential.models.VMECameraUpdate
import com.visioglobe.visiomoveessential.models.VMECameraUpdateBuilder
import com.visioglobe.visiomoveessential.models.VMELocation
import com.visioglobe.visiomoveessential.models.VMEPoi
import com.visioglobe.visiomoveessential.models.VMEPosition
import com.visioglobe.visiomoveessential.models.VMESceneContext

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

    fun readableMapToLocation(locationMap: ReadableMap): VMELocation {
        return VMELocation(
            position = locationMap.getMap("position")
                ?.let { readableMapToPosition(it) }!!,
            accuracy = locationMap.getDouble("accuracy"),
            bearing = locationMap.getDouble("bearing")
        )
    }

    fun altitudeModeToString(altitudeMode: VMEPoiAltitudeMode) : String{
        return when(altitudeMode){
            VMEPoiAltitudeMode.RELATIVE -> "\"relative\""
            VMEPoiAltitudeMode.ABSOLUTE -> "\"absolute\""
        }
    }
    fun anchorModeToString(anchorMode: VMEPoiAnchorMode) : String{
        return when(anchorMode){
            VMEPoiAnchorMode.TOP_LEFT -> "\"topleft\""
            VMEPoiAnchorMode.TOP_CENTER -> "\"topcenter\""
            VMEPoiAnchorMode.TOP_RIGHT -> "\"topright\""
            VMEPoiAnchorMode.CENTER_LEFT -> "\"centerleft\""
            VMEPoiAnchorMode.CENTER -> "\"center\""
            VMEPoiAnchorMode.CENTER_RIGHT ->  "\"centerright\""
            VMEPoiAnchorMode.BOTTOM_LEFT -> "\"bottomleft\""
            VMEPoiAnchorMode.BOTTOM_CENTER -> "\"bottomcenter\""
            VMEPoiAnchorMode.BOTTOM_RIGHT -> "\"bottomright\""
        }
    }

    fun displayModeToString(displayMode: VMEPoiDisplayMode) : String{
        return when(displayMode){
            VMEPoiDisplayMode.OVERLAY -> "\"overlay\""
            VMEPoiDisplayMode.INLAY -> "\"inlay\""
        }
    }

    /*fun orientationToString(orientation: VMEPoiOrientation) : String{
        return when(orientation.toString()){
        }
    }*/

    fun vMEPoiToVMPoi(poi : VMEPoi): String{
        var result = "{\"altitudeMode\" : " + altitudeModeToString(poi.altitudeMode) + " ,"
        result += "\"htmlDescription\" :\"" + poi.htmlDescription.replace(Regex("""(\r\n)|\n"""), " <br />") + "\" ,"
        result += "\"name\" :\"" + poi.name + "\" ,"
        result += "\"imageURL\" :\"" + poi.imageURL.toString()+ "\" ,"
        result += "\"id\" :\""  + poi.id+ "\" ,"
        result += "\"icon\" :\"" + poi.icon+ "\" ,"
        result += "\"categories\" :\"" + poi.categories+"\" ,"
        result +="\"position\" :"+ "{\"altitude\" :" + (poi.position?.altitude ?: 0.0) +"," + "\"longitude\" :" + (poi.position?.longitude
            ?: 0.0) +"," + "\"latitude\" :" + (poi.position?.latitude ?: 0.0) + "," +"\"scene\" :" +
                "{\"buildingID\" :\"" + poi.position?.scene?.buildingID +"\",\"floorID\" :\"" + poi.position?.scene?.floorID + "\"}},"
        result +="\"size\" :{\"scale\" :" + poi.size.scale+ ",\"constantSizeDistant\":" + poi.size.sizeDistance + "},"
        result += "\"orientation\" :\"" + poi.orientation+"\" ,"
        result += "\"displayMode\" :" + displayModeToString(poi.displayMode)+" ,"
        result += "\"anchorMode\" :" + anchorModeToString(poi.anchorMode)+","
        result += "\"visibilityRamp\" :{\"fullyVisible\" : ${poi.visibilityRamp.fullyVisible}, \"fullyInvisible\" : ${poi.visibilityRamp.fullyInvisible}, \"startInvisible\" : ${poi.visibilityRamp.startInvisible}, \"startVisible\" : ${poi.visibilityRamp.startVisible}} }"
        return result
    }
}