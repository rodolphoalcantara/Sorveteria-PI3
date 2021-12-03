create database sorveteriadb;
use sorveteriadb;

create table cliente(

	id_cli int not null auto_increment,
    CPF varchar(11) not null unique,
    nome varchar(60) not null,
    email varchar(120) not null,
    sexo varchar(11)not null,
    data_nasc varchar(10) not null,
    telefone varchar(11) not null,
    endereco varchar(255) not null,
    cidade varchar(60) not null,
    estado varchar(60) not null,
    
    primary key (id_cli)
  );
  
  create table funcionario(

	id_fun int not null auto_increment,
    CPF varchar(11) not null unique,
    nome varchar(60) not null,
    sexo varchar(11)not null,
    data_nasc varchar(10) not null,
    login varchar(255) not null,
    senha varchar(255) not null,
    fun_novo varchar(1) not null,
    
    primary key (id_fun)
  );
  
  create table fornecedor(

	id_for int not null auto_increment,
    CNPJ varchar(11) not null unique,
    nome varchar(60) not null,
    email varchar(120) not null,
    telefone varchar(11) not null,
    endereco varchar(255) not null,
    cidade varchar(60) not null,
    estado varchar(60) not null,
    segmento varchar(60) not null,
    
    primary key (id_for)
  );
  

create table venda(
    id_venda int not null auto_increment,
    data_venda date not null,
    valor_total decimal(10,2) not null,
    fk_id_cli int not null,
    fk_id_fun int not null,

    primary key (id_venda),
    foreign key (fk_id_cli) references cliente(id_cli),
    foreign key (fk_id_fun) references funcionario(id_fun)
);

create table produto(
    id_produto int not null auto_increment,
    nome varchar(60) not null,
    valor_unitario decimal(10,2) not null,
    estoque int not null,
    descricao varchar(500),
    tipo varchar(30) not null,

    primary key (id_produto),
);

create table item_venda(
    id_item int not null auto_increment,
    quantidade int not null,
    valor_subtotal decimal(10,2) not null,
    fk_id_venda int not null,
    fk_id_produto int not null,

    primary key (id_item),
    foreign key (fk_id_venda) references venda(id_venda),
    foreign key (fk_id_produto) references produto(id_produto)
);

create table log_estoque(
	id_log int not null auto_increment,
    data_op date not null,
    operacao varchar(7) not null,
    quantidade int not null,
    fk_id_produto int not null,
    fk_id_fun int not null,
    
    primary key (id_log),
    foreign key (fk_id_fun) references funcionario(id_fun),
    foreign key (fk_id_produto) references produto(id_produto)
);


insert into funcionario(CPF, nome, sexo, data_nasc, login, senha, fun_novo) values (
	('00000000000', 'Administrador', 'masculino', '2021-01-01', 'admin', '$2a$08$IhC.vrzOTi/E1x9ADTMTeeuAkqLLDMKtPrsYg0tvl7d5bQEqyPENS', 'N'),
	('11111111111', 'Naga Freitas', 'masculino', '2021-01-01', 'nagafreitas', '$2a$08$gHq3G.zAADui9iuPqF/d0ORmsxzEpbQcP3C0LXhUPsBOxb6ufGXqa', 'N')
);
