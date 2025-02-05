# Sistema para gestão de eventos.

#Tecnologias utilizadas:
- Java 17
- banco de dados postgresql
- docker-compose
- Swagger

#Passos para utilizar o sistema:
1- Gere um pacote .jar
2- Caso não exista o banco de dados postgresql instalado, basta clonar o repositório > acessar a pasta raiz pelo terminal e executar o seguinte comando: docke-compose up -d. 
Caso exista o banco postgresql instalado em sua maquina, será necessario criar um banco com o seguinte nome: gestao_eventos
3- executar o comando: java -jar gestao_vagas.jar

#Para acessar a documentação acessar o o link: http://localhost:8080/swagger-ui/index.html

