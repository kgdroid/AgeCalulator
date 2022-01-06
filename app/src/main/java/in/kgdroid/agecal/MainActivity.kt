package `in`.kgdroid.agecal

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import org.joda.time.PeriodType
import java.text.SimpleDateFormat
import java.time.Period
import java.util.*

class MainActivity : AppCompatActivity() {
    //ID's
    private var tvDOB: TextView?=null
    private var totalMin: TextView?=null
    private var totalYear: TextView?=null
    private var totalMonth: TextView?=null
    private var totalDay: TextView?=null
    private var totalHour: TextView?=null
    private var totalSec: TextView?=null
    private var ageYear: TextView?=null
    private var ageMonth: TextView?=null
    private var ageDay: TextView?=null
    private var totalY: TextView?=null
    private var totalM: TextView?=null
    private var totalD: TextView?=null
    private var totalH: TextView?=null
    private var totalMi: TextView?=null
    private var totalS: TextView?=null
    private var tvAgeYear: TextView?=null
    private var tvAgeMonth: TextView?=null
    private var tvAgeDay: TextView?=null
    private var birthMonth: TextView?=null
    private var birthDay: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        //Find Id's
        tvDOB= findViewById(R.id.tvDOB)
        totalMin= findViewById(R.id.totalMin)
        totalYear= findViewById(R.id.totalYear)
        totalMonth= findViewById(R.id.totalMonth)
        totalDay= findViewById(R.id.totalDay)
        totalHour= findViewById(R.id.totalHour)
        totalSec= findViewById(R.id.totalSec)
        ageYear= findViewById(R.id.ageYear)
        ageMonth= findViewById(R.id.ageMonth)
        ageDay= findViewById(R.id.ageDay)
        totalY= findViewById(R.id.totalY)
        totalM= findViewById(R.id.totalM)
        totalD= findViewById(R.id.totalD)
        totalH= findViewById(R.id.totalH)
        totalMi= findViewById(R.id.totalMi)
        totalS= findViewById(R.id.totalS)
        tvAgeYear= findViewById(R.id.tvAgeYear)
        tvAgeMonth= findViewById(R.id.tvAgeMonth)
        tvAgeDay= findViewById(R.id.tvAgeDay)
        birthDay= findViewById(R.id.birthDay)
        birthMonth= findViewById(R.id.birthMonth)

        val btn:Button= findViewById(R.id.btnCalender)
        btn.setOnClickListener{
            btnClicked()
        }
    }

    private fun btnClicked(){
        val myCalender= Calendar.getInstance()
        val pickedYear= myCalender.get(Calendar.YEAR)
        val pickedMonth= myCalender.get(Calendar.MONTH)
        val pickedDay= myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd= DatePickerDialog(this, { _, year, month, dayOfMonth ->

            val dobString= "$dayOfMonth/${month+1}/$year"
            tvDOB?.text=dobString

            val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate= sdf.parse(dobString)
            val mToday= sdf.format(Calendar.getInstance().time)
            val date2= sdf.parse(mToday)
            val startDate= theDate.time
            val endDate= date2.time


            //Seconds
            val secPeriod= org.joda.time.Period(startDate, endDate, PeriodType.seconds())
            val dobInSec= secPeriod.seconds
            totalSec?.text=dobInSec.toString()
            if(dobInSec>1)
                totalS?.text= getString(R.string.stringSeconds)
            //Minutes
            val minPeriod= org.joda.time.Period(startDate, endDate, PeriodType.minutes())
            val dobInMin= minPeriod.minutes
            totalMin?.text=dobInMin.toString()
            if(dobInMin>1)
                totalMi?.text= getString(R.string.stringMinutes)
            //Hours
            val hoursPeriod= org.joda.time.Period(startDate, endDate, PeriodType.hours())
            val dobInHours= hoursPeriod.hours
            totalHour?.text=dobInHours.toString()
            if(dobInHours>1)
                totalH?.text= getString(R.string.stringHours)
            //Days
            val daysPeriod= org.joda.time.Period(startDate, endDate, PeriodType.days())
            val dobInDays= daysPeriod.days
            totalDay?.text=dobInDays.toString()
            if(dobInDays>1)
                totalD?.text= getString(R.string.stringDays)
            //Months
            val monthPeriod= org.joda.time.Period(startDate, endDate, PeriodType.months())
            val dobInMonths= monthPeriod.months
            if(dobInMonths>1)
                totalM?.text= getString(R.string.stringMonths)
            totalMonth?.text=dobInMonths.toString()
            //Years
            val yearPeriod= org.joda.time.Period(startDate, endDate, PeriodType.years())
            val dobInYear= yearPeriod.years
            totalYear?.text=dobInYear.toString()
            if(dobInYear>1)
                totalY?.text= getString(R.string.stringYears)

            //Age
            val period= org.joda.time.Period(startDate, endDate, PeriodType.yearMonthDay())
            val displayYears= period.years
            val displayMonths= period.months
            val displayDays= period.days

            ageYear?.text=displayYears.toString()
            ageMonth?.text= displayMonths.toString()
            ageDay?.text= displayDays.toString()
            if(displayYears>1)
                tvAgeYear?.text=getString(R.string.stringYears)
            if(displayMonths>1)
                tvAgeMonth?.text=getString(R.string.stringMonths)
            if(displayDays>1)
                tvAgeDay?.text=getString(R.string.stringDays)

            //Next Birthday
            val nextDOB= "$dayOfMonth/${month+1}/${pickedYear+1}"
            val nextDate= sdf.parse(nextDOB)
            val nextBday= nextDate.time
            val bPeriod= org.joda.time.Period(nextBday, endDate, PeriodType.yearMonthDay())
            val nextMonth= bPeriod.months*-1
            val nextDay= bPeriod.days*-1
            birthMonth?.text= nextMonth.toString()
            birthDay?.text= nextDay.toString()
        },
            pickedYear,
            pickedMonth,
            pickedDay
        )

        dpd.datePicker.maxDate= System.currentTimeMillis()-86400000
        dpd.show()



    }
}