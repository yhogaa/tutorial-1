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
- **[Tutorial 3](#module-3---oo-principles--software-maintainability)**<br>

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

## Module 3 - OO Principles & Software Maintainability
### Reflection
1. SOLID Principle
- **Single Responsibility Principle (SRP)** adalah prinsip yang menekankan bahwa class seharusnya hanya memiliki satu tanggung jawab atau fungsionalitas utama. Pada code yang sudah saya buat, saya telah menerapkan SRP ini yaitu memisahkan `ProductController` dan `CarController`.
- **Open-Closed Principle (OCP)**, artinya entitas perangkat lunak (kelas, modul, dll.) seharusnya dapat diperluas tanpa mengubah kode yang sudah ada. Saya menerapkan prinsip ini dengan cara mengubah function edit Car agar menggunakan set sehingga ketika mengedit tidak set masing-masing atributnya. Jadi, ketika ada class mengextend Car dengan atribut berbeda (tidak hanya nama, warna, dan jumlah) akan dapat memakai fungsi edit tersebut.
- **Liskov Substitution Principle (LSP)**, berarti objek dari kelas turunan harus bisa digunakan sebagai pengganti objek kelas induk tanpa mengubah kebenaran program. Saya sudah menerapkan prinsip LSP dengan menghapus `CarController extends ProductController`, agar class CarController bisa memiliki fungsionalitas dan sifat seputar car saja.
- **Interface Segregation Principle (ISP)**, artinya tidak boleh dipaksa untuk mengimplementasikan interface yang tidak relevan. Kode saya telah menerapkan ISP dengan cara `CarServiceImpl` mengimplementasikan `CarService` dan `ProductServiceImpl` mengimplementasikan `ProductService`. Masing-masing saling mengimplementasikan interface yang relevan.
- **Dependency Inversions Principle (DIP)**, DIP memiliki arti modul level tinggi tidak boleh bergantung pada modul-level rendah. Keduanya harus bergantung pada abstraksi. Pada before-solid, sempat ada bagian kode saya yang tidak menerapkan prinsip ini. Maka saya memperbaikinya dengan cara mengubah import `CarServiceImpl` menjadi import `CarService` pada `CarController`. Dengan begitu `CarController` menjadi bergantung kepada `CarService` yang lebih menggambarkan abstraksi.

2.  Keuntungan menerapkan SOLID Principle adalah:<br>
Dengan menerapkan prinsip-prinsip SOLID, kode akan menjadi lebih teratur, mudah dimodifikasi, dan dapat di-maintain dengan baik. Dengan begitu, developer dapat mengurangi risiko rapuhnya perangkat lunak, mempermudah untuk melakukan perubahan yang dibutuhkan di masa depan, contohnya:
- Dengan menerapkan SRP, contohnya memisahkan Controller Home, Product dan Car membuat kode lebih mudah dimengerti karena ketika dilihat suatu class controller dapat jelas dimengerti controller tersebut untuk apa.
- Dengan menerapkan OCP, contohnya dengan membuat fungsi edit Car tidak diset per atribut, membuat kode saya extensible karena ketika ada class mengextend Car dengan atribut berbeda (tidak hanya nama, warna, dan jumlah) akan dapat memakai fungsi edit tersebut.

3. Kerugian tidak menerapkan SOLID Priinciple adalah:<br>
Apabila kita tidak menggunakan prinsip SOLID pada kode kita, kode akan menjadi lebih sulit untuk di-maintain ke depannya karena rendahnya readability dari kode tersebut. Akibatnya, kode akan menjadi lebih rentan pada perubahan dan akan sulit untuk melakukan testing.
- Apabila tidak menerapkan SRP, misalnya menggabungkan Controller Home, Product dan Car membuat ambigu yang melihat kode karena sulit dimengerti suatu class controller itu maksudnya untuk apa.
- Apabila tidak menerapkan OCP, contohnya dengan membuat fungsi edit Car diset per atribut, membuat kode saya tidak extensible karena ketika ada class mengextend Car dengan atribut berbeda (tidak hanya nama, warna, dan jumlah) akan sulit memakai fungsi edit tersebut.