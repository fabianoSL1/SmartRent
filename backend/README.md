# Smart Rent
API Restful para alocação de veiculos.

## Local

### Requisitos

- Java 21
- Docker
- maven 


### instruções

1. Definir a variavel de ambiente da maquina como `local`.

```bash
export spring_profiles_active=local
```

2. Subir o container contendo o `PostgreSQL`.

```bash
docker compose up -d
```

3. iniciar a aplicação.

```bash
mvn spring-boot:run
```