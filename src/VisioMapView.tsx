import React, { forwardRef, useRef, useImperativeHandle } from 'react';
import {
  NativeModules,
  findNodeHandle,
  Platform,
  UIManager,
} from 'react-native';
import MapView, { Commands } from './VisioMapViewNativeComponent';
import { VMCameraUpdate, VMRouteRequest } from './VisioTypes';

const MODULE =
  Platform.OS === 'android' ? 'VisioglobeModule' : 'VisioMapViewManager';

const createFragment = (viewId: number | null) =>
  UIManager.dispatchViewManagerCommand(
    viewId,
    // we are calling the 'create' command
    UIManager.VisioMapViewManager.Commands.create.toString(),
    [viewId]
  );

export const VisioMapView = forwardRef((props, ref) => {
  const r = useRef();

  const _setExcludedAttributes = (value: string[]) => {
    Commands.setExcludedAttributes(r.current, value);
}

const _setExcludedModalities = (value: string[]) => {
    Commands.setExcludedModalities(r.current, value);
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
    Commands.setPois(r.current, data);
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

  const _animateCamera = (values: VMCameraUpdate) => {
    Commands.animateCamera(r.current,values,10,undefined);
  }

  const _getVersion = () => {
    let requestId = this._nextRequestId++;
    let requestMap = this._requestMap;
    let promise = new Promise(function (resolve, reject) {
      requestMap[requestId] = { resolve: resolve, reject: reject };
    });
    Commands.getVersion(r.current, requestId);

    /**promise.then((value) => {
      console.log(value);
    });**/
    return (promise);
  };

  /// EN COURS

  // Generate a queue for the next asked promises
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

////

  /*const _getDataSDKVersion = () => {
    Commands.getDataSDKVersion(r.current);
  }*/

  const _getMinDataSDKVersion = () =>{
    Commands.getMinDataSDKVersion(r.current);
  };

  const _updateCamera =  (update : VMCameraUpdate) => ({


  });

  useImperativeHandle(ref, () => ({
    customFunctionToCall: _customFunctionToCall,
    updateCamera: _updateCamera,
    animateCamera: _animateCamera,
    setPois: _setPois,
    resetPoisColor: _resetPoisColor,
    setPoisColor: _setPoisColor,
    computeRoute: _computeRoute,
    getPoiPosition: _getPoiPosition,
    setSelectorViewVisible: _setSelectorViewVisible,
    getVersion: _getVersion, 
    unloadMapView: _unloadMapView,
    unloadMapData: _unloadMapData,
    //getDataSDKVersion: _getDataSDKVersion, 
    getMinDataSDKVersion: _getMinDataSDKVersion,
    setExcludedAttributes: _setExcludedAttributes,
    setExcludedModalities: _setExcludedModalities,
    setLocationTrackingButtonToggleModes: _setLocationTrackingButtonToggleModes,
    setNavigationHeaderViewVisible: _setNavigationHeaderViewVisible,
    setCompassHeadingMarkerVisible: _setCompassHeadingMarkerVisible,
    showPoiInfo: _showPoiInfo,
    setStatisticsLog: _setStatisticsLog,
    setStatisticsLogCamera: _setStatisticsLogCamera,
    setStatisticsLogInterest: _setStatisticsLogInterest,
    setStatisticsLogLocation: _setStatisticsLogLocation,
    setStatisticsTrackedPoiIDs: _setStatisticsTrackedPoiIDs,
    setCompass: _setCompass,
  }));

  React.useEffect(() => {
    if (Platform.OS === 'android') {
      const viewId = findNodeHandle(r.current);
      console.debug('======> VIEW ID FROM MODULE:', viewId);
      createFragment(viewId);
    }
  }, []);

  return (
    <MapView
      ref={r}
      ///EN COURS
      onDataReturned={this._onDataReturned}
      ///
      style={{ ...props.style }}
      mapHash={props.mapHash}
      mapPath={props.mapPath}
      mapSecret={props.mapSecret}
      listeners={props.listeners}
      promptToDownload={props.promptToDownload}
    />
  );
});