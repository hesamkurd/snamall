package ir.mamhesam.snamall.feature.profile.auoth

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.android.gms.auth.api.phone.SmsRetriever
import ir.mamhesam.snamall.R
import ir.mamhesam.snamall.databinding.ActivityVerifyBinding
import ir.mamhesam.snamall.utils.SMSReceiver
import org.koin.androidx.viewmodel.ext.android.viewModel

class VerifyActivity : AppCompatActivity() , SMSReceiver.OTPReceiveListener{

    lateinit var binding :ActivityVerifyBinding

    var phone: String? = null
    var family: String? = null
    val authViewModel: AuthViewModel by viewModel()

    private var smsReceiver: SMSReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_verify)
        binding = ActivityVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel.register(intent.getStringExtra("phone"), intent.getStringExtra("name"))

        startSMSListener()
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
        val ss = show.replace("Kkvibt5RjAv", "")

        binding.edtCode.setText(ss.toString().trim())
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