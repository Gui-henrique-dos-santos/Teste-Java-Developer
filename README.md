
## Testando o Projeto

Siga as etapas abaixo para testar o projeto localmente.

### Pré-requisitos

- Certifique-se de ter o Java JDK 8+ instalado em seu sistema.
- Você deve ter o Apache Maven instalado. Você pode baixá-lo em [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).

### Clonando o Repositório

1. Abra o terminal e navegue até o diretório onde você deseja clonar o repositório.
2. Execute o seguinte comando para clonar o repositório:

```bash
git clone https://github.com/Gui-henrique-dos-santos/Teste-Java-Developer.git
```

### Compilando o Projeto

1. Navegue até o diretório raiz do projeto:

```bash
cd Teste-Java-Developer
```

2. Compile o projeto usando o Maven:

```bash
mvn clean install
```

### Executando a Aplicação

1. Após a conclusão da compilação, você pode iniciar a aplicação com o seguinte comando:

```bash
java -jar target/guilherme.jar
```

2. A aplicação será iniciada e estará disponível em `http://localhost:8080`.

### Acessando o Swagger

A documentação da API pode ser acessada através do Swagger, que fornece uma interface interativa para explorar os endpoints da API. Abra o navegador da web e acesse o seguinte URL:

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

Aqui, você pode visualizar informações detalhadas sobre os endpoints da API, fazer chamadas de teste e explorar os recursos disponíveis.

### Testando os Endpoints

Você pode testar os endpoints da API diretamente usando o Swagger ou ferramentas como o [Postman](https://www.postman.com/) ou [curl](https://curl.se/). Certifique-se de que o aplicativo esteja em execução e siga a documentação fornecida pelo Swagger.

Lembre-se de que o ambiente de desenvolvimento pode variar, então ajuste as etapas acima conforme necessário com base nas configurações específicas do seu projeto.

### Estrutura de Testes das Controladoras

Os testes das controladoras estão organizados nas pastas `src/test/java/com/controller`. Nesta estrutura, você encontrará testes unitários para as controladoras do projeto. Cada controladora deve ter sua classe de teste correspondente.

Para executar os testes das controladoras, você pode usar ferramentas como o JUnit ou IDEs de desenvolvimento que oferecem suporte à execução de testes. Certifique-se de que as dependências necessárias estejam configuradas adequadamente no arquivo de configuração do projeto.

Certifique-se de manter os testes das controladoras atualizados à medida que desenvolve ou modifica as funcionalidades da API. Os testes desempenham um papel fundamental na garantia da qualidade do código e na detecção de problemas precocemente no ciclo de desenvolvimento.
