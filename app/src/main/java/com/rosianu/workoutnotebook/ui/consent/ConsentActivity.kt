package com.rosianu.workoutnotebook.ui.consent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rosianu.workoutnotebook.R
import com.rosianu.workoutnotebook.R.layout.activity_consent
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.core.ui.BaseActivity
import com.rosianu.workoutnotebook.ui.consent.adapter.ConsentAdapter
import com.rosianu.workoutnotebook.ui.consent.adapter.ConsentAdapter.*
import com.rosianu.workoutnotebook.ui.consent.adapter.ConsentItemWrapper
import com.rosianu.workoutnotebook.ui.consentPdf.ConsentPdfActivity
import com.rosianu.workoutnotebook.ui.onboarding.OnboardingActivity
import com.rosianu.workoutnotebook.ui.register.RegisterActivity
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
                ActionsViewHolderDenyListener { viewModel.showDeclinedConfirmation() },
                PdfLink { openPdf() },
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

    private fun openPdf() {
        startActivity(
            ConsentPdfActivity.newIntent(this)
        )
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