function validar(){
    let cnpj = formFornecedor.cnpj.value;
    let nome = formFornecedor.nome.value;
    let email = formFornecedor.email.value;
    let telefone = formFornecedor.telefone.value;
    let endereco = formFornecedor.endereco.value;
    let estado = formFornecedor.estado.value;
    let cidade = formFornecedor.cidade.value;
    let segmento = formFornecedor.segmento.value;

    if(cnpj === ""){
        alert('Preencha o campo CNPJ')
        formFornecedor.cnpj.focus();
        return false;
    }else if(nome === ""){
        alert('Preencha o campo Nome')
        formFornecedor.nome.focus();
        return false;
    }
    else if(email === ""){
        alert('Preencha o campo E-mail')
        formFornecedor.email.focus();
        return false;
    }
    else if(telefone === ""){
        alert('Preencha o campo Telefone')
        formFornecedor.telefone.focus();
        return false;
    }
    else if(endereco === ""){
        alert('Preencha o campo Telefone')
        formFornecedor.endereco.focus();
        return false;
    }
    else if(estado === ""){
        alert('Preencha o campo Estado')
        formFornecedor.estado.focus();
        return false;
    }
    else if(cidade === ""){
        alert('Preencha o campo Cidade')
        formFornecedor.cidade.focus();
        return false;
    }
    else if(segmento === ""){
        alert('Preencha o campo Segmento')
        formFornecedor.segmento.focus();
        return false;
    }
    else{
        alert('Fornecedor cadastrado com sucesso')
        document.forms["formFornecedor"].submit()
    }

}