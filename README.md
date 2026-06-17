# 📋 Reminders

A simple, elegant Android app to store and quickly access your most important personal reminders — passwords, PINs, dates, and more — all in one place.

---

## 📱 Screens

### 1. Main Screen — Tab Navigation

The app opens to a two-tab interface (`TabLayout` + `ViewPager2`), letting you swipe or tap between **Passwords** and **General** reminders.

```
┌─────────────────────────────────────┐
│  ┌──────────────┬──────────────────┐ │
│  │   Password   │     General      │ │  ← TabLayout
│  └──────────────┴──────────────────┘ │
│                                       │
│          [Fragment content]           │  ← ViewPager2
│                                       │
└─────────────────────────────────────┘
```

---

### 2. Password Tab

Stores credentials and PIN codes. Each item is displayed as a **Material card** with an icon, a label, and the saved value below it. Tap any card to edit its value.

```
┌─────────────────────────────────────┐
│  ┌──────────────┬──────────────────┐ │
│  │  ● Password  │     General      │ │
│  └──────────────┴──────────────────┘ │
│                                       │
│  ┌───────────────────────────────┐   │
│  │  📶  WIFI PASSWORD            │   │
│  │      MyWifiPassword123        │   │
│  └───────────────────────────────┘   │
│                                       │
│  ┌───────────────────────────────┐   │
│  │  📱  TABLET PIN               │   │
│  │      1234                     │   │
│  └───────────────────────────────┘   │
│                                       │
│  ┌───────────────────────────────┐   │
│  │  🔒  BIKE LOCK                │   │
│  │      325                      │   │
│  └───────────────────────────────┘   │
│                                       │
└─────────────────────────────────────┘
```

**Reminder items:**
| Icon | Label | Example Value |
|------|-------|---------------|
| 📶 WiFi | Wifi Password | `MyWifiPassword123` |
| 📱 Tablet | Tablet Pin | `1234` |
| 🔒 Bike | Bike Lock | `325` |

---

### 3. General Tab

Stores everyday general reminders. Same card-based layout as the Password tab.

```
┌─────────────────────────────────────┐
│  ┌──────────────┬──────────────────┐ │
│  │   Password   │   ● General      │ │
│  └──────────────┴──────────────────┘ │
│                                       │
│  ┌───────────────────────────────┐   │
│  │  🗑️  BIN DAY                  │   │
│  │      Friday                   │   │
│  └───────────────────────────────┘   │
│                                       │
│  ┌───────────────────────────────┐   │
│  │  🛡️  NATIONAL INSURANCE NUMBER│   │
│  │      AA BB CC DD 77           │   │
│  └───────────────────────────────┘   │
│                                       │
│  ┌───────────────────────────────┐   │
│  │  🎂  WEDDING ANNIVERSARY      │   │
│  │      29th August 2023         │   │
│  └───────────────────────────────┘   │
│                                       │
└─────────────────────────────────────┘
```

**Reminder items:**
| Icon | Label | Example Value |
|------|-------|---------------|
| 🗑️ Bin | Bin Day | `Friday` |
| 🛡️ Insurance | National Insurance Number | `AA BB CC DD 77` |
| 🎂 Cake | Wedding Anniversary | `29th August 2023` |

---

### 4. Edit Dialog

Tapping any card opens a **Material Alert Dialog** with an outlined text field pre-filled with the current value. You can update or clear the value and save.

```
┌─────────────────────────────────────┐
│                                       │
│   Update Value                        │
│                                       │
│   ┌───────────────────────────────┐   │
│   │ Value                         │   │
│   │  MyWifiPassword123            │   │
│   └───────────────────────────────┘   │
│                                       │
│              [ CANCEL ]  [ SAVE ]     │
│                                       │
└─────────────────────────────────────┘
```

---

## ✨ Features

- **📑 Two-tab layout** — Passwords and General reminders, accessible by swipe or tap
- **🃏 Material card UI** — Clean card-based design for each reminder item
- **✏️ Inline editing** — Tap any card to open a dialog and update the value instantly
- **💾 Persistent storage** — All values are saved to `SharedPreferences` and survive app restarts
- **🌙 Dark mode support** — Follows system theme (Material 3 DayNight)
- **📱 Scrollable** — Both tabs scroll vertically if content overflows the screen

---

## 🏗️ Architecture

```
MainActivity
├── TabLayout  (Password | General)
└── ViewPager2
    ├── PasswordFragment          → SharedPreferences("password")
    │   ├── Wifi Password card
    │   ├── Tablet Pin card
    │   └── Bike Lock card
    └── GeneralFragment           → SharedPreferences("general")
        ├── Bin Day card
        ├── National Insurance Number card
        └── Wedding Anniversary card

Each Fragment:
  onCardClick() → showEditDialog() → MaterialAlertDialog
                                         → Save → SharedPreferences.edit { putString() }
                                         → displayValue() refreshes UI
```

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Kotlin |
| UI Framework | Android Views + Material 3 |
| Navigation | ViewPager2 + TabLayout |
| Data Binding | ViewBinding |
| Persistence | SharedPreferences |
| Min SDK | 26 (Android 8.0 Oreo) |
| Target SDK | 37 |
| Build System | Gradle (AGP 9.2.1) |

### Dependencies

```toml
androidx-core-ktx       = "1.19.0"
appcompat               = "1.6.1"
material                = "1.10.0"
activity-ktx            = "1.13.0"
constraintlayout        = "2.2.1"
```

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Ladybug or newer
- JDK 11+
- Android SDK 37

### Build & Run

1. **Clone the repository**
   ```bash
   git clone <repo-url>
   cd Reminders
   ```

2. **Open in Android Studio**
   - File → Open → select the `Reminders` folder

3. **Run on device/emulator**
   - Click ▶ Run or press `Shift+F10`
   - Requires API 26+ device or emulator

4. **Build APK from command line**
   ```bash
   ./gradlew assembleDebug
   # Output: app/build/outputs/apk/debug/app-debug.apk
   ```

---

## 📁 Project Structure

```
app/src/main/
├── java/com/boudour/reminders/
│   ├── MainActivity.kt          # Host activity, sets up TabLayout + ViewPager2
│   ├── PasswordFragment.kt      # Passwords tab logic
│   └── GeneralFragment.kt       # General reminders tab logic
└── res/
    ├── layout/
    │   ├── activity_main.xml        # TabLayout + ViewPager2
    │   ├── password_fragment.xml    # Password cards layout
    │   ├── fragment_general.xml     # General cards layout
    │   └── dialog_edit_reminder.xml # Edit value dialog
    ├── drawable/
    │   ├── icon_wifi.xml
    │   ├── icon_lock.xml
    │   ├── icon_tablet.xml
    │   ├── icon_bin.xml
    │   ├── icon_insurance.xml
    │   └── icon_cake.xml
    └── values/
        ├── strings.xml
        ├── colors.xml
        ├── styles.xml
        └── themes.xml
```

---

## 📝 License

```
Copyright (C) 2026 Boudour
Licensed under the Apache License, Version 2.0
```

