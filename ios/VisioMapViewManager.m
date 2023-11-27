//
//  MapViewManager.m
//  Visioglobe
//
//  Created by Remi on 17/07/2023.
//  Copyright © 2023 Facebook. All rights reserved.
//

#import <React/RCTBridgeModule.h>
#import <React/RCTViewManager.h>

@interface RCT_EXTERN_MODULE(VisioMapViewManager, RCTViewManager)
+ (BOOL)requiresMainQueueSetup
{
  return NO;
}
RCT_EXPORT_VIEW_PROPERTY(mapHash, NSString)
RCT_EXPORT_VIEW_PROPERTY(mapPath, NSString)
RCT_EXPORT_VIEW_PROPERTY(mapSecret, NSNumber)
RCT_EXTERN_METHOD(customFunctionToCall: (nonnull NSNumber *) reactTag)
RCT_EXTERN_METHOD(setPois: (nonnull NSNumber *) reactTag
                  data: (nonnull NSString *)data)
RCT_EXTERN_METHOD(setPoisColor: (nonnull NSNumber *) reactTag
                  poiIDs: (nonnull NSArray<NSString *> *)poiIDs)
RCT_EXTERN_METHOD(resetPoisColor: (nonnull NSNumber *) reactTag)
RCT_EXTERN_METHOD(computeRoute: (nonnull NSNumber *) reactTag
                  origin: (nonnull NSString *) origin
                  destinations: (nonnull NSArray<NSString *> *) destinations)

RCT_EXTERN_METHOD(getVersion: (nonnull NSNumber *)reactTag
                  requestID: (nonnull NSNumber *) requestID)
RCT_EXTERN_METHOD(animateCamera : (nonnull NSNumber *)reactTag
                  data: (nonnull NSDictionary *) data
                  duration :(nonnull NSNumber*) duration);
RCT_EXTERN_METHOD(getCameraContext : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(updateCamera : (nonnull NSNumber *)reactTag
                  data: (nonnull NSDictionary *)data);
RCT_EXTERN_METHOD(animateScene : (nonnull NSNumber *)reactTag
                  data: (nonnull NSDictionary *)data);
RCT_EXTERN_METHOD(updateScene : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(createLocationFromLocation : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(createPositionFromLocation : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(getLocationTrackingMode : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(setLocationTrackingMode : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(getLocationTrackingButtonToggleModes : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(setLocationTrackingButtonToggleModes : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(getNavigationHeaderViewVisible : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(setNavigationHeaderViewVisible : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(getSelectorViewVisible : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(removePoi : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(removePois : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(getCategory : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(getPoi : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(getPoiBoundingPositions : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(queryAllCategoryIDs : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(queryAllPoiIDs : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(queryPois : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(resetPoiColor : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(setPoiSize : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(setPoisSize : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(setPoiPosition : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(setPoisPosition : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(showPoiInfo : (nonnull NSNumber *)reactTag);
RCT_EXTERN_METHOD(setCategories : (nonnull NSNumber *)reactTag);

RCT_EXTERN_METHOD(customFunctionToCall: () reactTag)
@end

