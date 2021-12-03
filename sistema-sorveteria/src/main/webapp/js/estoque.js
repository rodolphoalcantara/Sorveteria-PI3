$("#btnBusca").submit(function(ev){
    ev.preventDefault();
    
    buscarCliente();
    /*var nomeProduto = $("#txtBusca").val();
    
    if (nomeProduto.length > 3) {
        window.location.href = ("estoque/listar/?p="+nomeProduto);
    }*/
})

function buscarProduto(){
    var nomeProduto = $("#txtBusca").val();
    
    if (nomeProduto.length > 3) {
        window.location.href = ("estoque/listar/?p="+nomeProduto);
    }
}

function buscarCliente() {
    
    var campoBusca = $("#nomeCliente");
    var nomeCliente = campoBusca.val();
    var tamanhoBusca = nomeCliente.length;
    if (tamanhoBusca < 3) {
        mostrarTelaAlerta("Digite, pelo menos, 3 caracteres");
    } else {
        $('#tabelaClientes tbody').empty();
        var url = "buscaCliente?nomeCliente=" + nomeCliente;
        $.ajax(url).done(function (resposta) {
            // Retorno do servlet
            var jsonClientes = JSON.parse(resposta);
            if (jsonClientes.length === 0) {
                mostrarTelaAlerta("A busca não encontrou resultados");
            }
            console.log(jsonClientes);
            // Adicionando resultado na lista
            jsonClientes.forEach(function (cliente) {
                $("#tabelaClientes").find('tbody')
                        .append($('<tr>')
                                .append($('<td>').append(cliente.nome))
                                .append($('<td>').append(cliente.cpf))
                                .append($('<td>').append(cliente.email))
                                .append($('<td>').append(cliente.data_nasc))
                                .append($('<td>').append(cliente.cidade))
                                .append($('<td>').append(cliente.sexo))
                                );
            })

        }).fail(function () {
            console.log("Erro!");
        })
    }
}
    


$("#btnAddProduto").click(function(){
    window.location.href = ("/protected/cliente/formCliente.jsp");
});

function mostrarTelaAlerta(texto) {
    $("#txtAlerta").html(texto);
    $("#msgAlerta").css("display", "block");
    setTimeout(function () {
        $("#msgAlerta").css("display", "none");
    }, 1000)
}

function mostrarModalExclusaoCliente(id, nome) {
    $("#nomeCliente").html(nome);
    $("#idCliente").val(id);
    $('#modalExclusaoCliente').modal('show');
}
function mostrarModalExclusaoProduto(id, nome) {
    $("#nomeProduto").html(nome);
    $("#idProduto").val(id);
    $('#modalExclusaoProduto').modal('show');
}

function excluirCliente() {
    $.get("excluirCliente?id=" + $("#idCliente").val(), function (resposta) {
        $('#modalExclusaoCliente').modal('hide')
        window.location.reload();
    }
    );
}

function excluirProduto() {
    $.get("excluirProduto?id=" + $("#idProduto").val(), function (resposta) {
        $('#modalExclusaoProduto').modal('hide')
        window.location.reload();
    }
    );
}

function buscarClienteCPF(){
    $.get("buscarClienteVenda?cpf=" + $("#txtBusca").val(), function (resposta){
       $("#nomeCliente").val(resposta.toString());
       window.location.reload();
    });
}


function adicionarProdutoCarrinho() {

    var idProd = $("#selectProduto").val();
    var nomeProd = $("#selectProduto").find("option:selected").text();
    var quantidade = $("#quantidadeProd").val();

    window.location.href=(`atualizarCarrinho?id=${idProd}&nome=${nomeProd}&quant=${quantidade}`);
}

function retirarCarrinho() {
    $("#tableCarrinho").on('click', '#btnDelete', function (event) {
        let id = $(this).parent().parent().find('#idList').text();
        window.location.href=(`atualizarCarrinho?idLista=${id}`);
    });
}

function finalizarVenda() {
    $.post("insertVenda", function (resposta) {
        //$('#modalExclusaoCliente').modal('hide')
        window.location.href=("/listarProdsDisp");
        alert(resposta);
    }
    );
}

