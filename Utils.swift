//
//  Utils.swift
//  react-native-visioglobe
//
//  Created by Rémi on 24/11/2023.
//

import Foundation
import VisioMoveEssential
import React

class Utils{
    
    static func getNativeViewMode(data: NSDictionary) -> VMEViewMode{
        var viewMode: VMEViewMode = VMEViewMode.unknown;
        if(data["viewMode"] as! Int == 0) {
            viewMode = VMEViewMode.floor
        } else if (data["viewMode"] as! Int == 1){
            viewMode = VMEViewMode.global
        } else if (data["viewMode"] as! Int == 2){
            viewMode = VMEViewMode.unknown
        }
        return viewMode;
    }
    
    static func getNativeHeading(data : NSDictionary) -> VMECameraHeading{
    var heading: VMECameraHeading = VMECameraHeading.initCameraHeadingCurrent();
    if (((data["heading"] as! NSDictionary)["current"]) as! Bool == true){
        heading = VMECameraHeading.initCameraHeadingCurrent();
    } else if ((data["heading"] as! NSDictionary)["heading"] is String){
        heading = VMECameraHeading.initCameraHeading(poiID: (data["heading"] as! NSDictionary)["heading"] as! String);
    } else if ((data["heading"] as! NSDictionary)["heading"] is NSNumber){
        heading = VMECameraHeading.initCameraHeading(value: ((data["heading"] as! NSDictionary)["heading"] as! NSNumber).doubleValue);
    }
        return heading;
    }
    
    static func getNativePosition(pos : NSDictionary) -> VMEPosition{
        let sceneContext = getNativeSceneContext(scene: pos["scene"] as? NSDictionary)
        let VMPosition = VMEPosition.init(
            latitude: (pos["altitude"] as! NSNumber).doubleValue,
            longitude: (pos["longitude"] as! NSNumber).doubleValue,
            altitude: (pos["altitude"] as! NSNumber).doubleValue,
            scene: sceneContext
        );
        return VMPosition;
    }
    
    static func getNativeSceneContext(scene : NSDictionary?) -> VMESceneContext{
        var sceneContext : VMESceneContext = VMESceneContext.init();
        if ( scene != nil){
            sceneContext = VMESceneContext.init(buildingID: scene?["buildingID"] as? String,
                                                floorID: scene?["floorID"] as? String);
        }
        return sceneContext;
    }
}
