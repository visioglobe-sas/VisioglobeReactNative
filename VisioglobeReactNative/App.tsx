/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, { useEffect, useRef } from 'react';
import { SafeAreaView } from 'react-native-safe-area-context';
import { Button, StyleSheet, findNodeHandle } from 'react-native';
import RTNMapView from 'rtn-map-view/js/RTNMapViewNativeComponent';
import {Commands} from 'rtn-map-view/js/RTNMapViewNativeComponent';
const styles = StyleSheet


const App: () => JSX.Element = () => {
  const ref = useRef(null);
  const viewId = findNodeHandle(ref.current);

  function loadMapView(): void {
    console.log("cc isme" + typeof ref.current);
    if (ref != null) {
      Commands.loadMapView(ref.current);
    }
  }

  return (
    <SafeAreaView style={{width: '100%', height: '100%'}} >
      <RTNMapView
      ref={ref}
      mapHash='dev-m219a3bb03e5be89ce238a54e088aab2eb0d9b736'
      mapSecretCode={0}
      style={{width: '100%', height: '90%'}} 
  />
  <Button title='load' onPress={loadMapView}/>
  </SafeAreaView>
  );
};
export default App;