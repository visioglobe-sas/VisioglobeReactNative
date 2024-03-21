import React, { useCallback, useEffect, useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, ScrollView, Alert } from 'react-native';
import CheckBox from '@react-native-community/checkbox';
import VisioMapView from 'react-native-visioglobe';
import { VMCameraHeading, VMCameraPitch, VMCameraUpdate, VMERouteRequestType, VMLocation, VMPosition, VMRouteDestinationsOrder, VMRouteRequest, VMSceneUpdate, VMViewModeType, pitchType } from '../../src/VisioTypes';
import { VMPoi } from '../../src/VisioTypes';
import {request, PERMISSIONS, check, RESULTS} from 'react-native-permissions';
import Geolocation from 'react-native-geolocation-service';
import ButtonsGettingStarted from './components/ButtonsGettingStarted';
import ButtonsIndoorPositionSystem from './components/ButtonsIndoorPositionSystem';
import { Steps } from './components/ThreeStateButton';

function useHookWithRefCallback() {
  const ref = React.useRef<VisioMapView>(null)
  const setRef = useCallback((node: string) => {
    if (ref.current) {
      // Make sure to cleanup any events/references added to the last instance
    }
    
    if (node) {
      // Check if a node is actually passed. Otherwise node would be null.
      // You can now do what you need to, addEventListeners, measure, etc.
    }
    
    // Save a reference to the node
    ref.current = node
  }, [])
  
  return [setRef]
}

