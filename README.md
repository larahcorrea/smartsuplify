# SmartSuplify

### [Vídeo pitch do projeto](https://www.youtube.com/watch?v=yv77UEPtWuE)
### Protótipo das telas mobile no [figma](https://www.figma.com/file/NzQZk5mPjwQDZ7tL2Ng0yI/Prot%C3%B3tipo-Mobile?type=design&node-id=0-1&mode=design&t=HajvnkP5kFbgQxSe-0)

---

### Documentação dos primeiros endpoints pensados no desenvolvimento inicial da API

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

### Códigos de retorno
|codigo| significado
|-|-
|200 | dados retornados com sucesso
|201 | criado com sucesso
|204 | apagado com sucesso
|400 | dados inválidos
|404 | não encontrado

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

### Códigos de retorno
|codigo| significado
|-|-
|200 | dados retornados com sucesso
|201 | criado com sucesso
|204 | apagado com sucesso
|400 | dados inválidos
|404 | não encontrado


## Requisição de cotação

Descrição dos endpoints de requisições

- `GET` /requisicoes: lista todas as requisições realizadas pelos funcionarios da empresa
- `POST` /requisicoes: cadastra uma requisição
- `GET` /requisicoes/{id}: retorna uma requisição específica
- `DELETE` /requisicoes/{id}: apaga uma requisição especificada
- `PUT` /requisicoes/{id}: atualizar os dados de uma requisição

### Exemplo de JSON
```js
{
    "id": long,
    "dataAbertura": "localdate",
    "dataEncerramento": "localdate",
    "status": "string",
    "itensRequisicao": [
        "itemPedido": {
            "id": long,
            "quantidade": Double,
            "estoque": Estoque
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

