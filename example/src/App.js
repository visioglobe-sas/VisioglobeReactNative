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

export default function App() {
  const ref = React.useRef(null);


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
    if (ref.current) {
      ref.current.setPoisColor();
    }
  };
  ////////////////////////////////////////////////////////////////////////////////////

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
    const origin = 'B2-UL01-ID0002';
    const destination = ['B3-UL01-ID0022', 'B2-UL01-ID0081'];
    ref.current.computeRoute(origin, destination);
  };

  const getVersion = async () => {
    //console.log(ref.current.getVersion());
    let promise = ref.current.getVersion()
    promise.then((value) => {
      alert(value);
    });
  }

  return (
    <>
    <VisioMapView
        style={{
          // converts dpi to px, provide desired height
          height:
            Platform.OS === 'ios'
              ? 400
              : 400,
          // converts dpi to px, provide desired width
          width:
            Platform.OS === 'ios'
              ? Dimensions.get('window').width
              : PixelRatio.getPixelSizeForLayoutSize(
                  Dimensions.get('window').width
                ),
                backgroundColor: 'red',
        }}
        // hash="dev-c346e782b88c53bb6c891f439dbcc7e2cde0aaab"
        mapHash="dev-c346e782b88c53bb6c891f439dbcc7e2cde0aaab"
        mapPath="path"
        mapSecret={0}
        ref={ref}
      />
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
          onPress={() => setSelectorViewVisible(boolean)}
        >
          <Text style={styles.text}>{textSetSelectorViewButton}</Text>
        </TouchableOpacity>
        </View>
      <View style={[styles.container, {
      flexDirection: "row"
    }]}>
        <TouchableOpacity style={styles.button} 
        onPress={() => customMethod()}>
          <Text style={styles.text}>TO DO </Text>
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
      </>
  );
}

const styles = StyleSheet.create({
  container: {
    display: 'flex',
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
