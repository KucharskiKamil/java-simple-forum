# Forum Java z Thymeleaf i Spring Security

## Opis

To Forum to aplikacja internetowa, która pozwala użytkownikom na rejestrację, logowanie oraz tworzenie postów z własnymi tagami. Aplikacja została zbudowana z wykorzystaniem Java oraz Thymeleaf dla interfejsu użytkownika i Spring Security dla autentykacji i autoryzacji. Obsługuje treści w wielu językach, zapewniając wsparcie dla co najmniej dwóch języków. W tyle aplikacji wykorzystywane są relacje OneToMany i ManyToOne między encjami oraz Bean Validation do walidacji danych. Dodatkowo, aplikacja oferuje funkcje sortowania i stronicowania wyświetlanych danych po stronie backendu.

## Funkcje

- Rejestracja użytkowników i logowanie przy użyciu Spring Security.
- Możliwość tworzenia i przeglądania postów z własnymi tagami.
- Wsparcie dla wielu języków.
- Relacje OneToMany i ManyToOne między encjami bazy danych.
- Walidacja danych przy użyciu Bean Validation.
- Sortowanie i stronicowanie danych.

## Technologie

- Java
- Spring Boot
- Thymeleaf
- Spring Security
- JPA
- H2Database
- Bean Validation

## Wymagania

- JDK 1.8 lub nowszy
- Maven 3.2+
- MySQL Server 5.6 lub nowszy (lub inna relacyjna baza danych)

## Konfiguracja i Instalacja

1. Sklonuj repozytorium na swoją maszynę lokalną.
2. git clone [url do repozytorium]
3. Utwórz bazę danych i tabele zgodnie z dołączonymi skryptami SQL.
4. Zaktualizuj konfigurację bazy danych w pliku właściwości aplikacji (`application.properties` lub `application.yml`) swoimi szczegółami bazy danych.

## Uruchomienie Aplikacji

1. Przejdź do katalogu głównego projektu przez wiersz poleceń.
2. Uruchom aplikację
3. Dostęp do aplikacji internetowej pod adresem `http://localhost:8080`.

## Użytkowanie

- Zarejestruj się jako nowy użytkownik lub zaloguj przy użyciu istniejących danych.
- Nawiguj po forum, czytaj posty lub twórz własne.
- Wykorzystaj system tagowania do kategoryzowania i wyszukiwania postów.
- Zmień preferencje językowe w razie potrzeby.

   

