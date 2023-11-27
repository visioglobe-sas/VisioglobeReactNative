//
//  MapViewManager.swift
//  Visioglobe
//
//  Created by Alassane on 17/07/2023.
//  Copyright © 2023 Facebook. All rights reserved.
//

import Foundation
import UIKit
import VisioMoveEssential
import React

@objc(VisioMapViewManager)
class VisioMapViewManager: RCTViewManager {
    override func view() -> UIView! {
        return VisioMapView()
      }

    @objc
    func customFunctionToCall(_ reactTag: NSNumber) {
        print("=====> FIRST CUSTOM CALL FROM VIEW MANAGER")
        print(reactTag)
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.customFunctionToCall()
            }
        }
    }
    
    @objc
    func setPois(_ reactTag: NSNumber, data: NSString) {
        print("=====> SET POIS")
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.setPois(String(data))
            }
        }
    }
    
    @objc
    func setExcludedAttributes(_ reactTag: NSNumber, data:NSArray) {
        print("=====> SET EXCLUDED ATTRIBUTES")
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.setExcludedAttributes(data as! [String]);
            }
        }
        
    }
    
    @objc
    func setExcludedModalities(_ reactTag: NSNumber, data:NSArray) {
        print("=====> SET EXCLUDED MODALITIES")
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.setExcludedModalities(data as! [String]);
            }
        }
        
    }

    
    @objc
    func setPoisColor(_ reactTag: NSNumber, poiIDs: [NSString]) {
        print("=====> SET POIS COLOR FROM VIEW MANAGER")
    }
    
    @objc
    func resetPoisColor(_ reactTag: NSNumber) {
        print("=====> SET POIS FROM VIEW MANAGER")
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.resetPoisColor()
            }
        }
    }
    
    @objc
    func computeRoute(_ reactTag: NSNumber, origin: NSString, destinations: [NSString]) {
        print("=====> COMPUTE ROUTE FROM VIEW MANAGER")
        let lDestinations = destinations.map { $0 as String }
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                // view.computeRoute(String(origin), lDestinations: lDestinations)
            }
        }
    }
    
    @objc
    func getVersion(_ reactTag: NSNumber)->Void{
        //resolver("version");
        /**DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                let version = view.getVersion()
                if !version.isEmpty {
                    resolve(version)
                } else {
                    reject("error", "Failed to get version in iOS", nil)
                }
            }
        }**/
    }
    @objc func animateCamera(_ reactTag: NSNumber, data: NSDictionary, duration: NSNumber, callback: NSDictionary?) {
        print("ANIMATE CAMERA");
        let duration = duration.doubleValue;
        
        var viewMode: VMEViewMode = Utils.getNativeViewMode(data: data);
        
        var heading: VMECameraHeading = Utils.getNativeHeading(data: data);
        
        var target: [AnyHashable] = [];
        let positions = (data["targets"]) as! Array<Any>;
        var _ : VMEPosition;
        for position in positions {
            if (!(position is NSString)) {
                let VMPosition = Utils.getNativePosition(pos: position as! NSDictionary)
                target.append(VMPosition);
            } else if(position is NSString){
                let pos = position as! NSString;
                target.append(pos);
            } else{
                print ("ERROR ON ANIMATE CAMERA POINT:" + (position as! String))
            }
        }
        
        let cameraUpdate = VMECameraUpdate.initCameraUpdate { builder in
            builder.heading = heading;
            builder.paddingBottom = (data["paddingBottom"] as? Double)!;
            builder.paddingTop = (data["paddingTop"] as? Double)!;
            builder.paddingLeft = (data["paddingLeft"] as? Double)!;
            builder.paddingRight = (data["paddingRight"] as? Double)!;
            builder.viewMode = viewMode;
            builder.targets = target;
        }
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.animateCamera(cameraUpdate, duration: duration)
            }
        }
    }

    @objc func getCameraContext(_ reactTag: NSNumber) {
        print("GET CAMERA CONTEXT")
    }

    @objc func updateCamera(_ reactTag: NSNumber, data: NSDictionary) {
        print("UPDATE CAMERA")
        var viewMode: VMEViewMode = Utils.getNativeViewMode(data: data);
        
        var heading: VMECameraHeading = Utils.getNativeHeading(data: data);
        
        var target: [AnyHashable] = [];
        let positions = (data["targets"]) as! Array<Any>;
        var _ : VMEPosition;
        for position in positions {
            if (!(position is NSString)) {
                let VMPosition = Utils.getNativePosition(pos: position as! NSDictionary)
                target.append(VMPosition);
            } else if(position is NSString){
                let pos = position as! NSString;
                target.append(pos);
            } else{
                print ("ERROR ON ANIMATE CAMERA POINT:" + (position as! String))
            }
        }
        
        let cameraUpdate = VMECameraUpdate.initCameraUpdate { builder in
            builder.heading = heading;
            builder.paddingBottom = (data["paddingBottom"] as? Double)!;
            builder.paddingTop = (data["paddingTop"] as? Double)!;
            builder.paddingLeft = (data["paddingLeft"] as? Double)!;
            builder.paddingRight = (data["paddingRight"] as? Double)!;
            builder.viewMode = viewMode;
            builder.targets = target;
        }
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.updateCamera(cameraUpdate);
            }
        }
    }

    @objc func animateScene(_ reactTag: NSNumber, data: NSDictionary) {
        print("ANIMATE SCENE")
        let viewMode: VMEViewMode = Utils.getNativeViewMode(data: data);
        var animateScene : VMESceneUpdate = VMESceneUpdate.sceneUpdate(viewMode: viewMode);
        if (data["buildingID"] != nil) {
            animateScene = VMESceneUpdate.sceneUpdate(viewMode: viewMode, buildingID: data["buildingID"] as? String)
        } else if (data["floorID"] != nil){
            animateScene = VMESceneUpdate.sceneUpdate(viewMode:viewMode,floorID:data["floorID"] as? String);
        }
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.animateScene(animateScene);
            }
        }

    }

    @objc func updateScene(_ reactTag: NSNumber) {
        print("UPDATE SCENE")
    }

    @objc func createLocationFromLocation(_ reactTag: NSNumber) {
        print("CREATE LOCATION FROM LOCATION")
    }

    @objc func createPositionFromLocation(_ reactTag: NSNumber) {
        print("CREATE POSITION FROM LOCATION")
    }

    @objc func getLocationTrackingMode(_ reactTag: NSNumber) {
        print("GET LOCATION TRACKING MODE")
    }

    @objc func setLocationTrackingMode(_ reactTag: NSNumber) {
        print("SET LOCATION TRACKING MODE")
    }

    @objc func getLocationTrackingButtonToggleModes(_ reactTag: NSNumber) {
        print("GET LOCATION TRACKING BUTTON TOGGLE MODES")
    }

    @objc func setLocationTrackingButtonToggleModes(_ reactTag: NSNumber) {
        print("SET LOCATION TRACKING BUTTON TOGGLE MODES")
    }

    @objc func getNavigationHeaderViewVisible(_ reactTag: NSNumber) {
        print("GET NAVIGATION HEADER VIEW VISIBLE")
    }

    @objc func setNavigationHeaderViewVisible(_ reactTag: NSNumber) {
        print("SET NAVIGATION HEADER VIEW VISIBLE")
    }

    @objc func getSelectorViewVisible(_ reactTag: NSNumber) {
        print("GET SELECTOR VIEW VISIBLE")
    }

    @objc func removePoi(_ reactTag: NSNumber) {
        print("REMOVE POI")
    }

    @objc func removePois(_ reactTag: NSNumber) {
        print("REMOVE POIS")
    }

    @objc func getCategory(_ reactTag: NSNumber) {
        print("GET CATEGORY")
    }

    @objc func getPoi(_ reactTag: NSNumber) {
        print("GET POI")
    }

    @objc func getPoiBoundingPositions(_ reactTag: NSNumber) {
        print("GET POI BOUNDING POSITIONS")
    }

    @objc func queryAllCategoryIDs(_ reactTag: NSNumber) {
        print("QUERY ALL CATEGORY IDS")
    }

    @objc func queryAllPoiIDs(_ reactTag: NSNumber) {
        print("QUERY ALL POI IDS")
    }

    @objc func queryPois(_ reactTag: NSNumber) {
        print("QUERY POIS")
    }

    @objc func resetPoiColor(_ reactTag: NSNumber) {
        print("RESET POI COLOR")
    }

    @objc func setPoiSize(_ reactTag: NSNumber) {
        print("SET POI SIZE")
    }

    @objc func setPoisSize(_ reactTag: NSNumber) {
        print("SET POIS SIZE")
    }

    @objc func setPoiPosition(_ reactTag: NSNumber) {
        print("SET POI POSITION")
    }

    @objc func setPoisPosition(_ reactTag: NSNumber) {
        print("SET POIS POSITION")
    }

    @objc func showPoiInfo(_ reactTag: NSNumber) {
        print("SHOW POI INFO")
    }

    @objc func setCategories(_ reactTag: NSNumber) {
        print("SET CATEGORIES")
    }

    
  /* override static func requiresMainQueueSetup() -> Bool {
    return true
  } */
}

