import React, { MutableRefObject } from 'react';
import { ViewStyle } from 'react-native';

export type VMESceneContext = {
  buildingID? : string,
  description : string,
  floorID? : string
}

export type VMEPosition = {
  altitude: number,
  description? : string,
  latitude : number,
  longitude : number,
  scene : VMESceneContext
}

export type VMELocation =  {
  accuracy: number,
  bearing: number,
  description?: string,
  position: VMEPosition
}

export type VMEPoi = {
  altitudeMode: VMEPoiAltitudeMode,
  anchorMode: VMEPoiAnchorMode,
  categories: string[],
  displayMode: VMEPoiDisplayMode,
  htmlDescription: string,
  icon: string,
  id: string,
  imageURL: string,
  name: string,
  orientation: VMEPoiOrientation,
  position: VMEPosition,
  size: VMEPoiSize,
  visibilityRamp : VMEPoiVisibilityRamp
}

export type VMEPoiAltitudeMode = {
  rawValue : "absolute" | "relative"
}

export type VMEPoiAnchorMode = {
 rawValue : "bottomcenter" | "bottomleft" | "bottomright" | "center" | "centerleft" | "centerright" | "topcenter" | "topleft" | "topright"
}

export type VMEPoiDisplayMode = {
  rawValue: "inlay" | "overlay"
}

export type VMEPoiOrientation = {
  //method to implement
}

export type VMEPoiSize = {
  sclae : number,
  constantSizeDistant : number
  //Créer ou appeler hors de la vue de l'integrateur l'initialiser (voir doc SDK )
}

export type VMEPoiVisibilityRamp = {
  fullyInvisible: number,
  fullyVisible: number,
  startInvisible: number,
  startVisible: number,
  ///Créer ou appeler hors de la vue de l'integrateur l'initialiser (voir doc SDK )
}

export type VMEMapViewRef = {
  getPoiPosition: (poiID: string) => Promise<VMEPosition>; 
};

export type VMEMapViewProps = 
  {
    ref: MutableRefObject<VMEMapViewRef | undefined>;
    style: Pick<
    ViewStyle,
      | 'width'
      | 'height'
      | 'backgroundColor'
      | 'margin'
      | 'marginTop'
      | 'marginBottom'
      | 'marginLeft'
      | 'marginRight'
      | 'position'
      | 'left'
      | 'right'
      | 'bottom'
      | 'top'
      | 'minHeight'
      | 'maxHeight'
      | 'minWidth'
      | 'maxWidth'
      | 'opacity'
      | 'elevation'
      | 'zIndex'
      >;
    mapProps: {
      mapPath?: string;
      mapSecretCode?: number;
      mapHash?: string;
      mapServerUrl?: string;
  };
}
  
declare const VMEMapView: React.FC<VMEMapViewProps>;

export default VMEMapView;
  