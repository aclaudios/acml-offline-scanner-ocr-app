# Snap2Text – Offline Scanner

Snap2Text is a minimalistic **offline document scanner** for Android.  
It uses **Tesseract OCR** embedded locally to capture text from documents without requiring internet access.

## ✨ Features
- Capture documents using the Android camera.
- Process images with offline OCR (Tesseract).
- Display recognized text instantly.
- Export results to PDF.
- Simple, single-screen UI inspired by `docs/mockup.html`.

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
