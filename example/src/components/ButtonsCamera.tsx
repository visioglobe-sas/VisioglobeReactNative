import ThreeStateButton from './ThreeStateButton';
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
import { VMPosition, VMLocation, VMCameraHeading, VMCameraPitch, VMCameraUpdate, VMViewModeType, pitchType } from '../../../src/VisioTypes';
import ButtonsIndoorPositionSystem from './ButtonsIndoorPositionSystem';


/**
 * 
 * @param ref 
 * @returns Buttons for getting started menu : display current location / mock location on map
 */

const ButtonsCamera = (ref : React.MutableRefObject<VisioMapView>) => {
    const buttonAnimate = ['Animate 1', 'Animate 2', 'Animate 3'];

    const handleClickAnimate = (currentState: any) => {
        switch (currentState) {
            case 1 : 
                const heading1 : VMCameraHeading = {
                current: true
              }
                const pitch1 : VMCameraPitch = {
                type: pitchType.default,
                pitch: -90
              }
                const values1 : VMCameraUpdate = {
                heading : heading1,
                paddingBottom: 50,
                paddingLeft: 50,
                paddingRight : 50,
                paddingTop : 50,
                pitch : pitch1,
                targets : ["B2-UL00"],
                viewMode : VMViewModeType.floor,
              }
                const animationDuration1 = 3
                ref.current.animateCamera(values1,animationDuration1);
            case 2 :
                const values2 : VMCameraUpdate = {
                    heading: {
                        heading: 15,
                        current: false
                    },
                    paddingBottom: 0,
                    paddingLeft: 0,
                    paddingRight: 0,
                    paddingTop: 0,
                    pitch: {
                        pitch: undefined,
                        type: pitchType.current
                    },
                    targets: ["B3-UL00"],
                    viewMode: VMViewModeType.global
                }
                const animationDuration2 = 6
                ref.current.animateCamera(values2,animationDuration2);
            case 3 : 
                const values3 : VMCameraUpdate = {
                  heading: {
                      current: true
                  },
                  paddingBottom: 0,
                  paddingLeft: 0,
                  paddingRight: 0,
                  paddingTop: 0,
                  pitch: {
                    type: pitchType.default,
                    pitch: -90
                  },
                  targets: ["B1-UL00"],
                  viewMode: VMViewModeType.global
                }
                const animationDuration3 = 2 
                ref.current.animateCamera(values3,animationDuration3);
        }
        // Do something with the current state, like sending it to an API, updating state, etc.
      };

    const buttonUpdate = ['Update 1', 'Update 2', 'Update 3'];

    const handleClickUpdate = (currentState: any) => {
        switch (currentState) {
            case 1 : 
                const heading1 : VMCameraHeading = {
                current: true
              }
                const pitch1 : VMCameraPitch = {
                type: pitchType.default,
                pitch: -90
              }
                const values1 : VMCameraUpdate = {
                heading : heading1,
                paddingBottom: 50,
                paddingLeft: 50,
                paddingRight : 50,
                paddingTop : 50,
                pitch : pitch1,
                targets : ["B2-UL00"],
                viewMode : VMViewModeType.floor,
              }
                const animationDuration1 = 3
                ref.current.updateCamera(values1);
            case 2 :
              const position2 : VMPosition = {
                altitude: 0.0,
                latitude: 45.74200,
                longitude: 4.88400
              }
                const values2 : VMCameraUpdate = {
                    heading: {
                        heading: 15,
                        current: false
                    },
                    paddingBottom: 0,
                    paddingLeft: 0,
                    paddingRight: 0,
                    paddingTop: 0,
                    pitch: {
                        pitch: undefined,
                        type: pitchType.current
                    },
                    targets: [position2],
                    viewMode: VMViewModeType.global
                }
                const animationDuration2 = 6
                ref.current.updateCamera(values2);
            case 3 : 
                const values3 : VMCameraUpdate = {
                  heading: {
                      current: true
                  },
                  paddingBottom: 0,
                  paddingLeft: 0,
                  paddingRight: 0,
                  paddingTop: 0,
                  pitch: {
                    type: pitchType.default,
                    pitch: -90
                  },
                  targets: ["B1-UL00"],
                  viewMode: VMViewModeType.global
                }
                const animationDuration3 = 2 
                ref.current.animateCamera(values3,animationDuration3);
        }
        // Do something with the current state, like sending it to an API, updating state, etc.
      };

    return (
        <View style={styles.container}>
            <ThreeStateButton buttonTexts={buttonAnimate} onClick={handleClickAnimate}/>
            <ThreeStateButton buttonTexts={buttonUpdate} onClick={handleClickUpdate}/>
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