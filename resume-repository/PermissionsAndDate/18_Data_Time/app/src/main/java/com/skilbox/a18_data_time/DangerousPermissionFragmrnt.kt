package com.skilbox.a18_data_time

import LocationListViewAdapter
import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_dangerous_permission_fragmrnt.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime.*
import org.threeten.bp.ZoneId
import kotlin.random.Random.Default.nextLong

class DangerousPermissionFragmrnt : Fragment(R.layout.fragment_dangerous_permission_fragmrnt) {

    private var rationaleDialog: AlertDialog? = null

    private var dataTimePickerDialog: DatePickerDialog? = null

    private var timePickerDialog: TimePickerDialog? = null

    private var userLocation = listOf<UserLocation>()

    private var locationListViewAdapter: LocationListViewAdapter? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var locationRequest: LocationRequest

    private lateinit var locationCallback: LocationCallback

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true

        initList()

        needPermission()

        show_location.setOnClickListener {
            showLocationInfo()
            needPermission()

        }

        Log.e("I'am in ", "onActivityCreated")

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        getLocationUpdates()
        startLocationUpdates()

    }

    private fun getLocationUpdates() {
        Log.e("I'am in ", "getLocationUpdates")

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        locationRequest = LocationRequest()
        locationRequest.interval = 50
        locationRequest.fastestInterval = 50
        locationRequest.smallestDisplacement = 170f
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return

                if (userLocation.isNotEmpty()) {

                    val location = locationResult.lastLocation

                    userLocation = userLocation + listOf<UserLocation>(
                        UserLocation(
                            nextLong(),
                            location.latitude,
                            location.longitude,
                            location.altitude,
                            location.speed,
                            location.accuracy,
                            Instant.now(),
                            genereitImegeLinc()
                        )
                    )

                    locationListViewAdapter?.submitList(userLocation)
                }

            }
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {

            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        if (checkPermission() && rationaleDialog != null) {
            showMeToast("Невозможно получить локацию без разрешения")
        } else {
            needPermission()
            initList()
        }
    }

    private fun showCurrentLocation() {
        val isLocationPermissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        if (isLocationPermissionGranted) {
            Log.e("Permission", "Permission is $isLocationPermissionGranted")
        } else {

            needPermission()
        }
    }

    private fun showRashionalDialog() {
        rationaleDialog = AlertDialog.Builder(requireContext())
            .setMessage("Для отображения локации необходим доступ к локации (что по мнению разработчика логично 8))")
            .setPositiveButton("Ok", { _, _ -> requestLocationPermission() })
            .setNegativeButton(
                "No",
                { _, _ -> showMeToast("Невозможно получить локацию без разрешения") }
            )
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rationaleDialog?.dismiss()
        rationaleDialog = null
        dataTimePickerDialog?.dismiss()
        dataTimePickerDialog = null
        timePickerDialog?.dismiss()
        timePickerDialog = null

    }

    private fun showLocationInfo() {
        Log.e("showLocationInfo", "showLocationInfo")

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),

                Manifest.permission.ACCESS_FINE_LOCATION
            )

            showRashionalDialog()

            showCurrentLocation()
        } else {
            Log.e("checkSelfPermission", "False!")
            LocationServices.getFusedLocationProviderClient(requireContext())
                .lastLocation
                .addOnSuccessListener {
                    it?.let {
                        Log.e(
                            "LocationProviderClient",
                            "Lat = ${it.latitude}, Lng = ${it.longitude}, Alt=${it.altitude}, Speed=${it.speed}, Accuracy = ${it.accuracy}"
                        )
                        userLocation = userLocation + listOf<UserLocation>(
                            UserLocation(
                                nextLong(),
                                it.latitude,
                                it.longitude,
                                it.altitude,
                                it.speed,
                                it.accuracy,
                                Instant.now(),
                                genereitImegeLinc()
                            )

                        )

                        Log.e("get Location", userLocation.toString())
                        Log.e("User Location","$locationListViewAdapter")

                        cleanListMassage.visibility = View.INVISIBLE
                        user_location_list_view.visibility = View.VISIBLE
                        locationListViewAdapter?.submitList(userLocation)

                        Log.e("User Location2","$userLocation")

                    } ?: Log.e("LocationProviderClient", "Локация отсутствует")
                }
                .addOnCanceledListener { Log.e("LocationProviderClient", "Запрос отклонен") }
                .addOnFailureListener {
                    Log.e(
                        "LocationProviderClient",
                        "Запрос завершился не удачно"
                    )
                }
        }
    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            PERMISSION_REQUEST_CODE
        )

        needPermission()
    }

    private fun initList() {

        locationListViewAdapter = LocationListViewAdapter { position -> changeDataTime(position) }

        Log.e("addflap1", "initList")

        with(user_location_list_view) {
            adapter = locationListViewAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        locationListViewAdapter?.submitList(userLocation)

        Log.e("addflap2", "List = $userLocation")
    }

    private fun changeDataTime(position: Int) {

        val currentDataTime = userLocation[position].currenTime

        Log.e("I'am in", "changeDataTime позиция $position")
        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                TimePickerDialog(
                    requireContext(),
                    { _, hourOfDay, minute ->
                        val zonaDateTime = of(year, month + 1, dayOfMonth, hourOfDay, minute)
                            .atZone(ZoneId.systemDefault())
                        userLocation[position].currenTime = zonaDateTime.toInstant()

                        locationListViewAdapter?.notifyDataSetChanged()
                    },
                    currentDataTime.atZone(ZoneId.systemDefault()).hour,
                    currentDataTime.atZone(ZoneId.systemDefault()).minute,
                    true
                )
                    .show()
            },
            currentDataTime.atZone(ZoneId.systemDefault()).year,
            currentDataTime.atZone(ZoneId.systemDefault()).month.value - 1,
            currentDataTime.atZone(ZoneId.systemDefault()).dayOfMonth
        )
            .show()
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 4243
    }

    private fun checkPermission(): Boolean {
        return (
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            )
    }

    private fun needPermission() {
        Log.e("I'am in", "needPermission and ${userLocation.isEmpty()}")
        if (checkPermission()) {
            user_location_list_view.visibility = View.INVISIBLE
            cleanListMassage.visibility = View.VISIBLE
            cleanListMassage.text = "для получения списка\n" +
                "локаций необходим доступ к разрешениям.\n"
            show_location.text = "Разрешить"

        } else {

            show_location.text = "Получить локацию"
            if (userLocation.isEmpty()) {

                cleanListMassage.visibility = View.VISIBLE
                cleanListMassage.text = "Нет локаций для отображения"
            } else {

                user_location_list_view.visibility = View.VISIBLE
                cleanListMassage.visibility = View.INVISIBLE
            }
            initList()
        }
    }

    private fun showMeToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    private fun genereitImegeLinc(): String {
        val imageLinc = listOf(
            "https://www.onetwotrip.com/ru/blog/wp-content/uploads/2016/10/time-to-travel.jpg",
            "https://img.gazeta.ru/files3/497/6316497/468404-pic4_zoom-1000x1000-25061.jpg",
            "http://www.mobilmusic.ru/mfile/b4/70/11/771909-320.jpg",
            "https://www.vinegret.cz/media/uploads/2018/12/%D0%BC%D0%B0%D0%BB%D1%8C%D0%B4%D0%B8%D0%B2%D1%8B.jpg"

        )

        return imageLinc.random()
    }
}
