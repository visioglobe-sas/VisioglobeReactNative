"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.VisioMapView = void 0;
var _react = _interopRequireWildcard(require("react"));
var _reactNative = require("react-native");
var _VisioMapViewNativeComponent = _interopRequireWildcard(require("./VisioMapViewNativeComponent"));
function _getRequireWildcardCache(nodeInterop) { if (typeof WeakMap !== "function") return null; var cacheBabelInterop = new WeakMap(); var cacheNodeInterop = new WeakMap(); return (_getRequireWildcardCache = function (nodeInterop) { return nodeInterop ? cacheNodeInterop : cacheBabelInterop; })(nodeInterop); }
function _interopRequireWildcard(obj, nodeInterop) { if (!nodeInterop && obj && obj.__esModule) { return obj; } if (obj === null || typeof obj !== "object" && typeof obj !== "function") { return { default: obj }; } var cache = _getRequireWildcardCache(nodeInterop); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (key !== "default" && Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj.default = obj; if (cache) { cache.set(obj, newObj); } return newObj; }
const MODULE = _reactNative.Platform.OS === 'android' ? 'VisioglobeModule' : 'VisioMapViewManager';
const createFragment = viewId => _reactNative.UIManager.dispatchViewManagerCommand(viewId,
// we are calling the 'create' command
_reactNative.UIManager.VisioMapViewManager.Commands.create.toString(), [viewId]);
const VisioMapView = /*#__PURE__*/(0, _react.forwardRef)((props, ref) => {
  const r = (0, _react.useRef)();
  const _setExcludedAttributes = value => {
    _VisioMapViewNativeComponent.Commands.setExcludedAttributes(r.current, value);
  };
  const _setExcludedModalities = value => {
    _VisioMapViewNativeComponent.Commands.setExcludedModalities(r.current, value);
  };
  const _setLocationTrackingButtonToggleModes = value => {
    _VisioMapViewNativeComponent.Commands.setLocationTrackingButtonToggleModes(r.current, value);
  };
  const _setNavigationHeaderViewVisible = value => {
    _VisioMapViewNativeComponent.Commands.setNavigationHeaderViewVisible(r.current, value);
  };
  const _setCompassHeadingMarkerVisible = value => {
    _VisioMapViewNativeComponent.Commands.setCompassHeadingMarkerVisible(r.current, value);
  };
  const _showPoiInfo = value => {
    _VisioMapViewNativeComponent.Commands.showPoiInfo(r.current, value);
  };
  const _setStatisticsLog = value => {
    _VisioMapViewNativeComponent.Commands.setStatisticsLog(r.current, value);
  };
  const _setStatisticsLogCamera = value => {
    _VisioMapViewNativeComponent.Commands.setStatisticsLogCamera(r.current, value);
  };
  const _setStatisticsLogInterest = value => {
    _VisioMapViewNativeComponent.Commands.setStatisticsLogInterest(r.current, value);
  };
  const _setStatisticsLogLocation = value => {
    _VisioMapViewNativeComponent.Commands.setStatisticsLogLocation(r.current, value);
  };
  const _setStatisticsTrackedPoiIDs = value => {
    _VisioMapViewNativeComponent.Commands.setStatisticsTrackedPoiIDs(r.current, value);
  };
  const _setCompass = value => {
    _VisioMapViewNativeComponent.Commands.setCompass(r.current, value);
  };
  const _customFunctionToCall = () => {
    _reactNative.NativeModules[MODULE].customFunctionToCall((0, _reactNative.findNodeHandle)(r.current));
  };
  const _setPois = data => {
    _VisioMapViewNativeComponent.Commands.setPois(r.current, data);
  };
  const _unloadMapData = () => {
    _VisioMapViewNativeComponent.Commands.unloadMapData(r.current);
  };
  const _unloadMapView = () => {
    _VisioMapViewNativeComponent.Commands.unloadMapView(r.current);
  };
  const _resetPoisColor = () => {
    _VisioMapViewNativeComponent.Commands.resetPoisColor(r.current);
  };
  const _setPoisColor = poiIDs => {
    _VisioMapViewNativeComponent.Commands.setPoisColor(r.current, poiIDs);
  };
  const _computeRoute = (origin, destination) => {
    _VisioMapViewNativeComponent.Commands.computeRoute(r.current, origin, destination);
  };
  const _getPoiPosition = poiID => {
    _VisioMapViewNativeComponent.Commands.getPoiPosition(r.current, poiID);
  };
  const _setSelectorViewVisible = visible => {
    _VisioMapViewNativeComponent.Commands.setSelectorViewVisible(r.current, visible);
  };
  const _animateCamera = values => {
    _VisioMapViewNativeComponent.Commands.animateCamera(r.current, values, 10, undefined);
  };
  const _getVersion = () => {
    let requestId = (void 0)._nextRequestId++;
    let requestMap = (void 0)._requestMap;
    let promise = new Promise(function (resolve, reject) {
      requestMap[requestId] = {
        resolve: resolve,
        reject: reject
      };
    });
    _VisioMapViewNativeComponent.Commands.getVersion(r.current, requestId);

    /**promise.then((value) => {
      console.log(value);
    });**/
    return promise;
  };

  /// EN COURS

  // Generate a queue for the next asked promises
  const _nextRequestId = 1;
  const _requestMap = new Map();
  const _onDataReturned = event => {
    let {
      requestId,
      result,
      error
    } = event.nativeEvent;
    let promise = (void 0)._requestMap[requestId];
    if (result) {
      // If it was successful, we resolve the promise.
      promise.resolve(result);
    } else {
      // Otherwise, we reject it.
      promise.reject(error);
    }
    // Finally, we clean up our request map.
    (void 0)._requestMap.delete(requestId);
  };

  ////

  /*const _getDataSDKVersion = () => {
    Commands.getDataSDKVersion(r.current);
  }*/

  const _getMinDataSDKVersion = () => {
    _VisioMapViewNativeComponent.Commands.getMinDataSDKVersion(r.current);
  };
  (0, _react.useImperativeHandle)(ref, () => ({
    customFunctionToCall: _customFunctionToCall,
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
    setCompass: _setCompass
  }));
  _react.default.useEffect(() => {
    if (_reactNative.Platform.OS === 'android') {
      const viewId = (0, _reactNative.findNodeHandle)(r.current);
      console.debug('======> VIEW ID FROM MODULE:', viewId);
      createFragment(viewId);
    }
  }, []);
  return /*#__PURE__*/_react.default.createElement(_VisioMapViewNativeComponent.default, {
    ref: r
    ///EN COURS
    ,
    onDataReturned: (void 0)._onDataReturned
    ///
    ,
    style: {
      ...props.style
    },
    mapHash: props.mapHash,
    mapPath: props.mapPath,
    mapSecret: props.mapSecret,
    listeners: props.listeners,
    promptToDownload: props.promptToDownload
  });
});
exports.VisioMapView = VisioMapView;
//# sourceMappingURL=VisioMapView.js.map