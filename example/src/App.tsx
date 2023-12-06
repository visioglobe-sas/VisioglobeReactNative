import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, ScrollView } from 'react-native';
import CheckBox from '@react-native-community/checkbox';
import VisioMapView from 'react-native-visioglobe';

const App = () => {
  const ref = React.useRef<VisioMapView>(null);
  const [isDropdownOpen, setDropdownOpen] = useState(false);
  const [checkbox1, setCheckbox1] = useState(false);
  const [checkbox2, setCheckbox2] = useState(false);
  const [checkbox3, setCheckbox3] = useState(false);
  const [checkBoxString1, setCheckBoxString1] = useState("");
  const [checkBoxString2, setCheckBoxString2] = useState("");
  const [checkBoxString3, setCheckBoxString3] = useState("");

  const toggleDropdown = () => {
    setDropdownOpen(!isDropdownOpen);
  };

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
  }


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
    setCheckbox1(!checkbox1);
  };

  const handleCheckbox2Change = () => {
    setCheckbox2(!checkbox2);
  };

  const handleCheckbox3Change = () => {
    setCheckbox3(!checkbox3);
  };

  return (
    <View style={styles.container}>
      <View style={styles.upper}>
      {/* Dropdown Menu */}
      <ScrollView style={[styles.dropdown, { height: '100%'}]}>

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

        <TouchableOpacity onPress={() => handleThemeClick('Bouton 5')} style={styles.dropdownButton}>
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
        mapHash="mc8f3fec89d2b7283d15cfcf4eb28a0517428f054"
        mapPath="path"
        mapSecret={0}
        ref={ref}
        promptToDownload={true}
        listeners={["buildingListener","cameraListener","mapListener","locationtrackingmodeListener","poiListener"]}
      />
      </View>
      </View>

      {/* Section avec 3 CheckBox en bas de l'écran */}
      <View style={styles.checkboxSection}>
        <View style= {styles.viewButton}>
        <CheckBox value={checkbox1} onValueChange={handleCheckbox1Change} />
        <Text>{checkBoxString1}</Text>
        </View>

        <View style= {styles.viewButton}>
        <CheckBox value={checkbox2} onValueChange={handleCheckbox2Change} />
        <Text>{checkBoxString2}</Text>
        </View>

        <View style= {styles.viewButton}>
        <CheckBox value={checkbox3} onValueChange={handleCheckbox3Change} />
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
  },
  upper: {
    marginTop : '6%',
    flex: 1,
    flexDirection: 'row',
  },
  mapview: {
    width:'80%',
    //marginLeft: '20%',
  },
  toggleButton: {
    padding: 10,
    backgroundColor: '#3498db',
    alignItems: 'center',
  },
  dropdown: {
    backgroundColor: '#e0e0e0',
    overflow: 'hidden',
    width: '20%',
  },
  dropdownButton: {
    justifyContent: 'center',
    height : '77%',
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
  }
});

export default App;
