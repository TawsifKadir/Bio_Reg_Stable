package com.xplo.code.ui.dashboard.payment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.xplo.code.R
import com.xplo.code.base.BaseActivity
import com.xplo.code.core.Bk
import com.xplo.code.databinding.ActivityPaymentBinding
import com.xplo.code.ui.dashboard.alternate.forms.AlPreviewFragment
import com.xplo.code.ui.dashboard.payment.forms.PaymentForm1Fragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Copyright 2022 (C) xplo
 *
 * Created  : 22/06/28
 * Author   : xplo
 * Desc     :
 * Comment  :
 */

@AndroidEntryPoint
class PaymentActivity : BaseActivity(), PaymentContract.View {

    companion object {
        private const val TAG = "PaymentActivity"

        @JvmStatic
        fun open(context: Context, parent: String?) {
            Log.d(TAG, "open() called with: context = $context, parent = $parent")
            val bundle = Bundle()
            bundle.putString(Bk.KEY_PARENT, parent)
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        var STEP = 0

    }

    private lateinit var binding: ActivityPaymentBinding
    private val viewModel: PaymentViewModel by viewModels()
    //private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initInitial()
        initView()
        initObserver()


    }

    override fun initInitial() {

    }

    override fun initView() {
        setToolBar()

        val parent = intent.getStringExtra(Bk.KEY_PARENT)
        Log.d(TAG, "initView: parent: $parent")

//        if (!isNetworkIsConnected) {
//            onNoInternet()
//            return
//        }

        //navigateToForm1()

    }

    override fun initObserver() {

        lifecycleScope.launchWhenStarted {
            viewModel.event.collect { event ->
                when (event) {

                    is PaymentViewModel.Event.Loading -> {
                        showLoading()
                    }

                    is PaymentViewModel.Event.GetItemSuccess -> {
                        hideLoading()
                        //onGetPayments(event.items)
                    }

                    is PaymentViewModel.Event.GetItemFailure -> {
                        hideLoading()
                        //onGetPaymentsFailure(event.msg)
                    }


                    else -> Unit
                }
            }
        }

    }

    private fun setToolBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle("Payments")
    }

    override fun onDestroy() {
        super.onDestroy()
        //presenter.detach()
        //presenter.onDetachView()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

//        when (item.itemId) {
//            android.R.id.home -> {
//                onBackPressed()
//            }
//        }
        return super.onOptionsItemSelected(item)
    }

    override fun navigateToForm1() {
        Log.d(TAG, "navigateToForm1() called")

        STEP = 1
        doFragmentTransaction(
            PaymentForm1Fragment.newInstance(null),
            PaymentForm1Fragment.TAG,
            false,
            true
        )

    }

    override fun navigateToForm2() {
        Log.d(TAG, "navigateToForm2() called")
        STEP = 2
//        doFragmentTransaction(
//            AlForm2Fragment.newInstance(null),
//            AlForm2Fragment.TAG,
//            true,
//            false
//        )
    }

    override fun navigateToForm3() {
        Log.d(TAG, "navigateToForm3() called")
        STEP = 3
//        doFragmentTransaction(
//            AlForm3Fragment.newInstance(null),
//            AlForm3Fragment.TAG,
//            true,
//            false
//        )
    }

    override fun navigateToForm4() {
        Log.d(TAG, "navigateToForm4() called")
        STEP = 4
//        doFragmentTransaction(
//            AlForm4Fragment.newInstance(null),
//            AlForm4Fragment.TAG,
//            true,
//            false
//        )
    }

    override fun navigateToForm5() {
        Log.d(TAG, "navigateToForm5() called")
        STEP = 5
//        doFragmentTransaction(
//            AlForm5Fragment.newInstance(null),
//            HhForm5Fragment.TAG,
//            true,
//            false
//        )
    }


    override fun navigateToPreview() {
        Log.d(TAG, "navigateToPreview() called")
        STEP = 6
        doFragmentTransaction(
            AlPreviewFragment.newInstance(null),
            AlPreviewFragment.TAG,
            true,
            false
        )
    }

    override fun onBackButton() {
        Log.d(TAG, "onBackButton() called")
        val entryCount = supportFragmentManager.backStackEntryCount
        Log.d(TAG, "onBackButton: $entryCount")

        if (entryCount < 1) {
            //onCloseDialog()
            return
        }

        supportFragmentManager.popBackStack()
    }

    override fun onNextButton() {
        Log.d(TAG, "onNextButton() called")

    }

    override fun doFragmentTransaction(
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean,
        clearBackStack: Boolean
    ) {
        Log.d(
            TAG,
            "doFragmentTransaction() called with: fragment = $fragment, tag = $tag, addToBackStack = $addToBackStack, clearBackStack = $clearBackStack"
        )

        if (clearBackStack) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commit()

    }

    override fun onPageAdd() {
        Log.d(TAG, "onPageAdd() called")

    }

}
