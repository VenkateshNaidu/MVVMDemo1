package com.venkygithub.mvvmdemo1

import android.app.Application
import com.venkygithub.mvvmdemo1.data.db.AppDatabase
import com.venkygithub.mvvmdemo1.data.network.MyApi
import com.venkygithub.mvvmdemo1.data.network.NetworkConnectionIntercepter
import com.venkygithub.mvvmdemo1.data.repository.UserRepository
import com.venkygithub.mvvmdemo1.ui.auth.AuthViewModelFactory
import com.venkygithub.mvvmdemo1.ui.home.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {

        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionIntercepter(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }

    }
}