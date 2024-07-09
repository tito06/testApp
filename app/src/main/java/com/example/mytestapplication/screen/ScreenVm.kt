package com.example.mytestapplication.screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapplication.data.App
import com.example.mytestapplication.data.RequestBody
import com.example.mytestapplication.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenVm  @Inject constructor(private val apiService: ApiService):ViewModel(){

    private val _appList = MutableStateFlow<List<App>>(emptyList())
    val appList: StateFlow<List<App>> = _appList





     fun appList( kid_id :Int){
        viewModelScope.launch {
            try {
                val response = apiService.getAppList(kid_id)

                _appList.value = response.body()!!.data.app_list

                Log.d("API Response", response.toString())


            }catch (e:Exception){
                Log.e("API Error", e.toString())
            }
        }
    }
}