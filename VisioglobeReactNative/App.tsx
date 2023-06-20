/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, { useEffect, useRef } from 'react';
import { SafeAreaView } from 'react-native-safe-area-context';
import { Button, StyleSheet, findNodeHandle, ToastAndroid } from 'react-native';
import RTNMapView from 'rtn-map-view/js/RTNMapViewNativeComponent';
import {Commands} from 'rtn-map-view/js/RTNMapViewNativeComponent';
const styles = StyleSheet


const App: () => JSX.Element = () => {
  const ref = useRef(null);
  const viewId = findNodeHandle(ref.current);

  function loadMapView(): void {
    if (ref != null) {
      Commands.loadMapView(ref.current);
    }
  }

  function loadMapData(): void {
    if (ref != null) {
      Commands.loadMapData(ref.current);
      ToastAndroid.show('loading map data', ToastAndroid.SHORT);
    }
  }

  function unloadMapData(): void {
    if (ref != null) {
      Commands.unloadMapData(ref.current);
      ToastAndroid.show('unloading map data', ToastAndroid.SHORT);
    }
  }

  function unloadMapView(): void {
    if (ref != null) {
      Commands.unloadMapView(ref.current);
    }
  }

  let props = {
    mapHash : "dev-m219a3bb03e5be89ce238a54e088aab2eb0d9b736",
    mapSecretCode : 0,
      }

  return (
    <SafeAreaView style={{width: '100%', height: '100%'}} >
      <Button title='load data' onPress={loadMapData}/>
      <RTNMapView
      ref={ref}
      {...props}
      style={{width: '100%', height: '85%'}}
  />
  
  <Button title='load view' onPress={loadMapView}/>
  </SafeAreaView>
  );
};
export default App;