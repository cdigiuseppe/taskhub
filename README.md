# TaskHub - Sistema di gestione di attività

## Descrizione
TaskHub è un'applicazione backend da scrivere in Java utilizzando Spring Boot (versione 3). 
Devi esporre delle API ReST che consentano di gestire Progetti, Utenti e Task

Condizioni fondamentali:
Gli utenti possono essere assegnati a più progetti.
Ogni progetto contiene più task
I task possono essere assegnati a uno o più utenti.

---

## Requisiti
- Java 17+
- Maven
- PostgreSQL o H2 (default)
- IDE consigliato: IntelliJ IDEA

---

## Setup progetto
1. ti consiglio di usare : https://start.spring.io/
  

2. Configurare `application.properties` o `application.yml`:
   ```properties
   spring.datasource.url=jdbc:h2:mem:taskhub
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   spring.jpa.hibernate.ddl-auto=update
   spring.h2.console.enabled=true
   ```
3. Usa l'IDE per avviare l'app in questa prima fase
   
4. Accedere alla console H2 se vuoi vedere i dati (considera che essendo MEM ad ogni riavvio il database di cancella, trova tu il modo per dirgli di mantenerli):
   - URL: http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:taskhub`

---

## Servizi ReST (da implementare)

Oltre al nome della risorsa, cerca di implementare anche un buon sistema di validazione dell'input (cerca java validator) e scegli il verbo http corretto

### Utenti
- Creazione utente
- Lista utenti

### Progetti
- Crea progetto
- Lista progetti
- Assegna utente a progetto (indizio: `POST /projects/{id}/users/{userId}`, queste sono PathVariable)

### Task
- Crea task
- Lista task per progetto
- Cambia stato task
- Assegna task a utente

---

## To-do
- Progettare le entità e relazioni (User, Project, Task) usando Spring-JPA
- Utilizza la metodologia classica Controller - Service - Repository (usa i Repository di Spring per le query)
- Creare controller REST e mappare le risposte con DTO
---

## Extra challenge
- Endpoint per task in scadenza entro 3 giorni
- Esportazione dei task in CSV
- Reportistica (numero task completati per progetto)

## A Seguire
- Gestione degli errori centralizzata con @ControllerAdvice
- Aggiungere test
- Spring Security per autenticazione e autorizzazione
- (Extra) Aggiungere autenticazione JWT


