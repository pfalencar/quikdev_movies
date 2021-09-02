# jwt-simplificado
# QUIKDEV
## Practice Test - Back-end
## Creating a crud rest api getting access by a login (JWT). For storing was used H2 database.

## Movies 
Attributes: Title, Release Date, Genre, Overview, Cover Image, Rating, etc. 
 
#### Elements 
. An authentication route 
. An authenticated crud resource for creating, reading (listing all or one), updating and deleting, 
. An unauthenticated /stats route with statistics from the rest api 
  . Quantity of movies available 
  . Quantity of movies by genre.

## An authentication route 
Para autenticar um usuário é necessário cadastrar para depois acessar por meio login.
Como estamos utilizando JWT (JSON Web Token) a API gerará um Token no momento do cadastro para ser utilizado ao logar.

## REGISTRANDO (CADASTRANDO) UM USUÁRIO

## POST User Maria
http://localhost:8080/user

Em body, raw, JSON, digite no corpo da requisição:
*RequestBody:*
```
{
    "nome": "Maria",
    "email": "maria@gmail.com",
    "senha": "123"
}
```

*ResponseBody:*
```
eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzA2MDU5MzUsInN1YiI6Im1hcmlhQGdtYWlsLmNvbSIsImV4cCI6MTYzMDYwNzczNX0.ZmWhRI4hxLK6vgk0rEoWaqKp348HFYTsvVksQ96mrXo
```

Ao registrar um usuário com nome, login e senha, a api retorna um token.
HTTP STATUS: 201 CREATED.


## AUTENTICANDO (LOGANDO) O USUÁRIO CADASTRADO

## POST Login Maria
http://localhost:8080/login

Na aba Authorization 
   Em Type escolha Bearer Token
   Insira o Token que veio como resposta da requisição anterior (cadastro).


Em body, escolha o radio button: raw  escolha no combo box: JSON, digite no corpo da requisição.
RequestBody:
```
{
    "email": "maria@gmail.com",
    "senha": "123"
}
```

ResponseBody:
```
{
    "tipo": "maria@gmail.com",
    "email": "Maria",
    "nome": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzA2MDU5MzUsInN1YiI6Im1hcmlhQGdtYWlsLmNvbSIsImV4cCI6MTYzMDYwNzczNX0.ZmWhRI4hxLK6vgk0rEoWaqKp348HFYTsvVksQ96mrXo",
    "token": "Bearer ",
    "senha": null
}
```

HTTP STATUS 202 ACCEPTED.
Logar com email  senha e token.


An authenticated crud resource for creating, reading (listing all or one), updating and deleting

Para realizar o CRUD do filme, é necessário colocar o Token em cada uma das ações: POST, GETs, PUT e DELETE.

POST maria- movie Free Guy
http://localhost:8080/movie

Na aba Authorization <br />
   Em Type escolha Bearer Token <br />
   Insira o mesmo token gerado ao logar. <br />

Em body, escolha o radio button: raw,  escolha no combo box: JSON, digite no corpo da requisição: <br />
RequestBody:

```
{
    "title": "Free Guy: Assumindo o Controle",
    "releaseDate": "2021-08-19",
    "genre": "Comédia",
    "overview": "Um caixa de banco preso a uma entediante rotina tem sua vida virada de cabeça para baixo quando ele descobre que é personagem em um brutalmente realista vídeo game. Agora ele precisa lidar com o fato de que é o único que pode salvar o mundo.",
    "coverImage": "https://br.web.img3.acsta.net/c_310_420/pictures/21/06/10/20/47/1652456.jpg",
    "rating": "4"
}
```

ResponseBody: <br />
Movie saved successfully. <br />
HTTP STATUS: 201 CREATED. <br />

Todos os filmes serão cadastrados da mesma forma.

## GET ALL movie
http://localhost:8080/movie

Na aba Authorization <br />
   Em Type escolha Bearer Token <br />
   Insira o mesmo token gerado ao logar.

#### ResponseBody:
```
[
    {
        "id": 1,
        "title": "Free Guy: Assumindo o Controle",
        "releaseDate": "2021-08-19",
        "genre": "Comédia",
        "overview": "Um caixa de banco preso a uma entediante rotina tem sua vida virada de cabeça para baixo quando ele descobre que é personagem em um brutalmente realista vídeo game. Agora ele precisa lidar com o fato de que é o único que pode salvar o mundo.",
        "coverImage": "https://br.web.img3.acsta.net/c_310_420/pictures/21/06/10/20/47/1652456.jpg",
        "rating": 4
    },
    {
        "id": 2,
        "title": "Paw Patrol: The Movie",
        "releaseDate": "2021-09-01",
        "genre": "Aventura",
        "overview": "Cães falantes usam equipamentos especializados para investigar e resolver crimes, salvando sua pequena cidade.",
        "coverImage": "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcT9qUAfbQWvaBIS3j005NsAaOApnvadDGu9lUprxH7VAcztZDJg",
        "rating": 3
    }
]
```

HTTP STATUS: 200 OK

#### GET ONE movie
http://localhost:8080/movie/2 <br />

Na aba Authorization <br />
   Em Type escolha Bearer Token <br />
   Insira o mesmo token gerado ao logar <br />

ResponseBody:
```
{
    "id": 2,
    "title": "Paw Patrol: The Movie",
    "releaseDate": "2021-09-01",
    "genre": "aventura",
    "overview": "Cães falantes usam equipamentos especializados para investigar e resolver crimes, salvando sua pequena cidade.",
    "coverImage": "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcT9qUAfbQWvaBIS3j005NsAaOApnvadDGu9lUprxH7VAcztZDJg",
    "rating": 3
}
```

HTTP STATUS: 200 OK <br />

#### PUT movie Free Guy
http://localhost:8080/movie <br />

Na aba Authorization  <br />
   Em Type escolha Bearer Token <br />
   Insira o mesmo token gerado ao logar. <br />

Em body, escolha o radio button: raw,  escolha no combo box: JSON, digite no corpo da requisição: <br />
RequestBody:
```
{
    "id": 1,
    "title": "Free Guy: Assumindo o Controle",
    "releaseDate": "2021-10-19",
    "genre": "terror",
    "overview": "Um caixa de banco preso a uma entediante rotina tem sua vida virada de cabeça para baixo quando ele descobre que é personagem em um brutalmente realista vídeo game. Agora ele precisa lidar com o fato de que é o único que pode salvar o mundo.",
    "coverImage": "https://br.web.img3.acsta.net/c_310_420/pictures/21/06/10/20/47/1652456.jpg",
    "rating": "4"
}
```

ResponseBody: <br />

HTTP STATUS: 204 NO CONTENT


#### DELETE MOVIE by ID
http://localhost:8080/movie/1

Na aba Authorization <br />
   Em Type escolha Bearer Token <br />
   Insira o mesmo token gerado ao logar. <br />

ResponseBody: <br />

HTTP STATUS: 204 NO CONTENT


### An unauthenticated /stats route with statistics from the rest api 
. Quantity of movies available <br />
. Quantity of movies by genre. <br />

Os 2 endpoints de estatísticas que não precisam de autorização: <br />

#### GET stats/<genre>
http://localhost:8080/stats/aventura <br />

ResponseBody:<br />
1 <br />

HTTP STATUS: 200 OK

#### GET stats/available
http://localhost:8080/stats/available <br />

ResponseBody: <br />
1 <br />

HTTP STATUS: 200 OK



