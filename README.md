1.Descrição da Aplicação

A aplicação DEISIMDB é um sistema desktop desenvolvido em JavaFX para consulta e interação com os dados do SGV (Sistema de Gestão de Vídeos).
Toda a informação apresentada pela aplicação é carregada diretamente da Base de Dados SQL Server através de JDBC.

A aplicação permite visualizar filmes, elenco, diretores, classificações etárias, géneros, comentários, sentimentos e ratings associados a cada vídeo.

Esta aplicação acompanha o projeto de Base de Dados, mas o seu funcionamento é independente a nível de código.

2. Funcionalidades Principais

Consultar lista de filmes.

Ver detalhes completos de cada filme: título, duração, budget, país, continente, classificação etária e géneros.

Consultar elenco (atores) e diretores de cada filme.

Consultar ratings provenientes da tabela Movie_votes.

Consultar comentários associados ao filme, incluindo utilizador, rede social, sentimento e rating atribuído.

Carregamento automático da interface gráfica através de FXML.

Ligação e encerramento seguro da ligação à Base de Dados no arranque e no fecho da aplicação.

3,Descrição dos componentes:

MainApp.java
Classe responsável pela inicialização da aplicação, carregamento do FXML e ligação à base de dados.

DbConnection.java
Implementa um Singleton responsável pela ligação JDBC ao SQL Server.

controllers/
Contêm os controladores do JavaFX responsáveis pela gestão de eventos e queries.

models/
Contém os modelos de objetos utilizados na aplicação.

fxml/
Interfaces gráficas estruturadas em FXML.

5. Configuração da Ligação à Base de Dados

No ficheiro DbConnection.java, configurar:

String url = "jdbc:sqlserver://localhost:1433;databaseName=ProjetoFinalBD;encrypt=false";
String user = "sa";
String pass = "password";

6. Execução da Aplicação

Clonar o repositório.

Abrir a pasta app/ no IntelliJ IDEA.
Garantir que a Base de Dados está ativa.

Executar a classe: MainApp.java

A aplicação carregará automaticamente o FXML principal e estabelecerá ligação ao SQL Server.
8. Objetivo da Aplicação

A aplicação foi criada para fornecer ao utilizador uma interface simples e funcional para:

Consultar dados do SGV

Explorar conteúdos audiovisuais

Ver comentários, sentimentos e ratings

Navegar pelas relações entre filmes, atores, diretores e plataformas

O objetivo principal é complementar o projeto de Base de Dados e demonstrar a integração entre um sistema SQL Server e uma aplicação desktop JavaFX.
