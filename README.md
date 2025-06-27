# Parking System

Sistem manajemen parkir untuk gedung 3 lantai, dilengkapi login pengguna (gate entry/exit), alokasi slot otomatis, dan perhitungan biaya berbasis durasi. Backend menggunakan Spring Boot, dan frontend berupa aplikasi Android.

> 3 lantai parkir:
    - Lantai 1: motor;
    - Lantai 2–3: mobil & bus;
> Otomatisasi alokasi slot parkir
> Perhitungan biaya parkir per jam:
    - Motor: Rp 2.000/jam;
    - Mobil: Rp 4.000/jam;
    - Bus: Rp 6.000/jam;
>  Role-based login:
    - ENTRY_GATE;
    - EXIT_GATE;
>  Cek status slot parkir real-time
>  Backend: Spring Boot + H2 DB (in-memory)
>  Frontend: Android App (Retrofit + Fragment)

**BACKEND**:

  A.) Arsitektur Proyek

parking-system/
├── backend-springboot/
│ ├── src/main/java/com/example/parkingsystem/
│ │ ├── controller/
│ │ ├── service/
│ │ ├── model/
│ │ ├── repository/
│ │ └── security/
│ ├── application.properties
│ └── pom.xml
└── frontend-android/
├── app/src/main/java/com/example/parkingapp/
│ ├── activity/
│ ├── fragment/
│ ├── api/
│ └── model/
└── AndroidManifest.xml


 B.) Cara Menjalankan Proyek
     cd backend;
     mvn spring-boot:run;


 C.) ENDPOINT
  
| Endpoint        | Method | Keterangan               |
| --------------- | ------ | ------------------------ |
| /entry/park     | POST   | Input kendaraan masuk    |
| /exit/leave     | POST   | Kendaraan keluar & bayar |
| /slots/status   | GET    | Lihat status slot        |



  D.) LOGIN ROLE
   
| Username | Password | Role        |
| -------- | -------- | ----------- |
| entry    | 123      | ENTRY\_GATE |
| exit1    | 123      | EXIT\_GATE  |
| exit2    | 123      | EXIT\_GATE  |




**FRONTEND:**

A.) Cara menjalankan:
> Ubah di RetrofitClient.java:
  private static final String BASE_URL = "http://10.0.2.2:8080/";

> Build APK:
  Build > Build APK(s);

B.) FITUR:
> Login sesuai role
> EntryFragment → input kendaraan & kirim ke backend
> ExitFragment → input plat & hitung biaya keluar
> SlotListFragment → tampilkan status seluruh slot




**UNIT TESTING:**
   Menggunakan Spring Boot Test (@SpringBootTest)
   File contoh: ParkingServiceTest.java



**TOOLS:**
| Layer    | Teknologi                     |
| -------- | ----------------------------- |
| Backend  | Java 17, Spring Boot, H2, JPA |
| Frontend | Android (Java), Retrofit, XML |
| Tools    | Maven, Android Studio         |




Kontributor

    Nama: Varuna Dewi
    Role: Full-Stack Developer
    Email: varunadewi@gmail.com
    LinkedIn: https://linkedin.com/in/varunadewi
    GitHub: https://github.com/varunanaidu
    Github Parking-System Repository: https://github.com/varunanaidu/parking-system

    Jakarta-2025






