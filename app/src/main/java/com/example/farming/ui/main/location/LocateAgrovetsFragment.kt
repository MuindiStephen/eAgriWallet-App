package com.example.farming.ui.main.location

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.farming.R
import com.example.farming.model.Agrovet
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class LocateAgrovetsFragment : Fragment() , OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_locate_agrovets, container, false)
        // Initialize the MapView
        mapView = rootView.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        return rootView
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
        } else {
            // Request location permission
            requestLocationPermission()
        }

        // Add sample agrovet markers
        val agrovets = getAgrovetsData()
        for (agrovet in agrovets) {
            val location = LatLng(agrovet.latitude, agrovet.longitude)
            googleMap.addMarker(MarkerOptions().position(location).title(agrovet.name))
        }

        if (agrovets.isNotEmpty()) {
            val firstLocation = LatLng(agrovets[0].latitude, agrovets[0].longitude)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 18f))
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun getAgrovetsData(): List<Agrovet> {
        // Replace with your actual data retrieval logic
        // This is just a sample implementation
        return listOf(
            Agrovet("SIKATA AGROVET FARMERS CHOICE", 0.5929, 34.5429839),
            Agrovet("ROSE AGROVET", 0.5960, 34.543333),
            Agrovet("JOSEMO AGROVET DISTRIBUTORS BUNGOMA", 0.565110, 34.5431684),
            Agrovet("OMUSALE AGROVET", 0.565095, 34.5431600),
            Agrovet("MULTIDUSH AGROVET SUPPLIES", 0.565100, 34.545406)
        )
    }
}

/**
    private lateinit var map: GoogleMap
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap

        val agrovets = getAgrovetsData()

        for (agrovet in agrovets) {
            val location = LatLng(agrovet.longitude, agrovet.latitude)
            googleMap.addMarker(MarkerOptions().position(location).title(agrovet.name))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18f))

            Log.i(TAG, "Agrovets available $agrovet")
        }

        enableMyLocation()

        if (agrovets.isNotEmpty()) {
            val firstLocation = LatLng(agrovets[0].latitude, agrovets[0].longitude)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 18f))
        }

    }

    private fun getAgrovetsData(): List<Agrovet> {
        return listOf(
            Agrovet("SIKATA FARMERS CHOICE", 0.5929, 34.5429839),
            Agrovet("ROSE AGROVET", 0.5929, 34.5429839),
            Agrovet("JOSEMO DISTRIBUTORS BUNGOMA", 0.565095, 34.5431684),
            Agrovet("OMUSALE AGROVET", 0.565095, 34.5431684),
            Agrovet("MULTIDUSH AGROVET SUPPLIES", 0.565095, 34.5431684)
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_locate_agrovets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MapsInitializer.initialize(requireContext(), MapsInitializer.Renderer.LATEST){
            Log.i(TAG,it.name)
        }

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapLocateAgrovet) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun enableMyLocation() {
        if (isPermissionGranted()) {

            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            map.isMyLocationEnabled = true

        } else {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireActivity(), ACCESS_FINE_LOCATION
        ) === PackageManager.PERMISSION_GRANTED
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
//            if (grantResults.size > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED))
//                enableMyLocation()
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation()
            }
        }
    }

    companion object {
        const val TAG = "LocateAgrovetsFragment"
    }
}
        */