# REST API

Online store REST API described below.

## Create a new user

### Request

`POST /api/user`

#### Headers

	Content-Type: application/json
	Content-Length: <calculated when request is sent>
	Host: <calculated when request is sent>
	User-Agent: PostmanRuntime/7.32.3
	Accept: */*
	Accept-Encoding: gzip, deflate, br
	Connection: keep-alive

#### Request body

    {
	  "username": "user1",
	  "password": "password",
	  "repeatPassword": "password",
	  "phoneNumber": "+380000000000",
	  "name": "John",
	  "email": "john@gmail.com"
	}

### Response

    HTTP/1.1 201 Created
	X-Content-Type-Options: nosniff
	X-XSS-Protection: 0
	Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	Pragma: no-cache
	Expires: 0
	Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	X-Frame-Options: SAMEORIGIN
	Content-Length: 0
	Date: Tue, 25 Jul 2023 07:23:25 GMT
	Keep-Alive: timeout=60
	Connection: keep-alive

    []

## Get orders for a specific user

### Request

`GET /api/order`

#### Headers

	Authorization: Basic dXNlcjpwYXNzd29yZC11c2Vy
	Host: <calculated when request is sent>
	User-Agent: PostmanRuntime/7.32.3
	Accept: */*
	Accept-Encoding: gzip, deflate, br
	Connection: keep-alive

### Response

    HTTP/1.1 200 OK
	X-Content-Type-Options: nosniff
	X-XSS-Protection: 0
	Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	Pragma: no-cache
	Expires: 0
	Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	X-Frame-Options: SAMEORIGIN
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Tue, 25 Jul 2023 08:03:12 GMT
	Keep-Alive: timeout=60
	Connection: keep-alive

    [
		{
		    "id": 1,
		    "items": [
		        {
		            "number": 1,
		            "product": {
		                "id": 1,
		                "name": "monitor",
		                "countryName": "Australia"
		            }
		        },
		        {
		            "number": 2,
		            "product": {
		                "id": 1,
		                "name": "monitor",
		                "countryName": "Australia"
		            }
		        }
		    ]
		},
		{
		    "id": 2,
		    "items": [
		        {
		            "number": 5,
		            "product": {
		                "id": 2,
		                "name": "mouse",
		                "countryName": "Ukraine"
		            }
		        }
		    ]
		}
	]

## Create a new order for a specific user

### Request

`POST /api/order`

#### Headers

	Authorization: Basic dXNlcjpwYXNzd29yZC11c2Vy
	Content-Type: application/json
	Content-Length: <calculated when request is sent>
	Host: <calculated when request is sent>
	User-Agent: PostmanRuntime/7.32.3
	Accept: */*
	Accept-Encoding: gzip, deflate, br
	Connection: keep-alive

#### Request body

    {
		"items": [
		    {
		        "number": 8,
		        "product": {
		            "id": 2
		        }
		    },
		    {
		        "number": 1,
		        "product": {
		            "id": 3
		        }
		    }
		]
	}

### Response

    HTTP/1.1 201 Created
	X-Content-Type-Options: nosniff
	X-XSS-Protection: 0
	Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	Pragma: no-cache
	Expires: 0
	Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	X-Frame-Options: SAMEORIGIN
	Content-Length: 0
	Date: Tue, 25 Jul 2023 08:23:54 GMT
	Keep-Alive: timeout=60
	Connection: keep-alive

    []

## Get all product

### Request

`GET /api/product`

#### Headers

	Host: <calculated when request is sent>
	User-Agent: PostmanRuntime/7.32.3
	Accept: */*
	Accept-Encoding: gzip, deflate, br
	Connection: keep-alive

### Response

    HTTP/1.1 200 OK
	X-Content-Type-Options: nosniff
	X-XSS-Protection: 0
	Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	Pragma: no-cache
	Expires: 0
	Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	X-Frame-Options: SAMEORIGIN
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Tue, 25 Jul 2023 08:36:12 GMT
	Keep-Alive: timeout=60
	Connection: keep-alive

    [
		{
		    "id": 1,
		    "name": "monitor",
		    "countryName": "Australia"
		},
		{
		    "id": 2,
		    "name": "mouse",
		    "countryName": "Ukraine"
		},
		{
		    "id": 3,
		    "name": "microphone",
		    "countryName": "Spain"
		}
	]

