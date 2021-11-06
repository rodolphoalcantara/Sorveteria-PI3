$("#btnBusca").submit(function(ev){
    ev.preventDefault();
    
    var nomeProduto = $("#txtBusca").val();
    
    if (nomeProduto.length > 3) {
        window.location.href = ("estoque/listar?p="+nomeProduto);
    }
})

function buscarProduto(){
    var nomeProduto = $("#txtBusca").val();
    
    if (nomeProduto.length > 3) {
        window.location.href = ("estoque/listar?p="+nomeProduto);
    }
}
