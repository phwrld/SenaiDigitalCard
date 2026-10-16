package com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.data

import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.domain.model.Turma

fun turmaDataSource(): List<Turma> {
    return listOf(
        Turma(id = "1", nome = "Turma 1", curso = "Desenvolvimento de Sistemas", quantidadeAlunos = 32, horario = "13:30 - 17:30", semestre = "1º Semestre"),
        Turma(id = "2", nome = "Turma 2", curso = "Análise de Sistemas", quantidadeAlunos = 28, horario = "08:00 - 12:00", semestre = "2º Semestre"),
        Turma(id = "3", nome = "Turma 3", curso = "Redes de Computadores", quantidadeAlunos = 25, horario = "19:00 - 22:30", semestre = "1º Semestre"),
        Turma(id = "4", nome = "Turma 4", curso = "Banco de Dados", quantidadeAlunos = 30, horario = "13:30 - 17:30", semestre = "3º Semestre"),
        Turma(id = "5", nome = "Turma 5", curso = "Programação Mobile", quantidadeAlunos = 29, horario = "08:00 - 12:00", semestre = "2º Semestre"),
        Turma(id = "6", nome = "Turma 6", curso = "Segurança da Informação", quantidadeAlunos = 22, horario = "19:00 - 22:30", semestre = "4º Semestre"),
        Turma(id = "7", nome = "Turma 7", curso = "Engenharia de Software", quantidadeAlunos = 35, horario = "13:30 - 17:30", semestre = "1º Semestre"),
        Turma(id = "8", nome = "Turma 8", curso = "Internet das Coisas", quantidadeAlunos = 20, horario = "08:00 - 12:00", semestre = "3º Semestre")
    )
}