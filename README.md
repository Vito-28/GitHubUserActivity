# GitHub User Activity

Questo è un semplice progetto CLI per recuperare le attività recenti di un utente GitHub e visualizzarle nel terminale. 

## Risorse usate
Articolo che descrive le funzionalità, obiettivi del progetto:

https://roadmap.sh/projects/github-user-activity

## Requisiti

- Eclipse
- Java 11 o superiore
- Maven

## Installazione

1. Clona il repository:

   git clone https://github.com/Vito-28/GitHubUserActivity.git
   
   cd GitHubUserActivity

2. Compila il progetto:

   mvn clean install

3. Esegui l'applicazione:

   mvn exec:java

## Comandi

- **Inserisci in ingresso il nome utente di GitHub**:

  github-activity <username>

## Come funziona

Ogni event nel file JSON ha un parametro payload che a sua volta ha un parametro commits che rappresenta una lista di commit che hanno un parametro message che vengono poi stampati su schermo.

## NOTA: 

Il codice è ancora in fase di miglioramento.


## Licenza

Questo progetto è distribuito sotto la Licenza MIT.
