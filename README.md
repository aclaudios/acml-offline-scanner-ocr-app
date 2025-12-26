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

## 🔧 Android Environment Setup

### Prerequisites
To build and run this Android application, you need:

1. **Java Development Kit (JDK)**
   - JDK 8 or higher (JDK 11 or 17 recommended)
   - Verify: `java -version`

2. **Android Studio** (recommended) or **Android SDK Command-line Tools**
   - Download from: https://developer.android.com/studio
   - Android Studio includes:
     - Android SDK
     - Android SDK Platform-Tools (adb, etc.)
     - Android SDK Build-Tools
     - Android Emulator

3. **Android SDK Requirements**
   - **Compile SDK**: API 34 (Android 14)
   - **Min SDK**: API 24 (Android 7.0)
   - **Target SDK**: API 34 (Android 14)
   - **Build Tools**: 34.0.0 or higher

### Setting up Android SDK

#### Option 1: Using Android Studio (Recommended)
1. Install Android Studio from https://developer.android.com/studio
2. Launch Android Studio
3. Go to **Tools → SDK Manager**
4. In the **SDK Platforms** tab:
   - Check **Android 14.0 (API 34)** - for target SDK
   - Check **Android 7.0 (API 24)** - for minimum SDK support
5. In the **SDK Tools** tab, ensure these are installed:
   - Android SDK Build-Tools 34.0.0
   - Android SDK Platform-Tools
   - Android Emulator (if testing on emulator)
   - Android SDK Command-line Tools
6. Click **Apply** to install

#### Option 2: Using Command-line Tools
1. Download Android SDK command-line tools from https://developer.android.com/studio#command-tools
2. Extract to a directory (e.g., `~/Android/Sdk`)
3. Set `ANDROID_HOME` environment variable:
   ```bash
   export ANDROID_HOME=~/Android/Sdk
   export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin
   export PATH=$PATH:$ANDROID_HOME/platform-tools
   ```
4. Accept licenses:
   ```bash
   sdkmanager --licenses
   ```
5. Install required SDK components:
   ```bash
   sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
   ```

### Gradle Wrapper
This project includes a Gradle wrapper (`gradlew` / `gradlew.bat`) that automatically downloads the correct Gradle version (8.1.1).

**No need to install Gradle separately** - just use the wrapper scripts:
- **Linux/Mac**: `./gradlew`
- **Windows**: `gradlew.bat`

The first time you run the wrapper, it will download Gradle automatically.

### Required Permissions
The app requires the following Android permissions (configured in `AndroidManifest.xml`):

1. **CAMERA** (runtime permission)
   - Required for capturing document images
   - User must grant permission at runtime
   - App will prompt user on first use

2. **WRITE_EXTERNAL_STORAGE** (for Android 9 and below)
   - Required for saving PDF files
   - Automatically granted on Android 10+ (scoped storage)
   - Only needed for API 28 and below

3. **READ_EXTERNAL_STORAGE** (for Android 9 and below)
   - Required for selecting images from gallery
   - Automatically granted on Android 10+ (scoped storage)
   - Only needed for API 28 and below

### Project Structure
```
acml-offline-scanner-ocr-app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/acmlsys/snap2text/
│   │   │   │   └── MainActivity.kt          # Main application code
│   │   │   ├── res/                          # Android resources
│   │   │   │   ├── layout/                   # UI layouts
│   │   │   │   ├── drawable/                 # Icons and graphics
│   │   │   │   └── values/                   # Strings, colors, themes
│   │   │   ├── assets/tessdata/              # Tesseract OCR trained data
│   │   │   └── AndroidManifest.xml           # App configuration & permissions
│   │   └── build.gradle                      # App-level build configuration
├── build.gradle                              # Project-level build configuration
├── settings.gradle                           # Project settings
├── gradlew                                   # Gradle wrapper (Unix)
├── gradlew.bat                               # Gradle wrapper (Windows)
└── gradle/wrapper/                           # Gradle wrapper configuration
```

## 🛠️ Building

### Building Debug APK
The debug APK is signed with a debug keystore and is suitable for development and testing:

```bash
./gradlew assembleDebug
```

The debug APK will be generated at:
```
app/build/outputs/apk/debug/app-debug.apk
```

### Building Release APK
The release APK is optimized for distribution:

```bash
./gradlew assembleRelease
```

The release APK will be generated at:
```
app/build/outputs/apk/release/app-release.apk
```

