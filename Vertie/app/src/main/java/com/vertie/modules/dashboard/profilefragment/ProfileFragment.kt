package com.vertie.modules.dashboard.profilefragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.LinkedTreeMap
import com.squareup.moshi.Json
import com.vertie.Constants
import com.vertie.data.medicalRecord.FamilyMemberData
import com.vertie.databinding.FragmentProfileBinding
import com.vertie.javacode.apiManager.APIManager
import com.vertie.javacode.models.UserById
import com.vertie.javacode.utility.Debugger
import com.vertie.javacode.utility.Globals
import com.vertie.modules.base.BaseFragment
import com.vertie.modules.base.BaseViewModel
import com.vertie.modules.login.LoginActivity
import com.vertie.utils.bindingUtils.DataFormate.DataFormateInDDMMYYYY
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ProfileFragment @Inject constructor() : BaseFragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: FragmentProfileBinding
    private val TAG = this.javaClass.simpleName

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
//        user_id
//        var preferences: SharedPreferences
//        var editor: SharedPreferences.Editor = preferences.edit()
//        val uId:String= preferences.getString(UserPersistanceContract.UserEntry.USER_ID)
        val preferences = this.activity!!.getSharedPreferences("user", 0)

        val userId : String? = preferences.getString(com.vertie.data.user.source.local.UserPersistanceContract.UserEntry.USER_ID,null)
//        viewModel.getUserData(userId.toString())
        if (userId != null) {
//            val isMember  = preferences.getBoolean("isFamilyMembar", false);
//            if(preferences.getBoolean("isFamilyMembar", false)){
//            apiCallFamilyMember(userId)
//            }else{
                apiCallUser(userId)
//            }
        }

        viewModel.medicalRecordData.observe(viewLifecycleOwner){
            if (it != null){
                binding.tvName.text = it.firstName + " "+it.lastName
                binding.tvEm.text = it.email
                binding.tvAddr.text = it.streetAddress1 + " " +it.streetAddress2
                binding.tvMob.text = it.primaryPhone
                binding.tvCountryy.text = it.country
                binding.tvGenderr.text = it.gender
                binding.tvDOB.text = DataFormateInDDMMYYYY(it.dob)
                binding.tvUName.text = it.firstName.get(0) + ""+it.lastName.get(0)
            }
        }

        viewModel.medicalRecordData2.observe(viewLifecycleOwner){
            if (it != null){
//                binding.tvName.text = it.firstName + " "+it.lastName
//                binding.tvEm.text = it.email
//                binding.tvAddr.text = it.streetAddress1 + " " +it.streetAddress2
//                binding.tvMob.text = it.primaryPhone
//                binding.tvCountryy.text = it.country
//                binding.tvGenderr.text = it.gender
//                binding.tvDOB.text = DataFormateInDDMMYYYY(it.dob)
//                binding.tvUName.text = it.firstName.get(0) + ""+it.lastName.get(0)
            }
        }

        binding.btnEditProfile.setOnClickListener {
            activity?.let{
                val settings = context?.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE)
                if (settings != null) {
                    settings?.edit().clear().commit()
                }
                val intent = Intent (it, LoginActivity::class.java)
                it.startActivity(intent)
                activity?.finish()
            }
        }
        return binding.root
    }

    private fun apiCallFamilyMember(userId:String){
        val call = APIManager
            .getUserManagerService(
                this.activity!!,
                GsonConverterFactory.create(GsonBuilder().setLenient().create())
            )
            .getFamilyMemberById(userId)
        Globals.showProgressDialog(this.activity!!)
        call.enqueue(object : Callback<FamilyMemberData?> {
            override fun onResponse(
                call: Call<FamilyMemberData?>,
                response: Response<FamilyMemberData?>
            ) {
                val memberObj : FamilyMemberData? = response.body()
                Globals.hideProgressDialog()
//                this.activity!!.finish()
                binding.tvName.text = memberObj?.name
                binding.tvEm.text = " - "//userData.email  relationId

                binding.tvAddr.text = memberObj?.zipCode
                binding.tvMob.text = " - " //userData.primaryPhone
                binding.tvCountryy.text = " - " //userData.country
                binding.tvGenderr.text = memberObj?.gender
                binding.tvDOB.text = memberObj?.dob?.let { DataFormateInDDMMYYYY(it) }
                binding.tvUName.text =  memberObj?.name?.take(2)
            }
            override fun onFailure(call: Call<FamilyMemberData?>, t: Throwable) {
                Globals.hideProgressDialog()
            }
        })
    }

    private fun apiCallUser(userId:String){
        val call = APIManager
            .getUserManagerService(
                this.activity!!,
                GsonConverterFactory.create(GsonBuilder().setLenient().create())
            )
            .getUserById2(userId)
        Globals.showProgressDialog(this.activity!!)
        call.enqueue(object : Callback<UserById?> {
            override fun onResponse(
                call: Call<UserById?>,
                response: Response<UserById?>
            ) {
                Globals.hideProgressDialog()
                val userData = response.body()
                Debugger.debugD(TAG, response.toString())
                binding.tvName.text = userData!!.firstName + " "+userData.lastName
                binding.tvEm.text = userData.email
                binding.tvAddr.text = userData.streetAddress1 + " " +userData.streetAddress2
                binding.tvMob.text = userData.primaryPhone
                binding.tvCountryy.text = userData.country
                binding.tvGenderr.text = userData.gender
                binding.tvDOB.text = DataFormateInDDMMYYYY(userData.dob)
                binding.tvUName.text = userData.firstName.get(0) + ""+userData.lastName.get(0)
            }
            override fun onFailure(call: Call<UserById?>, t: Throwable) {
                Globals.hideProgressDialog()
            }
        })
    }



    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }

}