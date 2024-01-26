import React, { MutableRefObject } from 'react';
import { ViewStyle } from 'react-native';

export type VMSceneContext = {
  buildingID : string | null,
  description? : string,
  floorID : string | null
}

export type VMPosition = {
  altitude: number,
  description? : string,
  latitude : number,
  longitude : number,
  scene? : VMSceneContext
}

export type VMLocation =  {
  accuracy: number,
  bearing: number,
  description?: string,
  position: VMPosition
}

export type VMPoi = {
  altitudeMode: VMPoiAltitudeMode,
  anchorMode: VMPoiAnchorMode,
  categories: string[],
  displayMode: VMPoiDisplayMode,
  htmlDescription: string,
  icon: string,
  id: string,
  imageURL: string,
  name: string,
  orientation: VMPoiOrientation,
  position: VMPosition,
  size: VMPoiSize,
  visibilityRamp : VMPoiVisibilityRamp
}

export type VMPoiAltitudeMode = {
  rawValue : "absolute" | "relative"
}

export type VMPoiAnchorMode = {
 rawValue : "bottomcenter" | "bottomleft" | "bottomright" | "center" | "centerleft" | "centerright" | "topcenter" | "topleft" | "topright"
}

export type VMPoiDisplayMode = {
  rawValue: "inlay" | "overlay"
}

export type VMPoiOrientation = {
  //method to implement
}

export type VMPoiSize = {
  sclae : number,
  constantSizeDistant : number
  //Créer ou appeler hors de la vue de l'integrateur l'initialiser (voir doc SDK )
}

export type VMPoiVisibilityRamp = {
  fullyInvisible: number,
  fullyVisible: number,
  startInvisible: number,
  startVisible: number,
  ///Créer ou appeler hors de la vue de l'integrateur l'initialiser (voir doc SDK )
}

export type VMCameraHeading = {
  heading?: string | number;//mandatory or numbre or current
  current: boolean;
}

export type VMCameraPitch = {
  pitch?: number;
  type?: pitchType;
}

export type VMCameraUpdate = {
  heading: VMCameraHeading;
  paddingBottom: number;
  paddingLeft: number;
  paddingRight: number;
  paddingTop: number;
  pitch: VMCameraPitch;
  targets: (string | VMPosition)[];
  viewMode: VMViewModeType;
}

export type VMAnimationCallback = {

}

export type VMCameraContext = {

}

export type VMSceneUpdate = { 
  viewMode: VMViewModeType,
  buildingID : string | null,
  floorID : string | null
}

export type VMCategory = { 
  icon: string
  id: string
  name: string
}

export type VMPoiFilter = {
  radius : number
  restrictToPoiIDs? : string[]
  restrictToTargetLayer : boolean
  target : any //TO DO??
}

export type VMPoiFilterCallback = {
  
}

export type VMRouteRequest = {
  animateAllRoute: boolean
  destinationsOrder: VMRouteDestinationsOrder
  isAccessible: boolean
  origin: VMPosition|String
  destinations : (VMPosition|String)[]
  requestType: VMERouteRequestType
}

export interface NativeProps extends ViewProps {
  mapPath?: string;
  mapSecret?: number;
  mapHash?: string;
  listeners?: [string]; //List of listener to instantiate with the view
  promptToDownload?: boolean;
  onDataReturned : any;
  onMapLoaded : any;
  // other props go here...
}

export const Commands: NativeCommands = codegenNativeCommands<NativeCommands>({
  supportedCommands: [
    'setPois',
    'computeRoute',
    'setPoisColor',
    'getPoiPosition',
    'setSelectorViewVisible',
    'animateCamera',
    'updateCamera',
    'unloadMapView',
    'loadMapView',
    'setNavigationHeaderViewVisible',
    'getSelectorViewVisible',
    'removePois',
    'getPoi',
    'showPoiInfo',
    'setCategories',
    'setExcludedAttributes',
    'setExcludedModalities',
    'updateLocation',
    'showSearchViewWithTitle'
  ],
});

const VisioMapView = codegenNativeComponent<NativeProps>(
  Platform.OS === 'android' ? 'VisioMapViewManager' : 'VisioMapView'
) as HostComponent<NativeProps>;
export default VisioMapView;
