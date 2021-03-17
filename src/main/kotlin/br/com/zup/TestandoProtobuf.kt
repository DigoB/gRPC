package br.com.zup

fun main() {
    val request = FuncionarioRequest
        .newBuilder()
        .setNome("Rodrigo Braz")
        .setCpf("111.111.111-11")
        .setSalario(2000.0)
        .setAtivo(true)
        .setCargo(Cargo.DEV)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua A")
            .setCep("11111-111")
            .setComplemento("Casa 2")
            .build())
        .build()

    println(request)
}