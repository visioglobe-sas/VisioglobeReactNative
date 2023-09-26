package com.visioglobe;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class VisioGetVersionReturnedEvent extends Event<VisioGetVersionReturnedEvent> {
    public static final String EVENT_NAME = "getVersion";
    private WritableMap payload;

    public VisioGetVersionReturnedEvent(@IdRes int viewId, int requestId, @NonNull String versionString) {
      super(viewId);
      payload = Arguments.createMap();
      payload.putInt("requestId", requestId);
      payload.putString("result", versionString);
    }

  @Override
    public String getEventName() {
      return "getVersion";
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
      rctEventEmitter.receiveEvent(getViewTag(), getEventName(), payload);
    }
}