**Note**: For production releases, you need to sign the APK with your release keystore. See [Signing Configuration](#signing-configuration) below.

### Building Android App Bundle (AAB)
Android App Bundles are the recommended publishing format for Google Play Store:

```bash
./gradlew bundleRelease
```

The AAB file will be generated at:
```
app/build/outputs/bundle/release/app-release.aab
```

AAB files are more efficient than APKs as Google Play generates optimized APKs for each device configuration.

### Signing Configuration
For release builds, you need to configure signing in `app/build.gradle`:

```gradle
android {
    signingConfigs {
        release {
            storeFile file(System.getenv("KEYSTORE_PATH") ?: "path/to/your/keystore.jks")
            storePassword System.getenv("KEYSTORE_PASSWORD")
            keyAlias System.getenv("KEY_ALIAS")
            keyPassword System.getenv("KEY_PASSWORD")
        }
    }
    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

**Security Best Practices**:
- Never commit keystore files or passwords to version control
- Use environment variables for sensitive credentials
- Store keystore files in a secure location outside the project directory
- Alternatively, use `gradle.properties` (add to `.gitignore`) or CI/CD secrets

Example environment variable setup:
```bash
export KEYSTORE_PATH=/secure/path/to/your/keystore.jks
export KEYSTORE_PASSWORD=your-store-password
export KEY_ALIAS=your-key-alias
export KEY_PASSWORD=your-key-password
```

### Installing on Device
Once built, you can install the APK on a connected device or emulator:

```bash
# Install debug APK
adb install app/build/outputs/apk/debug/app-debug.apk

# Install release APK
adb install app/build/outputs/apk/release/app-release.apk

# Install with replacement (if app is already installed)
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Clean Build
To clean the project and remove all build artifacts:

```bash
./gradlew clean
```

### Build Variants
The project supports the following build variants:
- **debug**: For development and testing, includes debugging symbols
- **release**: Optimized for production, may be signed with release keystore

You can view all available tasks:
```bash
./gradlew tasks
```

## 🧪 Testing

### Testing on Android Emulator (AVD)

#### Prerequisites
- Android Studio installed with Android SDK
- An Android Virtual Device (AVD) configured with:
  - API Level 24 (Android 7.0) or higher
  - Camera emulation enabled
  - Sufficient storage space (at least 2GB free)

#### Testing Image Capture
1. **Launch the emulator**: Start your AVD from Android Studio (Tools → Device Manager → Run).
2. **Install the app**: 
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```
   Or drag and drop the APK file into the emulator window.
3. **Grant camera permission**: When prompted, tap "Allow" to grant camera access.
4. **Test capture functionality**:
   - Tap the "Capture" button in the app.
   - The emulator's virtual camera will open.
   - The virtual camera displays a test pattern or animated scene.
   - Tap the capture button to take a photo.
   - Verify the captured image appears in the document preview area.
5. **Expected results**:
   - Camera opens without crashes.
   - Image is captured and displayed correctly.
   - App transitions smoothly back to the main screen.

#### Testing Gallery Selection (Emulator)
1. Before testing, load sample images into the emulator:
   ```bash
   adb push /path/to/sample-document.jpg /sdcard/Pictures/
   ```
2. In the app, tap the "Gallery" button.
3. Select the sample document image.
4. Verify the image loads and displays correctly.

#### Testing OCR (Emulator)
1. After capturing or selecting an image with clear text:
   - Wait for "Processing image..." toast message.
   - OCR processing happens automatically.
   - Watch for "OCR completed" or "No text found" toast.
2. **Expected results**:
   - OCR processes without crashes (even if text recognition is limited).
   - Recognized text appears in the "Recognized Text" section.
   - For emulator virtual camera images, text recognition may be limited due to the test pattern.
   - Use gallery images with clear text for better OCR results in the emulator.

#### Testing PDF Export (Emulator)
1. After OCR completes and text is displayed:
   - Tap the "Save PDF" button.
   - Wait for the success toast: "PDF saved: Snap2Text_[timestamp].pdf".
2. **Verify PDF creation**:
   ```bash
   adb shell ls -l /sdcard/Android/data/com.acmlsys.snap2text/files/Documents/
   ```
   You should see the generated PDF file with a timestamp.
3. **Retrieve PDF from emulator** (optional):
   ```bash
   adb pull /sdcard/Android/data/com.acmlsys.snap2text/files/Documents/Snap2Text_*.pdf ./
   ```
4. **Expected results**:
   - PDF is created without errors.
   - File is accessible in the app-specific directory.
   - PDF viewer intent opens (if a PDF viewer app is installed in the emulator).

### Testing on Physical Android Device

#### Prerequisites
- Android device running Android 7.0 (API 24) or higher
- USB debugging enabled on the device (Settings → Developer options → USB debugging)
- Device connected to your computer via USB
- ADB installed and device recognized (`adb devices` shows your device)

#### Testing Image Capture
1. **Install the app on device**:
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```
2. **Grant camera permission**: Allow camera access when prompted.
3. **Test capture with a real document**:
   - Place a printed document (receipt, book page, newspaper) on a flat surface.
   - Ensure good lighting (avoid shadows and glare).
   - Tap "Capture" button in the app.
   - Position camera directly above the document (not at an angle).
   - Keep the camera steady and tap the capture button.
   - Verify the captured image appears clearly in the preview area.
4. **Expected results**:
   - Real camera opens instantly.
   - Image capture is smooth and responsive.
   - Captured image displays with correct orientation.

#### Testing OCR (Physical Device)
1. **Best practices for OCR testing**:
   - Use documents with clear, printed text (not handwritten).
   - Ensure high contrast (dark text on white/light background).
   - Use good lighting and avoid shadows.
   - Keep document flat and unwrinkled.
   - Position camera perpendicular to document surface.
2. **Perform OCR test**:
   - Capture or select an image following best practices above.
   - Wait for automatic OCR processing.
   - Observe the "OCR completed" toast message.
   - Review the recognized text in the "Recognized Text" section.
3. **Expected results**:
   - OCR completes within 3-10 seconds (depending on image size and device performance).
   - Recognized text closely matches the original document.
   - Minor errors are acceptable (OCR is not 100% accurate).
   - No app crashes or freezes during processing.

#### Testing Gallery Selection (Physical Device)
1. Open the app and tap "Gallery" button.
2. Navigate to your device's photo gallery.
3. Select a photo of a document.
4. Verify the image loads and OCR processing begins automatically.
5. **Expected results**:
   - Gallery opens correctly.
   - Image selection works smoothly.
   - Selected image displays properly.

#### Testing PDF Export (Physical Device)
1. After capturing/selecting an image and OCR completes:
   - Tap "Save PDF" button.
   - Wait for success toast with filename.
2. **Verify PDF on device**:
   - Open the Files app on your Android device.
   - Navigate to: `Android/data/com.acmlsys.snap2text/files/Documents/`
   - Locate the PDF file (e.g., `Snap2Text_20231225_143022.pdf`).
   - Tap to open with a PDF viewer.
3. **Verify PDF contents**:
   - Check that PDF contains the title "Snap2Text OCR Result".
   - Verify the timestamp is present.
   - Confirm the recognized text is included and properly formatted.
   - Text should be readable and wrapped correctly on the page.
4. **Expected results**:
   - PDF is created successfully.
   - File is accessible via Files app.
   - PDF viewer opens the file correctly.
   - Content matches the OCR result displayed in the app.

### Testing Other Features

#### Share Functionality
1. After OCR completes, tap the "Share" button.
2. Select a sharing method (Messages, Email, etc.).
3. Verify the OCR text is included in the share intent.

#### Copy Text Functionality
1. After OCR completes, tap the "Copy Text" button.
2. Open another app (Notes, Messages).
3. Long-press and paste.
4. Verify the OCR text is pasted correctly.

#### Exit Functionality
1. Tap the "Exit" button (in toolbar or bottom action bar).
2. Verify the app closes completely.

### Common Issues and Troubleshooting

#### OCR Returns No Text
- **Cause**: Poor image quality, low contrast, or unclear text.
- **Solution**: Recapture with better lighting, ensure document is flat, and use high-contrast documents.

#### Camera Permission Denied
- **Cause**: User denied camera permission.
- **Solution**: Go to device Settings → Apps → Snap2Text → Permissions → Enable Camera.

#### PDF Export Fails
- **Cause**: Storage permission issues or insufficient storage.
- **Solution**: 
  - Check available storage space.
  - For Android 9 and below, ensure storage permissions are granted.
  - Try restarting the app.

#### App Crashes on Launch
- **Cause**: Missing Tesseract data files or incompatible device.
- **Solution**: 
  - Clear app data: Settings → Apps → Snap2Text → Storage → Clear Data.
  - Reinstall the app.
  - Ensure device runs Android 7.0 (API 24) or higher.

#### Blurry or Rotated Images
- **Cause**: Camera shake or incorrect orientation handling.
- **Solution**: 
  - Hold device steady when capturing.
  - Ensure good lighting to reduce capture time.
  - The app automatically corrects orientation based on EXIF data.

### Performance Expectations
- **OCR Processing Time**: 3-10 seconds depending on image size and device performance.
- **PDF Generation**: < 1 second for typical OCR results.
- **App Size**: ~50MB (includes Tesseract library and trained data).
- **Memory Usage**: ~100-200MB during OCR processing.

### Testing Checklist
Use this checklist to verify all features work correctly:

**Emulator Testing:**
- [ ] App installs and launches without errors
- [ ] Camera permission prompt appears and works
- [ ] Virtual camera opens and captures images
- [ ] Gallery selection works with pre-loaded images
- [ ] OCR processes images (even if recognition is limited)
- [ ] PDF export creates files successfully
- [ ] Share and copy features work correctly
- [ ] Exit button closes the app

**Physical Device Testing:**
- [ ] App installs and launches without errors
- [ ] Real camera opens and captures clear images
- [ ] OCR accurately recognizes text from real documents
- [ ] PDF export works and files are accessible via Files app
- [ ] PDF content is correct and properly formatted
- [ ] Gallery selection works with device photos
- [ ] Share feature opens sharing options correctly
- [ ] Copy feature copies text to clipboard
- [ ] Exit button closes the app completely

## 🏢 Credits
Developed by [ACML Systems](https://www.acmlsys.com).
