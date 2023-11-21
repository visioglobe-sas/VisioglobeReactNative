/* eslint-disable react-native/no-inline-styles */
import React, { useState } from 'react';
import {
  View,
  StyleSheet,
  TouchableOpacity,
  Text,
  PixelRatio,
  Dimensions,
  Platform,
} from 'react-native';
import VisioMapView from 'react-native-visioglobe';
import { VMCameraHeading, VMCameraPitch, VMCameraUpdate, VMERouteRequestType, VMRouteDestinationsOrder, VMRouteRequest, VMViewModeType } from '../../src/VisioTypes';

export default function App() {
  const ref = React.useRef<VisioMapView>(null);


  ///////////////////////// Activate and deactivate selector view /////////////////////////
  var boolean = false;

  const [textSetSelectorViewButton,setSelectorViewText] = useState("Stop Selector View"); 

  const onPressSetSelectorView = () => {
    if (boolean === false){
      setSelectorViewText("Use Selector View");
    } else {
      setSelectorViewText("Stop Selector View");
    }
  }

  const setSelectorViewVisible = () => {
    onPressSetSelectorView();
    ref.current.setSelectorViewVisible(boolean);
    boolean = !boolean;
  }
  ///////////////////////////////////////////////////////////////////////////////////////



  ///////////////////////// Button text for setPois /////////////////////////////////////
  const [textSetPoisButton,setPoisText] = useState("Set cat POIs"); 

  const onPressSetPois = () => {
    if (textSetPoisButton === "Set cat POIs"){
      setPoisText("Remove cat POIs");
    } else {
      setPoisText("Set cat POIs");
    }
  }

  const setPois = () => {
    // do something
    const greenCatData =
    ' {"catCringe":{"name":"Black cat","icon":"https://upload.wikimedia.org/wikipedia/commons/4/4f/Kitty_emoji.png","description":"Black cat is here","features":{"image":{"icon":"https://upload.wikimedia.org/wikipedia/commons/4/4f/Kitty_emoji.png","position":[45.74131,4.88216,0.0],"anchorMode":"bottomCenter","scale":15.0,"altitudeMode":"absolute"}}}} ';
    onPressSetPois();
    if (textSetPoisButton === "Set cat POIs"){
      ref.current.setPois(greenCatData);
    } else {
      //removePoi native method
    }
  };
  ////////////////////////////////////////////////////////////////////////////////////


  ////////////////////////////// Test Method /////////////////////////////////////////
  const customMethod = () => {
    if (ref.current != null) {
      ref.current.setPoisColor();
    }
  };
  ////////////////////////////////////////////////////////////////////////////////////


  const animateCamera = () => {
    const heading : VMCameraHeading = {
      current: true
    }
    const pitch : VMCameraPitch = {
      pitch : -30,
    }
    const values : VMCameraUpdate = {
      heading : heading,
      paddingBottom: 50,
      paddingLeft: 60,
      paddingRight : 50,
      paddingTop : 50,
      pitch : pitch,
      targets : ["B2-UL00"],
      viewMode : VMViewModeType.floor,
    }
    if (ref.current) {
      ref.current.animateCamera(values,5,undefined);
    }
  };

  const resetPoisColor = () => {
    // do something
    ref.current.resetPoisColor();
  };

  const setPoisColor = () => {
    // do something
    const data = ['B1-UL0-001', 'B1-UL0-002', 'B1-UL0-003'];
    ref.current.setPoisColor(data);
  };

  const computeRoute = () => {
    // do something
    const route : VMRouteRequest = {
      animateAllRoute: false,
      destinationsOrder: VMRouteDestinationsOrder.optimalFinishOnLast,
      isAccessible: true,
      origin: "B1-UL00-ID0039",
      destinations: ["B4-UL05-ID0032", "B2-LL01-ID0011", "B3-UL00-ID0070"],
      requestType: VMERouteRequestType.fatest
    }
    ref.current.computeRoute(route);
  };

  const getVersion = async () => {
    //console.log(ref.current.getVersion());
    let promise = ref.current.getVersion()
    //promise.then((value: string) => {
      //console.log(value);
    };

  return (
    <View style={{display: "flex", flex:1}}>
    <VisioMapView
        style={{
        flex:1
        }}
        mapHash="mc8f3fec89d2b7283d15cfcf4eb28a0517428f054"
        mapPath="path"
        mapSecret={0}
        ref={ref}
        promptToDownload={true}
        listeners={["buildingListener","cameraListener","mapListener","locationtrackingmodeListener","poiListener"]}
      />
      <View style={{flexDirection: "column",flexShrink:1, flex: 0}}>
      <View style={[styles.container, {
      flexDirection: "row"
    }]}>
        <TouchableOpacity style={styles.button} onPress={() => getVersion()}>
          <Text style={styles.text}>Display SDK Version</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.button} onPress={() => setPois()}>
          <Text style={styles.text}> {textSetPoisButton}</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={styles.button}
          onPress={() => animateCamera()}
        >
          <Text style={styles.text}>{"Animate camera"}</Text>
        </TouchableOpacity>

        </View>

      <View style={[styles.container, {
      flexDirection: "row"
    }]}>
        <TouchableOpacity style={styles.button} 
        onPress={() => computeRoute()}>
          <Text style={styles.text}>TESTING </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.button}
          onPress={() => customMethod()}
        >
          <Text style={styles.text}>TO DO </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.button}
          onPress={() => customMethod()}
        >
          <Text style={styles.text}>TO DO</Text>
        </TouchableOpacity>
      </View>
      </View>
      </View>
  );
}

const styles = StyleSheet.create({
  container: {
    /*display: 'flex',*/
    backgroundColor: '#90EE90',
    alignItems: 'center',
    // justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  button: {
    width: "23%",
    padding: 20,
    borderRadius: 10,
    margin: '4%',
    backgroundColor: 'green',
  },
  closeButton: {
    position: 'absolute',
    zIndex: 200,
    backgroundColor: 'red',
    top: 100,
    alignSelf: 'center',
  },
  text: {
    color: 'white',
    fontWeight: 'bold',
  },
});
