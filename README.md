# Pemrograman Lanjut A
> Fadrian Yhoga Pratama - 2206819395

> Akses aplikasi melalui: [ADVShop](https://tutorial-adpro-yhogaa.koyeb.app/)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=yhogaa_tutorial-1&metric=coverage)](https://sonarcloud.io/summary/new_code?id=yhogaa_tutorial-1)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=yhogaa_tutorial-1&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=yhogaa_tutorial-1)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=yhogaa_tutorial-1&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=yhogaa_tutorial-1)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=yhogaa_tutorial-1&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=yhogaa_tutorial-1)

## Daftar Modul:
- **[Tutorial 1](#module-1---coding-standards)**<br>
- **[Tutorial 2](#module-2---cicd--devops)**<br>

## Module 1 - Coding Standards
### Reflection 1
Setelah mempelajari modul _coding standards_, saya memahami bahwa untuk membuat code saya lebih mudah dimengerti dan dapat dimaintenance (pelihara) ada beberapa _coding standard_ yang bisa saya terapkan pada code saya. Beberapa contohnya adalah:
1. Penggunaan nama fungsi dan variabel yang jelas sehingga code saya dapat mudah dibaca dan dimengerti orang lain
2. Menggunakan fungsi yang efektif dan hanya melakukan satu tugas untuk membuat fungsi lebih mudah dipahami.
3. Memberikan dokumentasi pada beberapa bagian code yang logikanya tidak dideskripsikan langsung

Tetapi saya menemukan beberapa kekurangan yang ada pada code saya yaitu mengenai _secure coding_ dimana saya belum mengantisipasi berbagai serangan yang dapat mengancam program saya. Beberapa solusi yang dapat saya lakukan adalah:
1. Menerapkan validasi input yang lebih spesifik dimana saya sekarang hanya menerapkan validasi untuk _negative numbers quantity_ saja, menurut saya itu tidak cukup. 
2. Menerapkan _Authentication & Authorization_ untunk mengantisipasi hal-hal berbahaya yang dapat membahayakan program saya.


### Reflection 2
1. Jumlah unit test dalam suatu class tidak bisa ditentukan oleh jumlah yang spesifik. Sebaiknya, setiap fungsi atau metode dalam kelas memiliki setidaknya satu unit test. Namun, jumlah unit test bisa lebih banyak tergantung pada kompleksitas fungsi atau metode tersebut. Untuk memastikan bahwa unit test kita cukup untuk memverifikasi program yang ada, kita bisa menggunakan metrik seperti _code coverage_. _Code coverage_ adalah persentase kode program yang telah diuji oleh unit test. Jika code coverage tinggi, berarti sebagian besar kode telah diuji. Namun, memiliki code coverage 100% tidak berarti kode kita bebas dari bug atau kesalahan. Code coverage 100% hanya berarti bahwa setiap baris kode telah dieksekusi setidaknya sekali selama pengujian. Tetapi, ini tidak menjamin bahwa semua kasus dan kondisi telah diuji. Oleh karena itu, selain code coverage, kita juga perlu mempertimbangkan aspek lain seperti kasus uji yang beragam dan pengecekan manual.
2. Membuat class Java baru yang mirip dengan _functional test suite_ sebelumnya dengan _setup procedures_ dan _instance variables_ yang sama mungkin menimbulkan beberapa masalah terkait dengan prinsip _clean code_. Masalah yang bisa saja terjadi adalah duplikasi code yang membuat code susah untuk dimaintenance. Jika ada perubahan pada _setup procedures_ atau _instance variables_, Kita mungkin perlu memperbarui semua class _functional test_ yang menggunakan mereka. Ini dapat membuat maintenance code menjadi lebih sulit. Solusi untuk masalah ini adalah menerapkan prinsip **DRY (Don't Repeat Yourself)**. Kita bisa membuat class **base test** yang berisi _setup procedures_ dan _instance variables_ yang umum digunakan. Kemudian, class _functional test_ lainnya dapat mewarisi class base ini. Dengan cara ini, jika ada perubahan pada _setup procedures_ atau _instance variables_, kita hanya perlu memperbarui di satu tempat, yaitu di class base test. Ini akan membuat code kita lebih mudah untuk dimaintenance dan lebih clean. Selain itu, ini juga akan meningkatkan keterbacaan dan struktur dari code kita.


## Module 2 - CI/CD & DevOps
### Reflection
1. Berikut adalah beberapa _code quality issues_ yang terdapat pada kode saya setelah dilakukan _code scanning_ oleh PMD:
   - **Unused Import**, Ada beberapa import pada kode saya yang sebenarnya itu tidak digunakan. Saya menghapus semua imports yang tidak digunakan tersebut untuk menyelesaikan masalah ini.
   - **Unnecessary Modifiers**, Penggunaan _Modifier_ `public` tidak diperlukan pada _interface_ karena secara default method-method dalam _interface_ bersifat `public`. Saya menghapus modifier public yang tidak diperlukan tersebut untuk mengatasi masalah ini

2. Saya sudah mengimplementasikan **CI/CD** pada kode saya. **_Continuous Integration_ (CI)** diimplementasikan dengan menggunakan Github Actions yang menjalankan workflows seperti `ci.yml`, `pmd.yml`, dan `scorecard.yml` setiap ada push, pull, dan merge pada setiap branch. Untuk **_Continuous Deployment_ (CD)** saya menggunakan Koyeb sebagai platform untuk deployment yang akan secara otomatis melakukan re-deploy setiap ada perubahan pada branch `master`. 