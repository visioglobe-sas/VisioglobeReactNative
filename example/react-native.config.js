const path = require('path');
const pak = require('../package.json');

module.exports = {
  dependencies: {
    [pak.name]: {
      root: path.join(__dirname, '..'),
    },
  },
  project:{
    android: {
      unstable_reactLegacyComponentNames: [
          "VisioMapViewManager"
      ]
    },
    ios: {
      unstable_reactLegacyComponentNames: [
          // list of conponents that needs to be wrapped by the interop layer
      ]
    }
  },
};
