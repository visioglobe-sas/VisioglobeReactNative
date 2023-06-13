/*import React from 'react';
import { ViewProps } from 'react-native/types';
import RTNMapView, { Commands } from "./RTNMapViewNativeComponent";

type Props = ViewProps & {
    mapPath: string
    maphash: string
    mapSecretCode : Int32
    mapServerUrl : string
};

type ComponentRef = InstanceType<typeof RTNMapView>;

export default class MapView extends React.Component<Props> {
  ref = React.createRef<ComponentRef>();

  // ADD THIS TRIGGER METHOD
  trigger = () => {
    if(this.ref.current) {
      Commands.trigger(this.ref.current);
    }
  }

  render() {
    return <RTNMapView {...this.props} ref={this.ref}/>
  }
};*/