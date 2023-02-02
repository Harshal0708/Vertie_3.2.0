package com.vertie

class Constants {

    companion object {
        const val CONNECT_TIMEOUT : Long                = 60 * 1000 // 1 minutes
        const val READ_TIMEOUT : Long                   = 120 * 1000 // 2 minutes
        const val WRITE_TIMEOUT : Long                  = 120 * 1000 // 2 minutes
        const val ENGLISH_CODE                          = "en"
        const val ARABIC_CODE                           = "ar"
        const val DEFAULT_COUNTRY_CODE                  = "91"
        const val BEARER                                = "Bearer"
        const val PREFERENCES_NAME                      = "user"
        const val BROADCAST_REFRESH                     = "refresh"
        const val DISMISS_TO_LOGIN                      = "dismissToLogin"

        // api routers
        const val SIGN_UP                                  = "Authenticate/Register"
        const val LOGIN                                    = "auth/Login"
        const val GET_Medical_Records_By_Date_Type         = "/Medical/GetMedicalRecordsByDateType"
        const val VERIFY_CODE                              = "Authenticate/VerifyCode"
        const val FORGET_PASSWORD                          = "Authenticate/ForgetPassword"
        const val RESET_PASSWORD                           = "Authenticate/ResetPassword"
        const val GET_USER                                 = "Users/CurrentUser"
        const val EDIT_PROFILE                             = "Users/UpdateProfile"
        const val UPLOAD_IMAGE                             = "Upload/Image"
        const val USER_DOCUMENTS                           = "UserDocuments"
        const val ACTIVE_SHIPMENTS                         = "Shipments/MyActiveShipments"
        const val LOOKUPS                                  = "Lookups"
        const val ERROR_BROADCAST_KEY                      = "com.vertie.errorBroadCast"
        const val ERROR_BROADCAST_EXTRA_MESSAGE            = "errorBroadCast.message"
        const val CUSTOM_ERROR                             = "logic_error"
        const val SHIPMENTS                                = "Shipments"
        const val Available_TimeSlots                      = "TimeSlots/AvailableTimeSlots"
        const val FINISH_AUTH_FRAGMENTS_KEY                = "finishAuthFragments"
        const val GET_ALL_RECORDS                          = "Medical/GetAllRecords"
        const val ADD_RECORD                               = "Medical/AddMedicalRecords"
        const val GetUserById                              =  "/Auth/GetUserById/";

        const val API_BASEURL                              =   "https://vertieapi.azurewebsites.net";
        const val API_LOGIN                                =   "/auth/Login";
        const val API_ForgotPassword                       =   "/Auth/ForgotPassword";
        const val API_AddMedicalRecords                    =   "/Medical/AddMedicalRecords";
        const val API_AddStepOne                           =   "/Number/saveNumber";
        const val API_GetUserById                          =   "/Auth/GetUserById/";
        const val API_GetFamilyMemberById                  =   "/Auth/GetFamilyMemberById/";
        const val API_GET_ALL_RECORDS                      =   "/Medical/GetAllRecords";

        //Database
        const val DATABASE_NAME = "vertie"

        //SDK
        const val PARTNER_NAME = "Vertie"
        const val SDK_KEY = "Kenkou4Vertie!"

        //steps
        const val step1 = "1/7: Basics"
        const val step2 = "2/7: Missed days"
        const val step3 = "3/7: Hospital/Emergency Room"
        const val step4 = "4/7: Urinary Tract Infection (UTI)"
        const val step5 = "5/7: Stress and coping"
        const val step6 = "6/7: Pain Rating"
        const val step7 = "7/7: Skin and Wound Assessments"

        //questionIds
        //step1
        const val id11 = "1999330d-baf5-4d42-991b-cecbecbeec4e"
        const val id12 = "317eeee7-9642-4a83-809e-beb55f3dd29e"
        const val id13 = "35f8b903-6410-4e1f-87a6-63dc90039636"
        const val id14 = "2512b298-6ccf-4af9-b452-7d1f860bb841"

        const val id21 = "2a9c9c99-5796-4e6b-b661-9ec9bff3c492"
        const val id22 = "58a3d047-7c14-4187-a28b-4ec7c185693c"
        const val id23 = ""
        const val id24 = "66079d7d-228f-433d-bf31-9c00e312f83a"

        const val id31 = "8d6161b6-28cc-4f85-9849-340713ae3d1c"
        const val id32 = "bf5c838e-c4ba-4154-8b16-d495e68d82d7"
        const val id33 = "af6becc0-f129-40f2-b56f-fdafdb4a382b"
        const val id34 = "0c877e1b-f5b4-48e9-9fb9-5093ac3850c5"

        const val id41 = "1f0af9d1-0919-41d7-a3d5-fc1b57b9a146"

        const val id51 = "9c396c5b-9ee4-4f39-8ab0-f08cf00fed42"

        const val id61 = "41aa8ff3-a10a-4698-8362-e94092b2342a"

        const val id71 = "a5d42c3d-a0b9-45ad-8a8a-6f94c2200afd"
        const val id72 = "f2ee11b6-70a3-44ca-90f8-d4cac19636b2"
        const val id73 = "d7295843-8b1e-4d17-b0a1-35e8481186ed"

        //local storage data
        const val userList = "userData"
        const val proxyUserList = "userlist"
    }
}