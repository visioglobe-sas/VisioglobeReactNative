import React, { MutableRefObject } from 'react';
import { ViewStyle } from 'react-native';

export type VMEMapViewRef = {
  customFunctionToCall: () => void;
  setPois: (data: string) => void;
  resetPoisColor: () => void;
  setPoisColor: (poiIDs: Array<string>) => void;
  computeRoute: (origin: string, destinations: Array<string>) => void;
  getPoiPosition: (poiID: string) => void;
};

export type VMEMapViewProp =
  | ({
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
    } & { mapPath: string; mapSecret: number })
  | { mapHash: string };

declare const VisioMapView: React.FC<VMEMapViewProp>;
export default VisioMapView;
