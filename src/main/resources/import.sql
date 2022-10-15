insert into tb_usuario (nome, apelido, email, senha, avatar) values ("1", "te1", "teste1@teste.com", "12345678", 1);
insert into tb_usuario (nome, apelido, email, senha, avatar) values ("2", "te2", "teste2@teste.com", "12345678", 1);
insert into tb_usuario (nome, apelido, email, senha, avatar) values ("3", "te3", "teste3@teste.com", "12345678", 1);
insert into tb_topico (nome, descricao) values ("testeee1", "testesssasasaas");
insert into tb_topico (nome, descricao) values ("testeee2", "testesssasasaas");
insert into tb_topico (nome, descricao) values ("testeee3", "testesssasasaas");

insert into tb_postagem (id_postagem, titulo, conteudo, id_usuario, id_topico) values (1, "titulos", "conteudo", 1 , 1);
insert into tb_pontuacao_postagem (postagem_id_postagem, usuario_id_usuario, pontuacao) values (1, 1, -1);
insert into tb_pontuacao_postagem (postagem_id_postagem, usuario_id_usuario, pontuacao) values (1, 2, 1);
insert into tb_pontuacao_postagem (postagem_id_postagem, usuario_id_usuario, pontuacao) values (1, 3, -1);


insert into tb_topico_has_seguidores (id_usuario, id_topico) values (1, 1);
insert into tb_topico_has_seguidores (id_usuario, id_topico) values (1, 2);
insert into tb_topico_has_seguidores (id_usuario, id_topico) values (1, 3);
insert into tb_topico_has_seguidores (id_usuario, id_topico) values (2, 1);
insert into tb_topico_has_seguidores (id_usuario, id_topico) values (2, 2);
insert into tb_topico_has_seguidores (id_usuario, id_topico) values (3, 1);

insert into tb_comentario (conteudo, id_usuario, id_postagem) values ("oiii", 1, 1);
insert into tb_comentario (conteudo, id_usuario, id_postagem) values ("oiii2", 2, 1);
insert into tb_comentario (conteudo, id_usuario, id_postagem) values ("oiii3", 3, 1);