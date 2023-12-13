# react-native-visioglobe

react native bridge for visioglobe sdk

## Installation


```sh
yarn bootstrap 
```

puis dans example 
```sh
yarn start
```

## Usage

```js
import Visioglobe from "react-native-visioglobe";

// ...

const result = await Visioglobe.multiply(3, 7);
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

## Known Issue 

In case of implicit declaration with ios : 
clean and build the app <strong>in xcode</strong> before calling yarn ios 

## HOW TO USE THIS BRIDGE

Each of the following functionalities is demonstrated in the code in the example section. You can
go directly to the file with all the demo code by [clicking here](https://github.com/visioglobe-sas/VisioglobeReactNative/blob/main/example/src/App.tsx).  

<details>
    <summary> Basic Section </summary>
    This part show you how to load/unload a map.  
        * <details>
             <summary> Display Props </summary>
            This shows you the minimal props you need to have within the VisioMapView component.  
            If you have trouble getting any of them, please ask us in our [help platform](https://my.visioglobe.com) .  
            
            More specifically you have:  
            
            - Map Hash: A string to retrieve your map from our server. Using it will allows the map to be updated every time you are using your map is updated from our editor.
            - Map Secret Code : Your secret code to load the map. 
            - Map Path : If you want to use a local bundle, please indicate his path here. Note that using a local bundle means, updating it manually when the map is modified.  
        </details>  
        
        * <details>
            <summary> Unload Map View </summary>  
            If you want to hide the map, you can using unload map view. You do not need to provide any argument.
        </details>
</details> 
