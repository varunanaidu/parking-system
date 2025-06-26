# Parking System Android App

## Fitur
- Login sesuai role
- Parkir masuk (input plat + jenis kendaraan)
- Parkir keluar (input plat → lihat biaya)
- Daftar slot real-time

## Build
- BASE_URL Retrofit: `http://10.0.2.2:8080/`

## Struktur
- LoginActivity: otentikasi Basic Auth
- EntryFragment: untuk ROLE_ENTRY_GATE
- ExitFragment: untuk ROLE_EXIT_GATE
- SlotListFragment: tampilkan status slot

## Build APK
Klik `Build > Build Bundle(s)/APK(s) > Build APK(s)` lalu install di emulator
