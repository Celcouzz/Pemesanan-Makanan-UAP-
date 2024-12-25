# README - Sistem Pemesanan Makanan dengan Java Swing

Deskripsi Proyek
Proyek ini adalah aplikasi Sistem Pemesanan Makanan berbasis GUI yang dibuat menggunakan Java Swing. Aplikasi ini memungkinkan pengguna untuk memilih menu makanan, memasukkan informasi pemesan, menentukan jenis pemesanan (makan di tempat atau dibawa pulang), dan menampilkan tabel rincian pesanan. Aplikasi ini cocok untuk simulasi restoran atau sebagai proyek pembelajaran GUI.

Fitur Utama
1.	Halaman Restoran
o	Menampilkan gambar restoran.
o	Tombol untuk melanjutkan ke halaman identitas pemesan.
2.	Halaman Identitas Pemesan
o	Input untuk nama pelanggan.
o	Input untuk nomor pesanan.
o	Pilihan jenis pemesanan (Makan di tempat atau Bawa pulang).
3.	Halaman Pemilihan Menu
o	Pilihan kategori makanan: Appetizers, Main Courses, Desserts.
o	Tampilan gambar makanan sesuai menu yang dipilih.
o	Input jumlah pesanan.
o	Tombol untuk menambahkan pesanan.
o	Tampilan daftar pesanan yang telah dibuat.
o	Tombol untuk melihat tabel pesanan.
4.	Tabel Pesanan
o	Menampilkan daftar pesanan dalam bentuk tabel dengan informasi berikut: 
	Nama pelanggan.
	Jenis pesanan.
	Nomor pesanan.
	Nama menu.
	Jumlah.
	Total harga.




Struktur Kode
1.	Model:
o	Menu: Representasi item menu makanan dengan nama, harga, deskripsi, dan path gambar.
o	Order: Representasi pesanan pelanggan dengan rincian seperti nama pelanggan, jenis pesanan, nomor pesanan, menu yang dipesan, dan jumlah.
2.	GUI Pages:
o	RestaurantPage: Halaman utama restoran.
o	CustomerInfoPage: Halaman untuk input identitas pemesan.
o	MenuSelectionPage: Halaman untuk memilih menu dan melihat daftar pesanan.
o	OrderTablePage: Halaman untuk menampilkan tabel daftar pesanan.
3.	Main Class:
o	FoodOrderingSystem: Kelas utama yang menjalankan aplikasi.

Alur Aplikasi
1.	Restoran:
o	Pengguna memulai aplikasi dari halaman restoran dengan menekan tombol "Pesan" untuk melanjutkan ke tahap berikutnya.
2.	Identitas Pemesan:
o	Pengguna memasukkan nama, nomor pesanan, dan memilih jenis pesanan.
o	Setelah mengisi data, pengguna menekan tombol "Next" untuk masuk ke halaman menu.
3.	Pemilihan Menu:
o	Pengguna memilih kategori menu dan makanan yang diinginkan.
o	Gambar menu yang dipilih ditampilkan.
o	Pengguna memasukkan jumlah makanan yang ingin dipesan.
o	Daftar pesanan akan ditampilkan di area teks.
o	Tombol "View Table" digunakan untuk melihat semua pesanan dalam bentuk tabel.
4.	Tabel Pesanan:
o	Semua rincian pesanan ditampilkan dalam tabel untuk mempermudah peninjauan.

Cara Menjalankan Proyek
1.	Pastikan Java Development Kit (JDK) sudah terinstal di sistem Anda.
2.	Unduh atau salin kode sumber ke proyek Java Anda.
3.	Pastikan folder gambar (Gambar/) berisi gambar-gambar yang disebutkan di kode.
4.	Jalankan aplikasi dengan menjalankan metode main di kelas FoodOrderingSystem.

File dan Folder
•	FoodOrderingSystem.java: File utama yang berisi semua logika aplikasi.
•	Gambar/: Folder untuk menyimpan gambar yang digunakan pada aplikasi. 

o	Contoh gambar yang dibutuhkan: 
	Restoran.jpeg
	Lumpia.jpeg
	Kentang_Goreng.jpeg
	Burger.jpeg
	Pizza.jpeg
	Pasta.jpeg
	Es_krim.jpeg
	Kue.jpeg

Teknologi yang Digunakan
•	Bahasa Pemrograman: Java
•	Framework GUI: Java Swing

Peningkatan yang Mungkin
1.	Penyimpanan Data:
o	Menyimpan data pesanan ke database atau file untuk akses di masa depan.
2.	Validasi Input:
o	Menambahkan validasi lebih ketat pada input nama pelanggan dan nomor pesanan.
3.	Antarmuka Lebih Menarik:
o	Meningkatkan desain GUI dengan library seperti JavaFX atau Swing LookAndFeel.
4.	Pencetakan Invoice:
o	Menambahkan fitur untuk mencetak faktur pesanan pelanggan.
