package blackstone.com.mybike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import blackstone.com.mybike.presenter.Contract
import blackstone.com.mybike.presenter.Presenter

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.VisibleRegion

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, Contract.View, GoogleMap.OnCameraIdleListener {

    private lateinit var mMap: GoogleMap
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        presenter = Presenter()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.moveCamera(presenter.getDefaultLatLngZoom())
        mMap.setOnCameraIdleListener(this)

        presenter.setView(this)
        presenter.loadData(this)
        presenter.initClusterMarker(this, mMap)

    }

    override fun toastError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        Log.e("ERROR", msg)
    }

    override fun getVisibleRegion(): VisibleRegion = mMap.projection.visibleRegion

    override fun onCameraIdle() {
        presenter.mapRefresh(getVisibleRegion())
    }
}
