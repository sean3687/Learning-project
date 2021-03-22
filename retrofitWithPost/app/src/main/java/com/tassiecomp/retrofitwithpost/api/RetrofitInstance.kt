package com.tassiecomp.retrofitwithpost.api

import com.tassiecomp.retrofitwithpost.utls.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// we use this new class because we have to make instance as SINGLETON
//what is SINGLETON
//싱글톤은 말 그대로 혼자라는 뜻입니다. 클래스의 생성을 딱!! 1개로 정한다는 뜻이죠.
//따라서 해당 클래스의 객체는 static으로 생성이 됩니다.
//장점 1.코드의 가독성 2. 불필요한 객체 생성을 피하기위해 3.코드가 좀더 세밀해진다.
object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:SimpleApi by lazy{
        retrofit.create(SimpleApi::class.java)
    }
}