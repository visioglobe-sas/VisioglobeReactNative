/* This is here to implements future API */
import type {TurboModule} from 'react-native/Libraries/TurboModule/RCTExport';
import {TurboModuleRegistry} from 'react-native';

export type TypeVgSceneContext = {
    buildingID?: string
    floorID?: string
  }

export type TypeVGPosition = {
    latitude: number
    longitude: number
    altitude: number
    scene: TypeVgSceneContext
}

export type TypeVGLocation = {
    position: TypeVGPosition
    bearing: number
    accuracy: number
}

export type TypeVGCategory = {
    id: string
    name: string
    icon: string
}

export type TypeVGPoi = {
    id: string
    name: string
    icon: string
    categories?: TypeVGCategory
}

export type TypeVGSegment = {
    duration: number
    length: number
    maneuver: string
    floorTransition? : string
    path : TypeVGPosition[]
}

export type TypeVGRouteRequest = {
    origin: TypeVGPosition
    destinations: TypeVGPosition[]
}

export enum EnumeVGRouteType {
    FASTEST,
    SHORTEST
}

export type TypeVGRouteResult {
    destinations : TypeVGPosition[]
    duration : number
    length : number
    segments : TypeVGSegment[]
}

export interface Spec extends TurboModule {
  beginRouteRequest(type: EnumeVGRouteType, destinationOrder: string, accessible : boolean): Promise<TypeVGRouteRequest>;
  setOrigin(route : TypeVGRouteRequest, position: TypeVGPosition);
  addDestinations(route : TypeVGRouteRequest, position: TypeVGPosition);
  computeRoute(route: TypeVGRouteRequest, callback:((Any) => TypeVGRouteResult)):
}

export default TurboModuleRegistry.get<Spec>(
  'RTNMapViewModule',
) as Spec | null;