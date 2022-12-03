
drop table if exists tb_topico_has_seguidores;

drop table if exists tb_usuario;

drop table if exists tb_comentario;

drop table if exists tb_pontuacao_comentario;

drop table if exists tb_pontuacao_postagem;

drop table if exists tb_postagem;

drop table if exists tb_resposta_duvida;

drop table if exists tb_topico;

create table tb_topico_has_seguidores (
    id bigint IDENTITY(1,1) ,
    id_topico bigint,
    id_usuario bigint,
    primary key (id)
)

create table tb_usuario (
   id_usuario bigint IDENTITY(1,1),
    apelido varchar(255) not null,
    avatar varchar(255),
    descricao varchar(255),
    email varchar(255),
    id_github bigint,
    is_ativo bit,
    nome varchar(255) not null,
    senha varchar(255),
    primary key (id_usuario)
)

create table tb_comentario (
   id_comentario bigint IDENTITY(1,1),
    atualizado_em time,
    conteudo varchar(255),
    criado_em time,
    id_usuario bigint,
    id_postagem bigint,
    primary key (id_comentario)
)

create table tb_pontuacao_comentario (
   id_comentario bigint not null,
    id_usuario bigint not null,
    pontuacao integer not null,
    primary key (id_comentario, id_usuario)
)

create table tb_pontuacao_postagem (
   id_postagem bigint not null,
    id_usuario bigint not null,
    pontuacao integer not null,
    primary key (id_postagem, id_usuario)
)

create table tb_postagem (
   id_postagem bigint IDENTITY(1,1),
    atualizado_em time,
    conteudo varchar(255),
    criado_em time,
    titulo varchar(255),
    id_usuario bigint,
    id_topico bigint not null,
    primary key (id_postagem)
)

create table tb_resposta_duvida (
   resolvido bit,
    resolvido_em time,
    id_resposta bigint,
    id_postagem bigint not null,
    primary key (id_postagem)
)

create table tb_topico (
   id_topico bigint IDENTITY(1,1),
    descricao varchar(255) not null,
    nome varchar(255) not null,
    primary key (id_topico)
)

alter table tb_topico_has_seguidores
   add foreign key (id_topico)
   references tb_topico
   on delete cascade

alter table tb_topico_has_seguidores
   add foreign key (id_usuario)
   references tb_usuario

alter table tb_comentario
   add foreign key (id_usuario)
   references tb_usuario

alter table tb_comentario
   add foreign key (id_postagem)
   references tb_postagem

alter table tb_pontuacao_comentario
   add foreign key (id_comentario)
   references tb_comentario
   on delete cascade

alter table tb_pontuacao_comentario
   add foreign key (id_usuario)
   references tb_usuario

alter table tb_pontuacao_postagem
   add foreign key (id_postagem)
   references tb_postagem
   on delete cascade

alter table tb_pontuacao_postagem
   add foreign key (id_usuario)
   references tb_usuario

alter table tb_postagem
   add foreign key (id_usuario)
   references tb_usuario

alter table tb_postagem
   add foreign key (id_topico)
   references tb_topico

alter table tb_resposta_duvida
   add foreign key (id_resposta)
   references tb_comentario
   on delete cascade

alter table tb_resposta_duvida
   add foreign key (id_postagem)
   references tb_postagem