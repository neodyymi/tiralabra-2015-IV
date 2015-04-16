# Viikko5
###Mitä opin tällä viikolla?
Tällä viikolla jostain syystä hyvin selkeä asia tuntui epäselvältä. Käytin yllättävän paljon aikaa tutkiessani minkä kokoinen syöte RSA algoritmilla voidaan salata. Helposti ymmärrettävä looginen lopputuloshan oli, että syötteen tulee olla avainta pienempi biteissä. Tämä siis siksi, koska RSA-salaus perustuu modulukseen.

Selvitin itselleni myös Base64 koodauksen, joka näyttäisi olevan yleinen tapa säilöä salausavaimet. Loin oman luokan joka toteuttaa Base64 toiminnot BigInteger luvuilla.

###Mikä jäi epäselväksi?


###Miten ohjelma on edistynyt?
Sovellus on kehittynyt ihan mukavasti. Base64 enkoodaus on uusin tapa jolla ajattelin helpommin käsitellä avaimia ja salattua dataa.

Lisäsin myös toiminnallisuudet tiedostoon kirjoittamiseen ja tiedostosta lukemiseen. En ole vielä lisännyt toiminnallisuutta käyttöliittymään kokonaisuudessaan, mutta se on pian edessä.

###Mitä teen seuraavaksi?
Seuraavaksi tavoitteenani on saada testikattavuus ajantasalle. Tähän asti testaaminen on ollut vain sitä, että testaan itse käsin sovellusta, mutta se ei voi jatkua loputtomiin.

Tarkoituksena olisi käydä myös pajassa selvittelemässä mitä olennaista olen jättänyt huomioimatta sovelluksessani.
