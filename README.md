# E-Commerce Backend Application with Microservices

## Product Catalog Service
- Running GRPC on PORT 9001
- Using Postgresql as DB
- Only GRPC Server

## Order Service
- Running GRPC on PORT 9002
- Using Postgresql as DB
- GRPC Server and GRPC Client
- GRPC Client of Product Catalog Service & Inventory Service
  
## Inventory Service
- Running GRPC on PORT 9003
- Using Postgresql as DB
- Only GRPC Server

## Notification Service
- Using Mongo as DB
- Recieving notifications used to be send to user through email, sms and whatsapp using Kafka.
