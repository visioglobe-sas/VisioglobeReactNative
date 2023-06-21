import type {ViewProps} from 'ViewPropTypes';
import type {HostComponent} from 'react-native';
import type {ComponentType} from 'react';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import codegenNativeCommands from 'react-native/Libraries/Utilities/codegenNativeCommands';
import React from 'react';


export interface NativeProps extends ViewProps {
    filePath? : string;
    mapPath?: string; 
    mapSecretCode?: Int32; 
    mapHash?: string; 
    mapServerUrl?: string;
  }
  
export default codegenNativeComponent<NativeProps>(
    'RTNMapView'
  ) as HostComponent<NativeProps>;


interface NativeCommands {
  loadMapView: (
) => void;
  }

export const Commands: NativeCommands =
  codegenNativeCommands<NativeCommands>({
    supportedCommands: ['loadMapView'],
  });
  