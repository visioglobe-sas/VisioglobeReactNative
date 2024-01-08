import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, ScrollView, Alert } from 'react-native';
import CheckBox from '@react-native-community/checkbox';
import VisioMapView from 'react-native-visioglobe';
import { VMCameraHeading, VMCameraPitch, VMCameraUpdate, VMERouteRequestType, VMLocation, VMPosition, VMRouteDestinationsOrder, VMRouteRequest, VMSceneUpdate, VMViewModeType, pitchType } from '../../src/VisioTypes';


export default function App(){
  const ref = React.useRef<VisioMapView>(null);
  const [isDropdownOpen, setDropdownOpen] = useState(false);
  const [checkbox1, setCheckbox1] = useState(false);
  const [checkbox2, setCheckbox2] = useState(false);
  const [checkbox3, setCheckbox3] = useState(false);
  const [checkBoxString1, setCheckBoxString1] = useState("");
  const [checkBoxString2, setCheckBoxString2] = useState("");
  const [checkBoxString3, setCheckBoxString3] = useState("");
  const [opacity3, setOpacity3] = useState(1);
  const [disable3, setDisable3] = useState(false);
  const mapHash="mc8f3fec89d2b7283d15cfcf4eb28a0517428f054"
  const mapPath="path"
  const mapSecret=0

  //Camera Section 
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
  const showMapInfo = (mapHash : string, mapSecret : number, mapPath : string) => {
    Alert.alert("Current map props: ", " Hash :" + mapHash +",\n Path : " + mapPath + ",\n Secret Code : " + mapSecret);
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

  const setCategories = (values : string) => {
    if (ref.current) {
      ref.current.setCategories(values);
    }
  }

  const computeRoute = (value : VMRouteRequest) => {
    if (ref.current) {
      ref.current.computeRoute(value);
    }
  }

  //Poi section

  const getPoi = (value : string) => {
    if (ref.current) {
      ref.current.getPoi(value);
    }
  }

  const handleButtonClick = (buttonText: string) => {
    // Faites quelque chose avec le bouton cliqué
    console.log(`Bouton cliqué: ${buttonText}`);
    // Fermer le dropdown menu après avoir cliqué sur un bouton
    setDropdownOpen(false);
  };

  const handleCameraClick = () =>{
    setCheckBoxString1("Animate Camera");
    setCheckBoxString2("Update Camera");
    setCheckBoxString3("Camera Context");
  };




  const handleBasicClick = () =>{
    setCheckBoxString1("Display props");
    setCheckBoxString2("Unload Map View");
    setCheckBoxString3("Reload Map View");
  }

  const handleRoutingClick = () =>{
    setCheckBoxString1("Simple Route");
    setCheckBoxString2("Accessible Route");
    setCheckBoxString3("Optimal Route");
  }

  const handlePOIClick = () =>{
    setCheckBoxString1("Create POIs");
    setCheckBoxString2("Remove POIs");
    setCheckBoxString3("Custom POIs");
  }

  const handleSearchClick = () => {
    setCheckBoxString1("Open SearchBar");
    setCheckBoxString2("Show POI");
    setCheckBoxString3("Set Categorie");
  }

  const handleThemeClick = ( ) => {
    setCheckBoxString1("Set Theme");
    setCheckBoxString2("Overlay");
    setCheckBoxString3("");
  }

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
        console.log("La chaîne correspond à 'Create POIs'.");
      }
    
      if (checkBoxString1 === "Open SearchBar") {
        console.log("La chaîne correspond à 'Open SearchBar'.");
      }
    
      if (checkBoxString1 === "Set Theme") {
        console.log("La chaîne correspond à 'Set Theme'.");
      }
    } else {


    }
  setCheckbox1(!checkbox1);
  };

  const handleCheckbox2Change = () => {
    if (!checkbox2){
      setCheckbox1(false)
      setCheckbox3(false)

      /*Camera : Update Camera*/
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
          origin: "B3-UL01-ID-0013",
          destinations: ["B3-UL00-ID0073"],
          requestType: VMERouteRequestType.fatest
        }
        computeRoute(value);
      }
      
      if (checkBoxString2 === "Remove POIs") {
        console.log("La chaîne correspond à 'Remove POIs'.");
      }
      
      if (checkBoxString2 === "Show POI") {
        let res = ref.current.getPoi("B1-UL00-ID0034");
        res.then((value: any) => {
          Alert.alert("getPoi result is " + value);
        });
      }
      
      if (checkBoxString2 === "Overlay") {
        console.log("La chaîne correspond à 'Overlay'.");
      }
    }
      setCheckbox2(!checkbox2);
  };

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
    }
    setCheckbox3(!checkbox3);
  };

  return (
    <View style={styles.container}>
      <View style={styles.upper}>
      {/* Dropdown Menu */}
      <ScrollView style={[styles.dropdown, /*{ height: '100%'}*/]}>

      <TouchableOpacity onPress={() => handleBasicClick()} style={styles.dropdownButton}>
          <Text>Basic</Text> 
          {/*overlay ??*/}
        </TouchableOpacity>

        <TouchableOpacity onPress={() => handleCameraClick()} style={styles.dropdownButton}>
          <Text>Camera</Text>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => handleRoutingClick()} style={styles.dropdownButton}>
          <Text>Routing</Text>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => handlePOIClick()} style={styles.dropdownButton}>
          <Text>POI</Text>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => handleSearchClick()} style={styles.dropdownButton}>
          <Text>Search</Text>
        </TouchableOpacity>

        <TouchableOpacity onPress={() => handleThemeClick()} style={styles.dropdownButton}>
          <Text>Theme</Text> 
          {/*overlay ??*/}
        </TouchableOpacity>

        {/* Ajoutez autant de boutons que nécessaire */}
      </ScrollView>

      <View style = {styles.mapview}>
      <VisioMapView
        style={{
        flex:1
        }}
        mapHash={mapHash}
        mapPath={mapPath}
        mapSecret={mapSecret}
        ref={ref}
        promptToDownload={true}
        listeners={["buildingListener","cameraListener","mapListener","locationtrackingmodeListener","poiListener"]}
      />
      </View>
      </View>

      {/* Section avec 3 CheckBox en bas de l'écran*/}
      <View style={styles.checkboxSection}>
        <View style= {styles.viewButton}>
        <CheckBox value={checkbox1} 
        onValueChange={handleCheckbox1Change} />
        <Text>{checkBoxString1}</Text>
        </View>

        <View style= {styles.viewButton}>
        <CheckBox value={checkbox2} 
        onValueChange={handleCheckbox2Change} />
        <Text>{checkBoxString2}</Text>
        </View>

        <View style= {styles.viewButton} { ... {opacity : opacity3}}>
        <CheckBox value={checkbox3} 
        onValueChange={handleCheckbox3Change}
        disabled={disable3}
        />
        <Text>{checkBoxString3}</Text>
        </View>
    </View>
    </View>
  );
};


const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection :'column',
    height :'100%'

  },
  upper: {
    flex: 1,
    flexDirection: 'row',
  },
  mapview: {
    width:'80%',
    textAlign:'right'
    //marginLeft: '20%',
  },
  toggleButton: {
    //padding: 10,
    backgroundColor: '#3498db',
    alignItems: 'center',
  },
  dropdown: {
    backgroundColor: '#e0e0e0',
    overflow: 'scroll',
    width: '20%',
    height:'200%'
  },
  dropdownButton: {
    justifyContent: 'center',
    height : '40%',
    padding: 5,
    textAlign :'center',
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
  },
  checkboxSection: {
    height: '15%',
    backgroundColor: '#f0f0f0',
    flexDirection: 'row',
    justifyContent:'space-around',
    alignItems: 'center',
  },
  viewButton : {
    width:'30%',
    padding: 4, 
    borderWidth: 3, 
    borderRadius : 20,
    justifyContent : 'center',
    alignItems : 'center'
  },
  viewButton3 : {
    width:'30%',
    padding: 4, 
    borderWidth: 3, 
    borderRadius : 20,
    justifyContent : 'center',
    alignItems : 'center',
    opacity : 0.50
  }
});

 App;
