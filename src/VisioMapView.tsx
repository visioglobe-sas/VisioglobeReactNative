import React, { forwardRef, useRef, useImperativeHandle, Component } from 'react';
import {
  NativeModules,
  findNodeHandle,
  Platform,
  UIManager,
  NativeMethods,
} from 'react-native';
import MapView, { Commands, NativeProps } from './VisioMapViewNativeComponent';
import { VMCameraUpdate, VMLocation, VMPoi, VMRouteRequest, VMSceneUpdate } from './VisioTypes';
//import codegenNativeComponent, { NativeComponentType } from 'react-native/Libraries/Utilities/codegenNativeComponent';
import { Double } from 'react-native/Libraries/Types/CodegenTypes';
const MODULE =
  Platform.OS === 'android' ? 'VisioglobeModule' : 'VisioMapViewManager';

const createFragment = (viewId: number | null) =>
  UIManager.dispatchViewManagerCommand(
    viewId,
    // we are calling the 'create' command
    UIManager.VisioMapViewManager.Commands.create.toString(),
    [viewId]
  );

  // We need to keep track of all running requests, so we store a counter.
  let _nextRequestId = 1;
  // We also need to keep track of all the promises we created so we can
  // resolve them later.
  let _requestMap = new Map<number,Promise<any>>();
    

