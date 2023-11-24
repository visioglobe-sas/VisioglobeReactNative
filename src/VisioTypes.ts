export type VMSceneContext = {
  buildingID : string | null,
  description : string,
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
export enum pitchType {
  current,
  default,
}

export type VMCameraPitch = {
  pitch: number;
  type?: pitchType;
}

export enum VMViewModeType {
  floor,
  global,
  unkown,
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

}

export enum VMLocationTrackingMode {
  custom,
  follow,
  none
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

export enum VMRouteDestinationsOrder {
  closest,
  inOrder,
  optimal,
  optimalFinishOnLast
}
 
export enum VMERouteRequestType {
  fatest,
  shortest
}

