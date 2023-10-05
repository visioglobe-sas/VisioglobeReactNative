"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = exports.Commands = void 0;
var _reactNative = require("react-native");
require("./VisioTypes");
var _codegenNativeComponent = _interopRequireDefault(require("react-native/Libraries/Utilities/codegenNativeComponent"));
var _codegenNativeCommands = _interopRequireDefault(require("react-native/Libraries/Utilities/codegenNativeCommands"));
function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }
const Commands = (0, _codegenNativeCommands.default)({
  supportedCommands: ['setPois', 'resetPoisColor', 'computeRoute', 'setPoisColor', 'getPoiPosition', 'setSelectorViewVisible', 'getVersion',
  //android only
  'animateCamera', 'getCameraContext', 'updateCamera', 'animateScene', 'updateScene', 'unloadMapData', 'unloadMapView', 'createLocationFromLocation', 'createPositionFromLocation', 'getLocationTrackingMode', 'setLocationTrackingMode', 'getLocationTrackingButtonToggleModes', 'setLocationTrackingButtonToggleModes', 'getNavigationHeaderViewVisible', 'setNavigationHeaderViewVisible', 'getSelectorViewVisible', 'removePoi', 'removePois', 'getCategory', 'getPoi', 'getPoiBoundingPositions', 'queryAllCategoryIDs', 'queryAllPoiIDs', 'queryPois', 'resetPoiColor', 'setPoiSize', 'setPoisSize', 'setPoiPosition', 'setPoisPosition', 'showPoiInfo', 'setCategories', 'setExcludedAttributes', 'setExcludedModalities', 'setCompassHeadingMarkerVisible', 'setStatisticsLog', 'setStatisticsLogCamera', 'setStatisticsLogInterest', 'setStatisticsLogLocation', 'setStatisticsTrackedPoiIDs', 'setCompass'
  //'getDataSDKVersion', 
  //'getMinDataSDKVersion',
  ]
});
exports.Commands = Commands;
const VisioMapView = (0, _codegenNativeComponent.default)(_reactNative.Platform.OS === 'android' ? 'VisioMapViewManager' : 'VisioMapView');
var _default = VisioMapView;
exports.default = _default;
//# sourceMappingURL=VisioMapViewNativeComponent.js.map