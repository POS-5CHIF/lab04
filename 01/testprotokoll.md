# Testprotokoll Koenig

## GET /einzahlung/{kn}

Existiert:

```bash
michael@MichaelLaptop:~$ curl -i localhost:8080/einzahlung/1
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 10 Nov 2020 19:14:00 GMT

{"id":1,"betrag":25.00,"date":"2019-09-04","schueler":{"kn":1,"zuname":"Barbara","vorname":"Bauer"}}
```

Existiert nicht:

```bash
michael@MichaelLaptop:~$ curl -i localhost:8080/einzahlung/100
HTTP/1.1 404
Content-Type: text/plain;charset=UTF-8
Content-Length: 25
Date: Tue, 10 Nov 2020 19:12:51 GMT

Can't find einzahlung 100
```

## GET /einzahlungen/{kn}

Existiert:

```bash
michael@MichaelLaptop:~$ curl -i localhost:8080/einzahlungen/1
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 10 Nov 2020 19:14:34 GMT

[{"id":1,"betrag":25.00,"date":"2019-09-04","schueler":{"kn":1,"zuname":"Barbara","vorname":"Bauer"}},{"id":13,"betrag":15.00,"date":"2019-10-06","schueler":{"kn":1,"zuname":"Barbara","vorname":"Bauer"}}]
```

Existiert nicht:

```bash
michael@MichaelLaptop:~$ curl -i localhost:8080/einzahlungen/100
HTTP/1.1 404
Content-Type: text/plain;charset=UTF-8
Content-Length: 23
Date: Tue, 10 Nov 2020 19:15:03 GMT

Can't find schueler 100
```

## GET /schueler/{minBetrag}

Nicht-leere Antwort:

```bash
michael@MichaelLaptop:~$ curl -i localhost:8080/schueler/30
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 10 Nov 2020 19:19:38 GMT

[{"kn":1,"zuname":"Barbara","vorname":"Bauer"},{"kn":5,"zuname":"Barbara","vorname":"Hofer"},{"kn":6,"zuname":"Gustav","vorname":"Imbacher"},{"kn":7,"zuname":"Andreas","vorname":"Hofer"}]m
```

Leere Antwort:

```bash
michael@MichaelLaptop:~$ curl -i localhost:8080/schueler/100
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 10 Nov 2020 19:19:55 GMT

[]
```

## POST /einzahlung/{kn}

Gültige Request:

```bash
michael@MichaelLaptop:~$ curl --location --request POST 'localhost:8080/einzahlung/1' \
    --header 'Content-Type: application/json' \
    --data-raw '{"betrag": 5, "date": "2019-12-10"}' -i

HTTP/1.1 201
Location: http://localhost:8080/einzahlung/22
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 10 Nov 2020 19:33:28 GMT

{"id":22,"betrag":5,"date":"2019-12-10","schueler":{"kn":1,"zuname":"Barbara","vorname":"Bauer"}}
```

Ungültige Request: Betrag zu klein:

```bash
michael@MichaelLaptop:~$ curl --location --request POST 'localhost:8080/einzahlung/1' \
    --header 'Content-Type: application/json' \
    --data-raw '{"betrag": 4, "date": "2019-12-10"}' -i

HTTP/1.1 400
Content-Type: text/plain;charset=UTF-8
Content-Length: 603
Date: Tue, 10 Nov 2020 19:34:26 GMT
Connection: close

Validation failed for argument [0] in public org.springframework.http.ResponseEntity<at.michaelkoenig.lab04_1.model.Einzahlung> at.michaelkoenig.lab04_1.web.AppController.addEinzahlung(at.michaelkoenig.lab04_1.model.Einzahlung,java.lang.Integer): [Field error in object 'einzahlung' on field 'betrag': rejected value [4]; codes [Min.einzahlung.betrag,Min.betrag,Min.java.math.BigDecimal,Min]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [einzahlung.betrag,betrag]; arguments []; default message [betrag],5]; default message [must be greater than or equal to 5]]
```

Ungültige Request: Datum in Zukunft:

```bash
michael@MichaelLaptop:~$ curl --location --request POST 'localhost:8080/einzahlung/1' \
    --header 'Content-Type: application/json' \
    --data-raw '{"betrag": 5, "date": "2030-12-10"}' -i

HTTP/1.1 400
Content-Type: text/plain;charset=UTF-8
Content-Length: 647
Date: Tue, 10 Nov 2020 19:37:37 GMT
Connection: close

Validation failed for argument [0] in public org.springframework.http.ResponseEntity<at.michaelkoenig.lab04_1.model.Einzahlung> at.michaelkoenig.lab04_1.web.AppController.addEinzahlung(at.michaelkoenig.lab04_1.model.Einzahlung,java.lang.Integer): [Field error in object 'einzahlung' on field 'date': rejected value [2030-12-10]; codes [PastOrPresent.einzahlung.date,PastOrPresent.date,PastOrPresent.java.time.LocalDate,PastOrPresent]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [einzahlung.date,date]; arguments []; default message [date]]; default message [must be a date in the past or in the present]]
```

Ungültige Request: Schüler existiert nicht:

```bash
michael@MichaelLaptop:~$ curl --location --request POST 'localhost:8080/einzahlung/100' \
    --header 'Content-Type: application/json' \
    --data-raw '{"betrag": 5, "date": "2019-12-10"}' -i

HTTP/1.1 404
Content-Type: text/plain;charset=UTF-8
Content-Length: 23
Date: Tue, 10 Nov 2020 19:40:02 GMT

Can't find schueler 100
```

Ungültige Request: ID wird übergeben:

```bash
michael@MichaelLaptop:~$ curl --location --request POST 'localhost:8080/einzahlung/1' \
    --header 'Content-Type: application/json' \
    --data-raw '{"id": 1, "betrag": 5, "date": "2019-12-10"}' -i

HTTP/1.1 400
Content-Type: text/plain;charset=UTF-8
Content-Length: 28
Date: Tue, 10 Nov 2020 19:39:04 GMT
Connection: close

Einzahlung ID should be null
```
