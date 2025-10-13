# Sustav za upravljanje prijevozom
Aplikacija omogućuje upravljanje entitetima i operacijama specifičnim za područje "Sustav za upravljanje prijevozom".
Entiteti (npr. User, Booking, Record, Item) moraju biti u paketu `entity`, dok se glavna klasa nalazi u paketu `app`.
Treba definirati apstraktnu klasu (npr. Person) i sučelja (npr. Reservable, Schedulable) prema potrebi domene.
Jedan od entiteta mora koristiti builder pattern za konstrukciju objekata.

Potrebno je implementirati označene i neoznačene iznimke koje se logiraju korištenjem Logback biblioteke.
Kolekcije List, Set i Map koriste se za spremanje podataka, a lambda izrazi za filtriranje i sortiranje entiteta.

Generičke klase, npr. EntityCollection<T> i Relation<A,B>, moraju biti implementirane.
Podaci za prijavu (korisničko ime i hashirana lozinka) čitaju se iz tekstualne datoteke, dok se promjene entiteta serijaliziraju u binarne datoteke.

JavaFX ekrani moraju omogućiti prijavu korisnika (barem dvije uloge), upravljanje entitetima kroz TableView (CRUD operacije uz potvrdu izmjena i brisanja)
te prikaz povijesti promjena učitanih iz binarne datoteke.

Baza podataka mora sadržavati tablice povezane s entitetima aplikacije te klasu za konekciju, izvršavanje upita i zatvaranje konekcije.
Niti se koriste za paralelne procese (npr. istovremeno osvježavanje podataka i spremanje promjena), uz sinkronizaciju dijeljenih resursa.

### <u><b>Sve klase moraju biti kraće od 200 linija koda, bez SonarQube problema i s Javadoc dokumentacijom.</b></u>