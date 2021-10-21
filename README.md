# Catálogo de produtos
Catálogo de produtos com Java e Spring Boot.

## product-ms
CRUD e busca de produtos filtrando por *name, description e price*.

### Formato
O formato do produto:
  {
    "id": "string",
    "name": "string",
    "description": "string",
    "price": 59.99
  }
Os campos *name, description e price* são obrigatórios e o *price* é positivo.

### Endpoints
| Verbo HTTP  |  Resource path    |           Descrição           |
|-------------|:-----------------:|------------------------------:|
| POST        |  /products        |   Criação de um produto       |
| PUT         |  /products/{id}   |   Atualização de um produto   |
| GET         |  /products/{id}   |   Busca de um produto por ID  |
| GET         |  /products        |   Lista de produtos           |
| GET         |  /products/search |   Lista de produtos filtrados |
| DELETE      |  /products/{id}   |   Deleção de um produto       |

#### POST /products
Esse endpoint cria um produto na base de dados e retorna o *id* gerado e HTTP 201.
Entrada:
  {
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
Retorno:
  {
    "id": "id gerado",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
Em caso de algum erro de validação, retorna um HTTP 400 indicando uma requisição inválida, no formato:
  {
    "status_code": integer,
    "message": "string"
  }
No campo *status_code* vem o código HTTP do erro de validação (400 Bad Request). Em *message* vem uma mensagem genérica indicando o erro ocorrido.

#### PUT /products/\{id\}
Esse endpoint atualiza um produto baseado no {id} passado via path param. 
Quando o produto não é localizado, devolve um HTTP 404. Se for localizado, retorna *name, description e price* no body da requisição.
Entrada:
  {
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
Retorno:
  {
    "id": "id atualizado",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
Se tiver erro:
  {
    "status_code": integer,
    "message": "string"
  }

#### GET /products/\{id\}
Retorna o produto da base com um HTTP 200. Se não encontra, retorna um HTTP 404.
Retorno:
  {
    "id": "id buscado",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }

#### GET /products
Lista todos os produtos com HTTP 200. Se não existir nenhum, retorna uma lista vazia.
Retorno com produtos:
[
  {
    "id": "id produto 1",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  },
  {
    "id": "id produto 2",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
]
Retorno vazio:
[]

#### GET /products/search
Retorna todos os produtos filtrados de acordo com query parameters passados na URL: q, min_price e max_price.
Nenhum é obrigatório.
Onde:
| Query param |  Ação de filtro     
|-------------|:---------------------------------------------------------------:|
| q           |  deverá bater o valor contra os campos *name* e *description*   |
| min_price   | deverá bater o valor ">=" contra o campo *price*                |
| max_price   | deverá bater o valor "<=" contra o campo *price*                |
**Exemplo: /products/search?min_price=10.5&max_price=50&q=sabonete**
Retorno com produtos filtrados/buscados:
[
  {
    "id": "id produto 1",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  },
  {
    "id": "id produto 2",
    "name": "nome",
    "description": "descrição",
    "price": <preco>
  }
]
Retorno vazio:
[]

#### DELETE /products/\{id\}
Apaga um registro de produto na base de dados. Dando certo, retorna um HTTP 200. Se não encontrar o *id*, retorna um HTTP 404

## Porta
A porta do serviço foi definida em 9999, ficando a base url então: http://localhost:9999
