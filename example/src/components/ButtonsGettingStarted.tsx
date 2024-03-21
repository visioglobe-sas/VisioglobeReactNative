import React, {useState} from 'react';
import {View, Switch, StyleSheet, Text} from 'react-native';
import VisioMapView from 'react-native-visioglobe';

/**
 * 
 * @param ref 
 * @returns Buttons for getting started menu : load/unload map view
 */

const ButtonsGettingStarted = (ref : React.MutableRefObject<VisioMapView>) => {
  const [isEnabled, setIsEnabled] = useState(false);
  const toggleSwitch = () => {
    setIsEnabled(previousState => !previousState);
    if (isEnabled){
        ref.current.loadMapView();
    }else {
        ref.current.unloadMapView();
    }
};

  return (
    <View style={styles.container}>
      <View style={styles.onoffcontainer}>
        <Text style={{fontWeight:'600', padding:15, fontSize:19, fontFamily:'Urbanist'}}> Map data </Text>
          <View style={styles.onofflabel}>
            <Switch
            trackColor={{false: '#393E47', true: '#0094F0'}}
            thumbColor={isEnabled ? '#FFFFFF' : '#FFFFFF'}
            ios_backgroundColor="#3e3e3e"
            onValueChange={toggleSwitch}
            value={isEnabled}
            />
            <Text style={{fontWeight:'400', fontSize:19, fontFamily:'Inter'}} >Unload/Load</Text>
        </View>
      </View>

    </View>
  );
};

const styles = StyleSheet.create({
    container: {
      flex: 1,
      flexDirection : 'row',
      alignItems: 'center',
      justifyContent: 'center',
    },
    onoffcontainer: {
      flex: 1,
      backgroundColor: "#282B33",
      borderRadius : 10,
      borderColor: 'grey',
      borderWidth: 0.5,
      paddingTop : 24,
      padding : 16
    },
    onofflabel: {
      alignItems:'center',
      flexDirection : 'row',
      paddingLeft : 10,
      textAlign:'center',
    }  
  });

  export default ButtonsGettingStarted;