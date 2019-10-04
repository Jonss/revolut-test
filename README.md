# Revolut test

### Purpose:

### How to run?

### Considerations:


POST /accounts HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: a3cc0fdd-e5df-326d-f8c4-5000233bd0d6

{
	"full_name": "João Santana",
	"nick_name": "Jonss",
	"email": "joaosantana@mail.com",
	"phone_number": "+55 11 999999999999"
}


GET /accounts/joaosantana.ti@gmail.com HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 447c15f9-73fc-5c7e-a0d2-9a4f758689e0


POST /deposit HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 94e75247-b16c-596a-6084-1298d87ed65e

{
	"value": {
		"amount": 10000,
		"currency": "BRL"
	},
	"account": {
		"email": "joaosantana.ti@gmail.com"
	}
}

POST /accounts HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 7b6511c0-32cc-669c-664b-959a4657efa6

{
	"full_name": "Júpiter Stein",
	"nick_name": "Júpi",
	"email": "jupiter.stein@gmail.com",
	"phone_number": "+55 11 9999999"
}

POST /transactions/transfer HTTP/1.1
Host: localhost:8080
x-account-id: 8333c892-954a-442a-a320-d6ab963d726f
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 70818b54-fd5a-9278-32c4-deb1334a3af1

{
	"amount": 1299,
	"currency": "BRL",
	"destiny": {
		"email": "jupiter.stein@gmail.com"
	}
}