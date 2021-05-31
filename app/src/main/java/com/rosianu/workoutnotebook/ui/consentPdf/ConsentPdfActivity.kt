package com.rosianu.workoutnotebook.ui.consentPdf

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.rosianu.core.ui.BaseActivity
import com.rosianu.workoutnotebook.R.id.pdf
import com.rosianu.workoutnotebook.R.layout.activity_consent_pdf

class ConsentPdfActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_consent_pdf)

        val pdf: PDFView = findViewById(pdf)
        pdf.fromAsset("privacy_policy.pdf").load()
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, ConsentPdfActivity::class.java)
    }

}