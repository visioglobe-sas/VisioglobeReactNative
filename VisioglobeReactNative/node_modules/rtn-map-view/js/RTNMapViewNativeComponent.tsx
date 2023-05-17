import type {ViewProps} from 'ViewPropTypes';
import type {HostComponent} from 'react-native';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';

export interface NativeProps extends ViewProps {
  mapPath?: string;
  mapSecretCode?: string;
  mapHash?: string;
  mapServerUrl?: string;
  promptUserToDlMap?: string;
  // add other props here
}

export default codegenNativeComponent<NativeProps>(
  'RTNMapView',
) as HostComponent<NativeProps>;