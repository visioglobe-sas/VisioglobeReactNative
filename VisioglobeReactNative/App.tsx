/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import { SafeAreaView } from 'react-native-safe-area-context';
import { StyleSheet } from 'react-native/types';
import RTNMapView from 'rtn-map-view/js/RTNMapViewNativeComponent';

const styles = StyleSheet

const App: () => JSX.Element = () => {
  return (
      <RTNMapView
      style={{width: '100%', height: '100%'}} 
      mapHash='dev-m219a3bb03e5be89ce238a54e088aab2eb0d9b736'
      mapSecretCode={0}
  />
  );
};
export default App;