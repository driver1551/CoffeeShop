package com.example.sevenwindstest.data.repository

import android.location.Location

interface LocationClient {
    suspend fun getLastKnownLocation(): Location?
}
