Refleksi Modul 3
1. Implementasi SOLID pada aplikasi saya:  
   1. SRP (Single Responsibility Principle)
      Saya telah memastikan bahwa setiap class memiliki tanggung jawab yang jelas dengan memisahkannya berdasarkan fungsinya. Sebagai contoh, class yang menangani homepage, produk, dan mobil telah dikelompokkan dalam file terpisah. Pemisahan ini diterapkan baik dalam repository (CarRepository & ProductRepository), service (CarServiceImpl & ProductServiceImpl), model (Car & Product), maupun controller (CarController, HomePageController, & ProductController). Pendekatan yang sama juga diterapkan dalam kode pengujian agar lebih terorganisir.
   2. LSP (Liskov Substitution Principle)
      Prinsip ini diterapkan dalam perubahan struktur kode antara branch before-solid dan after-solid, di mana sebelumnya, controller untuk produk dan mobil berada dalam satu file, tetapi kemudian dipisahkan menjadi CarController.java dan ProductController.java. Pemisahan ini dilakukan karena kedua class tersebut tidak memiliki hubungan hierarkis yang mengharuskan pewarisan (inheritance).
   3. OCP (Open/Closed Principle)
      Saya menerapkan prinsip ini dengan menambahkan metode update() pada model Car, sehingga subclass cukup melakukan overriding metode tersebut tanpa harus mengubah perilaku superclass.
   4. ISP (Interface Segregation Principle)
      Prinsip ini diterapkan dengan memisahkan interface untuk model Car dan Product, sehingga setiap service hanya mengimplementasikan metode yang benar-benar dibutuhkan tanpa harus bergantung pada metode yang tidak relevan.
   5. DI (Dependency Inversion Principle)
      Penerapan DI terlihat pada CarController.java, di mana sebelumnya, class ini secara langsung menggunakan implementasi CarServiceImpl.java. Namun, untuk mengikuti prinsip DI, ketergantungan tersebut diubah menjadi CarService, yaitu interface yang lebih abstrak. Dengan cara ini, controller tidak bergantung langsung pada implementasi tertentu, sehingga memudahkan pengujian dan pemeliharaan kode.
2. Keuntungan mengimplementasikan SOLID
   - SRP: Dengan membagi tanggung jawab setiap class secara spesifik, kompleksitas kode berkurang sehingga proses pengembangan dan pemeliharaan (termasuk debugging) menjadi lebih mudah.

   - LSP: Memastikan bahwa setiap subclass tetap dapat menggantikan superclass tanpa mengubah perilaku sistem. Meskipun saat ini belum ada subclass dalam program saya, prinsip ini akan sangat membantu jika nanti diperlukan ekspansi sistem.

   - OCP: Membantu dalam pengembangan subclass tanpa harus mengubah superclass yang sudah ada. Jika di masa depan saya ingin menambahkan model baru yang merupakan turunan dari model yang ada, prinsip ini akan sangat berguna.

   - ISP: Memastikan bahwa setiap interface hanya memiliki metode yang relevan, sehingga implementasi lebih efisien dan tidak terbebani dengan metode yang tidak diperlukan.

   - DI: Mempermudah pemeliharaan kode, terutama jika ingin mengganti implementasi service. Misalnya, jika CarController.java awalnya menggunakan CarServiceImpl, lalu saya ingin menggantinya dengan CarServiceMock untuk pengujian, perubahan dapat dilakukan tanpa perlu mengedit kode di CarController.java karena sudah bergantung pada interface CarService.java.
3. Kekurangan tidak mengimplementasikan SOLID
   - Kesulitan dalam pemeliharaan kode, karena tidak ada pemisahan tanggung jawab yang jelas, sehingga sulit bagi developer untuk menemukan dan memperbaiki fitur tertentu.
   - Uji coba kode menjadi sulit, karena fungsionalitas yang bercampur dalam satu class menyulitkan proses pengujian.
   - Beban implementasi yang tidak perlu, terutama jika ada interface besar yang mencakup banyak fungsi yang tidak selalu digunakan oleh class yang mengimplementasikannya.
   - Ketergantungan tinggi antar komponen, sehingga sulit mengganti atau memodifikasi kode tanpa memengaruhi banyak bagian lain dalam sistem.
   - Perubahan kecil dapat berdampak besar, karena integrasi kode yang tidak baik dan kurangnya struktur yang jelas.
Refleksi Modul 2.1
Pada module kali ini saya menyelesaikan beberapa masalah terkait yang disampaikan saat code scanning:
1. Mengganti public modifier dengan default modifier di ProductService.java
2. Menerapkan hash remediation pada workflow scorecard.yml menggunakan https://app.stepsecurity.io
3. Menambahkan LICENSE sebagai tanda orisinalitas product ini

Refleksi Modul 2.2
Menurut saya, saya telah menerapkan CI/CD pipelines. Dimana proses integrasi update dan perubahan, dilakukan secara otomatis
ketika ada event push atau pull request. Dan juga, deployment secara otomatis di Koyeb. Proses CI yang saya
implementasikan juga include testing dan code scanning. Sehingga, saya bisa meningkatkan code quality. 
Pada proses CD, saya menggunakan koyeb untuk hosting aplikasi saya. Selain itu, saya menggunakan docker untuk memudahkan 
deploy aplikasi dengan mengirim image.

Refleksi Modul 1.1

Pada exercise modul ini, kita diminta untuk membuat fitur mengedit dan menghapus product yang sudah kita tambahkan. 
Untuk melihat aspek clean code yang telah saya terapkan, kita perlu tahu terlebih dahulu apa saya aspek yang menjadi
tolak ukur clean itu sendiri:
1. Meaningful name
    Saya telah memberikan nama - nama yang cukup representative pada setiap variable, method, maupun class. Penggunaan
nama yang saya berikan juga tidak memberikan ambiguitas pada pembaca. Pembaca dapat dengan mudah memahami source code
tanpa perlu bertanya - tanya maksud kode yang diberikan. 
2. Function
   Saya telah membuat function yang melakukan 1 hal dan cukup ringkas. Function yang terdapat di source code tidak
memiliki terlalu banyak line. Sehingga, unittesting mudah untuk dilakukan
3. Comment
    Saya menggunakan sedikit comment pada implementasi kali ini. Hal tersebut disebabkan oleh pemberian nama yang cukup
representative dengan fungsi sebenarnya.
4. Formatting and White space
    setiap kali melakukan deklarasi method, if/else, class, for/while. Isi kode tersedut diindentasi sehingga meningkatkan
readibility. 
5. Error Handling
   Saya selalu memastikan bahwa operasi yang berpotensi error, misalnya mengambil data dari struktur data, itu selalu 
divalidasi terlebih dahulu. Namun, saya melihat kurangnya implementasi try catch. Padahal, menurut saya akan lebih baik
untuk menggunakan try catch agar kode yang dihasilkan lebih baik. Hal ini akan menjadi catatan kedepan.

Refleksi modul 1.2
1. Writing unit test brings calm and secure mind. even though my code past all the test given, but error is inevitable. 
Therefore, I need to always think about every scenario as much as possible where a bug might appear. To ensure the quality
our code, code coverage is a really good metric. But, code coverage alone can not guarantee bugs or errors free.
2. The resulting test code will be bad (quality wise) due to code redundancy. Maintainability of code is also affected,
where if there is changes in one set up, then we need to change the other one. While clearly, we can write better code 
and avoid redundancy.