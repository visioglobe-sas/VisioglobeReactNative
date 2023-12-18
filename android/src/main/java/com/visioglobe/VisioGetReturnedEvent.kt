package com.visioglobe

import androidx.annotation.IdRes
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.events.Event
import com.facebook.react.uimanager.events.RCTEventEmitter

class VisioGetReturnedEvent(@IdRes viewId: Int, requestId: Int, result: WritableMap?) :
    Event<VisioGetReturnedEvent>(viewId) {
    private val payload: WritableMap = Arguments.createMap()

    init {
        payload.putInt("requestId", requestId)
        payload.putMap("result", result)
    }

    override fun getEventName(): String {
        return EVENT_NAME
    }

    override fun dispatch(rctEventEmitter: RCTEventEmitter) {
        rctEventEmitter.receiveEvent(viewTag, eventName, payload)
    }

    companion object {
        const val EVENT_NAME = "VisioGetReturnedEvent"
    }
}