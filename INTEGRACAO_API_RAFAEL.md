# Integração com a API do professor Rafael

A identidade visual original do **The Senai Digital Card** foi mantida: fotos
(`homelanderr`), logos, imagens, cores, cards e navegação aluno/professor.

## Contratos utilizados

- `POST /auth/login`: JSON `{"login":"...","senha":"..."}`.
  A resposta aceita `id`, `nome`, `matricula`, `curso`, `turma`, `token`.
- `GET /unidades-curriculares`: envia `Authorization: Bearer <token>` e lê
  `id`, `nome`, `professor`, `nota1`, `nota2`, `media`, `faltas`.
- Na carteira, nome, curso, matrícula, turma e QR Code passam a vir do login.
  A foto e o visual original continuam os mesmos; foto e QR de exemplo só no Preview.

## Como executar

1. Inicie a API compatível com a do professor na porta **8080** do computador.
2. Use o **emulador Android** (a base URL é `http://10.0.2.2:8080/`).
   Em um celular físico, configure no `NetworkFactory` o IP LAN do computador;
   o celular deve conseguir alcançar a máquina onde está a API.
3. No Android Studio, sincronize o Gradle e execute o aplicativo.
4. Teste login válido, login inválido, UCs, voltar e sair.
5. Para testes locais: `./gradlew testDebugUnitTest`.

## Limitações conhecidas

- O contrato público do app do Rafael lido neste trabalho **não possui papel
  `PROFESSOR`**, nem endpoints para listar turmas ou lançar faltas.
  O aplicativo só libera as telas de professor quando uma API adaptada retornar
  `"tipo":"PROFESSOR"`. Nessas telas, os exemplos de turmas/faltas ainda
  são **locais e não persistem**. Não confunda o botão de falta com um
  lançamento no servidor.
- O QR Code representa a matrícula. Não é um token de segurança e só terá
  validação externa se houver um serviço para conferir essa matrícula.
- O token é armazenado apenas em memória durante a sessão; fechar o processo
  exige novo login. Em produção, a API deve usar HTTPS e a configuração de
  tráfego HTTP local deve ser restringida.
- O backend **não faz parte deste repositório**; disponibilidade e credenciais
  válidas precisam ser verificadas executando a API.
