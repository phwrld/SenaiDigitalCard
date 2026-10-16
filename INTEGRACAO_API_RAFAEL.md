# Integração com a API — estrutura atual

Este guia foi atualizado após a refatoração para acompanhar as pastas do projeto do professor.

O documento completo agora é **[ESTRUTURA_E_INTEGRACAO.md](ESTRUTURA_E_INTEGRACAO.md)**. Consulte nele a localização de cada classe, as camadas de autenticação, as rotas e o procedimento de teste.

Resumo:
- **Login:** `feature/login/{data,domain,presentation}/`
- **UCs do aluno:** `feature/aluno/unidadescurriculares/{data,domain,presentation}/`
- **Navegação:** `app/navigation/{auth,aluno,professor}/`
- **HTTP:** `core/network/NetworkClient.kt` e `AuthInterceptor.kt`
- **Injeção:** `app/di/DefaultAppContainer.kt`
- **API local:** `http://10.0.2.2:8080/` no emulador Android, usando `POST auth/login` e `GET unidades-curriculares`

As telas de turma e faltas do professor são demonstrativas e não persistem dados na API.
