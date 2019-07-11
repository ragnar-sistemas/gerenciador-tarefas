create database gerenciador;
use gerenciador;

create table usr_usuarios (
usr_id int primary key auto_increment,
usr_email varchar(100) not null,
usr_senha varchar(100) not null
);

create table tar_tarefas (
tar_id int primary key auto_increment,
tar_titulo varchar(50) not null,
tar_descricao varchar(100) default null,
tar_data_expiracao date not null,
tar_concluida bit default false,
usr_id int not null 
);

alter table tar_tarefas add constraint fk_tar_tarefas_usr_usuarios 
foreign key (usr_id) references usr_usuarios(usr_id);