## Get specific product by id

### Request

`GET /api/product/1`

#### Headers

	Host: <calculated when request is sent>
	User-Agent: PostmanRuntime/7.32.3
	Accept: */*
	Accept-Encoding: gzip, deflate, br
	Connection: keep-alive

### Response

    HTTP/1.1 200 OK
	X-Content-Type-Options: nosniff
	X-XSS-Protection: 0
	Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	Pragma: no-cache
	Expires: 0
	Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	X-Frame-Options: SAMEORIGIN
	Content-Type: application/json
	Transfer-Encoding: chunked
	Date: Tue, 25 Jul 2023 08:43:54 GMT
	Keep-Alive: timeout=60
	Connection: keep-alive

    {
		"id": 1,
		"name": "monitor",
		"countryName": "Australia"
	}

## Update specific product by id

### Request

`PUT /api/product/1`

#### Headers

	Authorization: Basic dXNlcjpwYXNzd29yZC11c2Vy
	Content-Type: application/json
	Content-Length: <calculated when request is sent>
	Host: <calculated when request is sent>
	User-Agent: PostmanRuntime/7.32.3
	Accept: */*
	Accept-Encoding: gzip, deflate, br
	Connection: keep-alive

#### Request body

    {
	  "name": "computer screen",
	  "countryName": "Australia"
	}

### Response

    HTTP/1.1 204 No Content
	X-Content-Type-Options: nosniff
	X-XSS-Protection: 0
	Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	Pragma: no-cache
	Expires: 0
	Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	X-Frame-Options: SAMEORIGIN
	Date: Tue, 25 Jul 2023 08:56:19 GMT
	Keep-Alive: timeout=60
	Connection: keep-alive

## Create new product

### Request

`POST /api/product`

#### Headers

	Content-Type: application/json
	Content-Length: <calculated when request is sent>
	Host: <calculated when request is sent>
	User-Agent: PostmanRuntime/7.32.3
	Accept: */*
	Accept-Encoding: gzip, deflate, br
	Connection: keep-alive

#### Request body

    {
	  "name": "lamp",
	  "countryName": "Brazil"
	}

### Response

    HTTP/1.1 201 Created
	X-Content-Type-Options: nosniff
	X-XSS-Protection: 0
	Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	Pragma: no-cache
	Expires: 0
	Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	X-Frame-Options: SAMEORIGIN
	Content-Length: 0
	Date: Tue, 25 Jul 2023 07:23:25 GMT
	Keep-Alive: timeout=60
	Connection: keep-alive

    []

## Delete specific product by id

### Request

`DELETE /api/product/3`

#### Headers

	Authorization: Basic YWRtaW46cGFzc3dvcmQtYWRtaW4=
	Host: <calculated when request is sent>
	User-Agent: PostmanRuntime/7.32.3
	Accept: */*
	Accept-Encoding: gzip, deflate, br
	Connection: keep-alive

#### Request body
	[]

### Response

    HTTP/1.1 204 No Content
	X-Content-Type-Options: nosniff
	X-XSS-Protection: 0
	Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	Pragma: no-cache
	Expires: 0
	Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	X-Frame-Options: SAMEORIGIN
	Date: Thu, 27 Jul 2023 12:12:34 GMT
	Keep-Alive: timeout=60
	Connection: keep-alive


## Delete all product

### Request

`DELETE /api/product`

#### Headers

	Authorization: Basic YWRtaW46cGFzc3dvcmQtYWRtaW4=
	Host: <calculated when request is sent>
	User-Agent: PostmanRuntime/7.32.3
	Accept: */*
	Accept-Encoding: gzip, deflate, br
	Connection: keep-alive

#### Request body
	[]

### Response

    HTTP/1.1 204 No Content
	X-Content-Type-Options: nosniff
	X-XSS-Protection: 0
	Cache-Control: no-cache, no-store, max-age=0, must-revalidate
	Pragma: no-cache
	Expires: 0
	Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	X-Frame-Options: SAMEORIGIN
	Date: Thu, 27 Jul 2023 12:29:32 GMT
	Keep-Alive: timeout=60
	Connection: keep-alive