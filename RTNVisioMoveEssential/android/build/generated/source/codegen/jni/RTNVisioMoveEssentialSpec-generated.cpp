
/**
 * This code was generated by [react-native-codegen](https://www.npmjs.com/package/react-native-codegen).
 *
 * Do not edit this file as changes may cause incorrect behavior and will be lost
 * once the code is regenerated.
 *
 * @generated by codegen project: GenerateModuleJniCpp.js
 */

#include "RTNVisioMoveEssentialSpec.h"

namespace facebook {
namespace react {

static facebook::jsi::Value __hostFunction_NativeVisioMoveEssentialSpecJSI_VMEMapControllerBuilder(facebook::jsi::Runtime& rt, TurboModule &turboModule, const facebook::jsi::Value* args, size_t count) {
  static jmethodID cachedMethodId = nullptr;
  return static_cast<JavaTurboModule &>(turboModule).invokeJavaMethod(rt, PromiseKind, "VMEMapControllerBuilder", "(Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;ZLcom/facebook/react/bridge/Promise;)V", args, count, cachedMethodId);
}

NativeVisioMoveEssentialSpecJSI::NativeVisioMoveEssentialSpecJSI(const JavaTurboModule::InitParams &params)
  : JavaTurboModule(params) {
  methodMap_["VMEMapControllerBuilder"] = MethodMetadata {5, __hostFunction_NativeVisioMoveEssentialSpecJSI_VMEMapControllerBuilder};
}

std::shared_ptr<TurboModule> RTNVisioMoveEssentialSpec_ModuleProvider(const std::string &moduleName, const JavaTurboModule::InitParams &params) {
  if (moduleName == "RTNVisioMoveEssential") {
    return std::make_shared<NativeVisioMoveEssentialSpecJSI>(params);
  }
  return nullptr;
}

} // namespace react
} // namespace facebook
