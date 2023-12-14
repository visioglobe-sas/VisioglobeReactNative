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

### Sections  

____
<details>
<summary> Basic </summary>  
This part show you how to load/unload a map.

### ***Display Props***  
This shows you the minimal props you need to have within the VisioMapView component.  
If you have trouble getting any of them, please ask us in our [help platform](https://my.visioglobe.com).

More specifically you have:           
- *Map Hash* : A string to retrieve your map from our server. Using it will allows the map to be updated every time you are using your map is updated from our editor. Is mandatory if Path is not used.
- *Map Secret Code* : Your secret code to load the map. 
- *Map Path* : A string if you want to use a local bundle, please indicate his path here. Note that using a local bundle means, updating it manually when the map is modified. Is mandatory if Hash is not used.  
- *Ref* : a ref to the MapView, mandatory to use this bridge.  

````typescript  
const ref = React.useRef<VisioMapView>(null);
<VisioMapView
        style={{
        style
        }}
        mapHash={"mapHash"}
        mapPath={"mapPath"}
        mapSecret={0}
        ref={ref}
      />
```` 

### ***Unload Map View*** 
If you want to hide the map, you can using unload map view. You do not need to provide any argument.

`````typescript 
const unloadMapView = () => {
    if (ref.current) {
      ref.current.unloadMapView();
    }
  }
`````

Then you can call it like in the [example](https://github.com/visioglobe-sas/VisioglobeReactNative/blob/main/example/src/App.tsx?plain=1#L207) 

### ***Reload Map View*** 
If you want to load the map **after hiding it**, you can with load map view. You do not need to provide any argument.

`````typescript 
 const loadMapView = () => {
    if (ref.current) {
      ref.current.loadMapView();
    }
  } 
`````

Then you can call it like in the [example](https://github.com/visioglobe-sas/VisioglobeReactNative/blob/main/example/src/App.tsx?plain=1#L243) 

</details> 

____ 
<details>
<summary> Camera </summary>

### ***Animate Camera***  
This allows you to define a camera movement for a ***duration*** you define according to your ***VMCameraUpdate***.

>VMCameraUpdate is a TSObject defined with :        
>- *Heading* : VMHeading object define as :
>    - **heading** : string | number
>    - **current** : if you want to use current heading
>- *paddingBottom* : the property used to define the space between the camera and its bottom-borders.
>- *paddingLeft* : the property used to define the space between the camera and its left-borders.
>- *paddingRight* : the property used to define the space between the camera and its right-borders.
>- *paddingTop* : the property used to define the space between the camera and its top-borders.
>- *pitch* : pitch //COMING NEXT TO DOC.
>- *targets* : the target you want to have at the camera (can be VMPosition or POIID (string))
>- *viewMode* : an enum of type VMViewModeType:
>    - floor
>    - global
>    - unkown

````typescript  
const animateCamera = (values: VMCameraUpdate) => {
    if (ref.current) {
      ref.current.animateCamera(values,3 //duration here fixed to 3
      );
    }
  };

//Then you can use it like :

  const heading : VMCameraHeading = {
          current: true
        }

        const pitch : VMCameraPitch = {
          type: pitchType.default,
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
```` 

### ***Update Camera***  
This allows you to define a camera movement **instantly** you define according to your ***VMCameraUpdate***. Here we are playing with the pitch and we are using a [**VMLocation**](https://github.com/visioglobe-sas/VisioglobeReactNative/blob/main/README.md?plain=1#L110) as a target.

````typescript  
const updateCamera = (values: VMCameraUpdate) => {
    if (ref.current) {
      ref.current.updateCamera(values);
    }
  };

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
```` 

</details> 

____ 
<details>
    <summary> Routing </summary>
</details> 

____ 
<details>
    <summary> Search </summary>
</details> 

____ 
<details>
    <summary> POI </summary>
</details> 

____ 
<details>
    <summary> Search </summary>
</details> 
____ 


