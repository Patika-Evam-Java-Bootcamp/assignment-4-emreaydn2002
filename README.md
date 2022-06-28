# Template4

Bir öğrenci kayıt sistemi yapalım. Öğrenci bilgilerini json uzantılı bir dosyadan okuyalım. Okuduğumuz bilgileri bir listede tutalım, daha sonra liste dönen bir de bilgileri okuma metodu olsun. Dosyadan okuma ve console a okuduğumuz bilgileri basma metodları 2 ayrı thread olarak planlansın. Öğrenci bilgileri json maplenerek Student.java gibi bir class taki attribute lara set edilecek. Projeyi maven projesi olarak oluşturalım.
Ödevi yaparken Single responsibility prensibine uyalım. Ek olarak thread class ı için Runnable interface ini implemente edelim.
Okuma ve console a yazma işlemleri arasında Thread.sleep(3000) kullanalım, data okunmadan console a yazdırma işleminin olmaması gerekiyor. Json örneği şu şekilde olabilir ;
{"students": [{"name":"deneme", "surname":"deneme", "age": 18},
{"name":"deneme", "surname":"deneme", "age": 18},
{"name":"deneme", "surname":"deneme", "age": 18},
{"name":"deneme", "surname":"deneme", "age": 18} ]}
