package com.rtnmapview;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.visioglobe.visiomoveessential.VMEMapView

class MapView : FrameLayout {
  var mMapView : VMEMapView? = null
  private var rootView: View? = null

  constructor(context: Context?) : super(context!!) {
    val inflater = LayoutInflater.from(context)
    rootView = inflater.inflate(R.layout.map_view, null)
    rootView?.let{ mMapView = rootView!!.findViewById<View>(R.id.map) as VMEMapView}
  }

  //constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
  //}
}
