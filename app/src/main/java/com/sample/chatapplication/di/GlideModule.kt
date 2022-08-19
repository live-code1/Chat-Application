package com.sample.chatapplication.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped



//ToDo 2 : Create A Hilt module class that is annotated with @Module.
@Module

//ToDo 3: you must annotate Hilt modules with @InstallIn to tell Hilt which Android class each module will be used or installed in
// we will use it inside our fragment and so you can add @InstallIn(FragmentComponent::class)
@InstallIn(ActivityComponent::class)
class GlideModule {

    //@Singleton
    // add it to create one single instance but to do that you must change
    // FragmentComponent to ApplicationComponent
    // @ActivityContext to @ApplicationContext

    @ActivityScoped
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(0)
            .error(0)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH).format(DecodeFormat.PREFER_ARGB_8888)
    }

    @ActivityScoped
    @Provides
    fun provideGlide(
        @ActivityContext context: Context,
        requestOptions: RequestOptions?
    ): RequestManager = Glide.with(context).setDefaultRequestOptions(requestOptions!!)
}
