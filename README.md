# acmebank-account-manager
Just another coding exercise.  

## How do I run it?

```
mvn spring-boot:start
```

## How do I check my balance?

```
http://localhost:8080/accounts/12345678
```

## How do I make a transfer?

```
curl --location --request POST 'http://localhost:8080/transfers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "from": "12345678",
    "to": "88888888",
    "currency": "HKD",
    "amount": 500
}'
```
