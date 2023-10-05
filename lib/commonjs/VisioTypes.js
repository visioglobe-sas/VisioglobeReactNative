"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.pitchType = exports.VMViewModeType = void 0;
let pitchType = /*#__PURE__*/function (pitchType) {
  pitchType[pitchType["current"] = 0] = "current";
  pitchType[pitchType["default"] = 1] = "default";
  return pitchType;
}({});
exports.pitchType = pitchType;
let VMViewModeType = /*#__PURE__*/function (VMViewModeType) {
  VMViewModeType[VMViewModeType["floor"] = 0] = "floor";
  VMViewModeType[VMViewModeType["global"] = 1] = "global";
  VMViewModeType[VMViewModeType["unkown"] = 2] = "unkown";
  return VMViewModeType;
}({});
exports.VMViewModeType = VMViewModeType;
//# sourceMappingURL=VisioTypes.js.map