class VisioMapView: UIView, VMELifeCycleListener, VMEAnimationCallback {
    var mMapController: VMEMapController!
    var mMapView: VMEMapView!  // assuming VMEMapView is the correct type
    let label: UILabel = UILabel()
    
    @objc var mapHash: NSString = ""
    @objc var mapPath: NSString = ""
    @objc var mapSecret: NSNumber = 0
    @objc var listeners: NSArray = []

    override init(frame: CGRect) {
        super.init(frame: frame)
        print("====> INIT")
    }
    
    override func didSetProps(_ changedProps: [String]!) {
        print("====> DID SET PROPS")
        print("mapHash" + (self.mapHash as String) as String)
        print("mapPath" + (self.mapPath as String) as String)
        print(Int32(truncating: self.mapSecret))
        print(self.listeners)
        
        mMapController = VMEMapController.initController(builderBlock: { builder in
            builder.mapHash = self.mapHash as String
            builder.mapSecretCode = Int(truncating: self.mapSecret)
        })
        mMapView = VMEMapView(mapController: mMapController, frame: self.bounds)
        self.addSubview(mMapView)

        mMapController.setLifeCycleListener(self)
        mMapController.loadMapData()
    }
    
    func customFunctionToCall() {
        print("=====> LOG FROM CUSTOM FUNCTION")
    }
    
    
    func setPois(_ data: String) {
        let result = mMapController.setPois(data: data);
        print("=====> SET POIS RESULT")
        print(result)
    }
    
