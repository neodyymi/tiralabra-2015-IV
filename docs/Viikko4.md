# Viikko4
###Mitä opin tällä viikolla?
Löysin muutamia valmiita toteutuksia RSA-algoritmista Javalla. Olen selaillut niitä ymmärtääkseni käytettyjä toteutuksia. Hahmotan nyt paremmin miten RSA-salaus oikeasti toimii ja mihin se perustuu. Erittäin avulias oli Leonid Grinbergin Introduction to Cryptography and RSA (http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-045j-automata-computability-and-complexity-spring-2011/lecture-notes/MIT6_045JS11_rsa.pdf).

###Mikä jäi epäselväksi?
Toteutukseni nojaa vahvasti tällä hetkellä BigInteger tyyppiin. Täytyy selvitellä miten siirtyisin pois siitä. Lisäksi hieman epäselvää on, kuinka absoluuttinen on avaimen pituus biteissä, koska toteutukseni ottaa vain kaksi alkulukua, jotka ovat puolet sen pituudesta ja kertoo ne keskenään. Tämä ei aina osu oikeaan pituuteen käsittääkseni.

###Miten ohjelma on edistynyt?
Sovellus on kehittynyt vaiheeseen, jossa sitä pystyy käyttämään avainten luontiin, ja näillä avaimilla salaamiseen ja purkamiseen. Tämä on erittäin epäkäytännöllistä vielä. Silti ainakin perustavanlaatuinen toiminnallisuus on olemassa ja vaikka testaukseni on vain manuaalista toistaiseksi, kaikki näyttää toimivan oikein.

###Mitä teen seuraavaksi?
Seuraavaksi luon toiminnallisuudet, että sovellus on käyttökelpoisempi. Eli lisään mahdollisuuden käyttää omia avaimia syötteenä tai tiedostosta ladattuna.

Testaus on asia joka on jäänyt puuttumaan pahasti toistaiseksi. Se on osa-alue jonka aion laittaa kuntoon mahdollisimman pian. Sitten ainakin näen, jos rikon jotain toteuttaessani uudistuksia.

Minun täytyy myös siirtyä pois tällä hetkellä käyttämistäni valmiista funktioista joita Javasta löytyy ja luoda nämä toiminnallisuudet itse.

Suunnitelmissani on myös ottaa käyttöön Maven.
