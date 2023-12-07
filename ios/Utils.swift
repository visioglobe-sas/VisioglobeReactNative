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
    static func getNativePitch(data: NSDictionary) -> VMECameraPitch{
        var data : NSDictionary = data["pitch"] as! NSDictionary;
        if (data.contains{ $0.key as! String == "type" }){
        if ((data["type"]) as! Int == 0){
            return VMECameraPitch.initCameraPitchCurrent()
        } else if ((data["type"]) as! Int == 1){
            return VMECameraPitch.initCameraPitchDefault()
        } 
        }
        return VMECameraPitch.initCameraPitch(value: data["pitch"] as! Double)
    }
    
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
    
    static func getNativePosition(VMPos : NSDictionary) -> VMEPosition{
        if let scene = VMPos["scene"]{
            let sceneContext = getNativeSceneContext(scene: VMPos["scene"] as? NSDictionary)
            let VMPosition = VMEPosition.init(
                latitude: (VMPos["latitude"] as! NSNumber).doubleValue,
                longitude: (VMPos["longitude"] as! NSNumber).doubleValue,
                altitude: (VMPos["altitude"] as! NSNumber).doubleValue,
                scene: sceneContext
            );
            return VMPosition;
        }
        else {
            let VMPosition = VMEPosition.init(
                latitude: (VMPos["latitude"] as! NSNumber).doubleValue,
                longitude: (VMPos["longitude"] as! NSNumber).doubleValue,
                altitude: (VMPos["altitude"] as! NSNumber).doubleValue
            );
            return VMPosition;
            
        }
    }
    
    static func getNativeLocation(VMLocation : NSDictionary) -> VMELocation{
        let location : VMELocation = VMELocation.init(
            position : Utils.getNativePosition(VMPos: VMLocation["position"] as! NSDictionary),
            bearing : VMLocation["bearing"] as! Double,
            accuracy: VMLocation["accuracy"] as! Double
        );
        return location;
    }
    
    static func getNativeSceneContext(scene : NSDictionary?) -> VMESceneContext{
        var sceneContext : VMESceneContext = VMESceneContext.init();
        if ( scene != nil){
            sceneContext = VMESceneContext.init(buildingID: scene?["buildingID"] as? String,
                                                floorID: scene?["floorID"] as? String);
        }
        return sceneContext;
    }
    
    static func getNativeRouteRequestType(data : NSDictionary) -> VMERouteRequestType {
        var routeRequestType : VMERouteRequestType = VMERouteRequestType.fastest
        if(data["requestType"] as! Int == 1) {
            routeRequestType = VMERouteRequestType.shortest
        }
        
        return routeRequestType;
    }
    
    static func getNativeRouteDestinationsOrder(data : NSDictionary) -> VMERouteDestinationsOrder {
        var routeDestinationsOrder : VMERouteDestinationsOrder
        switch data["destinationsOrder"] as! Int{
        case 0 :
            routeDestinationsOrder = VMERouteDestinationsOrder.closest
        case 1:
            routeDestinationsOrder = VMERouteDestinationsOrder.inOrder
        case 2 :
            routeDestinationsOrder = VMERouteDestinationsOrder.optimal
        case 3:
            routeDestinationsOrder = VMERouteDestinationsOrder.optimalFinishOnLast
        default:
            routeDestinationsOrder = VMERouteDestinationsOrder.inOrder
        }
        return routeDestinationsOrder;
        
    }
    
    static func getNativeRouteRequest(data : NSDictionary)->
    VMERouteRequest{
        return (VMERouteRequest(
            requestType: getNativeRouteRequestType(data: data),
            destinationsOrder: getNativeRouteDestinationsOrder(data: data),
            accessible: data["isAccessible"] as! Bool))
    }
    
}
