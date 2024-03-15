import React from 'react';
import {
  StyleSheet,
  Button,
  View,
  SafeAreaView,
  Text,
  Alert,
} from 'react-native';
import VisioMapView from 'react-native-visioglobe';
import Geolocation from 'react-native-geolocation-service';
import { VMPosition, VMLocation } from '../../../src/VisioTypes';


/**
 * 
 * @param ref 
 * @returns Buttons for getting started menu : display current location / mock location on map
 */

const ButtonsIndoorPositionSystem = (ref : React.MutableRefObject<VisioMapView>) => {

  return (
    <View style={styles.container}>
      <Button
      title='Get Location'
      onPress={() => {
        Geolocation.getCurrentPosition(
          position => {
            const VMPos: VMPosition = {
              altitude: position.coords.altitude || 0.0 ,
              latitude: position.coords.latitude,
              longitude: position.coords.longitude
            }
            const VMLoc: VMLocation = {
              accuracy: position.coords.accuracy,
              bearing: 0.0,
              position : VMPos
            }
            console.log(position);
            return VMLoc
          },

          error => {
            console.log(error.code, error.message);
          },
          {enableHighAccuracy: true, timeout: 15000, maximumAge: 10000},
        );
      }
    } 
      />
    </View>
  );
};

const styles = StyleSheet.create({
    container: {
      flex: 1,
      alignItems: 'center',
      justifyContent: 'center',
    },
  });

  export default ButtonsIndoorPositionSystem;