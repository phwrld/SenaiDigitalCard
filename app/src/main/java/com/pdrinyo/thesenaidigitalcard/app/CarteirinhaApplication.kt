package com.pdrinyo.thesenaidigitalcard.app

import android.app.Application
import com.pdrinyo.thesenaidigitalcard.app.di.AppContainer
import com.pdrinyo.thesenaidigitalcard.app.di.DefaultAppContainer

class CarteirinhaApplication : Application() {
    val container: AppContainer by lazy {
        DefaultAppContainer()
    }
}