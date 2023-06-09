import type {ViewProps} from 'ViewPropTypes';
import type {HostComponent} from 'react-native';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';

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