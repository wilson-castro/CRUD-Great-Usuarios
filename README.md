# PROJETO CRUD-USUÁRIOS-GREAT

## Comando para iniciar os containers 
### docker-compose -f docker-compose-file.yaml up

## Caso docker dê problema, seguir os passos
### 1) Comentar as partes referentes ao frontend e backend no docker-compose-file e tentar rodar o comando para iniciar os containers para iniciar apenas o referente ao postgres
### 2) Caso o host no aplication do container no banco seja alterado ou não conecte, substitui-lo no application.properties pelo IPAddress o comando:
#### docker inspect crud_db_1 | grep 'IPAddress'
### 3) Visualizar se o banco está carregado no link:
#### http://localhost:8888/?pgsql=db&username=docker&db=db_great&ns=public
### 4) Iniciar o fron end com comando:
#### yarn dev
### 5) Já existe um jar do backend no entando aconselhavel usar inteliji e startar por ele