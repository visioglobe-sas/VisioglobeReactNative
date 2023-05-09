import type {TurboModule} from 'react-native/Libraries/TurboModule/RCTExport';
import {TurboModuleRegistry} from 'react-native';

export interface Spec extends TurboModule {
  VMEMapControllerBuilder(mapPath:string, mapSecretCode:number, mapHash:string, mapServerURL:string,promptUserToDownloadMap: boolean): Promise<any>;
}

export default TurboModuleRegistry.get<Spec>(
  'RTNVisioMoveEssential',
) as Spec | null;