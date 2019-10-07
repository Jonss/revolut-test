### Purpose:

This is a home task for Revolut job application. 


### How to run?

You can start the main method in Application.kt, or generate a jar with `./gradlew build` the run `java -jar build/libs/revolut-test-1.0-SNAPSHOT-all.jar`.

### Considerations:

- I used Ktor[https://ktor.io/] in webservice layer, using Koin[https://insert-koin.io/] to handle DI. The other parts of application where written mostly in Java.
- For persistence, I used jdbc, using a lib called Jdbi.

- The idea is, when a transaction is created, a new row in balance table is created. It will add or subtract from latest total before operation, then on next operation this new balance will be the source of truth.
- The amount is in cents. Even cryptocurrency would work, handling satoshis in BTC for instance.

*** 
- Yet about transaction, my idea was handle a transaction as an event. Receive a transaction, add a new row and subtract. To handle concurrency I intended to verify asynchronously if the balance is positive or zero. 

## Requests:

#### Create account
```
curl -X POST
    http://localhost:8080/accounts \
    -H 'Content-Type: application/json' \
-d '{
	    "full_name": "João Santana",
	    "nick_name": "Jonss",
	    "email": "joao.santana@mail.com",
	    "phone_number": "+55 11 999999999"
    }'

curl -X POST
    http://localhost:8080/accounts \
    -H 'Content-Type: application/json' \
-d '{
	    "full_name": "Jupiter Stein",
	    "nick_name": "jupineo",
	    "email": "jupiter.stein@mail.com",
	    "phone_number": "+55 11 888888888"
    }'
```

#### Get account by email
```
curl -X GET http://localhost:8080/accounts/{email-registered} \
    -H 'Content-Type: application/json'
```

#### Deposit value for account 

```
```

#### Transfer values for other users

```
curl -X POST \
  http://localhost:8080/transactions/transfer \
  -H 'Content-Type: application/json' \
  -d '{
        "amount": 1299,
        "currency": "BRL",
        "destiny": {
            "email": "jupiter.stein@mail.com"
        }
    }'
```
