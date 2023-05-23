import type {TurboModule} from 'react-native/Libraries/TurboModule/RCTExport';
import {TurboModuleRegistry} from 'react-native';


export interface Spec extends TurboModule {
  init(mapPath?: string, mapSecretCode?: number, mapHash?: string, mapServerUrl?: string, promptUserToDlMap?: boolean): void;
  loadView(): void;
  loadData(): void;
}

export default TurboModuleRegistry.get<Spec>(
  'RTNViewController',
) as Spec | null;

