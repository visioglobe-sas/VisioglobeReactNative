/**
 * This code was generated by [react-native-codegen](https://www.npmjs.com/package/react-native-codegen).
 *
 * Do not edit this file as changes may cause incorrect behavior and will be lost
 * once the code is regenerated.
 *
 * @generated by codegen project: GenerateModuleH.js
 */

#include "RTNVisioMoveEssentialSpecJSI.h"

namespace facebook {
namespace react {

static jsi::Value __hostFunction_NativeVisioMoveEssentialCxxSpecJSI_VMEMapControllerBuilder(jsi::Runtime &rt, TurboModule &turboModule, const jsi::Value* args, size_t count) {
  return static_cast<NativeVisioMoveEssentialCxxSpecJSI *>(&turboModule)->VMEMapControllerBuilder(rt, args[0].asString(rt), args[1].asNumber(), args[2].asString(rt), args[3].asString(rt), args[4].asBool());
}

NativeVisioMoveEssentialCxxSpecJSI::NativeVisioMoveEssentialCxxSpecJSI(std::shared_ptr<CallInvoker> jsInvoker)
  : TurboModule("RTNVisioMoveEssential", jsInvoker) {
  methodMap_["VMEMapControllerBuilder"] = MethodMetadata {5, __hostFunction_NativeVisioMoveEssentialCxxSpecJSI_VMEMapControllerBuilder};
}


} // namespace react
} // namespace facebook
