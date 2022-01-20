package com.cooltechworks.sample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import okhttp3.internal.Version
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    //public final String TAG = "KJH";
    private val networkService: NetworkService? = null

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv1)
    var tv1: TextView? = null

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv2)
    var tv2: TextView? = null

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv3)
    var tv3: TextView? = null

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv4)
    var tv4: TextView? = null

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv5)
    var tv5: TextView? = null

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv6)
    var tv6: TextView? = null

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv7)
    var tv7: TextView? = null

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv8)
    var tv8: TextView? = null

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv9)
    var tv9: TextView? = null

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.bt1)
    fun bt1_Click() {
        //GET
        val versionCall: Call<MutableList<Version>>? = networkService!!._version
        versionCall.enqueue(object : Callback<List<Version?>> {
            override fun onResponse(
                call: Call<List<Version?>>,
                response: Response<List<Version?>>
            ) {
                if (response.isSuccessful) {
                    val versionList: List<Version?> = response.body()!!
                    var version_txt = ""
                    for (version in versionList) {
                        version_txt += version.getVersion().toString() + "\n"
                    }
                    tv1.setText(version_txt)
                } else {
                    val StatusCode = response.code()
                    Log.i(ApplicationController.TAG, "Status Code : $StatusCode")
                }
            }

            override fun onFailure(call: Call<List<Version?>>, t: Throwable) {
                Log.i(ApplicationController.TAG, "Fail Message : " + t.message)
            }
        })
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.bt2)
    fun bt2_Click() {
        //POST
        val version = Version()
        version.setVersion("1.0.0.1")
        val postCall: Call<Version>? = networkService!!.post_version(version)
        postCall.enqueue(object : Callback<Version?> {
            override fun onResponse(call: Call<Version?>, response: Response<Version?>) {
                if (response.isSuccessful()) {
                    tv2.setText("등록")
                } else {
                    val StatusCode = response.code()
                    Log.i(ApplicationController.TAG, "Status Code : $StatusCode")
                }
            }

            override fun onFailure(call: Call<Version?>, t: Throwable) {
                Log.i(ApplicationController.TAG, "Fail Message : " + t.message)
            }
        })
    }

    @OnClick(R.id.bt3)
    fun bt3_click() {
        //PATCH
        val version = Version()
        version.setVersion("1.0.0.0.1")
        val patchCall: Call<Version> = networkService!!.patch_version(1, version)
        patchCall.enqueue(object : Callback<Version?> {
            override fun onResponse(call: Call<Version?>, response: Response<Version?>) {
                if (response.isSuccessful()) {
                    tv3.setText("업데이트")
                } else {
                    val StatusCode = response.code()
                    Log.i(ApplicationController.TAG, "Status Code : $StatusCode")
                }
            }

            override fun onFailure(call: Call<Version?>, t: Throwable) {
                Log.i(ApplicationController.TAG, "Fail Message : " + t.message)
            }
        })
    }

    @OnClick(R.id.bt4)
    fun bt4_click() {
        //DELETE
        val deleteCall: Call<Version> = networkService!!.delete_version(1)
        deleteCall.enqueue(object : Callback<Version?> {
            override fun onResponse(call: Call<Version?>, response: Response<Version?>) {
                if (response.isSuccessful()) {
                    tv4.setText("삭제")
                } else {
                    val StatusCode = response.code()
                    Log.i(ApplicationController.TAG, "Status Code : $StatusCode")
                }
            }

            override fun onFailure(call: Call<Version?>, t: Throwable) {
                Log.i(ApplicationController.TAG, "Fail Message : " + t.message)
            }
        })
    }

    @OnClick(R.id.bt5)
    fun bt5_click() {
        //Restaurant GET
        val getCall: Call<List<Restaurant>> = networkService!!._restaruant
        getCall.enqueue(object : Callback<List<Restaurant?>> {
            override fun onResponse(
                call: Call<List<Restaurant?>>,
                response: Response<List<Restaurant?>>
            ) {
                if (response.isSuccessful) {
                    val restaurantList: List<Restaurant?> = response.body()!!
                    var restaurant_txt = ""
                    for (restaurant in restaurantList) {
                        restaurant_txt += restaurant.getName() +
                                restaurant.getAddress() +
                                restaurant.getCategory() +
                                restaurant.getWeather() +
                                restaurant.getDistance() +
                                restaurant.getDescription().toString() +
                                "\n"
                    }
                    tv5.setText(restaurant_txt)
                } else {
                    val StatusCode = response.code()
                    Log.i(
                        ApplicationController.TAG,
                        "Status Code : " + StatusCode + " Error Message : " + response.errorBody()
                    )
                }
            }

            override fun onFailure(call: Call<List<Restaurant?>>, t: Throwable) {
                Log.i(ApplicationController.TAG, "Fail Message : " + t.message)
            }
        })
    }

    @OnClick(R.id.bt6)
    fun bt6_click() {
        //Restaurant POST
        val restaurant = Restaurant()
        restaurant.setName("음식점1")
        restaurant.setAddress("장소1")
        restaurant.setCategory(3)
        restaurant.setWeather(3)
        restaurant.setDistance(3)
        restaurant.setDescription("설명1")
        val postCall: Call<Restaurant> = networkService!!.post_restaruant(restaurant)
        postCall.enqueue(object : Callback<Restaurant?> {
            override fun onResponse(call: Call<Restaurant?>, response: Response<Restaurant?>) {
                if (response.isSuccessful()) {
                    tv6.setText("등록")
                } else {
                    val StatusCode = response.code()
                    try {
                        Log.i(
                            ApplicationController.TAG,
                            "Status Code : $StatusCode Error Message : " + response.errorBody()!!
                                .string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<Restaurant?>, t: Throwable) {
                Log.i(ApplicationController.TAG, "Fail Message : " + t.message)
            }
        })
    }

    @OnClick(R.id.bt7)
    fun bt7_click() {
        //Restaurant PATCH
        //Full or partial patch available
        val restaurant = Restaurant()
        restaurant.setName("이름22")
        restaurant.setAddress("장소22")
        restaurant.setCategory(3)
        restaurant.setWeather(1)
        restaurant.setDistance(2)
        restaurant.setDescription("장소22")
        val patchCall: Call<Restaurant> = networkService!!.patch_restaruant(1, restaurant)
        patchCall.enqueue(object : Callback<Restaurant?> {
            override fun onResponse(call: Call<Restaurant?>, response: Response<Restaurant?>) {
                if (response.isSuccessful()) {
                    tv7.setText("업데이트")
                } else {
                    val StatusCode = response.code()
                    try {
                        Log.i(
                            ApplicationController.TAG,
                            "Status Code : $StatusCode Error Message : " + response.errorBody()!!
                                .string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<Restaurant?>, t: Throwable) {
                Log.i(ApplicationController.TAG, "Fail Message : " + t.message)
            }
        })
    }

    @OnClick(R.id.bt8)
    fun bt8_click() {
        //Restaurant DELETE
        val deleteCall: Call<Restaurant> = networkService!!.delete_restaruant(2)
        deleteCall.enqueue(object : Callback<Restaurant?> {
            override fun onResponse(call: Call<Restaurant?>, response: Response<Restaurant?>) {
                if (response.isSuccessful()) {
                    tv8.setText("삭제")
                } else {
                    val StatusCode = response.code()
                    try {
                        Log.i(
                            ApplicationController.TAG,
                            "Status Code : $StatusCode Error Message : " + response.errorBody()!!
                                .string()
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<Restaurant?>, t: Throwable) {
                Log.i(ApplicationController.TAG, "Fail Message : " + t.message)
            }
        })
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.bt9)
    fun bt9_click() {
        val get_weather_pk_Call: Call<List<Restaurant>> =
            networkService!!.get_weather_pk_restaruant(1)
        get_weather_pk_Call.enqueue(object : Callback<List<Restaurant?>?> {
            override fun onResponse(
                call: Call<List<Restaurant?>?>,
                res: Response<List<Restaurant?>?>
            ) {
            }

            override fun onFailure(call: Call<List<Restaurant?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}

private fun <T> Call<T>?.enqueue(callback: Callback<List<Version?>>) {

}
