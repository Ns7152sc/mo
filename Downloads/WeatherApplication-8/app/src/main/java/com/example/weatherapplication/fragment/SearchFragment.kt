package com.example.weatherapplication.fragment

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.services.LocationService
import com.example.weatherapplication.MainActivity
import com.example.weatherapplication.R
import com.example.weatherapplication.Util
import com.example.weatherapplication.databinding.FragmentSearchBinding
import com.example.weatherapplication.model.CurrentConditions
import com.example.weatherapplication.network.ResultData
import com.example.weatherapplication.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var currentConditions: CurrentConditions
    private lateinit var locationManager: LocationManager
    private var locationNetwork: Location? = null
    private lateinit var locationGps: Location
    val viewModel: SearchViewModel by viewModels()
    var mLocationService: LocationService = LocationService()
    lateinit var mServiceIntent: Intent
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        (activity as MainActivity).supportActionBar?.title = "Search"
        if(Util.getShowNotification(requireContext())){
            binding.btnNotification.text = "Turn off notifications"
        }
        else{
            binding.btnNotification.text = "Turn on notifications"
        }
        initListeners()
        return binding.root
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(
        permissions: Array<String>,
        requestCode: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions!!, requestCode)
        }
    }

    private fun initListeners() {
        binding.btnSearch.setOnClickListener {
            locationNetwork = null
            getDataAndSubscribeEvents()
        }
        binding.btnSearchLocation.setOnClickListener {
            showLocationAccessPopup()
            if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(requireActivity(),arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                    ,1001)
            }
            else{
                getLocation()
                if(locationNetwork!=null){
                    getLocationAndSubscribeEvents()
                }
            }
        }
        binding.etZipCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnSearch.isEnabled = p0?.length == 5
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.btnNotification.setOnClickListener {
            if (binding.btnNotification.text.toString() == "Turn on notifications") {
                Util.setShowNotification(requireContext(), true)
                binding.btnNotification.text = "Turn off notifications"
                if (!Util.isLocationEnabledOrNot(requireContext())) {
                    Util.showAlertLocation(
                        requireContext(),
                        getString(R.string.gps_enable),
                        getString(R.string.please_turn_on_gps),
                        getString(
                            R.string.ok
                        )
                    )
                }
                requestPermissionsSafely(
                    arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), 200
                )

                mLocationService = LocationService()
                mServiceIntent = Intent(requireContext(), mLocationService.javaClass)
                if (!Util.isMyServiceRunning(mLocationService.javaClass, requireActivity())) {
                    requireActivity().startService(mServiceIntent)
                }
            } else {
                Util.setShowNotification(requireContext(), false)
                binding.btnNotification.text = "Turn on notifications"
                mLocationService = LocationService()
                mServiceIntent = Intent(requireContext(), mLocationService.javaClass)
                requireActivity().stopService(mServiceIntent)
            }
        }

    }

    private val repositoryObserver = Observer<ResultData<CurrentConditions?>> { resultData ->
        when (resultData) {
            is ResultData.Loading -> {
                Toast.makeText(requireContext(), "Loading data", Toast.LENGTH_SHORT).show()
            }
            is ResultData.Success -> {
                currentConditions = resultData.data!!
                if(locationNetwork!=null){
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToCurrentConditionsFragment(
                            currentConditions,
                            "",
                            locationNetwork?.latitude?.toFloat()!!,
                            locationNetwork?.longitude?.toFloat()!!,
                        )
                    )
                    locationNetwork = null
                }
                else {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToCurrentConditionsFragment(
                            currentConditions,
                            binding.etZipCode.text.toString()
                        )
                    )
                    locationNetwork = null
                }
            }
            is ResultData.Failed -> {
                showMessage(resultData.message.toString())
            }
            is ResultData.Exception -> {
                showMessage(resultData.exception?.message.toString())
            }
        }
    }

    private fun getDataAndSubscribeEvents() {
        val repositoriesLiveData = viewModel.getCurrentConditions(
            binding.etZipCode.text.toString(),
            "imperial",
            "df5f5ad7dec319cdbd10e03799917453"
        )
        repositoriesLiveData.observe(viewLifecycleOwner, repositoryObserver)
    }

    private fun getLocationAndSubscribeEvents() {
        val repositoriesLiveData = viewModel.getCurrentConditions(
            locationNetwork?.latitude.toString(),
            locationNetwork?.longitude.toString(),
            "imperial",
            "df5f5ad7dec319cdbd10e03799917453"
        )
        repositoriesLiveData.observe(viewLifecycleOwner, repositoryObserver)
    }

    private fun showMessage(text: String) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage(text)
            // if the dialog is cancelable
            .setCancelable(false)
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, id ->
                dialog.dismiss()

            })

        val alert = dialogBuilder.create()
        alert.setTitle("Alert Message")
        alert.show()
    }

    private fun showLocationAccessPopup() {

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                // Show an explanation to the user
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setMessage("App needs location permission to associate location with your product. Do you want to grant location permission")
                    ?.setCancelable(false)
                    ?.setPositiveButton(
                        "Yes, Grant permission",
                        DialogInterface.OnClickListener { dialog, id ->
                            ActivityCompat.requestPermissions(
                                requireActivity(),
                                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                                100
                            )
                        })
                    // negative button text and action
                    ?.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("Location Permission")
                alert.show()

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    100
                )
            }
        } else {
            getLocation()
        }
    }

    private fun getLocation() {
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (hasGps || hasNetwork) {

            if (hasGps) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    return
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, object : LocationListener {
                    override fun onLocationChanged(p0: Location) {
                        if (p0 != null) {
                            locationGps = p0
                        }
                    }

                })

                val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (localGpsLocation != null)
                    locationGps = localGpsLocation
            }
            if (hasNetwork) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0F, object : LocationListener {
                    override fun onLocationChanged(p0: Location) {
                        if (p0 != null) {
                            locationNetwork = p0

                        }
                    }

                })

                val localNetworkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (localNetworkLocation != null)
                    locationNetwork = localNetworkLocation
            }

            if(locationGps!= null && locationNetwork!= null){
                if(locationGps!!.accuracy > locationNetwork!!.accuracy){

                }else{

                }
            }

        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }

    override fun onDestroy() {
        if (::mServiceIntent.isInitialized) {
            requireActivity().stopService(mServiceIntent)
        }
        super.onDestroy()
    }
}