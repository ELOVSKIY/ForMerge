package com.example.myapplication.controllers.datalayer

class AuthDatalayerController {


    private val LOG_TAG = "AuthDatalayer"
    /* private lateinit var api: AuthApi

     fun AuthDatalayerController() {

         api = App.getDatalayerNetClient().createApi(AuthApi::class.java)
     }

     //Функция запроса к серверу для создания кода подтверждения
     fun createCode(phone: String, callback: Callback<Boolean>) {

         Log.d(LOG_TAG, api.createCode(phone).request().url().toString())
         api.createCode(phone).enqueue(callback)
         //Заглушка
         //        Response<Boolean> response = Response.success(true);
         //        callback.onResponse(api.createCode(phone), response);
     }


     //Функция для проверки отправленного кода подтверждения
     fun checkSmsCode(
         phone: String,
         code: String,
         deviceId: String,
         callback: Callback<ResultAuth>
     ) {

         val firebaseService = MyFirebaseMessagingService()
         firebaseService.onNewToken("")

         Log.d(
             LOG_TAG,
             api.checkSmsCode(
                 phone,
                 code,
                 deviceId,
                 App.getFirebaseToken()
             ).request().url().toString()
         )
         api.checkSmsCode(phone, code, App.getFirebaseToken(), deviceId).enqueue(callback)
         //Заглушка
         //        ResultAuth resultAuth = new ResultAuth();
         //        resultAuth.access_token = "Asdasd1asdq234asA";
         //        resultAuth.need_create = true;
         //        Response<ResultAuth> response = Response.success(resultAuth);
         //        callback.onResponse(api.login(phone,code,deviceId), response);
     }

     //Функция - заглушка для получения кода подтверждения (пока нет смс)
     fun getCode(phone: String, callback: Callback<String>) {
         Log.d(LOG_TAG, api.getCode(phone).request().url().toString())
         api.getCode(phone).enqueue(callback)
     }

     //Функция отправки запроса для создания пользователя
     fun createProfile(
         profile: User, deviceId: String, firebaseToken: String,
         photo64: String, callback: Callback<ResultAuth>
     ) {
         Log.d(
             LOG_TAG,
             Gson().toJson(
                 api.createUser(
                     profile.getProfilePhone().toString(),
                     /*profile.getSms_Code_Value(),
                     profile.getUser_Name(),*/
                     deviceId,
                     firebaseToken,
                     photo64
                 ).request().body()
             )
         )
         api.createUser(
             profile.getProfilePhone().toString(),
             /*user.getSms_Code_Value(),
             user.getUser_Name(),*/
             deviceId,
             firebaseToken,
             photo64
         ).enqueue(callback)
         //Заглушка
         //        ResultAuth resultAuth = new ResultAuth();
         //        resultAuth.access_token = "askjhsdkjfhjkhasdhfskid";
         //        Response<ResultAuth> response = Response.success(resultAuth);
         //        callback.onResponse(api.createUser(name, photo64), response);
     }

     //Отправка запроса для получения дерева всех подуслуг
     fun getSubServiceTree(callback: Callback<List<WorkType>>) {
         Log.d(LOG_TAG, api.subServiceTree.request().url().toString())
         api.subServiceTree.enqueue(callback)
     }

     fun getPlaces(apiKey: String, lang: String, text: String, callback: Callback<MapSearch>) {
         Log.d(LOG_TAG, api.subServiceTree.request().url().toString())
         api.getPlaces(apiKey, lang, text).enqueue(callback)
     }

     //Интерфейс ретрофита
     private interface AuthApi {

         @get:GET("auth/get-subservice-tree")
         val subServiceTree: Call<List<WorkType>>

         @FormUrlEncoded
         @POST("auth/create-code")
         fun createCode(@Field("User_Phone") phone: String): Call<Boolean>

         @FormUrlEncoded
         @POST("auth/check-sms-code")
         fun checkSmsCode(
             @Field("User_Phone") phone: String,
             @Field("Sms_Code_Value") code: String,
             @Field("Firebase_Token") firebaseToken: String,
             @Field("User_Token_Device_ID") deviceId: String
         ): Call<ResultAuth>


         @FormUrlEncoded
         @POST("auth/create-user")
         fun createUser(
             @Field("User_Phone") phone: String,
             @Field("Sms_Code_Value") code: String,
             @Field("User_Name") name: String,
             @Field("User_Token_Device_ID") deviceId: String,
             @Field("Firebase_Token") firebaseToken: String,
             @Field("Photo64") photo64: String
         ): Call<ResultAuth>

         //Потом удалим
         @GET("auth/get-code")
         fun getCode(@Query("User_Phone") phone: String): Call<String>

         @FormUrlEncoded
         @POST("auth/create-master")
         fun createMaster(
             @Field("User_Phone") phone: String,
             @Field("Sms_Code_Value") code: String,
             @Field("User_Name") name: String,
             @Field("User_Token_Device_ID") deviceId: String,
             @Field("Photo64") photo64: String,
             @Field("Portfolios64") portfolios64: String,
             @Field("Master_Is_Visit") masterIsVisit: Int,
             @Field("Master_About") masterAbout: String,
             @Field("Master_Home_Address") masterHomeAddress: String,
             @Field("Firebase_Token") firebaseToken: String,
             @Field("SubServices") jsonSubServiceList: String
         ): Call<ResultAuth>

         @GET("https://search-maps.yandex.ru/v1")
         fun getPlaces(
             @Query("apikey") apiKey: String,
             @Query("lang") lang: String,
             @Query("text") text: String
         ): Call<MapSearch>
     }*/


}