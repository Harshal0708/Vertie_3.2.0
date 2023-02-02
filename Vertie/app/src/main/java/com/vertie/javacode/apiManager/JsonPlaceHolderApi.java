package com.vertie.javacode.apiManager;


import com.vertie.Constants;
import com.vertie.data.medicalRecord.FamilyMemberData;
import com.vertie.javacode.models.MenualEntryObj;
import com.vertie.javacode.models.UserById;
import com.vertie.javacode.utility.Constant;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @POST(Constants.API_LOGIN)
    Call<Object> login(@Body HashMap<String, String> jsonPost);

    @Headers("Content-Type: application/json")
    @POST(Constants.API_AddMedicalRecords)
    Call<Object> addMedicalRecords(@Body HashMap<String, Object> jsonPost);

    @Headers("Content-Type: application/json")
    @POST(Constants.API_AddStepOne)
    Call<Object> saveNumber(@Body HashMap<String, Object> jsonPost);

    @Headers("Content-Type: application/json")
    @POST(Constants.API_AddMedicalRecords)
    Call<Object> addMedicalRecords2(@Body MenualEntryObj jsonPost);

    @POST(Constants.API_GET_ALL_RECORDS)
    Call<Object> reset_password(@Body HashMap<String, String> jsonPost);

    @POST(Constants.API_ForgotPassword)
    Call<Object> forgotpassword(@Body HashMap<String, String> jsonPost);

    @GET(Constants.API_GetUserById+"{id}")
    Call<Object> getUserById(@Path("id")String id);

    @GET(Constants.API_GetUserById+"{id}")
    Call<UserById> getUserById2(@Path("id")String id);

    @GET(Constants.API_GetFamilyMemberById+"{id}")
    Call<FamilyMemberData> getFamilyMemberById(@Path("id")String id);

//    @GET("${Constants.strategy}/{id}")
//    Call<Object> getUserById(@Path("id") id: Int): Response<StrategyDetailRes>

