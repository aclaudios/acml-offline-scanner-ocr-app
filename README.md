# Snap2Text – Offline Scanner

Snap2Text is a minimalistic **offline document scanner** for Android.  
It uses **Tesseract OCR** embedded locally to capture text from documents without requiring internet access.

## ✨ Features
- Capture documents using the Android camera.
- Select images from the device gallery.
- Process images with offline OCR (Tesseract).
- Display recognized text instantly.
- Export results to PDF.
- Share or copy recognized text.
- Simple, single-screen UI inspired by `docs/mockup.html`.

## 📸 Using the Scanner

### Capturing Documents
1. **Launch the app** - Grant camera permissions when prompted.
2. **Tap "Capture"** - Opens the device camera.
3. **Take a photo** - Capture a clear, flat document with good lighting.
4. **Automatic OCR** - The app processes the image and displays recognized text.
5. **View Results** - The captured image appears in the document preview area, and recognized text is displayed below.

### Selecting from Gallery
1. **Tap "Gallery"** - Opens the device photo gallery.
2. **Select an image** - Choose a document photo from your gallery.
3. **Automatic OCR** - The app processes the image and displays recognized text.

### Tips for Best Results
- Use good lighting and avoid shadows on the document.
- Ensure the document is flat and not wrinkled.
- Position the camera directly above the document (not at an angle).
- Use high-contrast documents (dark text on white background works best).
- Keep the camera steady to avoid blurry images.

## 📄 PDF Export
The app allows you to export recognized OCR text as PDF documents:

- **Tap the "Save PDF" button** in the bottom action bar to export the OCR result.
- PDFs are saved with a timestamped filename (e.g., `Snap2Text_20231225_143022.pdf`).
- On **Android 10+** (API 29+), files are saved to app-specific storage: `/Android/data/com.acmlsys.snap2text/files/Documents/`
- On **Android 9 and below**, files are saved to: `/Documents/`
- The PDF contains:
  - Title: "Snap2Text OCR Result"
  - Timestamp of when the PDF was generated
  - Recognized text with proper formatting and text wrapping
- After saving, the app will attempt to open the PDF with an available PDF viewer.
- All text is formatted on A4-sized pages (595x842 points) with 40-point margins.

### Accessing Exported PDFs
- **Android 10+**: Use the Files app → "Documents" folder in the app's directory
- **Android 9 and below**: Navigate to the device's Documents folder
- PDFs can be shared or viewed using any PDF reader app installed on the device

## 🔍 OCR Technology
The app uses **Tesseract OCR**, an open-source optical character recognition engine:
- **Fully Offline**: All OCR processing happens on-device with no internet connection required.
- **Trained Data**: Uses the English language trained data (eng.traineddata) embedded in the app.
- **Library**: Implements the `tess-two` library (Android wrapper for Tesseract OCR 9.1.0).
- **Processing**: 
  - Images are automatically rotated based on EXIF orientation data.
  - Tesseract extracts text from the processed bitmap image.
  - Recognized text is displayed in the UI and can be exported to PDF or shared.
- **Supported Languages**: Currently English only (additional language packs can be added).

## 🎨 UI/UX Design
The app features a minimalist dark-themed interface with:
- **Color Palette**: Dark gradient background (#0F172A) with cyan (#22D3EE) and purple (#A78BFA) accents
- **Typography**: Clean sans-serif fonts with clear hierarchy (titles, body text, hints)
- **Responsive Layout**: Adapts to different Android screen sizes (phones and tablets)
- **Icons**: Vector-based icons for all actions (capture, save, share, exit, gallery, copy)
- **Single Screen**: All functionality accessible from one intuitive screen
- **Components**:
  - Top app bar with logo and exit button
  - Central card with document capture controls
  - Document preview area with simulated paper effect
  - OCR results display with monospace font
  - Bottom action bar with 4 primary actions
  - Credits footer with link to ACML Systems

## 📱 Platform
- Android (APK/AAB builds).
- Works on both **local emulator (AVD)** and **physical devices**.

## 🚀 Roadmap
The development is tracked via GitHub Issues:
1. Refine UI/UX of single screen (Android).
2. Export OCR result to PDF (Android).
3. Implement Offline Scanner with OCR (Android).
4. Basic Android testing.
5. Support local Android emulator.
6. Configure Android environment.

## 📖 Documentation
- Design reference: [`docs/mockup.html`](docs/mockup.html)
- Android UI preview: [`docs/android-ui-preview.html`](docs/android-ui-preview.html)
- README and CHANGELOG are updated with each issue.

## 🛠️ Building
To build the Android application:
```bash
./gradlew assembleDebug  # Build debug APK
./gradlew assembleRelease  # Build release APK
```

The APK will be generated in `app/build/outputs/apk/`.

## 🏢 Credits
Developed by [ACML Systems](https://www.acmlsys.com).
