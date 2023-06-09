#import <React/RCTLog.h>
#import <React/RCTUIManager.h>
#import <React/RCTViewManager.h>

@interface RTNMapViewManager : RCTViewManager
@end

@implementation RTNMapViewManager

RCT_EXPORT_MODULE(RTNMapView)

RCT_EXPORT_VIEW_PROPERTY(mapPath, NSString)
RCT_EXPORT_VIEW_PROPERTY(mapHash, NSString)
RCT_EXPORT_VIEW_PROPERTY(mapSecretCode, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(mapServerURL, NSString)

@end