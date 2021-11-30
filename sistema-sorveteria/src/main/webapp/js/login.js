/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function enviarRequisicao() {
    let loginUsuario = document.querySelector("#loginUsuario").value;
    let senhaUsuario = document.querySelector("#senhaUsuario").value;

    let jsonReq = {
        "loginUsuario": loginUsuario,
        "senhaUsuario": senhaUsuario
    };

    let jsonB64 = btoa(JSON.stringify(jsonReq));

    $("#formLogin").submit(function (e) {
        $(this).append(`<input type="hidden" name="reqInput" value="${jsonB64}" />`);
        return true;
    })
    
}
