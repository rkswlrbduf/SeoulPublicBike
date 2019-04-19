package blackstone.com.mybike.presenter

import android.content.Context
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.VisibleRegion

interface Contract {

    interface View {
        fun toastError(msg: String)
        fun getVisibleRegion(): VisibleRegion
    }

    interface Presenter {
        fun setView(view: Contract.View)
        fun loadData(context: Context)
        fun initClusterMarker(context: Context, map: GoogleMap)
        fun getDefaultLatLngZoom(): CameraUpdate
        fun mapRefresh(visibleRegion: VisibleRegion)
    }

}