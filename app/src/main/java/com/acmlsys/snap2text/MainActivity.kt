package com.acmlsys.snap2text

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    private lateinit var btnCapture: MaterialButton
    private lateinit var btnGallery: MaterialButton
    private lateinit var btnSavePdf: MaterialButton
    private lateinit var btnShare: MaterialButton
    private lateinit var btnCopy: MaterialButton
    private lateinit var btnExit: MaterialButton
    private lateinit var btnExitToolbar: MaterialButton
    private lateinit var ocrText: MaterialTextView
    private lateinit var credits: MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        initializeViews()

        // Set up button click listeners
        setupClickListeners()
    }

    private fun initializeViews() {
        btnCapture = findViewById(R.id.btn_capture)
        btnGallery = findViewById(R.id.btn_gallery)
        btnSavePdf = findViewById(R.id.btn_save_pdf)
        btnShare = findViewById(R.id.btn_share)
        btnCopy = findViewById(R.id.btn_copy)
        btnExit = findViewById(R.id.btn_exit)
        btnExitToolbar = findViewById(R.id.btn_exit_toolbar)
        ocrText = findViewById(R.id.ocr_text)
        credits = findViewById(R.id.credits)
    }

    private fun setupClickListeners() {
        // Capture button
        btnCapture.setOnClickListener {
            showToast("Opening camera...")
            // TODO: Implement camera capture functionality in Issue 3
        }

        // Gallery button
        btnGallery.setOnClickListener {
            showToast("Opening gallery...")
            // TODO: Implement gallery selection functionality in Issue 3
        }

        // Save PDF button
        btnSavePdf.setOnClickListener {
            showToast("Saving as PDF...")
            // TODO: Implement PDF export functionality in Issue 2
        }

        // Share button
        btnShare.setOnClickListener {
            shareText()
        }

        // Copy button
        btnCopy.setOnClickListener {
            copyTextToClipboard()
        }

        // Exit buttons (both toolbar and bottom action)
        btnExit.setOnClickListener { finishAffinity() }
        btnExitToolbar.setOnClickListener { finishAffinity() }

        // Credits link
        credits.setOnClickListener {
            openWebsite()
        }
    }

    private fun shareText() {
        val text = ocrText.text.toString()
        if (text.isNotEmpty() && text != getString(R.string.no_text)) {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share text via"))
        } else {
            showToast("No text to share")
        }
    }

    private fun copyTextToClipboard() {
        val text = ocrText.text.toString()
        if (text.isNotEmpty() && text != getString(R.string.no_text)) {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("OCR Text", text)
            clipboard.setPrimaryClip(clip)
            showToast("Text copied to clipboard")
        } else {
            showToast("No text to copy")
        }
    }

    private fun openWebsite() {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.credits_url)))
            startActivity(browserIntent)
        } catch (e: Exception) {
            showToast("Cannot open browser")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
