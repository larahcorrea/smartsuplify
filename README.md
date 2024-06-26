# SmartSuplify

### [Vídeo pitch do projeto](https://www.youtube.com/watch?v=KTPVRScuVI4)
### Protótipo das telas mobile no [figma](https://www.figma.com/file/NzQZk5mPjwQDZ7tL2Ng0yI/Prot%C3%B3tipo-Mobile?type=design&node-id=0-1&mode=design&t=HajvnkP5kFbgQxSe-0)

---

## Integrantes:
* Alicia Guiradelo
* Ana Prado
* Arthur Foschiani
* Bruna Menegatti
* Larah Correa

## Documentação
Acesse[aqui](https://github.com/anadantasp/smartsuplify/tree/main/documentacao)

### Documentação dos endpoints pensados no desenvolvimento inicial da API

## Estoques

Descrição dos endpoints de estoque

- `GET` /estoques: lista todos os estoques dos produtos do sistema
- `POST` /estoques: cadastra estoque relacionado à um produto 
- `GET` /estoques/{id}: retorna um estoque relacionado à um produto específico
- `DELETE` /estoques/{id}: apaga um estoque relacionado à um produto especificado
- `PUT` /estoques/{id}: atualizar os dados do estoque 

### Exemplo de JSON
```js
{
    "id": long,
    "produto": {
        "id": long,
        "nome": "string",
        "tamanho": "string"
    }
    "quantidadeMin": Double,
    "quantidadeMax": Double,
    "quantidadeAtual": Double
}
```


## Produtos

Descrição dos endpoints de produtos

- `GET` /produtos: lista todas os produtos para cadastro de um estoque
- `POST` /produtos: cadastra produto
- `GET` /produtos/{id}: retorna um produto específico
- `DELETE` /produtos/{id}: apaga um produto especificado
- `PUT` /produtos/{id}: atualizar os dados do produto

### Exemplo de JSON
```js
{
    "id": long,
    "nome": "string",
    "tamanho": "string"  
}
```

## Serviços

Descrição dos endpoints de produtos

- `GET` /servicos: lista todas os produtos para cadastro de um estoque
- `POST` /servicos: cadastra produto
- `GET` /servicos/{id}: retorna um produto específico
- `DELETE` /servicos/{id}: apaga um produto especificado
- `PUT` /servicos/{id}: atualizar os dados do produto

### Exemplo de JSON
```js
{
    "id": long,
    "nome": "string",
    "cargaHoraria": "string"  
}
```
## Requisição de Solicitações

Descrição dos endpoints de requisições

- `GET` /solicitacoes: lista todas as requisições realizadas pelos funcionarios da empresa
- `POST` /solicitacoes: cadastra uma requisição
- `GET` /solicitacoes/{id}: retorna uma requisição específica
- `DELETE` /solicitacoes/{id}: apaga uma requisição especificada
- `PUT` /solicitacoes/{id}: atualizar os dados de uma requisição

### Exemplo de JSON
```js
{
    "id": long,
    "quantidade": "Double",
    "adquirivel" : "Adquirivel"
}
```

## Requisição de cotação

Descrição dos endpoints de requisições

- `GET` /cotacoes: lista todas as requisições realizadas pelos funcionarios da empresa
- `POST` /cotacoes: cadastra uma requisição
- `GET` /cotacoes/{id}: retorna uma requisição específica
- `DELETE` /cotacoes/{id}: apaga uma requisição especificada
- `PUT` /cotacoes/{id}: atualizar os dados de uma requisição

### Exemplo de JSON
```js
{
    "id": long,
    "data": "localdate",
    "status": "string",
    "solicitacoes": [
        "solicitacao": {
            "id": long,
            "quantidade": Double,
            "Adquirivel": Adquirivel
        }
    ]
}
```

### Códigos de retorno
|codigo| significado
|-|-
|200 | dados retornados com sucesso
|201 | criado com sucesso
|204 | apagado com sucesso
|400 | dados inválidos
|404 | não encontrado

