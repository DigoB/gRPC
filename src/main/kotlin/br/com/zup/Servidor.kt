package br.com.zup

import com.google.protobuf.Timestamp
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId

fun main() {

    val server = ServerBuilder
        .forPort(50051)
        .addService(FuncionarioEndpoint())
        .build()

    server.start()
    // Trava a execução do main para que o servidor continue rodando
    server.awaitTermination()
}

class FuncionarioEndpoint : FuncionarioServiceGrpc.FuncionarioServiceImplBase() {

    override fun cadastrar(request: FuncionarioRequest?, responseObserver: StreamObserver<FuncionarioResponse>?) {

        println(request!!)

        var nome: String? = request.nome
        if (!request.hasField(FuncionarioRequest.getDescriptor().findFieldByName("nome"))) {
            nome = "Campo não preenchido"
        }

        // Busca o instante em Java
        val instant = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()
        val criadoEm = Timestamp.newBuilder()
                .setSeconds(instant.epochSecond)
                .setNanos(instant.nano)


        val response = FuncionarioResponse.newBuilder()
            .setNome(nome)
            .setCriadoEm(criadoEm)
            .build()

        // Manda a response para o client
        responseObserver?.onNext(response)
        // Encerra a conexão com o client
        responseObserver?.onCompleted()
    }
}