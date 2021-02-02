Aplikacja serwera aplikacyjnego przygotowana przez Jakuba Fornalskiego w ramach pracy inżynierskiej:

"Serwis internetowy pozwalający na hierarchiczne wyszukiwanie i zakup elementów zastępczych do gier planszowych"

Projekt oparty jest o Maven oraz środowisko Docker. 

Do działania aplikacja wymaga zastąpienia pliku keystore.jks magazynem kluczy dla ważnego certyfikatu i 
podanie hasła do niego poprzez zmienną środowiskową BGES_KEYSTORE_PASSWORD. 

Dla celów demonstracyjnych, w projekcie znajduje sie samo-podpisany certyfikat demonstracyjny z hasłem "demo123". 
Dla poprawnego działa aplikacji klienta, certyfikat musi zostać dodany do wyjątków w przeglądarce poprzez np. otworzenie strony https://localhost:8443/. 

Generowanie plików wykonalnych:
```
mvn clean install
```
uruchomienie:
```
docker-compose up
```

W aplikacji domyślnie utworzone są dwa konta:
```
username:user
password:user
```
```
username:employee
password:employee
```

