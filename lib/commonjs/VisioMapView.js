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
  const _customFunctionToCall = () => {
    _reactNative.NativeModules[MODULE].customFunctionToCall((0, _reactNative.findNodeHandle)(r.current));
  };
  const _setPois = data => {
    _VisioMapViewNativeComponent.Commands.setPois(r.current, data);
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
  _nextRequestId = 1;
  _requestMap = new Map();
  _onDataReturned = event => {
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
    setPois: _setPois,
    resetPoisColor: _resetPoisColor,
    setPoisColor: _setPoisColor,
    computeRoute: _computeRoute,
    getPoiPosition: _getPoiPosition,
    setSelectorViewVisible: _setSelectorViewVisible,
    getVersion: _getVersion,
    //getDataSDKVersion: _getDataSDKVersion, 
    getMinDataSDKVersion: _getMinDataSDKVersion
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
    mapSecret: props.mapSecret
  });
});
exports.VisioMapView = VisioMapView;
//# sourceMappingURL=VisioMapView.js.map