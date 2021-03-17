package br.com.zup

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    val request = FuncionarioRequest
        .newBuilder()
        .setNome("Rodrigo Braz")
        .setCpf("111.111.111-11")
        .setIdade(26)
        .setSalario(2000.0)
        .setAtivo(true)
        .setCargo(Cargo.QA)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua A")
            .setCep("11111-111")
            .setComplemento("Casa 2")
            .build())
        .build()

    println(request)

    // Serializando os dados para binários e persistindo em disco
    request.writeTo(FileOutputStream("funcionario-request.bin"))

    // Carregando e desserializando os dados em disco

    val request2 = FuncionarioRequest.newBuilder()
        .mergeFrom(FileInputStream("funcionario-request.bin"))

    // Alterando o objeto que foi carregado
    request2.setCargo(Cargo.GERENTE)
        .build()

    println(request2)
}