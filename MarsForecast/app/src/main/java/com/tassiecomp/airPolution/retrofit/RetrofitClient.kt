package com.tassiecomp.airPolution.retrofit

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.tassiecomp.airPolution.App
import com.tassiecomp.airPolution.utils.API
import com.tassiecomp.airPolution.utils.isJsonArray
import com.tassiecomp.airPolution.utils.isJsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    //레트로핏 클라이언트 선언
    private var retrofitClient: Retrofit? = null

    //레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String):Retrofit?{
        Log.d("TAG","RetrofitClient - getClient()called")

        //logging interceptor 추가
        //왜추가하나 지금 로그로 모두 확인이되 지않는다. 그래서 인터셉터로 API와 서버사이 진행상황을 보기위해

        //okhttp 인스턴스 생성
        val client = OkHttpClient.Builder()

        //로그를 찍기위해 로깅 인터셉터 추가
        val loggingIntercepter = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
//                Log.d("TAG","RetrofitClient - log() called / message : $message")

                when {
                    message.isJsonObject() ->
                        Log.d("TAG", JSONObject(message).toString(4))

                    message.isJsonArray() ->
                        Log.d("TAG", JSONObject(message).toString(4))
                    else -> {
                        try{
                            Log.d("TAG", JSONObject(message).toString(4))
                        } catch (e:Exception) {
                            Log.d("TAG", message)
                        }
                    }
                }
            }



        })

        loggingIntercepter.setLevel(HttpLoggingInterceptor.Level.BODY)

        //위에서 설정한 로깅 인터셉터를 okhttp클라이언트에 추가한다.
        client.addInterceptor(loggingIntercepter)

        //기본 parameter intercepter 생성

        val baseParameterInterceptor: Interceptor = (object : Interceptor {

            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d("TAG","RetrofitClient - intercept() called")
                //오리지널 request
                val originalRequest = chain.request()

                //?client_id= asdf 이걸 해주는 과정
                //query parameter 추가하기
                val addedUrl = originalRequest.url.newBuilder().addQueryParameter("client_id", API.CLIENT_ID).build()

                val finalRequest = originalRequest.newBuilder()
                    .url(addedUrl)
                    .method(originalRequest.method,originalRequest.body)
                    .build()
//                return chain.proceed(finalRequest)

                val response = chain.proceed(finalRequest)

                //chain.proceed로 에러코드를 가져온다.
                if (response.code != 200) {
                    //handler를 써주는 이유는 Toast는 UIthread에있어야하는데App.instance로 인해main thread에 있게되었다. 그래서
                    //handler로 main looper에서 코드를 돌리게하고 UIThread에서 돌아가게된다.
                            //그럼왜 처음부터 하면안됨?
                                //그 이유는 toast메세지는 일단 Appcompatity 안에서 작동을해야하는데 여기에this로 부른다면, 어떻게될까?
                                //App compatity가 아니기때문에 잘못된코드다 그래서 context를 App.instance로 가져와서, 쓴다. 그러면 handler덕분에 UIThread에서 돌리게 될수있음 동시에
                                //Toast메세지까지 띄울수있게된다.
                    Handler(Looper.getMainLooper()).post{
                        Toast.makeText(App.instance,"${response.code} 에러입니다.", Toast.LENGTH_SHORT).show()
                    }


                }

                return response
            }
        })
        //위에서 설정한 기본parameter intercepter를 okhttp클라이언트에 추가한다.

//        client.addInterceptor(baseParameterInterceptor)

        //커넥션 타임아웃
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(10,TimeUnit.SECONDS)
        client.writeTimeout(10,TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)





        if (retrofitClient ==null) { //레트로핏 클라이언트 유무 확인

            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl) //위에 getclient에 변수를 가져온다.
                .addConverterFactory(GsonConverterFactory.create()) //Gson converter추가
                //위에서 설정한 클라이언트로 레트로핏 클라이언트를 설정한다.
                .client(client.build())
                .build()

        }
        return retrofitClient
    }
}