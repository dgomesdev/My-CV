package com.dgomesdev.mycv.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.dgomesdev.mycv.model.CvData
import com.dgomesdev.mycv.model.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CvViewModel(private val firebaseDatabase: FirebaseDatabase) : ViewModel() {

    private val _cvDataState =
        mutableStateOf(CvData(profile = Profile(title = "Loading...")))
    val cvDataState = _cvDataState

    init {
        fetchData()
    }

    private fun fetchData() {
        val databaseReference = firebaseDatabase.getReference("CvData")

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                _cvDataState.value = snapshot.getValue(CvData::class.java) ?: CvData()
            }

            override fun onCancelled(error: DatabaseError) {
                _cvDataState.value = CvData()
            }
        })
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CvViewModel(Firebase.database)
            }
        }
    }
}