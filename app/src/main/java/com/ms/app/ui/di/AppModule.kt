package com.ms.app.ui.di


import com.ms.app.ui.fragment.home.HomeViewModel
import com.ms.app.ui.fragment.my.MyViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

/**
 * Hilt requires included modules to be annotated with [InstallIn], as issues due to forgetting are potentially difficult to track.
 *
 * However, since the included [AssistedInject_AppModule] is auto-generated, for now we need to disable this check. Hilt allows us
 * to do it via [DisableInstallInCheck], along with the `disableModulesHaveInstallInCheck` compiler option declared in the module's build.gradle.
 */

@AssistedModule
@Module(includes = [AssistedInject_AppModule::class])
@DisableInstallInCheck
@InstallIn(ApplicationComponent::class)
interface AppModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun homeViewModelFactory(factory: HomeViewModel.Factory): AssistedViewModelFactory<*, *>


    @Binds
    @IntoMap
    @ViewModelKey(MyViewModel::class)
    fun myViewModelFactory(factory: MyViewModel.Factory): AssistedViewModelFactory<*, *>
}