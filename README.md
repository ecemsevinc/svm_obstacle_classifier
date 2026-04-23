# svm-obstacle-classifier

Otonom araç navigasyon sistemleri için geliştirilmiş, Pegasos algoritması
tabanlı Support Vector Machine sınıflandırıcısı. İki boyutlu koordinat
düzlemindeki engel verilerini maksimum marjin prensibiyle iki sınıfa ayırır
ve araç için matematiksel olarak en güvenli geçiş koridorunu belirler.

---

## Problem Tanımı

Bir otonom araç, çevresindeki engelleri iki gruba ayırmak zorundadır.

- Sınıf +1 — Statik engeller: duvarlar, binalar, sabit bariyerler
- Sınıf -1 — Dinamik engeller: yayalar, bisikletliler, diğer araçlar

Bu iki grubu birbirinden ayıran sonsuz sayıda doğru çizilebilir.
Ancak güvenlik açısından yalnızca biri anlamlıdır: her iki sınıfa olan
uzaklığı maksimum tutan doğru. Bu uzaklığa marjin, oluşan bölgeye
güvenlik koridoru denir.

---

## Nasıl Çalışır?

Algoritma, aşağıdaki karar sınırı denkleminin parametrelerini öğrenir.

    w1 * x + w2 * y + b = 0

Marjin genişliği 2 / ||w|| formülüyle hesaplanır. ||w|| küçüldükçe marjin
büyür. Pegasos algoritması her iterasyonda w vektörünü küçülterek marjini
maksimize eder ve aynı anda yanlış sınıflandırmaları düzeltir.

Yeni bir koordinat için tahmin sign fonksiyonuyla yapılır.

    Sonuç >= 0  →  Sınıf +1 (Statik Engel)
    Sonuç <  0  →  Sınıf -1 (Dinamik Engel)

---

## Proje Yapısı

    svm-obstacle-classifier/
    ├── Point.java    Engel koordinatı ve sınıf etiketini tutan veri sınıfı
    ├── SVM.java      Pegasos algoritması, tahmin ve sonuç metodları
    └── Main.java     Veri hazırlama ve uygulama giriş noktası

---

## Teknik Detaylar

| Başlık             | Detay          |
|--------------------|----------------|
| Dil                | Java           |
| Algoritma          | Pegasos SVM    |
| Zaman Karmaşıklığı | O(T x n)       |
| Harici Kütüphane   | Yok            |
| Eğitim Doğruluğu   | %100           |

Zaman karmaşıklığı açısından Dual SVM çözücüleri O(n³), SMO algoritması
O(n²) karmaşıklığında çalışırken Pegasos O(T x n) ile bu ikisinin önüne geçer.

---

## Neden Bu Sınır En Güvenli?

SVM problemi dışbükey (convex) bir optimizasyon problemidir. Dışbükey
problemlerin yerel minimumu ile global minimumu aynıdır. Bu, algoritmanın
bulduğu çözümün mümkün olan en iyi çözüm olduğunu matematiksel olarak
garanti eder. Başka hiçbir doğrusal sınır bu marjinden daha geniş bir
güvenlik koridoru oluşturamaz.