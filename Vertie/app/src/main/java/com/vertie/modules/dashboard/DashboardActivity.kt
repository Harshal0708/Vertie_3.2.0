package com.vertie.modules.dashboard

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.vertie.Constants
import com.vertie.R
import com.vertie.data.user.Userr
import com.vertie.databinding.ActivityDashboardBinding
import com.vertie.javacode.activities.ChildSelectionActivity
import com.vertie.javacode.activities.MenualAddActivity
import com.vertie.modules.base.BaseActivity
import com.vertie.modules.dashboard.historyfragment.HistoryFragment
import com.vertie.modules.dashboard.homefragment.HomeFragment
import com.vertie.modules.dashboard.homefragment.HomeViewModel
import com.vertie.modules.dashboard.profilefragment.ProfileFragment
import com.vertie.utils.services.CustomFirebaseMessagingService
import de.kenkou.sdk.headless.core.backend.AnonymousAuthTokenProvider
import de.kenkou.sdk.headless.domain.model.anamnesis.obqa.OnboardingQuestionnaireAnswersModel
import de.kenkou.sdk.visual.KenkouSDKVisual
import de.kenkou.sdk.visual.activityresults.KenkouActivityResult
import de.kenkou.sdk.visual.activityresults.KenkouResultsMapper
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

class DashboardActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var homeFragment: HomeFragment

    @Inject
    lateinit var historyFragment: HistoryFragment

    @Inject
    lateinit var profileFragment: ProfileFragment

    private lateinit var binding: ActivityDashboardBinding

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        setBottomNavConfig()
        setupPager()

        val testTokenProvider = AnonymousAuthTokenProvider()
        KenkouSDKVisual.initialize(
            testTokenProvider,
            applicationContext,
            partnerName = Constants.PARTNER_NAME,
            sdkKey = Constants.SDK_KEY
        )
    }

    private fun setBottomNavConfig() {
        binding.bottomNavigation.setOnItemSelectedListener {
            return@setOnItemSelectedListener when (it.itemId) {
                R.id.home -> {
                    binding.viewPager.setCurrentItem(0, false)
                    true
                }
                R.id.history -> {
                    binding.viewPager.setCurrentItem(1, false)
                    true
                }
                R.id.proxiUser -> {
                    val intent = Intent (this@DashboardActivity, ChildSelectionActivity::class.java)
                    startActivity(intent)
                    val userList = applicationContext.getSharedPreferences(Constants.userList, 0).getString(Constants.proxyUserList, null)
                    if (userList == "null") {}else{finish()}
                    true
                }
                R.id.profile -> {
                    binding.viewPager.setCurrentItem(2, false)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupPager() {
        val fragments : List<Fragment> = listOf<Fragment>(
            homeFragment,
            historyFragment,
            profileFragment
        )
        val adapter = TabsFragmentsPagerAdapter(this, fragments)
        binding.viewPager.apply {
            this.adapter = adapter
            offscreenPageLimit = fragments.count()
            registerOnPageChangeCallback(onPageChangeCallback)
        }
        binding.viewPager.setCurrentItem(1, false)
    }

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> {
                    binding.bottomNavigation.menu.findItem(R.id.home).isChecked = true
                }
                1 -> {
                    binding.bottomNavigation.menu.findItem(R.id.history).isChecked = true
                }
                2 -> {
                    binding.bottomNavigation.menu.findItem(R.id.profile).isChecked = true
                }
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }

    override fun refresh() {
        if (this::homeFragment.isInitialized) {
            homeFragment.refresh()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        lifecycleScope.launch {
            when (val activityResult = KenkouResultsMapper.onActivityResult(requestCode, resultCode, data)) {
                is KenkouActivityResult.Instructions -> {
                    //measurement instruction showed.
                    Log.d("TAG --- : ","Results :: ")
                    KenkouSDKVisual.presentLastBiofeedbackResults(this@DashboardActivity)
                }
                is KenkouActivityResult.Measurement -> {
                    //measurement finished
                    KenkouSDKVisual.presentPostMeasurementQuestionnaire(this@DashboardActivity, activityResult.measurement)
                }
                is KenkouActivityResult.PostMeasurementQuestionnaire -> {
                    KenkouSDKVisual.presentBiofeedbackIntroduction(this@DashboardActivity)
                }
                is KenkouActivityResult.Results -> {
                    KenkouSDKVisual.presentLastBiofeedbackResults(this@DashboardActivity)
                }
                is KenkouActivityResult.OnboardingQuestionnaireAnswers -> {
//                    KenkouSDKVisual.presentOnboardingQuestionnaire(activity)
                     OnboardingQuestionnaireAnswersModel(1994,1,127.00,69.00, System.currentTimeMillis(),12.12)
                    KenkouSDKVisual.presentLastBiofeedbackResults(this@DashboardActivity)
                }
                is KenkouActivityResult.BiofeedbackIntroduction ->{
                    KenkouSDKVisual.presentBiofeedbackBaseline(
                        this@DashboardActivity,true,true,true,null)
                }
                is KenkouActivityResult.BiofeedbackExercise ->{
                      KenkouSDKVisual.presentMeasurementInstructions(this@DashboardActivity)
                }
                is KenkouActivityResult.BiofeedbackBaseline ->{
                    Log.d("TAG --- : ","BiofeedbackBaseline :: ")
                    activityResult.baseline?.let {
                        KenkouSDKVisual.presentBiofeedbackExercise(this@DashboardActivity,
                            it,true,true,true)
                    }
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, DashboardActivity::class.java)
        }

        fun newNotificationIntent(context: Context?, data: HashMap<String, String>): Intent {
            return Intent(context, DashboardActivity::class.java)
                .also {
                    it.putExtra(
                        CustomFirebaseMessagingService.EXTRA_NOTIFICATION_DATA,
                        data as Serializable
                    )
                }
        }

        fun finishActivity(activity: Activity){
            activity.finish()
        }
    }
}