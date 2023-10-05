export type VMSceneContext = {
    buildingID?: string;
    description: string;
    floorID?: string;
};
export type VMPosition = {
    altitude: number;
    description?: string;
    latitude: number;
    longitude: number;
    scene: VMSceneContext;
};
export type VMLocation = {
    accuracy: number;
    bearing: number;
    description?: string;
    position: VMPosition;
};
export type VMPoi = {
    altitudeMode: VMPoiAltitudeMode;
    anchorMode: VMPoiAnchorMode;
    categories: string[];
    displayMode: VMPoiDisplayMode;
    htmlDescription: string;
    icon: string;
    id: string;
    imageURL: string;
    name: string;
    orientation: VMPoiOrientation;
    position: VMPosition;
    size: VMPoiSize;
    visibilityRamp: VMPoiVisibilityRamp;
};
export type VMPoiAltitudeMode = {
    rawValue: "absolute" | "relative";
};
export type VMPoiAnchorMode = {
    rawValue: "bottomcenter" | "bottomleft" | "bottomright" | "center" | "centerleft" | "centerright" | "topcenter" | "topleft" | "topright";
};
export type VMPoiDisplayMode = {
    rawValue: "inlay" | "overlay";
};
export type VMPoiOrientation = {};
export type VMPoiSize = {
    sclae: number;
    constantSizeDistant: number;
};
export type VMPoiVisibilityRamp = {
    fullyInvisible: number;
    fullyVisible: number;
    startInvisible: number;
    startVisible: number;
};
export type VMCameraHeading = {
    heading?: string | number;
    current?: boolean;
};
export declare enum pitchType {
    current = 0,
    default = 1
}
export type VMCameraPitch = {
    pitch?: number;
    type?: pitchType;
};
export declare enum VMViewModeType {
    floor = 0,
    global = 1,
    unkown = 2
}
export type VMCameraUpdate = {
    heading: VMCameraHeading;
    paddingBottom: number;
    paddingLeft: number;
    paddingRight: number;
    paddingTop: number;
    pitch: VMCameraPitch;
    targetPOIs?: string[];
    targetPositions?: VMPosition[];
    viewMode: VMViewModeType;
};
export type VMAnimationCallback = {};
export type VMCameraContext = {};
export type VMSceneUpdate = {};
export type VMLocationTrackingMode = {};
export type VMCategory = {};
export type VMPoiFilter = {};
export type VMPoiFilterCallback = {};
//# sourceMappingURL=VisioTypes.d.ts.map