package ir.zhiran2021.snamall.feature.profile.auoth

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.addTextChangedListener
import ir.zhiran2021.snamall.R
import ir.zhiran2021.snamall.databinding.ActivityVerifyLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class VerifyLoginActivity : AppCompatActivity() {
    private val coordinatorLayout: CoordinatorLayout by lazy {
        findViewById(R.id.verify_login_coordinator)
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


    lateinit var binding: ActivityVerifyLoginBinding

    val authViewModel: AuthViewModel by viewModel()
    var verifyCode1:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_verify_login)
        binding = ActivityVerifyLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel.login(intent.getStringExtra("phone"))
        authViewModel.loginLiveData.observe(this){
            verifyCode1 = it.code
        }
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
            inputMethodManager.showSoftInput(editTextOne, InputMethodManager.SHOW_FORCED)

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



        if (otpCode == verifyCode1){
            Toast.makeText(applicationContext, "ورود با موفقیت انجام شد", Toast.LENGTH_SHORT).show()
            finish()

            val inPutManger = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inPutManger.hideSoftInputFromWindow(coordinatorLayout.windowToken,0)
            return
        }else{
            Toast.makeText(applicationContext, "ورود انجام نشد", Toast.LENGTH_SHORT).show()
            reset()
        }

    }

}