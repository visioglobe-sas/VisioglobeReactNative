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
    func unloadMapView(_ reactTag: NSNumber) {
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.unloadMapView()
            }
        }
    }
    
    @objc
    func loadMapView(_ reactTag: NSNumber) {
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.loadMapView()
            }
        }
    }
    
    @objc
    func computeRoute(_ reactTag: NSNumber, data: NSDictionary) {
        print("=====> COMPUTE ROUTE FROM VIEW MANAGER")
        var destinations : [AnyHashable] = [];
        print(data);
        for position in data["destinations"] as! NSArray {
            if (!(position is NSString)) {
                let VMPosition = Utils.getNativePosition(VMPos: position as! NSDictionary)
                destinations.append(VMPosition);
            } else if(position is NSString){
                let pos = position as! NSString;
                destinations.append(pos);
            } else {
                print ("====> IOS COMPUTE ROUTE FAILED TO ADD DESTINATIONS");
            }
        }
        
        let routeRequest : VMERouteRequest = Utils.getNativeRouteRequest(data: data);
        
        var origin : AnyHashable = "";
        
        if (!(data["origin"] is NSString)) {
            origin = Utils.getNativePosition(VMPos: data["origin"] as! NSDictionary)
        } else {
            origin = data["origin"] as! String;
        }
        
        DispatchQueue.main.async{
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.computeRoute(routeRequest: routeRequest, destinations: destinations, origin: origin)
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
    @objc func animateCamera(_ reactTag: NSNumber, data: NSDictionary, duration: NSNumber) {
        print("ANIMATE CAMERA");
        let duration = duration.doubleValue;
        
        let viewMode: VMEViewMode = Utils.getNativeViewMode(data: data);
        
        let heading: VMECameraHeading = Utils.getNativeHeading(data: data);
        
        let pitch: VMECameraPitch = Utils.getNativePitch(data: data);
        
        var target: [AnyHashable] = [];
        let positions = (data["targets"]) as! Array<Any>;
        var _ : VMEPosition;
        for position in positions {
            if (!(position is NSString)) {
                let VMPosition = Utils.getNativePosition(VMPos: position as! NSDictionary)
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
            builder.pitch = pitch;
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
        let viewMode: VMEViewMode = Utils.getNativeViewMode(data: data);
        
        let heading: VMECameraHeading = Utils.getNativeHeading(data: data);
        
        let pitch: VMECameraPitch = Utils.getNativePitch(data: data);
        
        var target: [AnyHashable] = [];
        let positions = (data["targets"]) as! Array<Any>;
        var _ : VMEPosition;
        for position in positions {
            if (!(position is NSString)) {
                let VMPosition = Utils.getNativePosition(VMPos: position as! NSDictionary)
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
            builder.pitch = pitch;
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

    @objc func removePois(_ reactTag: NSNumber, data : NSArray) {
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.removePois(data as! [String]);
            }
        }
        print("REMOVE POIS")
        
    }

    @objc func getCategory(_ reactTag: NSNumber) {
        print("GET CATEGORY")
    }

    @objc func getPoi(_ reactTag: NSNumber, requestId : NSNumber ,value: NSString) {
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
    
    @objc func updateLocation(_ reactTag: NSNumber, data: NSDictionary){
        print("UPDATE LOCATION")
        let location : VMELocation = Utils.getNativeLocation(VMLocation: data);
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.updateLocation(location)
            }
        }
    }
    
    @objc func setCategories( _ reactTag: NSNumber, data : NSString){
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.setCategories(data as String)
            }
        }
        print("SET CATEGORIES")
    }

    @objc func showPoiInfo(_ reactTag: NSNumber, data : NSString) {
        print("SHOW POI INFO")
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.showPoiInfo(data as String)
            }
        }
    }
    
    @objc func showSearchViewWithTitle(_ reactTag: NSNumber, data : NSString){
        print("SHOW SEARCH VIEW")
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.showSearchViewWithTitle(data as String)
            }
        }

    }
    
    @objc func setCompass(_ reactTag: NSNumber, data : Bool){
        DispatchQueue.main.async {
            if let view = self.bridge.uiManager.view(forReactTag: reactTag) as? VisioMapView {
                view.setCompass(data)
            }
        }
    }

    
  /* override static func requiresMainQueueSetup() -> Bool {
    return true
  } */
}

class VisioMapView: UIView, VMELifeCycleListener, VMEAnimationCallback, VMEBuildingListener, VMECameraListener, VMEMapListener, VMELocationTrackingModeListener, VMEPoiListener, VMEComputeRouteCallback, VMECompassListener {
    func compassStateChanged(state: Bool) {

    }
    
    
    func computeRouteDidFinish(mapController: VisioMoveEssential.VMEMapController, parameters routeRequest: VisioMoveEssential.VMERouteRequest, result routeResult: VisioMoveEssential.VMERouteResult) -> Bool {
        return (true);
    }
    
