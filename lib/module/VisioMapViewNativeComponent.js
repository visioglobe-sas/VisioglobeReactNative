import { Platform } from 'react-native';
import './VisioTypes';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import codegenNativeCommands from 'react-native/Libraries/Utilities/codegenNativeCommands';
export const Commands = codegenNativeCommands({
  supportedCommands: ['setPois', 'resetPoisColor', 'computeRoute', 'setPoisColor', 'getPoiPosition', 'setSelectorViewVisible', 'getVersion',
  //android only
  'animateCamera', 'getCameraContext', 'updateCamera', 'animateScene', 'updateScene', 'unloadMapData', 'unloadMapView', 'createLocationFromLocation', 'createPositionFromLocation', 'getLocationTrackingMode', 'setLocationTrackingMode', 'getLocationTrackingButtonToggleModes', 'setLocationTrackingButtonToggleModes', 'getNavigationHeaderViewVisible', 'setNavigationHeaderViewVisible', 'getSelectorViewVisible', 'removePoi', 'removePois', 'getCategory', 'getPoi', 'getPoiBoundingPositions', 'queryAllCategoryIDs', 'queryAllPoiIDs', 'queryPois', 'resetPoiColor', 'setPoiSize', 'setPoisSize', 'setPoiPosition', 'setPoisPosition', 'showPoiInfo', 'setCategories', 'setExcludedAttributes', 'setExcludedModalities', 'setCompassHeadingMarkerVisible', 'setStatisticsLog', 'setStatisticsLogCamera', 'setStatisticsLogInterest', 'setStatisticsLogLocation', 'setStatisticsTrackedPoiIDs', 'setCompass'
  //'getDataSDKVersion', 
  //'getMinDataSDKVersion',
  ]
});

const VisioMapView = codegenNativeComponent(Platform.OS === 'android' ? 'VisioMapViewManager' : 'VisioMapView');
export default VisioMapView;
//# sourceMappingURL=VisioMapViewNativeComponent.js.map