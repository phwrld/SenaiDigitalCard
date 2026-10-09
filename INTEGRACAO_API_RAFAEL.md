# Senai Digital Card — integracao com API do Rafael

## Estrutura da unidade curricular do aluno

A implementacao segue as camadas do professor, sem substituir o visual do app:

- `feature/unidadecurricular/data/remote/dto/UnidadeCurricularDto.kt`
- `feature/unidadecurricular/data/remote/service/UnidadeCurricularApi.kt`
- `feature/unidadecurricular/data/repository/ApiUnidadeCurricularRepositoryImpl.kt`
- `feature/unidadecurricular/domain/model/UnidadeCurricularAluno.kt`
- `feature/unidadecurricular/domain/repository/UnidadeCurricularRepository.kt`
- `feature/unidadecurricular/presentation/UnidadeCurricularUiState.kt`
- `feature/unidadecurricular/presentation/UnidadeCurricularViewModel.kt`
- `feature/unidadecurricular/presentation/factory/UnidadeCurricularViewModelFactory.kt`
- `feature/unidadecurricular/presentation/screen/UnidadeCurricularAlunoScreen.kt`
- `feature/unidadecurricular/presentation/component/UnidadeCurricularAlunoCard.kt`

O login usa `POST /auth/login` e a unidade curricular usa
`GET /unidades-curriculares`, com `Authorization: Bearer <token>`.
A base URL configurada em `NetworkFactory` e `http://10.0.2.2:8080/`
(emulador com API rodando no computador).

Os objetos de dominio conservam todos os campos da API:
`id, nome, professor, nota1, nota2, media, faltas`.

## Se o Android Studio ainda mostra erro em dataSource()

**O arquivo `dataSource.kt` e sua chamada foram removidos.**
A tela correta fica na pasta `presentation/screen`, nao em
`presentation/component`. Atualize sua copia do GitHub:
```bash
git checkout master
git pull origin master
```
Ou baixe a branch/PR atualizado antes de mesclar. Confira que
nao existe outra copia antiga de `UnidadeCurricularAlunoScreen.kt`
na arvore de arquivos do app. Depois use **Sync Project with Gradle Files**
e **Build > Rebuild Project**.

## API e permissoes

A API de referencia nao descreve endpoints de professor/turmas/faltas;
essas telas sao demonstracoes locais e nao salvam lancamentos no servidor.
O login do aluno precisa retornar matricula e token validos.

A foto, os logos, as cores, o estilo do painel e dos cards foram mantidos.
O build de Android deve ser confirmado em CI ou no Android Studio antes do merge.
