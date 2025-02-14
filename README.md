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