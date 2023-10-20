package com.Viaato.app.activities

import com.Viaato.app.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.Viaato.app.NavigationViewModel
import com.Viaato.app.common.updateMapCopyrightPosition
import com.Viaato.app.sdkContext
import kotlinx.coroutines.launch
import ru.dgis.sdk.*
import ru.dgis.sdk.coordinates.Bearing
import ru.dgis.sdk.coordinates.GeoPoint
import ru.dgis.sdk.directory.SearchManager
import ru.dgis.sdk.geometry.GeoPointWithElevation
import ru.dgis.sdk.geometry.point
import ru.dgis.sdk.map.*
import ru.dgis.sdk.map.Map
import ru.dgis.sdk.navigation.*
import ru.dgis.sdk.routing.*

class MapNavigationActivity : AppCompatActivity(),TouchEventsObserver {
    private val sdkContext: Context by lazy { application.sdkContext }
    private lateinit var mapView: MapView
    lateinit var navigationManager: NavigationManager
    private lateinit var map: Map
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_map_navigation)
        super.onCreate(savedInstanceState)
        mapView = findViewById(R.id.mapView)
        navigationManager = NavigationManager(sdkContext)
        findViewById<NavigationView>(R.id.navigationView).apply {
            // Attach the created NavigationManager object to the NavigationView
            navigationManager = this@MapNavigationActivity.navigationManager
        }
        lifecycle.addObserver(mapView)
        mapView.getMapAsync { map ->
            // Create the data source
            val source = MyLocationMapObjectSource(
                sdkContext,
                MyLocationDirectionBehaviour.FOLLOW_SATELLITE_HEADING,
                createSmoothMyLocationController()
            )
            map.addSource(source)
            initMap(map)
        }
        // Create a NavigationManager object

        startNavigation()


    }
    private fun initMap(map: Map) {
        this.map = map
    }


    private fun startNavigation() {

        val routeBuildOptions = RouteBuildOptions(
            finishPoint = RouteSearchPoint(
                coordinates = GeoPoint(latitude = 25.197273, longitude = 55.276141),
                objectId = DgisObjectId(70000001031675897),
                levelId = null
            ),
            routeSearchOptions = RouteSearchOptions(PedestrianRouteSearchOptions(
                useIndoor = true,
                avoidStairways = false,
            )
            )
        )
        navigationManager.start(routeBuildOptions)
    }
}