export const VisioMapView = forwardRef((props: NativeProps, ref) => {
  const r = useRef() as React.MutableRefObject<Component<NativeProps, {}, any> & Readonly<NativeMethods>>;

  const _setExcludedAttributes = (value: [string]) => {
    Commands.setExcludedAttributes(r.current, value);
}

const _setExcludedModalities = (value: [string]) => {
    Commands.setExcludedModalities(r.current, value);
}
const _updateLocation = (value : VMLocation) =>{
    Commands.updateLocation(r.current, value);
}

const _setLocationTrackingButtonToggleModes = (value: string[]) => {
    Commands.setLocationTrackingButtonToggleModes(r.current, value);
}

const _setNavigationHeaderViewVisible = (value: boolean) => {
    Commands.setNavigationHeaderViewVisible(r.current, value);
}

const _setCompassHeadingMarkerVisible = (value: boolean) => {
    Commands.setCompassHeadingMarkerVisible(r.current, value);
}

const _showPoiInfo = (value: string) => {
    Commands.showPoiInfo(r.current, value);
}

const _showSearchViewWithTitle = (value: string) => {
  Commands.showSearchViewWithTitle(r.current, value);
}

const _setStatisticsLog = (value: boolean) => {
    Commands.setStatisticsLog(r.current, value);
}

const _setStatisticsLogCamera = (value: boolean) => {
    Commands.setStatisticsLogCamera(r.current, value);
}

const _setStatisticsLogInterest = (value: boolean) => {
    Commands.setStatisticsLogInterest(r.current, value);
}

const _setStatisticsLogLocation = (value: boolean) => {
    Commands.setStatisticsLogLocation(r.current, value);
}

const _setStatisticsTrackedPoiIDs = (value: string[]) => {
    Commands.setStatisticsTrackedPoiIDs(r.current, value);
}

const _setCompass = (value: boolean) => {
    Commands.setCompass(r.current, value);
}

  const _customFunctionToCall = () => {
    NativeModules[MODULE].customFunctionToCall(findNodeHandle(r.current));
  };

  const _setPois = (data: string) => {
    console.log(data);
    Commands.setPois(r.current, data);
  };

  const _loadMapView = () => {
    Commands.loadMapView(r.current);
  };

  const _unloadMapData = () => {
    Commands.unloadMapData(r.current);
  };

  const _unloadMapView = () => {
    Commands.unloadMapView(r.current);
  };

  const _resetPoisColor = () => {
    Commands.resetPoisColor(r.current);
  };

  const _setPoisColor = (poiIDs: string[]) => {
    Commands.setPoisColor(r.current, poiIDs);
  };

  const _computeRoute = (request : VMRouteRequest) => {
    Commands.computeRoute(r.current, request);
  };

  const _getPoiPosition = (poiID: string) => {
    Commands.getPoiPosition(r.current, poiID);
  };

  const _setSelectorViewVisible = (visible: boolean) => {
    Commands.setSelectorViewVisible(r.current, visible);
  };

  const _animateCamera = (values: VMCameraUpdate, duration : number) => {
    console.log(values);
    Commands.animateCamera(r.current,values,duration as Double);
  }

  const _animateScene = (values: VMSceneUpdate) => {
    console.log(values);
    Commands.animateScene(r.current,values);
  }

  const _removePois = (values: [string]) => {
    Commands.removePois(r.current,values)
  }

  const _getPoi = (value : string) => {
    let requestId: number = _nextRequestId++;
    let promise = new Promise(function (resolve, reject) {
      _requestMap.set(requestId,{ resolve: resolve, reject: reject });
    });
    Commands.getPoi(r.current,requestId,value);
    return promise;
  };

  /// EN COURS

  /* Generate a queue for the next asked promises
  const _nextRequestId = 1;

  const _requestMap = new Map();

  const _onDataReturned = (event: { nativeEvent: { requestId: any; result: any; error: any; }; }) => {
    let { requestId, result, error } = event.nativeEvent
    let promise = this._requestMap[requestId]
    if (result) {
      // If it was successful, we resolve the promise.
      promise.resolve(result)
    } else {
      // Otherwise, we reject it.
      promise.reject(error)
    }
    // Finally, we clean up our request map.
    this._requestMap.delete(requestId)
  }
  */

  /*const _getDataSDKVersion = () => {
    Commands.getDataSDKVersion(r.current);
  }*/

  const _getMinDataSDKVersion = () =>{
    Commands.getMinDataSDKVersion(r.current);
  };

  const _updateCamera =  (update : VMCameraUpdate) => {
    Commands.updateCamera(r.current,update);
  };

  const _setCategories = (value: string) => {
    Commands.setCategories(r.current, value);
  };
  useImperativeHandle(ref, () => ({
    customFunctionToCall: _customFunctionToCall,
    updateCamera: _updateCamera,
    animateCamera: _animateCamera,
    animateScene: _animateScene,
    setPois: _setPois,
    resetPoisColor: _resetPoisColor,
    setPoisColor: _setPoisColor,
    computeRoute: _computeRoute,
    getPoiPosition: _getPoiPosition,
    setSelectorViewVisible: _setSelectorViewVisible,
    //getVersion: _getVersion, 
    unloadMapView: _unloadMapView,
    unloadMapData: _unloadMapData,
    loadMapView : _loadMapView,
    //getDataSDKVersion: _getDataSDKVersion, 
    getMinDataSDKVersion: _getMinDataSDKVersion,
    setExcludedAttributes: _setExcludedAttributes,
    setExcludedModalities: _setExcludedModalities,
    setLocationTrackingButtonToggleModes: _setLocationTrackingButtonToggleModes,
    setNavigationHeaderViewVisible: _setNavigationHeaderViewVisible,
    setCompassHeadingMarkerVisible: _setCompassHeadingMarkerVisible,
    showPoiInfo: _showPoiInfo,
    setCategories: _setCategories,
    setStatisticsLog: _setStatisticsLog,
    setStatisticsLogCamera: _setStatisticsLogCamera,
    setStatisticsLogInterest: _setStatisticsLogInterest,
    setStatisticsLogLocation: _setStatisticsLogLocation,
    setStatisticsTrackedPoiIDs: _setStatisticsTrackedPoiIDs,
    setCompass: _setCompass,
    updateLocation: _updateLocation, 
    showSearchViewWithTitle: _showSearchViewWithTitle,
    removePois: _removePois,
    getPoi: _getPoi
  }));

  React.useEffect(() => {
    if (Platform.OS === 'android') {
      const viewId = findNodeHandle(r.current);
      console.debug('======> VIEW ID FROM MODULE:', viewId);
      createFragment(viewId);
    }
  }, []);

  const _onDataReturned = (event: { nativeEvent: { requestId: number; result: any; error: any; }; }) => {
    //console.log("cc ondatareturned here")
    // We grab the relevant data out of our event.
    let { requestId, result, error } = event.nativeEvent
    let promise = _requestMap.get(requestId);
    // Then we get the promise we saved earlier for the given request ID.
    if (promise != undefined){
    if (result) {
      // If it was successful, we resolve the promise.
        if (result.startsWith("getPoi")){
          //console.log(result.substr(result.indexOf(" ") + 1));
          result = stringToVMPoi(result.substr(result.indexOf(" ") + 1));
        }
      promise.resolve(result)
    } else {
      // Otherwise, we reject it.
      promise.reject(error)
    }
    // Finally, we clean up our request map.
  }
    _requestMap.delete(requestId)
    
  }

  /// UTILS

  function stringToVMPoi(data:string):VMPoi{
    let result : VMPoi = {
        altitudeMode: {
            rawValue: "absolute"
        },
        anchorMode: {
            rawValue: "center"
        },
        categories: [],
        displayMode: {
            rawValue: "inlay"
        },
        htmlDescription: "",
        icon: "",
        id: "",
        imageURL: "",
        name: "",
        orientation: {},
        position: {
            altitude: 0,
            description: undefined,
            latitude: 0,
            longitude: 0,
            scene: undefined
        },
        size: {
            sclae: 0,
            constantSizeDistant: 0
        },
        visibilityRamp: {
            fullyInvisible: 0,
            fullyVisible: 0,
            startInvisible: 0,
            startVisible: 0
        }
    };
    //console.log(data);
    data = JSON.parse(data);
    result.altitudeMode = data["altitudeMode"] 
    result.anchorMode = data["anchorMode"]
    result.htmlDescription = data["htmlDescription"]
    result.name = data["name"]
    result.imageURL = data["imageURL"]
    result.id = data["id"]
    result.icon = data["icon"]
    result.categories = data["categories"]
    result.position.altitude = data["position"]["altitude"]
    result.position.latitude = data["position"]["latitude"]
    result.position.longitude = data["position"]["longitude"]
    result.position.scene = data["position"]["scene"]
    result.size.sclae = data["size"]["scale"]
    result.size.constantSizeDistant = data["size"]["constantSizeDistant"]
    result.size.sclae = data["size"]["scale"]
    //TODO result.orientation = data["orientation"]
    result.displayMode = data["displayMode"]
    result.visibilityRamp.fullyInvisible = data["visibilityRamp"]["fullyInvisible"]
    result.visibilityRamp.fullyVisible = data["visibilityRamp"]["fullyVisible"]
    result.visibilityRamp.startInvisible = data["visibilityRamp"]["startInvisible"]
    result.visibilityRamp.startVisible = data["visibilityRamp"]["startVisible"]
    return result;
}

  return (
    <MapView
      ref={r}
      ///EN COURS
      onDataReturned={_onDataReturned}
      ///
      style={props.style}
      mapHash={props.mapHash}
      mapPath={props.mapPath}
      mapSecret={props.mapSecret}
      listeners={props.listeners}
      promptToDownload={props.promptToDownload}
    />
  );

  
});
