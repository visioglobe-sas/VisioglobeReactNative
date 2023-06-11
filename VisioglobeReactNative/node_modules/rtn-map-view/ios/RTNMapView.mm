#import "RTNCenteredText.h"

#import <react/renderer/components/RTNMapViewSpecs/ComponentDescriptors.h>
#import <react/renderer/components/RTNMapViewSpecs/EventEmitters.h>
#import <react/renderer/components/RTNMapViewSpecs/Props.h>
#import <react/renderer/components/RTNMapViewSpecs/RCTComponentViewHelpers.h>

#import "RCTFabricComponentsPlugins.h"

using namespace facebook::react;

@interface RTNMapView() <RCTRTNMapViewProtocol>
@end

@implementation RTNMapView {
  UIView *_view;
  UILabel *_hash;
  UILabel *_path;
  UILabel *_serverurl;

}

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
  return concreteComponentDescriptorProvider<RTNMapViewComponentDescriptor>();
}

- (instancetype)initWithFrame:(CGRect)frame
{
  if (self = [super initWithFrame:frame]) {
    static const auto defaultProps = std::make_shared<const RTNMapViewProps>();
    _props = defaultProps;

    _view = [[UIView alloc] init];
    _view.backgroundColor = [UIColor redColor];

    _hash = [[UILabel alloc] init];
    _hash.text = @"";

    _path = [[UILabel alloc] init];
    _path.text = @"";

    _label.translatesAutoresizingMaskIntoConstraints = false;
    [NSLayoutConstraint activateConstraints:@[
      [_label.leadingAnchor constraintEqualToAnchor:_view.leadingAnchor],
      [_label.topAnchor constraintEqualToAnchor:_view.topAnchor],
      [_label.trailingAnchor constraintEqualToAnchor:_view.trailingAnchor],
      [_label.bottomAnchor constraintEqualToAnchor:_view.bottomAnchor],
    ]];

    _label.textAlignment = NSTextAlignmentCenter;

    self.contentView = _view;
  }

  return self;
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
  const auto &oldViewProps = *std::static_pointer_cast<RTNMapViewProps const>(_props);
  const auto &newViewProps = *std::static_pointer_cast<RTNMapViewProps const>(props);

  if (oldViewProps.text != newViewProps.text) {
    _label.text = [[NSString alloc] initWithCString:newViewProps.text.c_str() encoding:NSASCIIStringEncoding];
  }

  [super updateProps:props oldProps:oldProps];
}

@end

Class<RCTComponentViewProtocol> RTNMapViewCls(void)
{
  return RTNMapView.class;
}