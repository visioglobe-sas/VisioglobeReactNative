/// <reference types="react" />
import type { ViewProps, HostComponent } from 'react-native';
import './VisioTypes';
import type { Double } from 'react-native/Libraries/Types/CodegenTypes';
import type { VMCameraContext, VMCameraUpdate, VMCategory, VMLocation, VMLocationTrackingMode, VMPoi, VMPoiFilter, VMPoiFilterCallback, VMPoiSize, VMPosition, VMSceneUpdate } from './VisioTypes';
export interface NativeProps extends ViewProps {
    mapPath?: string;
    mapSecret?: number;
    mapHash?: string;
    listeners?: [string];
    promptToDownload?: boolean;
}
type NativeComponentType = HostComponent<NativeProps>;
interface NativeCommands {
    setPois: (viewRef: React.ElementRef<NativeComponentType>, data: string) => void;
    unloadMapView: (viewRef: React.ElementRef<NativeComponentType>) => void;
    unloadMapData: (viewRef: React.ElementRef<NativeComponentType>) => void;
    setExcludedAttributes: (viewRef: React.ElementRef<NativeComponentType>, value: Array<string>) => void;
    setExcludedModalities: (viewRef: React.ElementRef<NativeComponentType>, value: Array<string>) => void;
    setLocationTrackingButtonToggleModes: (viewRef: React.ElementRef<NativeComponentType>, value: Array<string>) => void;
    setNavigationHeaderViewVisible: (viewRef: React.ElementRef<NativeComponentType>, value: boolean) => void;
    setCompassHeadingMarkerVisible: (viewRef: React.ElementRef<NativeComponentType>, value: boolean) => void;
    showPoiInfo: (viewRef: React.ElementRef<NativeComponentType>, poiID: string) => void;
    setStatisticsLog: (viewRef: React.ElementRef<NativeComponentType>, value: boolean) => void;
    setStatisticsLogCamera: (viewRef: React.ElementRef<NativeComponentType>, value: boolean) => void;
    setStatisticsLogInterest: (viewRef: React.ElementRef<NativeComponentType>, value: boolean) => void;
    setStatisticsLogLocation: (viewRef: React.ElementRef<NativeComponentType>, value: boolean) => void;
    setStatisticsTrackedPoiIDs: (viewRef: React.ElementRef<NativeComponentType>, value: Array<string>) => void;
    setCompass: (viewRef: React.ElementRef<NativeComponentType>, value: boolean) => void;
    resetPoisColor: (viewRef: React.ElementRef<NativeComponentType>) => void;
    /**
   * resetPoisColor : (
    viewRef: React.ElementRef<NativeComponentType>,
    poiIDs: [String]
    ) => Promise<[boolean]>;
    **/
    setPoisColor: (viewRef: React.ElementRef<NativeComponentType>, poiIDs: Array<string>) => void;
    /**
       * setPoisColor : (
        viewRef: React.ElementRef<NativeComponentType>,
        poiIDs: [String],
        colors: [String]
        ) => Promise<[boolean]>;
      **/
    computeRoute: (viewRef: React.ElementRef<NativeComponentType>, origin: string, destinations: Array<string>) => void;
    getPoiPosition: (viewRef: React.ElementRef<NativeComponentType>, poiID: string) => VMPosition;
    setSelectorViewVisible: (viewRef: React.ElementRef<NativeComponentType>, visible: boolean) => void;
    getVersion: (viewRef: React.ElementRef<NativeComponentType>, requestId: string) => Promise<string>;
    getMinDataSDKVersion: (viewRef: React.ElementRef<NativeComponentType>) => string;
    /** TODO
     * Listeners will not be exposed as function but as component props
    setBuildingListener: (VMEBuildingListener) => void;
    setCameraListener: (VMECameraListener) => void;
    setLocationTrackingModeListener : (VMELocationTrackingModeListener) => void;
    setPoiListener: (VMEPoiListener) => void;
    setMapListener: (VMEMapListener) => void;
    **/
    animateCamera: (viewRef: React.ElementRef<NativeComponentType>, cameraupdate: string[], //VMCameraUpdate, 
    duration: Double, callback: undefined) => void;
    getCameraContext: (viewRef: React.ElementRef<NativeComponentType>) => Promise<VMCameraContext>;
    updateCamera: (viewRef: React.ElementRef<NativeComponentType>, update: VMCameraUpdate) => void;
    animateScene: (viewRef: React.ElementRef<NativeComponentType>, animate: VMSceneUpdate) => void;
    updateScene: (viewRef: React.ElementRef<NativeComponentType>, update: VMSceneUpdate) => void;
    createLocationFromLocation: (viewRef: React.ElementRef<NativeComponentType>, CLLocation: any) => Promise<VMLocation>;
    createPositionFromLocation: (viewRef: React.ElementRef<NativeComponentType>, CLLocation: any) => Promise<VMPosition>;
    getLocationTrackingMode: (viewRef: React.ElementRef<NativeComponentType>) => Promise<VMLocationTrackingMode>;
    setLocationTrackingMode: (viewRef: React.ElementRef<NativeComponentType>, trackingmode: VMLocationTrackingMode) => void;
    getLocationTrackingButtonToggleModes: (viewRef: React.ElementRef<NativeComponentType>) => Promise<[VMLocationTrackingMode]>;
    getNavigationHeaderViewVisible: (viewRef: React.ElementRef<NativeComponentType>) => Promise<boolean>;
    getSelectorViewVisible: (viewRef: React.ElementRef<NativeComponentType>) => Promise<boolean>;
    removePoi: (viewRef: React.ElementRef<NativeComponentType>, poiID: String) => Promise<boolean>;
    removePois: (viewRef: React.ElementRef<NativeComponentType>, poiIDs: [String]) => Promise<[boolean]>;
    getCategory: (viewRef: React.ElementRef<NativeComponentType>, categoryID: String) => Promise<VMCategory>;
    getPoi: (viewRef: React.ElementRef<NativeComponentType>, poiID: String) => VMPoi | null;
    getPoiBoundingPositions: (viewRef: React.ElementRef<NativeComponentType>, poiID: String) => Promise<[VMPosition]>;
    queryAllCategoryIDs: (viewRef: React.ElementRef<NativeComponentType>) => Promise<[String]>;
    queryAllPoiIDs: (viewRef: React.ElementRef<NativeComponentType>) => Promise<[String]>;
    queryPois: (viewRef: React.ElementRef<NativeComponentType>, filter: VMPoiFilter, callback: VMPoiFilterCallback | undefined) => void;
    resetPoiColor: (viewRef: React.ElementRef<NativeComponentType>, poiID: String) => Promise<boolean>;
    setPoiSize: (viewRef: React.ElementRef<NativeComponentType>, poiID: String, size: VMPoiSize, animated: boolean) => Promise<boolean>;
    setPoisSize: (viewRef: React.ElementRef<NativeComponentType>, poiIDs: [String], sizes: [VMPoiSize], animated: [boolean]) => Promise<[boolean]>;
    setPoiPosition: (viewRef: React.ElementRef<NativeComponentType>, poiID: String, position: VMPosition, animated: boolean) => Promise<boolean>;
    setPoisPosition: (viewRef: React.ElementRef<NativeComponentType>, poiIDs: [String], positions: [VMPosition], animated: [boolean]) => Promise<[boolean]>;
    setCategories: (viewRef: React.ElementRef<NativeComponentType>, data: String) => Promise<[String: any]>;
}
export declare const Commands: NativeCommands;
declare const VisioMapView: HostComponent<NativeProps>;
export default VisioMapView;
//# sourceMappingURL=VisioMapViewNativeComponent.d.ts.map