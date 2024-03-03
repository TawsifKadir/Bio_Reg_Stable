package com.xplo.code.di


import com.xplo.code.data.db.DbController
import com.xplo.code.data.db.dao.BeneficiaryDao
import com.xplo.code.data.db.dao.HouseholdDao
import com.xplo.code.data.db.dao.PostDao
import com.xplo.code.data.db.repo.DbRepo
import com.xplo.code.data.db.repo.DbRepoImpl
import com.xplo.code.data_module.core.DispatcherProvider
import com.xplo.code.data_module.network.api.ContentApi
import com.xplo.code.data_module.network.api.UserApi
import com.xplo.code.data_module.repo.ContentRepo
import com.xplo.code.data_module.repo.ContentRepoImpl
import com.xplo.code.data_module.repo.UserRepo
import com.xplo.code.data_module.repo.UserRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    @Singleton
    @Provides
    fun providePostDao(): PostDao = DbController.getAppDb().postDao()

    @Singleton
    @Provides
    fun provideHouseholdDao(): HouseholdDao = DbController.getAppDb().householdDao()
    @Singleton
    @Provides
    fun provideBeneficiaryDao(): BeneficiaryDao = DbController.getAppDb().beneficiaryDao()

    @Singleton
    @Provides
    fun provideDbRepo(dao: PostDao, householdDao: HouseholdDao, beneficiaryDao: BeneficiaryDao): DbRepo =
        DbRepoImpl(dao, householdDao, beneficiaryDao)


    @Singleton
    @Provides
    fun provideUserRepo(api: UserApi): UserRepo = UserRepoImpl(api)

    @Singleton
    @Provides
    fun provideContentRepo(api: ContentApi): ContentRepo = ContentRepoImpl(api)
}