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