    func animateScene(_ sceneUpdate: VMESceneUpdate){
        print("=====> ANIMATE SCENE")
        mMapController.animateScene(sceneUpdate);
    }
    
    func animateCamera(_ cameraUpdate: VMECameraUpdate, duration: Double){
        print("=====> ANIMATE CAMERA")
        mMapController.animateCamera(cameraUpdate, duration : duration, callback: self);
    }
    
    func updateCamera(_ cameraUpdate: VMECameraUpdate){
        print("=====> UPDATE CAMERA")
        mMapController.updateCamera(cameraUpdate);
    }
    
    func setPoisColor(_ poiIDs: [String], lColors: [UIColor]) {
        let result = mMapController.setPoisColor(poiIDs: poiIDs, colors: lColors)
        print("=====> SET POIS COLOR RESULT")
        print(result)
    }
    
    func setExcludedAttributes(_ data:[String]){
        let result = mMapController.setExcludedAttributes(data)
        print("====> SET EXCLUDED ATTRIBUTES")
        print(result)
    }
    
    func setExcludedModalities(_ data:[String]){
        let result = mMapController.setExcludedModalities(data)
        print("====> SET EXCLUDED MODALITIES")
        print(result)
    }
    
    func resetPoisColor() {
        let lPoiIDs = mMapController.queryAllPoiIDs()
        print("=====> RESET POIS FROM FRAGMENT")
        print(lPoiIDs)
        let result = mMapController.resetPoisColor(poiIDs: lPoiIDs);
        print("=====> RESET POIS COLOR RESULT")
        print(result)
    }

    func getVersion() -> String {
        let lVersion = mMapController.getDataSDKVersion();
        print("=====> GET DATA SDK VERSION")
        return(lVersion)
    }
    
    // MARK: - VMEComputeRouteCallback
    /* @objc
    func computeRouteDidFinish(mapController: VMEMapController, parameters routeRequest: VMERouteRequest, result routeResult: VMERouteResult) -> Bool {
        print(String(format: "computeRouteDidFinish duration: %.0fmins and length: %.0fm ", (routeResult.duration) / 60, routeResult.length))
        return true
    }
    
    func computeRouteDidFail(mapController: VMEMapController, parameters routeRequest: VMERouteRequest, error: String) {
        print("computeRouteDidFail error: \(error)")
    }
    
    
    func computeRoute(_ origin: String, lDestinations: [String]) {
        print("=====> COMPUTE ROUTE")
        let lDestOrder = VMERouteDestinationsOrder.inOrder
        let lRouteRequest = VMERouteRequest(requestType: VMERouteRequestType.fastest, destinationsOrder: lDestOrder)
        lRouteRequest.setOrigin(origin)
        if (lRouteRequest.addDestinations(lDestinations)) {
            mMapController.computeRoute(lRouteRequest, callback: self)
        }
    } */

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()

        // Ensure the map view fills the entire view
        mMapView.frame = self.bounds
        label.frame = CGRect(x: 10, y: 10, width: self.bounds.width - 20, height: 30)
    }
    
    func mapDataDidLoad(mapController: VMEMapController, venueData: [String: Any]) {
        mMapController?.loadMapView(mapView: mMapView)
    }
}
