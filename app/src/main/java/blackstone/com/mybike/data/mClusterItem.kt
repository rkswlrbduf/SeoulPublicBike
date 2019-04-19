package blackstone.com.mybike.data

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class mClusterItem(lat: Double, lng: Double, title: String, snippet: String) : ClusterItem {

    private val position: LatLng = LatLng(lat, lng)
    private val title = title
    private val snippet = snippet

    constructor(lat: Double, lng: Double) : this(lat, lng, "", "")

    override fun getSnippet(): String = snippet

    override fun getTitle(): String = title

    override fun getPosition(): LatLng = position

}