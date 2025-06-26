# Parking System – Spring Boot

## Fitur
- 3 lantai parkir: motor, mobil, bus
- Biaya parkir sesuai durasi dan tipe kendaraan
- Entry/Exit role dengan login
- Slot terisi otomatis dan dikosongkan saat keluar

## Endpoint
- POST `/entry/park` → Parkir masuk
- POST `/exit/leave` → Hitung biaya dan keluar
- GET `/slots/status` → Lihat status slot

## User Login
| Username | Password | Role |
|----------|----------|------|
| entry    | 123      | ENTRY_GATE |
| exit1    | 123      | EXIT_GATE |
| exit2    | 123      | EXIT_GATE |

## Jalankan
```bash
mvn spring-boot:run


TESTING BACKEND

1. Jalankan mvn spring-boot:run

2. Buka http://localhost:8080/h2-console

    JDBC URL: jdbc:h2:mem:parkingdb

    Login: sa, kosongkan password

3. Cek tabel slot, user



Testing via Postman

1. POST /entry/park
    {
    "plate": "B1234ABC",
    "type": "CAR"
    }

2. POST /exit/leave?plate=B1234ABC

3. GET /slots/status