    func computeRouteDidFail(mapController: VisioMoveEssential.VMEMapController, parameters routeRequest: VisioMoveEssential.VMERouteRequest, error: String) {
        
    }
    
    var mMapController: VMEMapController!
    var mMapView: VMEMapView!  // assuming VMEMapView is the correct type
    let label: UILabel = UILabel()
    
    @objc var mapHash: NSString = ""
    @objc var mapPath: NSString = ""
    @objc var mapSecret: NSNumber = 0
    @objc var listeners: NSArray = []
    @objc var promptToDownload: Bool = true
    @objc var onMapLoaded: RCTBubblingEventBlock?

    override init(frame: CGRect) {
        super.init(frame: frame)
        print("====> INIT")
    }
    
    override func didSetProps(_ changedProps: [String]!) {
        print("====> DID SET PROPS")
        print("mapHash" + (self.mapHash as String) as String)
        print("mapPath" + (self.mapPath as String) as String)
        print(self.listeners)
        print(Int32(truncating: self.mapSecret))
        
        mMapController = VMEMapController.initController(builderBlock: { builder in
            builder.mapHash = self.mapHash as String
            builder.mapSecretCode = Int(truncating: self.mapSecret)
        })
        mMapView = VMEMapView(mapController: mMapController, frame: self.bounds)
        self.addSubview(mMapView)

        mMapController.setLifeCycleListener(self)
        mMapController.loadMapData()
        for element in listeners{
            switch(element as! String){
            case "buildingListener":
                print("====> SET BUILDING LISTENER")
                mMapController.setBuildingListener(self)
            case "cameraListener":
                print("====> SET CAMERA LISTENER")
                mMapController.setCameraListener(self)
            case "mapListener":
                print("====> SET MAP LISTENER")
                mMapController.setMapListener(self)
            case "locationtrackingmodeListener":
                print("====> SET LOCATION LISTENER")
                mMapController.setLocationTrackingModeListener(self)
            case "poiListener":
                print("====> SET POI LISTENER")
                mMapController.setPoiListener(self)
            case "compassListener":
                print("====> SET COMPASS LISTENER")
                mMapController.setCompassListener(self)
            default:
                print("====> SET LISTENERS")
            }
            
        }
        if (!(self.promptToDownload)){
            mMapController.setPromptUserToDownloadMap(enable: false);
        }
        mMapController.setBuildingListener(self)
    }
    
    func mapViewDidLoad(mapController: VMEMapController) {
        onMapLoaded!(["result": true])
    }
    
    func customFunctionToCall() {
        print("=====> LOG FROM CUSTOM FUNCTION")
    }
    
    func setCompass(_ data: Bool){
        let result: () = mMapController.setCompass(enabled: data)
        print("====> SET COMPASS")
        print(result)
    }
    
    func removePois(_ data: [String]){
        let result = mMapController.removePois(poiIDs: data)
        print("====> REMOVE POIS")
        print(result)
    }
    
    func loadMapView(){
        let result: () = mMapController.loadMapView(mapView: mMapView)
        print("====> LOAD MAP VIEW")
        print(result)
    }
    
    func showSearchViewWithTitle(_ data: String){
        let result: () = mMapController.showSearchViewWithTitle(data, callback: nil)
        print("=====> SHOW SEARCH VIEW WITH TITLE")
        print(result)
    }
    
    func setCategories(_ data: String){
        let result = mMapController.setCategories(data: data)
        print("=====> SHOW SET CATEGORIES")
        print(result)
    }
    
    func unloadMapView(){
        let result: () = mMapController.unloadMapView()
        print("=====> UNLOAD MAP VIEW")
        print(result)
    }
    
    func showPoiInfo(_ data: String){
        let result: () = mMapController.showPoiInfo(poiID: data);
        print("=====> SHOW POI INFO")
        print(result)
    }
    
    func updateLocation (_ data: VMELocation){
        let result: () = mMapController.updateLocation(data)
        print("=====> UPDATE LOCATION")
        print(result)
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
    
    func computeRoute(routeRequest : VMERouteRequest, destinations: [AnyHashable],origin : AnyHashable ){
        routeRequest.setOrigin(origin);
        let addDestinations = routeRequest.addDestinations(destinations);
        print(addDestinations);
        mMapController.computeRoute(routeRequest, callback: self)
        print("=====> COMPUTE ROUTE")
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
