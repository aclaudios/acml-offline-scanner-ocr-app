# Changelog

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
