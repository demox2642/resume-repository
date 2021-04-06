package network


import com.skillbox.github.data.AuthRepository
import okhttp3.Interceptor
import okhttp3.Response

class CustomSetingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiRequest = originalRequest.newBuilder()
            .header("Authorization", " token ${AuthRepository.Token.token}")
            .build()
        return chain.proceed(modifiRequest)
    }
}
