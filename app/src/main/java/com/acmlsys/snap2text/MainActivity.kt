package com.acmlsys.snap2text

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
            exportToPdf()
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

    private fun exportToPdf() {
        val text = ocrText.text.toString()
        if (text.isEmpty() || text == getString(R.string.no_text)) {
            showToast("No text to export")
            return
        }

        try {
            // Create PDF document
            val pdfDocument = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 size in points
            val page = pdfDocument.startPage(pageInfo)

            // Draw content on PDF
            val canvas: Canvas = page.canvas
            val paint = TextPaint()
            paint.color = Color.BLACK
            paint.textSize = 12f
            paint.isAntiAlias = true

            // Create StaticLayout for text wrapping
            val textWidth = pageInfo.pageWidth - 80 // 40pt margin on each side
            val staticLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StaticLayout.Builder
                    .obtain(text, 0, text.length, paint, textWidth)
                    .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                    .setLineSpacing(0f, 1.2f)
                    .setIncludePad(false)
                    .build()
            } else {
                @Suppress("DEPRECATION")
                StaticLayout(text, paint, textWidth, Layout.Alignment.ALIGN_NORMAL, 1.2f, 0f, false)
            }

            // Draw title
            val titlePaint = TextPaint()
            titlePaint.color = Color.BLACK
            titlePaint.textSize = 18f
            titlePaint.isFakeBoldText = true
            titlePaint.isAntiAlias = true
            canvas.drawText("Snap2Text OCR Result", 40f, 60f, titlePaint)

            // Draw timestamp
            val datePaint = TextPaint()
            datePaint.color = Color.GRAY
            datePaint.textSize = 10f
            datePaint.isAntiAlias = true
            val dateFormat = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
            val timestamp = dateFormat.format(Date())
            canvas.drawText(timestamp, 40f, 85f, datePaint)

            // Draw text content
            canvas.save()
            canvas.translate(40f, 110f)
            staticLayout.draw(canvas)
            canvas.restore()

            pdfDocument.finishPage(page)

            // Save PDF to file
            val fileName = "Snap2Text_${SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())}.pdf"
            val file = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // Android 10+ - use scoped storage
                File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName)
            } else {
                // Android 9 and below
                @Suppress("DEPRECATION")
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName)
            }

            // Ensure directory exists
            file.parentFile?.mkdirs()

            // Write PDF to file
            FileOutputStream(file).use { outputStream ->
                pdfDocument.writeTo(outputStream)
            }
            pdfDocument.close()

            showToast("PDF saved: ${file.name}")
            
            // Optionally, open the PDF
            openPdfFile(file)

        } catch (e: Exception) {
            showToast("Error saving PDF: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun openPdfFile(file: File) {
        try {
            val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                androidx.core.content.FileProvider.getUriForFile(
                    this,
                    "${applicationContext.packageName}.fileprovider",
                    file
                )
            } else {
                Uri.fromFile(file)
            }

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/pdf")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NO_HISTORY
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, "Open PDF with"))
            } else {
                showToast("PDF saved successfully")
            }
        } catch (e: Exception) {
            // If opening fails, just show success message
            showToast("PDF saved successfully")
        }
    }
}
