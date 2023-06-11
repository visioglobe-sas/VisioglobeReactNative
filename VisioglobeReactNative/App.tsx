/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import {
  SafeAreaView
} from 'react-native';

import {MapView} from './MapView';


const App: () => JSX.Element = () => {
  return (
    <SafeAreaView>
      <MapView
      mapHash='dev-m219a3bb03e5be89ce238a54e088aab2eb0d9b736'
      mapPath=''
      mapServerUrl=''
      mapSecretCode={0}
    style={{width: 100, height: 100}}
  /></SafeAreaView>
    
  );
};
export default App;