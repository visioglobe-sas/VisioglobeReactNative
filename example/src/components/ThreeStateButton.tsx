import React, { useState } from 'react';
import { View, TouchableOpacity, Text, StyleSheet } from 'react-native';

const NStateButton = ({ buttonTexts, onClick }) => {
  const [currentState, setCurrentState] = useState(0);

  const handlePress = () => {
    setCurrentState((prevState) => (prevState + 1) % buttonTexts.length);
    if (onClick) {
      onClick(currentState);
    }
  };

  return (
    <TouchableOpacity onPress={handlePress}>
      <View style={styles.button}>
        <Text style={styles.buttonText}>{buttonTexts[currentState]}</Text>
      </View>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  button: {
    backgroundColor: 'blue',
    padding: 10,
    borderRadius: 5,
    alignItems: 'center',
  },
  buttonText: {
    color: 'white',
    fontWeight: 'bold',
  },
});

export default NStateButton;

