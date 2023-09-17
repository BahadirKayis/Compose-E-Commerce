# FiriyaE-commerce


https://github.com/BahadirKayis/FiriyaE-commerce/assets/66027016/33baf60b-6447-44d3-ba3a-eaf984952374


Bağlantı olmadığında veya uygulamadayken bağlantı kesildiğinde sağ üstten network hatası çıkıyor
 # Login&Register
- tek sayfa şekilde yapıldı 
- email türü girilmek zorunlu
- password en az 4 haneli olmalı
- hata durumularında hata bulunan inputlar renk değiştirip açıklama çıkıyor
- continue buttonuna basılırsa giriş yapmadan devam ediliyor
- kayıtlı kullanıcı olmadığı bilgiler bulunamıyacak  için giriş yapılamayacak
- giriş yapılmışsa uygulamaya tekrar girdiği taktirde ana ekrana yönlendirilir

# Home
- itemlara tıklanıp detay sayfasına gidiliyor



# Detail
- Add to cart buttonuna basıldığında sepete ekler ve eklediğne dair snackbar çıkarır
- tekrar eklediğinde ürünün sayısı artar yeni ürün eklemez

# Basket
- ürünlerin bulunduğu card küçük tutuldu card'a tıklandığı zaman genişliyor
- ürünlerin arttırılma veya azaltılma durumuna göre sayısı animasyonlu şekilde değişiyor
- ürün fiyatı arttırğında ve total price arttığı zaman animasyonlu bir şekilde değişiyor
- şimdi öde dedğin zaman bottomSheet açılıp ödenecek kartı seçiyorsun seçtiğin kart ve ödediğine dair mesaj çıkartıyor ve sepeti silip seni ana ekran yönlendiriyor
- ürün adeti 1se ve - ye basılırsa ürün silinir
- ürün olmadığında boş olduğunu belirten icon çıkar

# Satın alma geçmişi
- her sepet tarihiyle beraber ayrı gösterilir
- giriş yapmamışsa bir bilgilendirme çıkarr ve butona basıt kayıt olma fragmentına gider
- ürün olmadığında boş olduğunu belirten icon çıkar
