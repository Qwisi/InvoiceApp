# InvoiceApp
Udělal jsem nějake componenty pro ui, potom dva screen jako jsou Invoice a Client. Zaprve chci spojit Clinet ui z Room, potom pokračovat s dalšima screen.
Ale mam problem že nejde mí databaze spustit na těchto řadcich
https://github.com/Qwisi/InvoiceApp/blob/3bc44ef3accb166b90ddc5288a9a1a572ff53642/app/src/main/java/com/example/invoiceapp/MainActivity.kt#L18-L22

Ja celí model viewModel a co se týče databazi tak jsem kopiroval z minuleho projektu kde alespon to fungovalo.. ale když to zakomentovat tak alespon možna se podivat jak to funguje bez připojeni zatim. Co mi funguje
Přesměřovani mezi Invoice Screen a Client Screen (nevím jestli takový spusob navigace je skvělý, přes ten mainNavigationController a ten enum class Screen),funguje hledani itemu v seznamu, vytvoreni noveho client jako (jmeno a prijmeni se přidava do seznamu)