export default function App(){
  const [ref] = useHookWithRefCallback()
  const mapHash="mc8f3fec89d2b7283d15cfcf4eb28a0517428f054"
  const mapPath="path"
  const mapSecret=0

/*  const [isDropdownOpen, setDropdownOpen] = useState(false);


  const [checkbox1, setCheckbox1] = useState(false);
  const [checkbox2, setCheckbox2] = useState(false);
  const [checkbox3, setCheckbox3] = useState(false);
  const [checkBoxString1, setCheckBoxString1] = useState("");
  const [checkBoxString2, setCheckBoxString2] = useState("");
  const [checkBoxString3, setCheckBoxString3] = useState("");
  const [opacity3, setOpacity3] = useState(1);
  const [disable3, setDisable3] = useState(false);
  const [location, setLocation] = useState(false);

  const resetCheckbox = () =>{
    setCheckbox1(false);
    setCheckbox2(false);
    setCheckbox3(false);
  }

  //Camera Section 
  const handleCameraClick = () =>{
    resetCheckbox();
    setCheckBoxString1("Animate Camera");
    setCheckBoxString2("Update Camera");
    setCheckBoxString3("");
    setDisable3(true);
  };

  const animateCamera = (values: VMCameraUpdate) => {
    if (ref.current) {
      ref.current.animateCamera(values,3 //duration here fixed to 3
      );
    }
  };

  const updateCamera = (values: VMCameraUpdate) => {
    if (ref.current) {
      ref.current.updateCamera(values);
    }
  };


  //Basic Section
  const handleBasicClick = () =>{
    resetCheckbox();
    setCheckBoxString1("Display props");
    setCheckBoxString2("Unload Map View");
    setCheckBoxString3("Reload Map View");
    setDisable3(false);
  }

  const showMapInfo = (mapHash : string, mapSecret : number, mapPath : string) => {
    Alert.alert("Current map props: ", " HASH :" + mapHash +",\n PATH : " + mapPath + ",\n SECRET CODE : " + mapSecret);
  };

  const unloadMapView = () => {
    if (ref.current) {
      ref.current.unloadMapView();
    }
  }

  const loadMapView = () => {
    if (ref.current) {
      ref.current.loadMapView();
    }
  } 


  //Search bar 
  const handleSearchClick = () => {
    resetCheckbox();
    setCheckBoxString1("Open SearchBar");
    setCheckBoxString2("Show POI");
    setCheckBoxString3("Get POI");
    setDisable3(false);
  }


  const showPoiInfo = (value : string) => {
    if (ref.current) {
      ref.current.showPoiInfo(value);
    }
  }

  const showSearchViewWithTitle = (values : string) => {
    if (ref.current) {
      ref.current.showSearchViewWithTitle(values);
    }
  }


  //Poi section
  const handlePOIClick = () =>{
    resetCheckbox();
    setCheckBoxString1("Create POIs");
    setCheckBoxString2("Remove POIs");
    setCheckBoxString3("Custom POIs");
    setDisable3(false);
  }

  const setPois = (values: string) => {
    if (ref.current) {
      ref.current.setPois(values);
    }
  };

  const removePois = (value : [string]) => {
    if (ref.current) {
      ref.current.removePois(value)
    }
  }


  //Routing Section
  const handleRoutingClick = () =>{
    resetCheckbox();
    setCheckBoxString1("Simple Route");
    setCheckBoxString2("Accessible Route");
    setCheckBoxString3("Optimal Route");
    setDisable3(false);
  }

  const computeRoute = (value : VMRouteRequest) => {
    if (ref.current) {
      ref.current.computeRoute(value);
    }
  }


  //Location Section
  const handleLocationClick = ( ) => {
    resetCheckbox();
    check(PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION)
    .then((result) => {
      switch (result) {
        case RESULTS.UNAVAILABLE:
          request(PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION).then((result) => {
            Alert.alert(result)
          });  
          break;
        case RESULTS.DENIED:
          request(PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION).then((result) => {
            Alert.alert(result)
          });  
          break;
        case RESULTS.LIMITED:
          request(PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION).then((result) => {
            if (result == RESULTS.GRANTED){
              setLocation(true)
            }
            Alert.alert(result)
          });  
          break;
        case RESULTS.GRANTED:
          setLocation(true)
          console.log('The permission is granted');
          break;
        case RESULTS.BLOCKED:
          request(PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION).then((result) => {
            Alert.alert(result)
          });  
          break;
      }
    })  
    setCheckBoxString1("Get VMLocation");
    setCheckBoxString2("Update Location");
    setCheckBoxString3("Update Location with new tracking mode");
    setDisable3(false);
  }
  const updateLocation = (value : VMLocation) => {
    if (ref.current) {
      ref.current.updateLocation(value);
    }
  }


  //Not used in this example
  const setCategories = (values : string) => {
    if (ref.current) {
      ref.current.setCategories(values);
    }
  }


  //Manage the first (left) button in the demo application
  const handleCheckbox1Change = () => {
    if (!checkbox1){
      setCheckbox2(false)
      setCheckbox3(false)
      if (checkBoxString1 === "Animate Camera") {
        const heading : VMCameraHeading = {
          current: true
        }
        const pitch : VMCameraPitch = {
          type: pitchType.default,
          pitch: -90
        }
        const values : VMCameraUpdate = {
          heading : heading,
          paddingBottom: 50,
          paddingLeft: 50,
          paddingRight : 50,
          paddingTop : 50,
          pitch : pitch,
          targets : ["B2-UL00"],
          viewMode : VMViewModeType.floor,
        }
        animateCamera(values)
      }

      if (checkBoxString1 === "Display props") {
        showMapInfo(mapHash,mapSecret,mapPath);
      }
    
      if (checkBoxString1 === "Simple Route") {
        const position: VMPosition = {
          altitude: 0.0,
          latitude: 45.7413,
          longitude: 4.88216
        }
        const value : VMRouteRequest = {
          animateAllRoute: false,
          destinationsOrder: VMRouteDestinationsOrder.closest,
          isAccessible: false,
          origin: position,
          destinations: ["B1-UL00-ID0034"],
          requestType: VMERouteRequestType.fatest
        }
        computeRoute(value);
      }
    
      if (checkBoxString1 === "Create POIs") {
        setPois('{"catCringe":{"name":"Black cat","icon":"https://upload.wikimedia.org/wikipedia/commons/4/4f/Kitty_emoji.png","description":"Black cat is here","feature":{"image":{"icon":"https://upload.wikimedia.org/wikipedia/commons/4/4f/Kitty_emoji.png","position":[45.74131,4.88216,0.0],"anchorMode":"bottomCenter","scale":15.0,"altitudeMode":"absolute"}}}} ');
      }
    
      if (checkBoxString1 === "Open SearchBar") {
        showSearchViewWithTitle("Search Bar View")
      }
    
      if (checkBoxString1 === "Get VMLocation") {
        //Get position
        if (location) {
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
              setLocation(false);
            },
            {enableHighAccuracy: true, timeout: 15000, maximumAge: 10000},
          );
        }
      }
    } else {


    }
  setCheckbox1(!checkbox1);
  };

  //Manage the second (middle) button in the demo application
  const handleCheckbox2Change = () => {
    if (!checkbox2){
      setCheckbox1(false)
      setCheckbox3(false)

      //Camera : Update Camera
      if (checkBoxString2 === "Update Camera") {
        const heading : VMCameraHeading = {
          current: true,
        }
        const pitch : VMCameraPitch = {
          pitch: -90,
        }
        const position : VMPosition = {
          altitude: 0.0,
          latitude: 45.74200,
          longitude: 4.88400
        }
        const values : VMCameraUpdate = {
          heading : heading,
          paddingBottom: 0,
          paddingLeft: 0,
          paddingRight : 0,
          paddingTop : 0,
          pitch : pitch,
          targets : [position],
          viewMode : VMViewModeType.floor,
        }
        updateCamera(values)
      }
  
      if (checkBoxString2 === "Unload Map View") {
        unloadMapView();
      }
      
      if (checkBoxString2 === "Accessible Route") {
        const value : VMRouteRequest = {
          animateAllRoute: false,
          destinationsOrder: VMRouteDestinationsOrder.closest,
          isAccessible: true,
          origin: "B3-UL01-ID0013",
          destinations: ["B3-UL00-ID0073"],
          requestType: VMERouteRequestType.fatest
        }
        computeRoute(value);
      }
      
      if (checkBoxString2 === "Remove POIs") {
        removePois(["catCringe"]);
      }
      
      if (checkBoxString2 === "Show POI") {
        showPoiInfo("B3-UL00-ID0073")
      }
      
      if (checkBoxString2 === "Update Location") {
        const location : VMLocation = {
          accuracy: 1.0,
          bearing: 1.0,
          position: {
            altitude: 0.0,
            latitude: 45.7413,
            longitude: 4.88216
          }
        }
        updateLocation(location)
      }
    }
      setCheckbox2(!checkbox2);
  };

  //Manage the third (right) button in the demo application
  const handleCheckbox3Change = () => {
    if (!checkbox3){
      setCheckbox1(false)
      setCheckbox2(false)
      if (checkBoxString3 === "Reload Map View") {
        loadMapView();
      }
      if (checkBoxString3 === "Set Categorie") {
        const value = '{"23": {"name": "The name","icon": "media/map/visio_island_essentials/category_service.png"}}';
        setCategories(value);
      }
      if (checkBoxString3 === "Optimal Route") {
        const position: VMPosition = {
          altitude: 0.0,
          latitude: 45.7413,
          longitude: 4.88216
        }
        const value : VMRouteRequest = {
          animateAllRoute: false,
          destinationsOrder: VMRouteDestinationsOrder.optimalFinishOnLast,
          isAccessible: false,
          origin: position,
          destinations: ["B1-UL00-ID0034","B3-UL01-ID0013"],
          requestType: VMERouteRequestType.shortest
        }
        computeRoute(value);
      }
      if (checkBoxString3 === "Get POI"){
        let res = ref.current.getPoi("B1-UL00-ID0034");
        res.then((value: VMPoi) => {
          //You have access to any existing field from VMPoi
          Alert.alert("getPoi name,description", "NAME: "+ value.name + "\nDESCRIPTION: " + value.htmlDescription);
        });
      }
      if (checkBoxString3 === "Custom POIs"){
        setPois('{"catCringe":{"name":"Lost Cat", "feature":{}}}')
      }
    }
    setCheckbox3(!checkbox3);
  };*/

  
  return (
    <View style={styles.container}>
        <VisioMapView
        ref={ref}
        style={styles.mapview}
        mapHash={mapHash}
        mapPath={mapPath}
        mapSecret={mapSecret}
        promptToDownload={true}
        listeners={["buildingListener","cameraListener","mapListener","locationtrackingmodeListener","poiListener"]}
        />
        <Steps status={'two'} text={''}></Steps>
    </View>
  );
};


const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection :'column',
  },
  mapview: {
    width:'100%',
    height:'80%',
    textAlign:'right',
  }
});

 App;
