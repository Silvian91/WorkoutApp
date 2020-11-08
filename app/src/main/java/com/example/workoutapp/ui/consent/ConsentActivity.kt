package com.example.workoutapp.ui.consent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.R.layout.activity_consent
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.ui.common.BaseActivity
import com.example.workoutapp.ui.consent.adapter.ConsentAdapter
import com.example.workoutapp.ui.consent.adapter.ConsentAdapter.ActionsViewHolderAcceptListener
import com.example.workoutapp.ui.consent.adapter.ConsentItemWrapper
import com.example.workoutapp.ui.onboarding.OnboardingActivity
import com.example.workoutapp.ui.register.RegisterActivity
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_consent.*

class ConsentActivity : BaseActivity() {

    private lateinit var viewModel: ConsentViewModel

    private lateinit var consentAdapter: ConsentAdapter

    private fun showData(consentData: List<ConsentItemWrapper>) {
        consentAdapter.items = consentData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activity_consent)
        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(ConsentViewModel::class.java)

        initConsentRecyclerView()

        subscribeToViewState()
    }

    private fun initConsentRecyclerView() {
        consent_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            consentAdapter = ConsentAdapter(
                ActionsViewHolderAcceptListener { viewModel.acceptedConsent() },
                ConsentAdapter.ActionsViewHolderDenyListener { viewModel.showDeclinedConfirmation() },
                this@ConsentActivity.lifecycle
            )
            adapter = consentAdapter
        }
    }

    private fun subscribeToViewState() {
        viewModel.viewState
            .doOnIoObserveOnMain()
            .subscribeBy { state ->
                showData(state.items)
                openRegister(state.accepted)
                showDialog(state.dialog)
                openOnboarding(state.denied)
            }
            .addTo(compositeDisposable)
    }

    private fun openRegister(consentAccepted: Boolean) {
        if (consentAccepted) {
            finish()
            startActivity(
                RegisterActivity.newIntent(this)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }

    private fun showDialog(dialog: Boolean) {
        if (dialog) {
            AlertDialog.Builder(this)
                .setMessage(R.string.text_consent_dialog_decline)
                .setNegativeButton(
                    R.string.text_dialog_alert_cancel
                ) { _, _ -> viewModel.setData()}
                .setPositiveButton(
                    R.string.text_dialog_alert_confirm
                ) { _, _ -> viewModel.deniedConsent() }
                .show()
        }
    }

    private fun openOnboarding(consentDenied: Boolean) {
        if (consentDenied) {
            startActivity(
                OnboardingActivity.newIntent(this)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, ConsentActivity::class.java)
    }


    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }

}