//
//    @GET(Constant.MM_API_GET_SKILLSETS)
//    Call<SkillsetsResponce> skillsets();
//
//    @GET(Constant.MM_API_GET_TOOLS)
//    Call<ToolsResponce> get_tools();
//
//    @GET(Constant.MM_API_GET_LICENCES)
//    Call<LicencesResponce> get_licences();
//
//    @POST(Constant.MM_API_SEND_VERIFICATION_CODE)
//    Call<ApiResponce> send_verification_code(@Body HashMap<String, String> jsonPost);
//
//    @POST(Constant.MM_API_VERIFY_ACCOUNT)
//    Call<ApiResponce> verify_account(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, Integer> jsonPost);
//
//    @POST(Constant.MM_API_RE_SEND_VERIFICATION_CODE)
//    Call<ApiResponce> resend_code(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token);
//
//    @POST(Constant.MM_API_FORGOT_PASSWORD)
//    Call<ApiResponce> forgot_password(@Body HashMap<String, String> jsonPost);
//
//    @POST(Constant.MM_API_RESET_PASSWORD)
//    Call<ApiResponce> reset_password(@Body HashMap<String, String> jsonPost);
//
//    @POST(Constant.MM_API_RESEND_FORGOT_CODE)
//    Call<ApiResponce> resend_forgot_code(@Body String forgot_password_code);
//
//    @POST(Constant.MM_API_CREATE_EMPLOYER_PROFILE)
//    Call<ApiResponce> create_employer_profile(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token,@Body RequestBody requestBody);
//
//    @POST(Constant.MM_API_UPDATE_EMPLOYER_PROFILE)
//    Call<UpdateEmployerProfile> update_employer_profile(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token,@Body UpdateEmployerProfile.Data data);
//
//    @POST(Constant.MM_API_UPDATE_EMPLOYER_PROFILE)
//    Call<ApiResponce> update_employer_profile(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token,@Body RequestBody requestBody);
//
//    @POST(Constant.MM_API_CREATE_TRADIE_PROFILE)
//    Call<ApiResponce> create_tradie_profile(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token,@Body RequestBody requestBody);
//
//    @POST(Constant.MM_API_GET_TRADIE_PROFILE)
//    Call<GetTradieProfileResponce> get_tradie_profile(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token);
//
//    @POST(Constant.MM_API_UPDATE_TRADIE_PROFILE)
//    Call<UpdateTradieProfile> update_tradie_profile(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token,@Body UpdateTradieProfile.Data data);
//
//    @POST(Constant.MM_API_UPDATE_TRADIE_PROFILE)
//    Call<ApiResponce> update_tradie_profile(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token,@Body RequestBody requestBody);
//
//    @POST(Constant.MM_API_LOGIN)
//    Call<UserResponce> login(@Body HashMap<String, String> jsonPost);
//
//    @POST(Constant.MM_API_GET_LOCATION)
//    Call<LocationResponce> get_location(@Body HashMap<String, String> jsonPost);
//
//    @POST(Constant.MM_API_GET_PAGES_CONTENT)
//    Call<GetPagesContentResponce> get_pages_content(@Body HashMap<String, String> jsonPost);
//
//    @POST(Constant.MM_API_GET_EMPLOYER_PROFILE)
//    Call<GetEmployerProfileResponce> get_employer_profile(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token);
//
//    @POST(Constant.MM_API_GET_COMPLETE_JOB)
//    Call<CompleteJobResponce> get_complete_job(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token);
//
//    @POST(Constant.MM_API_UPDATE_PASSWORD)
//    Call<GetMessageResponce> update_password(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    @POST(Constant.MM_API_UPDATE_APPS_SETTING)
//    Call<ApiResponce> update_apps_setting(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body UpdateAppSettingModel jsonPost);
//
//    @GET(Constant.MM_API_GET_APPS_SETTING)
//    Call<AppsSettingResponce> get_apps_setting(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token);
//
//    @POST(Constant.MM_API_REPORT_PROBLEM)
//    Call<GetMessageResponce> report_problem(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    @GET(Constant.MM_API_GET_PROBLEM_TYPE)
//    Call<ProblemTypesResponce> get_problem_type();
//
//    @GET(Constant.MM_API_GET_NOTIFICATIONS)
//    Call<NotificationsResponce> get_notifications(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token);
//
//    @POST(Constant.MM_API_CREATE_JOB)
//    Call<ApiResponce> create_job(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body CompleteJob jsonPost);
//
//    @GET(Constant.MM_API_GET_TRADIEREQURIED)
//    Call<TradieRequiredResponce> get_tradierequried();
//
//    @POST(Constant.MM_API_GET_FAVOURITE_TRADIE)
//    Call<FavouriteTradieResponce> get_favourite_tradie(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    @POST(Constant.MM_API_GET_AVAILABLE_TRADIES)
//    Call<AvailableTradiesResponce> get_available_tradies(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    @GET(Constant.MM_API_GET_FAVOURITE_ALLTRADIE)
//    Call<FavouriteTradieResponce> get_favourite_alltradie(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token);
//
//    @POST(Constant.MM_API_ADD_FAVOURITE_TRADIE)
//    Call<ApiResponce> add_favourite_tradie(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    @POST(Constant.MM_API_SEARCH_FAVOURITE_TRADIE)
//    Call<ApiResponce> search_favourite_tradie(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body String jsonPost);
//
//
//    //assign_job
//    //POST
//    //Header parameter :Client-Token
//    //tradie_uuid,job_uuid
//    @POST(Constant.MM_API_ASSIGN_JOB)
//    Call<ApiResponce> assign_job(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    @GET(Constant.MM_API_GET_EMPLOYER_JOBS)
//    Call<GetEmployerJobsResponce> get_employer_jobs(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token);
//
//    @GET(Constant.MM_API_GET_TRADIES_JOBS)
//    Call<GetTradiesJobs> get_tradies_jobs(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token);
//
//    //change_job_status
//    //POST
//    //Header parameter :Client-Token
//    //tradie_uuid,job_uuid
//    @POST(Constant.MM_API_CHANGE_JOB_STATUS)
//    Call<ApiResponce> change_job_status(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    //accept_job
//    @POST(Constant.MM_API_ACCEPT_JOB)
//    Call<ApiResponce> accept_job(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//
//    //secure_job
//    //POST
//    //Header parameter :Client-Token
//    //job_uuid,status Status must be Accepted Or Rejected
//    @POST(Constant.MM_API_SECURE_JOB)
//    Call<ApiResponce> secure_job(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    //cancel_job_bytradie
//    //POST
//    //Header parameter :Client-Token
//    //job_uuid,cancel_reason
//    @POST(Constant.MM_API_CANCEL_JOB_BYTRADIE)
//    Call<ApiResponce> cancel_job_bytradie(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    //cancel_job_byemployer
//    //POST
//    //Header parameter :Client-Token
//    //job_uuid,cancel_reason
//    @POST(Constant.MM_API_CANCEL_JOB_BYEMPLOYER)
//    Call<ApiResponce> cancel_job_byemployer(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    //comments_by_tradie
//    //POST
//    //Header parameter :Client-Token
//    //job_uuid,employer_uuid,comments,star_rate
//    @POST(Constant.MM_API_COMMENTS_BY_TRADIE)
//    Call<ApiResponce> comments_by_tradie(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    //comments_by_employer
//    //POST
//    //Header parameter :Client-Token
//    //job_uuid,tradie_uuid,comments,star_rate
//    @POST(Constant.MM_API_COMMENTS_BY_EMPLOYER)
//    Call<ApiResponce> comments_by_employer(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    @GET(Constant.MM_API_GET_NOT_AVAILABLE_TRADIE)
//    Call<GetNotAvailableTradie> get_not_available_tradie(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token);
//
//    @POST(Constant.MM_API_NOT_AVAILABLE_TRADIE)
//    Call<GetMessageResponce> not_available_tradie(@Header(Constant.MM_API_HEADER_CLIENT_TOKEN)String token, @Body HashMap<String, String> jsonPost);
//
//    //FaceBook Login/Register	first_name,last_name,email,facebook_id,social_platform='facebook'	POST    profile set process continue same as normol login
//    //Google  Login/Register	first_name,last_name,email,social_platform='google'	POST
//    @POST(Constant.MM_API_SOCIAL_LOGIN)
//    Call<ApiResponce> social_login( @Body HashMap<String, String> jsonPost);

}