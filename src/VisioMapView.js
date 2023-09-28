import React, { forwardRef, useRef, useImperativeHandle } from 'react';
import {
  NativeModules,
  findNodeHandle,
  Platform,
  UIManager,
} from 'react-native';
import MapView, { Commands } from './VisioMapViewNativeComponent';

const MODULE =
  Platform.OS === 'android' ? 'VisioglobeModule' : 'VisioMapViewManager';

const createFragment = (viewId) =>
  UIManager.dispatchViewManagerCommand(
    viewId,
    // we are calling the 'create' command
    UIManager.VisioMapViewManager.Commands.create.toString(),
    [viewId]
  );

export const VisioMapView = forwardRef((props, ref) => {
  const r = useRef();

  const _setExcludedAttributes = (value) => {
    Commands.setExcludedAttributes(r.current, value);
}

const _setExcludedModalities = (value) => {
    Commands.setExcludedModalities(r.current, value);
}

const _setLocationTrackingButtonToggleModes = (value) => {
    Commands.setLocationTrackingButtonToggleModes(r.current, value);
}

const _setNavigationHeaderViewVisible = (value) => {
    Commands.setNavigationHeaderViewVisible(r.current, value);
}

const _setCompassHeadingMarkerVisible = (value) => {
    Commands.setCompassHeadingMarkerVisible(r.current, value);
}

const _showPoiInfo = (value) => {
    Commands.showPoiInfo(r.current, value);
}

const _setStatisticsLog = (value) => {
    Commands.setStatisticsLog(r.current, value);
}

const _setStatisticsLogCamera = (value) => {
    Commands.setStatisticsLogCamera(r.current, value);
}

const _setStatisticsLogInterest = (value) => {
    Commands.setStatisticsLogInterest(r.current, value);
}

const _setStatisticsLogLocation = (value) => {
    Commands.setStatisticsLogLocation(r.current, value);
}

const _setStatisticsTrackedPoiIDs = (value) => {
    Commands.setStatisticsTrackedPoiIDs(r.current, value);
}

const _setCompass = (value) => {
    Commands.setCompass(r.current, value);
}

  const _customFunctionToCall = () => {
    NativeModules[MODULE].customFunctionToCall(findNodeHandle(r.current));
  };

  const _setPois = (data) => {
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

  const _setPoisColor = (poiIDs) => {
    Commands.setPoisColor(r.current, poiIDs);
  };

  const _computeRoute = (origin, destination) => {
    Commands.computeRoute(r.current, origin, destination);
  };

  const _getPoiPosition = (poiID) => {
    Commands.getPoiPosition(r.current, poiID);
  };

  const _setSelectorViewVisible = (visible) => {
    Commands.setSelectorViewVisible(r.current, visible);
  };

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
  _nextRequestId = 1;

  _requestMap = new Map();

  _onDataReturned = (event) => {
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

  useImperativeHandle(ref, () => ({
    customFunctionToCall: _customFunctionToCall,
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
