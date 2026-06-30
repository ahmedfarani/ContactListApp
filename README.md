<div align="center">

# 📱 ContactListApp

**A native Android contact management app demonstrating core Android concepts - RecyclerView, View Binding, intents, and Activity result handling.**

![Java](https://img.shields.io/badge/Java-007396?logo=java&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white)
![minSdk](https://img.shields.io/badge/minSdk-24-blue)
![targetSdk](https://img.shields.io/badge/targetSdk-36-blue)

</div>

---

## 📖 Overview

**ContactListApp** is a simple Contact Management application built with Java and standard Android XML UI. It demonstrates full CRUD-style interaction (Create, Read, Update, Delete) on a contact list, along with real device integrations - placing calls, sending SMS, sending email, and sharing contact info - built entirely with the Android SDK's native APIs.

## ✨ Features

- **Switchable list layouts** | toggle between a `LinearLayoutManager` (list) and `GridLayoutManager` (grid, 2 columns) via a spinner, with the chosen layout persisted across sessions using `SharedPreferences`
- **Add / Edit / Delete contacts** | dedicated activities for adding a new contact and editing an existing one, both returning results via `startActivityForResult` / `onActivityResult`
- **Delete confirmation dialog** | an `AlertDialog` confirms deletion before removing a contact from the list
- **Contact detail actions** | from the details screen, a contact can be:
  - Called directly (`Intent.ACTION_DIAL`)
  - Texted (`Intent.ACTION_VIEW` with an `sms:` URI)
  - Emailed (`Intent.ACTION_SEND`)
  - Shared as plain text via the system share sheet (`ShareCompat.IntentBuilder`)
- **Edge-to-edge UI** | every activity enables `EdgeToEdge` display and applies system bar insets as padding for a modern, immersive layout
- **Input validation** | the add-contact form rejects submission if any field (name, phone, email) is left empty

## 🛠️ Tech Stack

- **Java** | application logic across 5 activities
- **Android SDK** | `minSdk 24`, `targetSdk 36`, Java 11 compatibility
- **View Binding** | type-safe view access (no `findViewById`)
- **RecyclerView** | contact list rendering with a custom `ContactAdapter`
- **AndroidX** | `AppCompat`, `Material`, `ConstraintLayout`, `Activity`

> **Note on storage:** Contact data lives in an in-memory `ArrayList` for the current app session - it is not persisted to disk and resets on app restart. `SharedPreferences` is used only to remember the user's preferred list layout (Linear vs. Grid) between launches.

## 🚀 Getting Started

### Prerequisites

- Android Studio (latest stable recommended)
- JDK 11+

### Run the project

```bash
git clone https://github.com/ahmedfarani/ContactListApp.git
cd ContactListApp
```

Open the project in **Android Studio**, let Gradle sync, then run on an emulator or physical device (API 24+):

```bash
./gradlew installDebug
```

## 📁 Project Structure

```
ContactListApp/
└── app/src/main/java/com/example/contactslistapp/
    ├── MainActivity.java          # Contact list, layout toggle, add-result handling
    ├── AddContactActivity.java     # New contact form
    ├── EditContactActivity.java    # Edit existing contact form
    ├── DetailsActivity.java         # Contact detail view + call/SMS/email/share/delete
    ├── Contact.java                  # Contact data model
    └── ContactAdapter.java            # RecyclerView adapter
```

## 📜 License

This project is open source and available for personal or educational use.

---

<div align="center">

Built by **Ahmed Farani**

</div>
