package com.vertie.modules.dashboard.historyfragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.vertie.activity.QuestionActivity
import com.vertie.R
import com.vertie.data.medicalRecord.MedicalRecord
import com.vertie.data.medicalRecord.WeeklyGraphDataModel
import com.vertie.databinding.FragmentHistoryBinding
import com.vertie.modules.base.BaseFragment
import com.vertie.modules.base.BaseViewModel
import javax.inject.Inject


class HistoryFragment @Inject constructor() : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: FragmentHistoryBinding
    private val TAG = this.javaClass.simpleName
    var listDay : ArrayList<MedicalRecord> = ArrayList()
    var listWeek : ArrayList<MedicalRecord> = ArrayList()
    var listMonth : ArrayList<MedicalRecord> = ArrayList()
    var state : Int = 0

    private var chart: LineChart? = null
    private var chart2: LineChart? = null
    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HistoryViewModel::class.java)
    }

    private val historyAdapter : HistoryAdapter by lazy {
        HistoryAdapter(viewModel,activity!!.applicationContext,listDay)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.chart1.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyrecyclerview.adapter = historyAdapter
        binding.historyrecyclerview.layoutManager = LinearLayoutManager(activity)
        onConClickListenerslickListeners()
        viewModel.day()
        observe()
        viewModel.downloadAllRecords()
        detectScroll()
//        loadChart1()
//        loadChart2()
        binding.idll.visibility = View.GONE
        binding.ll.visibility = View.GONE
    }

    fun loadChart1(){
        chart= binding.chart1
        chart!!.setViewPortOffsets(0f, 0f, 0f, 0f)
        chart!!.setBackgroundColor(Color.rgb(104, 241, 175))
        // no description text
        chart!!.description.isEnabled = false
        // enable touch gestures
        chart!!.setTouchEnabled(true)
        // enable scaling and dragging
        chart!!.isDragEnabled = true
        chart!!.setScaleEnabled(true)
        // if disabled, scaling can be done on x- and y-axis separately
        chart!!.setPinchZoom(false)
        chart!!.setDrawGridBackground(false)
        chart!!.maxHighlightDistance = 300f
        val xx = chart!!.xAxis
        xx.isEnabled = false
        val yy = chart!!.axisLeft
        yy.setLabelCount(6, false)
        yy.textColor = Color.WHITE
        yy.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        yy.setDrawGridLines(false)
        yy.axisLineColor = Color.WHITE
        chart!!.axisRight.isEnabled = false
        chart!!.legend.isEnabled = false
        chart!!.animateXY(2000, 2000)
        setData(6, 10f)
        // don't forget to refresh the drawing
        chart!!.invalidate()
    }

    fun loadChart2(){
        chart2= binding.chart2
        chart2!!.setViewPortOffsets(0f, 0f, 0f, 0f)
        chart2!!.setBackgroundColor(Color.rgb(104, 241, 175))
        chart2!!.description.isEnabled = false
        chart2!!.setTouchEnabled(true)
        chart2!!.isDragEnabled = true
        chart2!!.setScaleEnabled(true)
        chart2!!.setPinchZoom(false)
        chart2!!.setDrawGridBackground(false)
        chart2!!.maxHighlightDistance = 300f
        val xx = chart2!!.xAxis
        xx.isEnabled = false
        val yy = chart2!!.axisLeft
        yy.setLabelCount(6, false)
        yy.textColor = Color.WHITE
        yy.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        yy.setDrawGridLines(false)
        yy.axisLineColor = Color.WHITE
        chart2!!.axisRight.isEnabled = false
        chart2!!.legend.isEnabled = false
        chart2!!.animateXY(2000, 2000)
        setData2(6, 10f)
        chart2!!.invalidate()
    }

    private fun setData(count: Int, range: Float) {
        val values = java.util.ArrayList<Entry>()
        for (i in 0 until count) {
            val `val` = (Math.random() * (range + 1)).toFloat() + 20
            values.add(Entry(i.toFloat(), `val`))
        }

        val set1: LineDataSet
        if (chart!!.data != null && chart!!.data.dataSetCount > 0) {
            set1 = chart!!.data.getDataSetByIndex(0) as LineDataSet
            set1.values = values
            chart!!.data.notifyDataChanged()
            chart!!.notifyDataSetChanged()
        }
        else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet 1")
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.2f
            set1.setDrawFilled(true)
            set1.setDrawCircles(false)
            set1.lineWidth = 1.8f
            set1.circleRadius = 4f
            set1.setCircleColor(Color.WHITE)
            set1.highLightColor = Color.rgb(244, 117, 117)
            set1.color = Color.WHITE
            set1.fillColor = Color.WHITE
            set1.fillAlpha = 100
            set1.setDrawHorizontalHighlightIndicator(false)
            set1.fillFormatter =
                IFillFormatter { dataSet: ILineDataSet?, dataProvider: LineDataProvider? ->
                    chart!!.axisLeft.axisMinimum
                }
            // create a data object with the data sets
            val data = LineData(set1)
            data.setValueTextSize(9f)
            data.setDrawValues(false)
            // set data
            chart!!.data = data
        }

    }

    private fun setData2(count: Int, range: Float) {
        val values = java.util.ArrayList<Entry>()
        for (i in 0 until count) {
            val `val` = (Math.random() * (range + 1)).toFloat() + 20
            values.add(Entry(i.toFloat(), 30.10F))//`val`))
        }

        val set2: LineDataSet
        if (chart2!!.data != null && chart2!!.data.dataSetCount > 0) {
            set2 = chart2!!.data.getDataSetByIndex(0) as LineDataSet
            set2.values = values
            chart2!!.data.notifyDataChanged()
            chart2!!.notifyDataSetChanged()
        } else {
            set2 = LineDataSet(values, "DataSet 2")
            set2.mode = LineDataSet.Mode.CUBIC_BEZIER
            set2.cubicIntensity = 0.2f
            set2.setDrawFilled(true)
            set2.setDrawCircles(false)
            set2.lineWidth = 1.8f
            set2.circleRadius = 4f
            set2.setCircleColor(Color.WHITE)
            set2.highLightColor = Color.rgb(244, 117, 117)
            set2.color = Color.WHITE
            set2.fillColor = Color.WHITE
            set2.fillAlpha = 100
            set2.setDrawHorizontalHighlightIndicator(false)
            set2.fillFormatter = IFillFormatter { dataSet: ILineDataSet?, dataProvider: LineDataProvider? ->
                    chart2!!.axisLeft.axisMinimum
                }
            // create a data object with the data sets
            val data2 = LineData(set2)
            data2.setValueTextSize(9f)
            data2.setDrawValues(false)
            chart2!!.data = data2
        }
    }

    private fun detectScroll(){
        binding.historyrecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == state){
                    //binding.navigator.animate().alpha(1.0f).setDuration(200)
                    binding.buttonAddManual.animate().alpha(1.0f).setDuration(200)
                    var handler = Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        //  binding.navigator.isVisible = true
                        binding.buttonAddManual.isVisible = true
                    },200)
                }else{
                    //binding.navigator.animate().alpha(0.0f).setDuration(200)
                    binding.buttonAddManual.animate().alpha(0.0f).setDuration(200)
                    var handler = Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        //  binding.navigator.isVisible = false
                        binding.buttonAddManual.isVisible = false
                    },200)
                }
            }
        }
        )
    }

    private fun onConClickListenerslickListeners(){
        binding.dayButton.setOnClickListener {
            viewModel.day()
            binding.idll.visibility = View.GONE
            binding.ll.visibility = View.GONE
        }

        binding.weekButton.setOnClickListener {
            viewModel.week()
            binding.idll.visibility = View.GONE
            binding.ll.visibility = View.VISIBLE
        }

        binding.monthButton.setOnClickListener {
            viewModel.month()
            binding.idll.visibility = View.GONE
            binding.ll.visibility = View.GONE
        }

        binding.buttonAddManual.setOnClickListener {
            activity?.let{
//                val intent = Intent (it, MenualHistoryActivity::class.java)
                val intent = Intent (it, QuestionActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

    private fun observe(){
        viewModel.Day.observe(viewLifecycleOwner){
            if(it){
                binding.dayButton.background = resources.getDrawable(R.drawable.menu_item_background_selected,null)
                binding.weekButton.background = resources.getDrawable(R.drawable.bottom_navigation_background,null)
                binding.monthButton.background = resources.getDrawable(R.drawable.bottom_navigation_background,null)
                binding.dayText.setTextColor(resources.getColor(R.color.white,null))
                binding.weekText.setTextColor(resources.getColor(R.color.black,null))
                binding.monthText.setTextColor(resources.getColor(R.color.black,null))
                historyAdapter.updateMedicalRecords(listDay)
            }
        }

        viewModel.Week.observe(viewLifecycleOwner){
            if(it){
                binding.weekButton.background = resources.getDrawable(R.drawable.menu_item_background_selected,null)
                binding.dayButton.background = resources.getDrawable(R.drawable.bottom_navigation_background,null)
                binding.monthButton.background = resources.getDrawable(R.drawable.bottom_navigation_background,null)
                binding.weekText.setTextColor(resources.getColor(R.color.white,null))
                binding.dayText.setTextColor(resources.getColor(R.color.black,null))
                binding.monthText.setTextColor(resources.getColor(R.color.black,null))
                historyAdapter.updateMedicalRecords(listWeek)
            }
        }

        viewModel.Month.observe(viewLifecycleOwner){
            if(it){
                binding.monthButton.background = resources.getDrawable(R.drawable.menu_item_background_selected,null)
                binding.weekButton.background = resources.getDrawable(R.drawable.bottom_navigation_background,null)
                binding.dayButton.background = resources.getDrawable(R.drawable.bottom_navigation_background,null)
                binding.monthText.setTextColor(resources.getColor(R.color.white,null))
                binding.weekText.setTextColor(resources.getColor(R.color.black,null))
                binding.dayText.setTextColor(resources.getColor(R.color.black,null))
                historyAdapter.updateMedicalRecords(listMonth)
            }
        }

        //observing data came from api and update the adapter
        viewModel.medicalRecordData.observe(viewLifecycleOwner){
            if (it != null){
                historyAdapter.updateMedicalRecords(it)
            }
        }

        viewModel.weeklyGraphDataModel.observe(viewLifecycleOwner){
            if (it != null){
                val weeklyGraphDataModel : List<WeeklyGraphDataModel> = it
//                historyAdapter.updateMedicalRecords(it)

//                val valueArr:Array<Float> = Array(weeklyGraphDataModel.size)

//                for (val i=0;i<weeklyGraphDataModel.size;i++){
//                    valueArr.set(22F)
//                }

//                weeklyGraphDataModel.size
//                setData2(6,30.10F)
            }
        }
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

}