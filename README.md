# Loja de Eletrônicos

# Sobre o projeto


Sistemas de Cadastro de Produtos

É um sistema simples, onde foi desenvolvido com intuito de aprender sobre a teamplate Engine Thymeleaf que facilita a criação de páginas HTML. Assim criando teamplates de forma mais fácil (Facilitanto a vida do Back-End).

O Sistema é um CRUD onde podemos 
   - Listar todos os Produtos;
   - Cadastrar um Novo Produto;
   - Editar Dados de um Produto já Cadastrado;
   - Excluir um Produto.

## Layout web

Na página principal são listados os Produtos cadastrados que o sistema possui e suas informações.


![Web 1](https://github.com/erolkss/beststore/blob/main/src/main/resources/assets/img/1_HomePage.png?raw=true)

Para Cadastrar um Novo Produto deve ser informando, **Nome do Produto**, **Marca**, **Categoria**, **Preço**, **Descrição do Produto** e uma **Imagem do Produto**.

![Web 2](https://github.com/erolkss/beststore/blob/main/src/main/resources/assets/img/2_Create_Product.png?raw=true)

Para editar dados de um Produto basta clicar no botão: ![Web 3](https://github.com/erolkss/beststore/blob/main/src/main/resources/assets/img/3_Button_Edit.png?raw=true) 
Onde serão carregados os dados do Produto. Sobrescreva algum dado e clicar em **Submit** para que a alteração esteja sendo salva no Sistema.

![Web 4](https://github.com/erolkss/beststore/blob/main/src/main/resources/assets/img/4_Edit_Product.png?raw=true)

Para Excluir um Produto basta na Página Principal clicar no botão: ![Web 6](https://github.com/erolkss/beststore/blob/main/src/main/resources/assets/img/5_Button_Delete.png?raw=true) 

Irá aparecer um Popup de confirmação,basta clicar em **Ok** para que a exclusão seja feita.

![Web 6](https://github.com/erolkss/beststore/blob/main/src/main/resources/assets/img/6_Are_you_sure.png?raw=true) 




# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
## Front end
- HTML
- Thymeleaf
- Bootstrap


# Como executar o projeto

## Back end
Pré-requisitos: Java 17

```bash
# clonar repositório
git clone https://github.com/erolkss/beststore.git

# executar o projeto
./mvnw spring-boot:run
```


# Autor

**Lucas Eduardo Lima**

https://www.linkedin.com/in/lucaserolima/
