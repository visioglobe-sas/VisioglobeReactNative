import type { ViewProps, HostComponent } from 'react-native';
import { Platform } from 'react-native';
import './VisioTypes';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import codegenNativeCommands from 'react-native/Libraries/Utilities/codegenNativeCommands';
import type { Double } from 'react-native/Libraries/Types/CodegenTypes';
import type { VMAnimationCallback, VMCameraContext, VMCameraUpdate, VMCategory, VMLocation, VMLocationTrackingMode, VMPoi, VMPoiFilter, VMPoiFilterCallback, VMPoiSize, VMPosition, VMRouteRequest, VMSceneUpdate } from './VisioTypes';

export interface NativeProps extends ViewProps {
  mapPath?: string;
  mapSecret?: number;
  mapHash?: string;
  listeners?: [string]; //List of listener to instantiate with the view
  promptToDownload?: boolean;
  onDataReturned : any;
  // other props go here...
}

type NativeComponentType = HostComponent<NativeProps>;

interface NativeCommands {
  //OK ANDROID & iOS
  setPois: (
    viewRef: React.ElementRef<NativeComponentType>,
    data: string
  ) => void;

  unloadMapView:  ( 
    viewRef: React.ElementRef<NativeComponentType>
  ) => void;

  unloadMapData: (
    viewRef: React.ElementRef<NativeComponentType>
  ) => void;
  
  setExcludedAttributes: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: Array<string>
  ) => void;
  
  setExcludedModalities: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: Array<string>
  ) => void;

  updateLocation: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: VMLocation
  ) => void;
  
  setLocationTrackingButtonToggleModes: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: Array<string>
  ) => void;
  
  setNavigationHeaderViewVisible: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: boolean
  ) => void;
  
  setCompassHeadingMarkerVisible: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: boolean
  ) => void;
  
  showPoiInfo: (
    viewRef: React.ElementRef<NativeComponentType>,
    poiID: string
  ) => void;

  showSearchViewWithTitle: (
    viewRef: React.ElementRef<NativeComponentType>,
    title: string
  ) => void;
  
  setStatisticsLog: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: boolean
  ) => void;
  
  setStatisticsLogCamera: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: boolean
  ) => void;
  
  setStatisticsLogInterest: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: boolean
  ) => void;
  
  setStatisticsLogLocation: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: boolean
  ) => void;
  
  setStatisticsTrackedPoiIDs: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: Array<string>
  ) => void;
  
  setCompass: (
    viewRef: React.ElementRef<NativeComponentType>,
    value: boolean
  ) => void;
  

  //To Update
  resetPoisColor: (viewRef: React.ElementRef<NativeComponentType>) => void;
      /**
     * resetPoisColor : (
      viewRef: React.ElementRef<NativeComponentType>, 
      poiIDs: [String]
      ) => Promise<[boolean]>;
      **/

  //To Update
  setPoisColor: (
    viewRef: React.ElementRef<NativeComponentType>,
    poiIDs: Array<string>
  ) => void;
  /**
     * setPoisColor : (
      viewRef: React.ElementRef<NativeComponentType>, 
      poiIDs: [String], 
      colors: [String]
      ) => Promise<[boolean]>;
    **/

  //ComputeROute to update
  computeRoute: (
    viewRef: React.ElementRef<NativeComponentType>,
    request : VMRouteRequest
  ) => void;
  //computeRoute : (viewRef: React.ElementRef<NativeComponentType>, routerequest: VMRouteRequest, callback: VMComputeRouteCallback?) => void;


  //To Update
  getPoiPosition: (
    viewRef: React.ElementRef<NativeComponentType>,
    poiID: string
  ) => VMPosition;

  //To Update
  setSelectorViewVisible: (
    viewRef: React.ElementRef<NativeComponentType>, 
    visible: boolean
    ) => void;

    //OK ANDROID Update iOs
    getVersion: (
      viewRef: React.ElementRef<NativeComponentType>, 
      //requestId: number
      ) => Promise<string>;
    
    //getDataSDKVersion: (viewRef: React.ElementRef<NativeComponentType>) => string;
    
    //TODO
    getMinDataSDKVersion: (
      viewRef: React.ElementRef<NativeComponentType>
      ) => string;

    /** TODO
     * Listeners will not be exposed as function but as component props
    setBuildingListener: (VMEBuildingListener) => void;
    setCameraListener: (VMECameraListener) => void;
    setLocationTrackingModeListener : (VMELocationTrackingModeListener) => void;
    setPoiListener: (VMEPoiListener) => void;
    setMapListener: (VMEMapListener) => void;
    **/

    //TODO ALL BELOW
    animateCamera: (
      viewRef: React.ElementRef<NativeComponentType>, 
      cameraupdate: VMCameraUpdate, 
      duration: Double, 
      //callback: VMAnimationCallback | undefined
      ) => void;

    getCameraContext: (
      viewRef: React.ElementRef<NativeComponentType>,
      ) => Promise<VMCameraContext>;

    updateCamera: (
      viewRef: React.ElementRef<NativeComponentType>, 
      update: VMCameraUpdate
      )=> void;

    animateScene : (
      viewRef: React.ElementRef<NativeComponentType>, 
      animate: VMSceneUpdate
      ) => void;

    updateScene : (
      viewRef: React.ElementRef<NativeComponentType>, 
      update: VMSceneUpdate
      ) => void;

    createLocationFromLocation : (
      viewRef: React.ElementRef<NativeComponentType>, 
      CLLocation: any
      ) => Promise<VMLocation>;

    createPositionFromLocation : (
      viewRef: React.ElementRef<NativeComponentType>, 
      CLLocation: any
      ) => Promise<VMPosition>;

    
    getLocationTrackingMode: (
      viewRef: React.ElementRef<NativeComponentType>
      ) => Promise<VMLocationTrackingMode>;

    /**LocationTrackingMode is an enum ts side and does not needs to exist
     * 
     * setLocationTrackingMode: (
      viewRef: React.ElementRef<NativeComponentType>, 
      trackingmode: VMLocationTrackingMode
      ) => void;
      **/

    getLocationTrackingButtonToggleModes: (
      viewRef: React.ElementRef<NativeComponentType>
      ) => Promise<[VMLocationTrackingMode]>;

    getNavigationHeaderViewVisible : (
      viewRef: React.ElementRef<NativeComponentType>
      ) => Promise<boolean>;

    getSelectorViewVisible : (
      viewRef: React.ElementRef<NativeComponentType>
      ) => Promise<boolean>;

    removePoi : (
      viewRef: React.ElementRef<NativeComponentType>, 
      poiID: String
      ) => Promise<boolean>;

    removePois : (
      viewRef: React.ElementRef<NativeComponentType>, 
      poiIDs: [String]
      ) => Promise<[boolean]>;

    getCategory : (
      viewRef: React.ElementRef<NativeComponentType>, 
      categoryID: String
      ) => Promise<VMCategory>;

    getPoi : (
      viewRef: React.ElementRef<NativeComponentType>, 
      poiID: String
      ) => VMPoi | null;

    getPoiBoundingPositions : (
      viewRef: React.ElementRef<NativeComponentType>, 
      poiID: String
      ) => Promise<[VMPosition]>;

    queryAllCategoryIDs : (
      viewRef: React.ElementRef<NativeComponentType>
      ) => Promise<[String]>;

    queryAllPoiIDs : (
      viewRef: React.ElementRef<NativeComponentType>
      ) => Promise<[String]>;

    queryPois : (
      viewRef: React.ElementRef<NativeComponentType>, 
      filter: VMPoiFilter, 
      callback: VMPoiFilterCallback | undefined
      ) => void;

    resetPoiColor : (
      viewRef: React.ElementRef<NativeComponentType>, 
      poiID: String
      ) => Promise<boolean>;

    setPoiSize : (
      viewRef: React.ElementRef<NativeComponentType>, 
      poiID: String, 
      size: VMPoiSize, 
      animated: boolean) => Promise<boolean>;

    setPoisSize : (
      viewRef: React.ElementRef<NativeComponentType>, 
      poiIDs: [String], 
      sizes: [VMPoiSize], 
      animated: [boolean]
      ) => Promise<[boolean]>;

    setPoiPosition : (
      viewRef: React.ElementRef<NativeComponentType>, 
      poiID: String, 
      position: VMPosition, 
      animated: boolean
      ) => Promise<boolean>;

    setPoisPosition : (
      viewRef: React.ElementRef<NativeComponentType>, 
      poiIDs: [String], 
      positions: [VMPosition], 
      animated: [boolean]
      ) => Promise<[boolean]>;

    setCategories : (
      viewRef: React.ElementRef<NativeComponentType>, 
      data: String
      ) => void;
}

