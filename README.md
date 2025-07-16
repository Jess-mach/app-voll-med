

# API Voll Med

Uma API REST para gerenciamento de consultas médicas, desenvolvida com Spring Boot e Spring Security.

## 📋 Sobre o Projeto

A API Voll Med é um sistema completo para gerenciamento de consultas médicas que permite:
- Autenticação de usuários com JWT
- Cadastro e gerenciamento de médicos
- Cadastro e gerenciamento de pacientes
- Agendamento e cancelamento de consultas
- Documentação automática com Swagger

## 🚀 Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.3** - Framework principal
- **Spring Security** - Segurança e autenticação
- **Spring Data JPA** - Persistência de dados
- **JWT** - Tokens de autenticação
- **Bean Validation** - Validação de dados
- **Swagger/OpenAPI** - Documentação da API
- **H2/MySQL** - Banco de dados (configurável)

## 📦 Estrutura do Projeto

```
med.voll.api/
├── controller/
│   ├── AutenticacaoController.java
│   ├── ConsultaController.java
│   ├── MedicoController.java
│   └── PacienteController.java
├── domain/
│   ├── consulta/
│   ├── endereco/
│   ├── medico/
│   ├── paciente/
│   └── usuario/
├── infra/
│   ├── exception/
│   └── security/
└── test/
    ├── controller/
    ├── domain/
    └── infra/
```

## 🔧 Instalação e Configuração

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6 ou superior
- MySQL (opcional, pode usar H2 em memória)

### Passos para instalação

1. **Clone o repositório**
```bash
git clone https://github.com/seu-usuario/voll-med-api.git
cd voll-med-api
```

2. **Configure o banco de dados**
```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/vollmed
spring.datasource.username=root
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
```

3. **Execute o projeto**
```bash
mvn spring-boot:run
```

4. **Acesse a documentação**
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/v3/api-docs

## 🔐 Autenticação

A API utiliza JWT (JSON Web Token) para autenticação. Para acessar os endpoints protegidos, é necessário:

1. **Fazer login** no endpoint `/login`
2. **Incluir o token** no header `Authorization: Bearer <token>`

### Exemplo de login:
```json
POST /login
Content-Type: application/json

{
  "login": "usuario@email.com",
  "senha": "senha123"
}
```

## 📚 Endpoints da API

### 🔑 Autenticação
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST   | `/login` | Autenticação de usuário |

### 👨‍⚕️ Médicos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST   | `/medicos` | Cadastrar médico |
| GET    | `/medicos` | Listar médicos (paginado) |
| GET    | `/medicos/{id}` | Detalhar médico |
| PUT    | `/medicos` | Atualizar médico |
| DELETE | `/medicos/{id}` | Excluir médico (soft delete) |

### 👥 Pacientes
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST   | `/pacientes` | Cadastrar paciente |
| GET    | `/pacientes` | Listar pacientes (paginado) |
| GET    | `/pacientes/{id}` | Detalhar paciente |
| PUT    | `/pacientes` | Atualizar paciente |
| DELETE | `/pacientes/{id}` | Excluir paciente (soft delete) |

### 📅 Consultas
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST   | `/consultas` | Agendar consulta |
| DELETE | `/consultas` | Cancelar consulta |

## 💻 Exemplos de Uso

### Cadastrar Médico
```json
POST /medicos
Authorization: Bearer <token>
Content-Type: application/json

{
  "nome": "Dr. João Silva",
  "email": "joao.silva@email.com",
  "telefone": "11999999999",
  "crm": "123456",
  "especialidade": "CARDIOLOGIA",
  "endereco": {
    "logradouro": "Rua das Flores, 123",
    "bairro": "Centro",
    "cep": "12345-678",
    "cidade": "São Paulo",
    "uf": "SP"
  }
}
```

### Agendar Consulta
```json
POST /consultas
Authorization: Bearer <token>
Content-Type: application/json

{
  "idMedico": 1,
  "idPaciente": 1,
  "data": "2024-12-15T14:00:00"
}
```

### Cancelar Consulta
```json
DELETE /consultas
Authorization: Bearer <token>
Content-Type: application/json

{
  "idConsulta": 1,
  "motivo": "PACIENTE_DESISTIU"
}
```

## 🛡️ Segurança

- **JWT Authentication**: Tokens seguros com expiração configurável
- **CORS**: Configuração de Cross-Origin Resource Sharing
- **Validação**: Validação robusta de dados de entrada
- **Soft Delete**: Exclusão lógica para manter histórico

## 📄 Documentação

A documentação completa da API está disponível através do Swagger UI após executar o projeto:
- **Swagger UI**: http://localhost:8080/swagger-ui.html

## 🧪 Testes

Para executar os testes:
```bash
mvn test
```

## 📈 Paginação

Os endpoints de listagem suportam paginação:
- `page`: Número da página (padrão: 0)
- `size`: Tamanho da página (padrão: 10)
- `sort`: Campo para ordenação (padrão: nome)

Exemplo: `GET /medicos?page=0&size=5&sort=nome`

## ⚠️ Tratamento de Erros

A API retorna respostas padronizadas para erros:
- **400 Bad Request**: Dados inválidos
- **401 Unauthorized**: Token inválido/expirado
- **404 Not Found**: Recurso não encontrado
- **500 Internal Server Error**: Erro interno do servidor

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 👥 Autores

- **Jessica Machado Franco** - *Desenvolvimento inicial*



⭐ Se este projeto te ajudou, não esqueça de dar uma estrela no repositório!


### Executar mysql
```bash
mysql -u root -p --skip-ssl
```