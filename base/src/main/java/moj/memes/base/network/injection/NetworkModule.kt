package moj.memes.base.network.injection

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import moj.memes.base.BuildConfig
import moj.memes.base.injection.scopes.PerApplication
import moj.memes.base.network.api.ImgFlipApi
import moj.memes.base.network.adapter.ApplicationJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class NetworkModule {

    @Provides
    @PerApplication
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @PerApplication
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    @PerApplication
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @PerApplication
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url = request.url()
                    .newBuilder()
                    //.addQueryParameter("api_key", "" /* API KEY HERE */)
                    .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    @Provides
    @PerApplication
    fun provideMoshi(): Moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()

    @Provides
    @PerApplication
    fun provideConverterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Provides
    @PerApplication
    fun provideImgFlipApi(builder: Retrofit.Builder,
                          okHttpClientBuilder: OkHttpClient.Builder,
                          httpLoggingInterceptor: HttpLoggingInterceptor,
                          converterFactory: Converter.Factory,
                          apiKeyInterceptor: Interceptor): ImgFlipApi {
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.addNetworkInterceptor(apiKeyInterceptor)
        val client = okHttpClientBuilder.build()
        return builder.client(client)
                .baseUrl(BuildConfig.IMG_FLIP_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converterFactory)
                .build()
                .create(ImgFlipApi::class.java)
    }
}
