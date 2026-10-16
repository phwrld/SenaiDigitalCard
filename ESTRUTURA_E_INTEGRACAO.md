# Estrutura do SenaiDigitalCard — referência Rafael

Reorganização por *features* inspirada em
[RafaelPinheiroCosta/CarteirinhaDigital2DEVEST](https://github.com/RafaelPinheiroCosta/CarteirinhaDigital2DEVEST), mantendo o pacote/namespace do app original e sua interface Compose.

## Estrutura

```text
app/
  App.kt
  MainActivity.kt
  CarteirinhaApplication.kt
  di/                       AppContainer e DefaultAppContainer
  navigation/               AppNavHost, GraphRoutes, ProfileGuard
    auth/                   AuthNavGraph e AuthRoutes
    aluno/                  AlunoNavGraph e AlunoRoutes
    professor/              ProfessorNavGraph e ProfessorRoutes
  session/                  SessionViewModel e Factory
  ui/                       AppShell (barra Voltar / Sair nas cores originais)
core/
  auth/                     SessionTokenStore (em memória)
  network/                  NetworkClient e AuthInterceptor
  designsystem/theme/       cores originais, Theme e Type
feature/
  login/
    data/remote/dto/        LoginRequestDto, LoginResponseDto, ErrorResponseDto
    data/remote/service/    AuthApi
    data/repository/        ApiLoginRepositoryImpl
    domain/model/           UsuarioLogado, PerfilAcesso
    domain/repository/      LoginRepository
    presentation/           LoginViewModel, LoginUiState, LoginEvent, Factory, screen/
  aluno/
    home/presentation/screen/
    carteirinha/presentation/{component,screen}/
    unidadescurriculares/{data,domain,presentation}/
  professor/
    home/presentation/screen/
    turmas/{data,domain,presentation}/
    unidadescurriculares/{domain,presentation}/
```

## API e autenticação

- URL padrão: `http://10.0.2.2:8080/`, acessível pelo **emulador Android** com backend rodando na máquina host. Em um celular real, altere a URL para um endereço da máquina acessível ao celular, preferindo HTTPS.
- `POST auth/login`: JSON `{"login":"...","senha":"..."}`. A resposta inclui `token`, `perfil`, `matricula`, `nome`, `curso` e `turma`. O projeto reconhece `tipo` de versões antigas se `perfil` estiver ausente, mas nunca deduz perfil a partir do nome.
- `GET unidades-curriculares`: envia `Authorization: Bearer <token>` pelo cliente autenticado. Reutiliza o **mesmo** `SessionTokenStore` usado pela sessão.
- A aplicação deixa de usar a `NetworkFactory` antiga e tem injeção manual de dependências num `AppContainer` compartilhado.
- Turmas e faltas de professor são **demonstração local**: o backend de referência não fornece endpoints de gravação. O botão de falta **não altera banco de dados**.
- Login ocorre exclusivamente pelo backend real; sem credenciais fixas, API fictícia ou logs HTTP com senhas.

## Interface preservada

Foram mantidas as telas Compose do projeto original: fundo azul, botões, cards, foto, logo, layout da carteirinha, QRCode de matrícula e recursos `drawable`. Foi adicionada apenas uma barra superior com **Voltar** e **Sair**, usando a mesma paleta.

## Validação

Existe CI em `.github/workflows/android-ci.yml` executando `:app:assembleDebug` e `:app:testDebugUnitTest`. O teste `ApiContractTest` verifica serialização de login, leitura de perfil e formato das UCs.

Para testar a integração real, execute o backend local na porta 8080, rode o app no emulador, entre como aluno/professor e verifique o fluxo e o token no endpoint de unidades curriculares. **Build e testes unitários não substituem o teste de integração com servidor ligado.**