export const Commands: NativeCommands = codegenNativeCommands<NativeCommands>({
  supportedCommands: [
    'setPois',
'resetPoisColor',
'computeRoute',
'setPoisColor',
'getPoiPosition',
'setSelectorViewVisible',
'getVersion',//android only
'animateCamera',
'getCameraContext',
'updateCamera',
'animateScene',
'updateScene',
'unloadMapData',
'unloadMapView',
'createLocationFromLocation',
'createPositionFromLocation',
'getLocationTrackingMode',
'getLocationTrackingButtonToggleModes',
'setLocationTrackingButtonToggleModes',
'getNavigationHeaderViewVisible',
'setNavigationHeaderViewVisible',
'getSelectorViewVisible',
'removePoi',
'removePois',
'getCategory',
'getPoi',
'getPoiBoundingPositions',
'queryAllCategoryIDs',
'queryAllPoiIDs',
'queryPois',
'resetPoiColor',
'setPoiSize',
'setPoisSize',
'setPoiPosition',
'setPoisPosition',
'showPoiInfo',
'setCategories',
'setExcludedAttributes',
'setExcludedModalities',
'setCompassHeadingMarkerVisible',
'setStatisticsLog',
'setStatisticsLogCamera',
'setStatisticsLogInterest',
'setStatisticsLogLocation',
'setStatisticsTrackedPoiIDs',
'setCompass',
'updateLocation',
'showSearchViewWithTitle',
    //'getDataSDKVersion', 
    //'getMinDataSDKVersion',
  ],
});

const VisioMapView = codegenNativeComponent<NativeProps>(
  Platform.OS === 'android' ? 'VisioMapViewManager' : 'VisioMapView'
) as HostComponent<NativeProps>;
export default VisioMapView;
