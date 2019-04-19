package blackstone.com.mybike.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import blackstone.com.mybike.R
import blackstone.com.mybike.data.BikeLocation
import blackstone.com.mybike.data.mClusterItem
import blackstone.com.mybike.util.NetWorkUtil
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.VisibleRegion
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val DEFAULT_LAT = 37.566535
const val DEFAULT_LNG = 126.97796919000007
const val DEFAULT_ZOOM = 14f

class Presenter : Contract.Presenter {

    private lateinit var view: Contract.View
    private val clusterItemArray: ArrayList<mClusterItem> = ArrayList()
    private lateinit var mClusterManager: ClusterManager<mClusterItem>

    override fun loadData(context: Context) {
        NetWorkUtil.getRetro().loadData().enqueue(object : Callback<BikeLocation> {
            override fun onFailure(call: Call<BikeLocation>, t: Throwable) {
                view.toastError(t.message.toString())
            }

            override fun onResponse(call: Call<BikeLocation>, response: Response<BikeLocation>) {
                val response = response.body()
                response!!.RentBikeStatus.bikeRowList.forEach {
                    clusterItemArray.add(mClusterItem(it.stationLatitude.toDouble(), it.stationLongitude.toDouble(), it.stationName, it.parkingBikeTotCnt))
                }
                mapRefresh(view.getVisibleRegion())
            }
        })
    }

    override fun setView(view: Contract.View) {
        this.view = view
    }

    override fun initClusterMarker(context: Context, map: GoogleMap) {
        mClusterManager = ClusterManager(context, map)
        mClusterManager.renderer =
            object : DefaultClusterRenderer<mClusterItem>(context, map, mClusterManager) {
                override fun onBeforeClusterItemRendered(item: mClusterItem?, markerOptions: MarkerOptions?) {
                    super.onBeforeClusterItemRendered(item, markerOptions)
                    markerOptions?.icon(BitmapDescriptorFactory.fromResource(R.drawable.cycle))
                }
            }
    }

    override fun mapRefresh(visibleRegion: VisibleRegion) {
        clusterItemArray.forEach {
            if ((it.position.longitude in visibleRegion.farLeft.longitude..visibleRegion.nearRight.longitude) && it.position.latitude in visibleRegion.nearRight.latitude..visibleRegion.farLeft.latitude) {
                if (!mClusterManager.algorithm.items.contains(it)) mClusterManager.addItem(it)
            } else {
                if (mClusterManager.algorithm.items.contains(it)) mClusterManager.removeItem(it)
            }
        }
        mClusterManager.cluster()
    }

    override fun getDefaultLatLngZoom(): CameraUpdate {
        return CameraUpdateFactory.newLatLngZoom(LatLng(DEFAULT_LAT, DEFAULT_LNG), DEFAULT_ZOOM)
    }

}