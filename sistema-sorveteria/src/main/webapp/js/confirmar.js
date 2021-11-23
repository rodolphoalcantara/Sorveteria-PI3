/**Confirmação de exclusão de fornecedor
 * @author @Renank16
 */

function confirmar(idFor){
    let resposta = confirm("Confirma a exclusão deste fornecedor?");
    if(resposta === true){
        window.location.href = "deleteFornecedor?idFor=" + idFor
    }
}