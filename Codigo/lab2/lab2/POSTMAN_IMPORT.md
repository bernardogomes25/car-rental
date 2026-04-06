# Importação da Collection no Postman

## Opção 1: Download Direto do Arquivo

1. Baixe o arquivo `postman_collection.json` deste repositório
2. Abra o Postman
3. Clique em **File** → **Import**
4. Selecione o arquivo `postman_collection.json`
5. Clique em **Import**

## Opção 2: Via GitHub (Recomendado)

1. Abra o Postman
2. Clique em **File** → **Import**
3. Selecione a aba **URL**
4. Cole: `https://raw.githubusercontent.com/seu-usuario/seu-repositorio/main/postman_collection.json`
5. Clique em **Import**

## Opção 3: Via Folder

1. Abra o Postman
2. Clique em **File** → **Import** → **Folder**
3. Selecione a pasta do projeto
4. Clique em **Import**

## Variáveis da Collection

A collection usa a variável `{{base_url}}` que está configurada como:
- **base_url**: `http://localhost:8080`

Se precisar mudar a porta, edite a variável na collection.

## Testando a API

1. Certifique-se de que a aplicação está rodando: `.\mvnw mn:run`
2. Abra a collection importada
3. Clique em cada request e pressione **Send**
4. Veja a resposta na aba **Response**

## Arquivos da Collection

- `postman_collection.json` - Collection pronta para importar
- Todos os 5 requests (Criar, Listar, Obter, Atualizar, Deletar)
- Testes automáticos para validar status code
