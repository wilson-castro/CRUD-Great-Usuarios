# PROJETO CRUD-USUÁRIOS-GREAT

## Comando para iniciar os containers 
### docker-compose -f docker-compose-file-example.yaml up

## Caso docker dê problema
### 1) Caso o host do container no banco seja alterado, substituir pelo IPAddress o comando:
#### docker inspect crud_db_1 | grep 'IPAddress'
### 2) Visualizar banco:
#### http://localhost:8888/?pgsql=db&username=docker&db=db_great&ns=public