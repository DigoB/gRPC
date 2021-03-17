package br.com.zup

import io.grpc.ManagedChannelBuilder

fun main() {

    // Informa os dados de conexão(endereço, porta, se o protocolo é seguro, etc)
    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
            // Cria um ambiente de testes
        .usePlaintext()
        .build()

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

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)
    val response = client.cadastrar(request)

    println(response)
}