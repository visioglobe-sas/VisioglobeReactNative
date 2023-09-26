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
export type VMCameraUpdate = {};
export type VMAnimationCallback = {};
export type VMCameraContext = {};
export type VMSceneUpdate = {};
export type VMLocationTrackingMode = {};
export type VMCategory = {};
export type VMPoiFilter = {};
export type VMPoiFilterCallback = {};
//# sourceMappingURL=VisioTypes.d.ts.map