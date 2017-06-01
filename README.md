Terinspirasi dari Aplikasi Supermarket Online asal Singapura,  [RedMart](http://redmart.com). Grocery Item View milik redmart ini sangat menarik, berupa tiles kecil yang terdapat tombol "ADD TO CART" dibagian bawah dimana saat ditekan akan berubah menjadi tombol plus dan minus yaitu untuk mengatur berapa jumlah yang akan dibeli.

![image](/storage/emulated/0/Pictures/Screenshots/IMG_20170601_124428.jpg)
> RedMart product tiles

Dengan demikian pengguna tidak perlu masuk ke item detail atau ke cart list hanya untuk mengatur jumlah yang akan dibeli, dimana cara ini banyak dilakukan aplikasi serupa yang lain.

Mari membuat tiles sendiri mirip seperti diatas, tanpa mengoyak jeroan Aplikasi redmart.

![image](/storage/emulated/0/Pictures/Screenshots/IMG_20170601_114738.jpg)
> My Own Tiles

Terlihat tiles berupa rounded corner, kita bisa menggunakan [CardView](https://developer.android.com/reference/android/support/v7/widget/CardView.html) untuk membuatnya. Gambar diatas memakai 3dp untuk corner radiusnya.

## Koreografi
Pada bagian bawah terdapat 2 elemen yang berdiri diatas LinearLayout, pertama quantity view yang didalamnya termasuk nilai quantity dan tombol (+) dan (-), kedua adalah tombol "BELI".
Quantity view di set GONE terlebih dahulu karena yang akan tampil di awal adalah tombol "BELI". Setelah tombol "BELI" di klik maka dia akan di set GONE dan quantity view di set VISIBLE, serta background di set merah. Tambahkan juga Action supaya nilai quantity bertambah saat ditekan tombol (+) dan berkurang saat ditekan tombol (-).
Jika nilai quantity lebih kecil dari 1 maka di set seperti awal, tombol "BELI" set VISIBLE dan quantity view set GONE serta background kembali ke putih.

## Animasi
![image](/storage/emulated/0/recordmaster/VideoEdit/tile.gif)
> RedMart product tiles animation

Pemberian animasi perlu dilakukan agar aplikasi tidak terasa kaku, juga sebagai touch feedback yaitu umpan balik setelah pengguna mengklik.
Tidak berlu berlebihan, cukup efek fade seperti diatas saja membuatnya terasa profesional.

Membuat animasi fade transisi seperti diatas cukup mudah. Berikut langkahnya:

Buat drawable transisi di folder  **drawable** 
```xml
<transition xmlns:android="http://schemas.android.com/apk/res/android">
	<item  android:drawable="@android:color/white" />
    <item android:drawable="@color/colorPrimary" />
</transition>
```
Diatas ada 2 layer transisi, yaitu yang pertama berupa warna putih, kedua warna primary dimana saya memakai warna merah untuk primarynya.

Panggil  `startTransition(diration)` untuk memulai transisi dan  `reverseTransition(duration)` untuk membalik transisi.

![image](/storage/emulated/0/recordmaster/VideoEdit/tile1.gif)
> My Own tiles animation

Berikut animasi tambahan yang saya terapkan pada nilai quantity saat tombol (+) dan (-) diklik.

![image](/storage/emulated/0/recordmaster/VideoEdit/tile2.gif)
> My Own tiles with extra animation

## Efek Ripple
Ripple Effect memberi feedback sentuhan yang keren, namun Ripple hanya berlaku di API 21 (lolipop) keatas. 
Berikut adalah perbedaan jika kita menerapkan Ripple Effect dan jika tidak menggunakannya.

![image](/storage/emulated/0/recordmaster/VideoEdit/ripple1.gif)
> Tanpa Ripple

![image](/storage/emulated/0/recordmaster/VideoEdit/ripple2.gif)
> Dengan Ripple

Buat drawable ripple di folder  **drawable-21** 
```xml
<ripple xmlns:android="http://schemas.android.com/apk/res/android" 
	android:color="#ff0000">
	<item android:id="@android:id/mask">
		<shape android:shape="rectangle">
			<solid android:color="?android:colorAccent" />
		</shape>
	</item>
</ripple>
```

Terapkan drawable diatas ke Button atau Touchable view yang lain, maka akan ada efek ripple merah saat dan setelah disentuh.

## Cart Item View

Disini saya sertakan juga item untuk di cart-nya yang menampilkan nama produk, berat dan harga serta total harga yang dihitung dengan cara mengkalikan harga produk dan jumlah yang dibeli.

![image](/storage/emulated/0/Pictures/Screenshots/IMG_20170601_151307.jpg)

## Masukan ke RecyclerView
Buat RecyclerView dengan GridLayoutManagar sehingga akan terlihat seperti ini

![image](/storage/emulated/0/Pictures/Screenshots/Screenshot_20170601-144103.png)

Dan untuk Cart List saya menggunakan NavigationDrawer disebelah kanan

![image](/storage/emulated/0/Pictures/Screenshots/Screenshot_20170601-144340.png)

Mau mencoba? [klik disini](https://appetize.io/app/0xvqk7f4bfx5c8tpyyjcfgy2um?device=nexus5&scale=75&orientation=portrait&osVersion=7.0)
 untuk mencoba via Appetize

### Keren kan?
Butuh kerja sama dalam membangun Aplikasi Android yang keren?
[Contact Me](http://telegram.me/agusibrahim)
* [Agus Ibrahim](http://fb.me/mynameisagoes)


## Reference
* http://redmart.com/
* https://guides.codepath.com/android/Android-Design-Guidelines
* http://petrnohejl.github.io/Android-Cheatsheet-For-Graphic-Designers/