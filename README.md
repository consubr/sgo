# SGO - Sistema de Gestão de Orçamentos
O Sistema de Gestão de Orçamentos foi desenvolvido como iniciativa de um trabalho de conclusão de curso realizado na FAFIMAN. Após a conclusão do trabalho foi disponibilizado para a comunidade para que seja utilizado há quem houver interesse.

## Características
- **Sistema Complexo:** Possui módulo de cliente, produto, ficha técnica, orçamentos e painel administrativo.
- **Responsivo:** Todo o sistema é preparado para vários tamanhos de tela.
- **Painel Moderno:** Desenvolvivo a partir do template Adminfaces com o design da dashboard mais famosa AdminLTE.
- **Leve e Rápido:** Otimizado para rodar com pouco recurso, sem perder desempenho.
- **Praticidade:** Envie um e-mail para o cliente com o orçamento ou faça a impressão em um relatório personalizado e profissional.

## Requisitos
- Banco de Dados MySQL
- Apache Tomcat 7+

## Instruções para uso
Para subir o projeto e para que o mesmo funcione corretamente com todos seus recursos, é necessário alterar algumas configurações como nome do banco de dados, senha do root do MySQL e algumas alterações no código que vai ser falado a seguir.

### MySQL
```bash
1. Criar um banco de dados no MySQL
2. Abrir o arquivo ``persistence.xml`` que se encontra na pasta ``/src/main/resources/META-INF/``
3. Mudar onde esta escrito "NOMEBANCO" para o nome do banco criado e o usuário, senha do MySQL.
4. Salvar o arquivo.
```

### Código
```bash
1. Localizar o arquivo `mail.properties` que se encontra na pasta `/src/main/resources/`
2. Abrir o arquivo e editar as configurações do servidor SMTP e login e senha do e-mail.
3. Salvar o arquivo.
4. Localizar a classe de conexão com o banco chamada `JPAUtil` e mudar para o nome do seu banco onde referencia o valor "NOMEBANCO".
5. Salvar o arquivo.
```

Após essas alterações, a aplicação está pronta para rodar.

## Contribuição
Sinta-se livre para dar um fork e contribuir com o projeto para a comunidade, juntos conseguimos mais, ou abra uma Issue que podemos discutir. :)

## Contato
Quem quiser entrar em contato comigo, ou me encontrar em alguma rede social para bater um papo pode me achar aqui:

- [Twitter](https://twitter.com/danilodecanini)
- [Facebook](https://www.facebook.com/danilo.decanini)
- [Instagram](https://www.instagram.com/danilodecanini/)
- [Github](https://github.com/danilodecanini)

## Licença
[GNU - Generic Public License](LICENSE.md) Copyright (c) - 2017
