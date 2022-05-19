package ir.zhiran2021.snamall.feature.profile.auoth

import android.content.Context
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.auth.api.phone.SmsRetriever
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.databinding.ActivityVerifyBinding
import ir.zhiran2021.snamall.utils.SMSReceiver
import org.koin.androidx.viewmodel.ext.android.viewModel

class VerifyActivity : AppCompatActivity() , SMSReceiver.OTPReceiveListener{

    private val coordinatorLayout: CoordinatorLayout by lazy {
        findViewById(R.id.verify_coordinator)
    }
    private val editTextOne: EditText by lazy {
        findViewById(R.id.edt_one)
    }
    private val editTextTwo: EditText by lazy {
        findViewById(R.id.edt_two)
    }
    private val editTextThree: EditText by lazy {
        findViewById(R.id.edt_three)
    }
    private val editTextFour: EditText by lazy {
        findViewById(R.id.edt_four)
    }

    lateinit var binding :ActivityVerifyBinding

    var phone: String? = null
    var family: String? = null
    val authViewModel: AuthViewModel by viewModel()

    private var smsReceiver: SMSReceiver? = null
    var verifyCode:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_verify)
        binding = ActivityVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)



        authViewModel.register(intent.getStringExtra("phone"), intent.getStringExtra("name"))
        authViewModel.registerLiveData.observe(this){
            verifyCode = it.code
        }

       // startSMSListener()

        setListener()
        initFocus()
    }

    private fun setListener(){
        coordinatorLayout.setOnClickListener {
            val inPutManger = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inPutManger.hideSoftInputFromWindow(coordinatorLayout.windowToken,0)
        }

        setTextChangedListener(formEditText = editTextOne , targetEditText = editTextTwo)
        setTextChangedListener(formEditText = editTextTwo , targetEditText = editTextThree)
        setTextChangedListener(formEditText = editTextThree , targetEditText = editTextFour)
        setTextChangedListener(formEditText = editTextFour, done = {
            verifyOTPCode()
        })

        setKeyListener(formEditText = editTextTwo , backToEditText = editTextOne)
        setKeyListener(formEditText = editTextThree , backToEditText = editTextTwo)
        setKeyListener(formEditText = editTextFour , backToEditText = editTextThree)
    }

    private fun initFocus(){
        editTextOne.isEnabled = true
        editTextOne.postDelayed({
             editTextOne.requestFocus()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(editTextOne,InputMethodManager.SHOW_FORCED)

        },500)
    }

    private fun reset(){
        editTextOne.isEnabled = false
        editTextTwo.isEnabled = false
        editTextThree.isEnabled = false
        editTextFour.isEnabled = false

        editTextOne.setText("")
        editTextTwo.setText("")
        editTextThree.setText("")
        editTextFour.setText("")

        initFocus()

    }

    private fun setTextChangedListener(
        formEditText:EditText,
        targetEditText:EditText? = null,
        done:(()->Unit)? = null
    ){
        formEditText.addTextChangedListener {
            it?.let {string->
                if (string.isNotEmpty()){
                    targetEditText?.let {editText->
                        editText.isEnabled = true
                        editText.requestFocus()


                    }?:run{
                        done ?.let {done->
                            done()

                        }
                    }

                    formEditText.clearFocus()
                    formEditText.isEnabled = false
                }

            }
        }
    }

    private fun setKeyListener(formEditText: EditText, backToEditText:EditText){
        formEditText.setOnKeyListener { _, _, event ->
            if (null != event && KeyEvent.KEYCODE_DEL == event.keyCode){
                backToEditText.isEnabled = true
                backToEditText.requestFocus()
                backToEditText.setText("")

                formEditText.clearFocus()
                formEditText.isEnabled = false
            }
            false
        }
    }

    private fun verifyOTPCode(){
        val otpCode = "${editTextOne.text.toString().trim()}"+ "${editTextTwo.text.toString().trim()}"+
                "${editTextThree.text.toString().trim()}"+ "${editTextFour.text.toString().trim()}"

        if (4 != otpCode.length){
            return
        }

        if (otpCode == verifyCode){
            Toast.makeText(applicationContext, "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show()
            finish()

            val inPutManger = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inPutManger.hideSoftInputFromWindow(coordinatorLayout.windowToken,0)
            return
        }

        Toast.makeText(applicationContext, "ثبت نام با انجام نشد", Toast.LENGTH_SHORT).show()
        reset()

    }

    private fun startSMSListener() {
        try {
            smsReceiver = SMSReceiver()
            smsReceiver!!.setOTPListener(this)
            val intentFilter = IntentFilter()
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION)
            this.registerReceiver(smsReceiver, intentFilter)
            val client = SmsRetriever.getClient(this)
            val task = client.startSmsRetriever()
            task.addOnSuccessListener {
                // API successfully started
                //  showToast("successfully")


            }
            task.addOnFailureListener {
                // Fail to start API
                //   showToast("Fail to start")

            }
        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    override fun onOTPReceived(otp: String?) {
        //  showToast("OTP Received: $otp")
        val codes = otp!!.replace("کد تایید شما ", "")
        val show = codes!!.replace("تست اپلیکیشن فروشگاهی", "")
        val ss = show.replace("ZhK9Rj5xdnq", "")

      //  binding.edtCode.setText(ss.toString().trim())
        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed(Runnable {
            Toast.makeText(applicationContext, "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT)
                .show()
            finish()

        }, 1000)

        // showToast(ss)
        //    edt
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver)
            smsReceiver = null
        }
    }

    override fun onOTPTimeOut() {
        showToast("OTP Time out")
    }

    override fun onOTPReceivedError(error: String?) {
        showToast(error.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver)
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}