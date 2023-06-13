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

  export interface Spec extends TurboModule {
    loadMapView(int: number): undefined;
  }
export default codegenNativeComponent<NativeProps>(
    'RTNMapView'
  ) as HostComponent<NativeProps>;
  
  // Add NativeCommands interface including trigger as the new 
// fabric native component method
interface NativeCommands {
  trigger: (
    viewRef: React.ElementRef<ComponentType>
  ) => void;
}
// Execute codegeNativeCommands function with proper supportedCommands 
// as shown below and export it
export const Commands: NativeCommands = codegenNativeCommands<NativeCommands>({
  supportedCommands: ['trigger'],
});