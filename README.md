
<h1>Projeto CRUD de Usuários - Teste do GREAT</h1>

<p><strong>Descrição:</strong> Este projeto é um sistema CRUD (Create, Read, Update, Delete) de usuários desenvolvido utilizando Spring Boot, Spring JPA, PostgreSQL, NextJS, Typescript, PrimeReact, Bulma e Docker. Ele permite a gestão de informações de usuários, incluindo operações básicas como adição, leitura, atualização e exclusão.</p>

<div align="center">
  <h2>Tecnologias</h2>
  <p>
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" title="Spring Boot" height="60" width="60" />
        &nbsp;&nbsp;
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" title="Postgresql" height="50" width="50" />
        &nbsp;&nbsp;
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/nextjs/nextjs-original.svg" title="NextJS" height="50" width="50" />
        &nbsp;&nbsp;
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/typescript/typescript-original.svg" style="background-color:#2e3136" title="Typescript" height="50" width="50" />
      &nbsp;&nbsp;
    <img src="https://avatars.githubusercontent.com/u/3494069?s=48&v=4" title="PrimeReact" height="50" width="50" />
        &nbsp;&nbsp;
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/bulma/bulma-plain.svg" title="Bulma" height="50" width="50" />
      &nbsp;&nbsp;
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original-wordmark.svg" alt="docker" title="Docker" height="50" width="50" />
  </p>
</div>

<h2>Instruções de Inicialização</h2>

<h3>Iniciando os Containers</h3>
<p>Para iniciar os containers, utilize o seguinte comando:</p>

<code>docker-compose -f docker-compose-file.yaml up</code>

<p>Se ocorrerem problemas com o Docker, siga as seguintes etapas:</p>
<ol>
    <li>Comente as partes referentes ao frontend e backend no arquivo <code>docker-compose-file.yaml</code> e tente rodar o comando novamente para iniciar apenas o PostgreSQL.</li>
    <li>Se houver problemas de conexão, substitua o host no arquivo <code>application.properties</code> pelo endereço IP usando o comando:</li>
    <code>docker inspect crud_db_1 | grep 'IPAddress'</code>
    <li>Verifique se o banco de dados foi carregado acessando o link: <a href="http://localhost:8888/?pgsql=db&username=docker&db=db_great&ns=public">http://localhost:8888/?pgsql=db&username=docker&db=db_great&ns=public</a></li>
</ol>

<h3>Iniciando o Frontend</h3>
<p>Para iniciar o frontend, utilize o seguinte comando:</p>

<code>yarn dev</code>

<h3>Iniciando o Backend (Alternativa)</h3>
<p>Já existe um arquivo JAR do backend. Recomenda-se usar o IntelliJ para iniciar o backend a partir dele.</p>

<p>Este projeto é uma aplicação web que integra diversas tecnologias modernas para oferecer um ambiente de desenvolvimento eficiente e uma interface de usuário amigável para a gestão de usuários.</p>
