# Changelog

## [0.7.0] - Android Environment Configuration
- Added Gradle wrapper (gradlew/gradlew.bat) for consistent build experience across platforms.
- Configured Gradle wrapper to use Gradle 8.1.1, compatible with Android Gradle Plugin 8.1.0.
- Added comprehensive Android Environment Setup section to README.
- Documented prerequisites: JDK requirements, Android Studio, and Android SDK setup.
- Included detailed instructions for setting up Android SDK via Android Studio and command-line tools.
- Documented required Android SDK components: API 34 (compile), API 24 (min), Build Tools 34.0.0.
- Documented required Android permissions: CAMERA, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE.
- Explained runtime vs storage permissions across different Android versions.
- Added comprehensive Building section with detailed APK and AAB generation instructions.
- Documented debug and release build processes with expected output paths.
- Added signing configuration guidance for release builds with security best practices.
- Included ADB commands for installing APKs on devices and emulators.
- Documented build variants (debug/release) and clean build procedures.
- Added project structure overview showing key directories and files.
- Enhanced build instructions with examples for generating APK and AAB formats.
- All build configurations validated: compileSdk 34, minSdk 24, targetSdk 34.
- AndroidManifest.xml properly configured with camera and storage permissions.
- Project structure documented for easy navigation and understanding.
- References design mockup at docs/mockup.html as per requirements.

## [0.6.0] - Local Android Emulator Support
- Configured app to run on Android emulator (AVD) without installation restrictions.
- Changed camera hardware requirement from required to optional in AndroidManifest.xml.
- Enables installation on emulators with or without camera support.
- Camera permission still requested at runtime for full functionality.
- Comprehensive emulator testing documentation already available in README.
- Documented emulator setup instructions including AVD configuration prerequisites.
- Documented testing procedures for image capture, gallery selection, OCR, and PDF export in emulator.
- Included troubleshooting guide for common emulator issues.
- Added performance expectations for emulator environment.
- Platform section in README now explicitly mentions emulator support.

## [0.5.0] - Basic Android Testing Documentation
- Added comprehensive testing instructions section to README.
- Documented manual testing procedures for Android emulator (AVD).
- Documented manual testing procedures for physical Android devices.
- Included step-by-step instructions for testing image capture functionality.
- Included step-by-step instructions for testing OCR functionality.
- Included step-by-step instructions for testing PDF export functionality.
- Added testing instructions for share, copy, and exit features.
- Documented prerequisites and setup steps for both emulator and physical device testing.
- Added common issues and troubleshooting section for testing scenarios.
- Included performance expectations for OCR and PDF generation.
- Added comprehensive testing checklist for both emulator and physical devices.
- Documented best practices for OCR testing (lighting, document positioning, text clarity).
- Added ADB commands for verifying PDF creation and retrieving files.
- Included instructions for loading sample images into emulator for testing.
- Updated CHANGELOG with testing documentation entry.

## [0.4.0] - Offline OCR Implementation
- Integrated Tesseract OCR engine for offline text recognition (tess-two library v9.1.0).
- Implemented camera capture functionality with permission handling.
- Added gallery image selection functionality.
- Embedded English trained data (eng.traineddata) in app assets.
- Automatic initialization of Tesseract OCR engine on app startup.
- Copy trained data from assets to app directory on first run.
- Image processing pipeline with EXIF orientation correction.
- Asynchronous OCR processing using Kotlin coroutines to prevent UI blocking.
- Display captured/selected images in the document preview area.
- Real-time OCR text recognition and display.
- Hide placeholder document view when image is captured.
- Uses ActivityResultContracts for modern camera and gallery integration.
- Implements FileProvider for secure camera image capture on Android 7.0+.
- Added ImageView to layout for displaying captured document images.
- Proper resource cleanup (TessBaseAPI) in onDestroy lifecycle method.
- Updated README with comprehensive usage instructions and OCR technology details.
- Updated CHANGELOG with OCR implementation entry.
- Works fully offline on both Android emulators and physical devices.

## [0.3.0] - PDF Export Feature
- Implemented PDF export functionality using Android's built-in PdfDocument API.
- Added "Save PDF" button handler to export OCR results to PDF format.
- PDFs are saved with timestamped filenames (e.g., Snap2Text_20231225_143022.pdf).
- Supports both Android 10+ scoped storage and legacy storage methods.
- PDFs include title, timestamp, and formatted text with proper wrapping on A4-sized pages.
- Integrated FileProvider for secure file sharing on Android 7.0+ (API 24+).
- Added automatic PDF viewer intent to open exported files.
- Created file_paths.xml configuration for FileProvider.
- Updated AndroidManifest.xml with FileProvider declaration.
- Updated README with comprehensive PDF export instructions.
- No additional dependencies required (uses native Android APIs).

## [0.2.0] - UI/UX Refinement
- Implemented single-screen Android layout with Material Design components.
- Added dark theme color palette (cyan and purple accents on dark background).
- Created vector drawable icons for all actions (capture, save, share, exit, gallery, copy).
- Implemented responsive layout with proper dimensions for different screen sizes.
- Added typography styles with clear text hierarchy.
- Created MainActivity with basic functionality (share, copy, exit).
- Included app bar, card layout, capture section, document preview, and OCR display.
- Added bottom action bar with 4 primary buttons and credits footer.
- Configured Android project structure with Gradle build files.
- Set up AndroidManifest.xml with required permissions (camera, storage).

## [0.1.0] - Initial setup
- Added README with project description.
- Added design mockup (`docs/mockup.html`).
- Created initial GitHub Issues roadmap.
