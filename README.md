Aplikacja serwera aplikacyjnego przygotowana w ramach pracy inżynierskiej "Serwis internetowy pozwalający na hierarchiczne wyszukiwanie i zakup elementów zastępczych do gier planszowych".

Projekt oparty jest o Maven oraz środowisko Docker. 

Do działania aplikacja wymaga zastąpienia pliku keystore.jks  magazynem kluczy dla ważnego certyfikatu i 
podanie hasła do niego poprzez zmienną środowiskową BGES_KEYSTORE_PASSWORD.

Generowanie plików wykonalnych:

mvn clean install

uruchomienie:

docker-compose up

