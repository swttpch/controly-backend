insert into tb_usuario (nome, apelido, email, senha, avatar, is_ativo) values ('ADMINISTRADOR', 'adm', 'adm@teste.com', '12345678', 1, true);
insert into tb_usuario (nome, apelido, email, senha, avatar, is_ativo) values ('MODERADOR', 'mod', 'mod@teste.com', '12345678', 1, true);
insert into tb_usuario (nome, apelido, email, senha, avatar, is_ativo) values ('USUARIO', 'usr', 'usr@teste.com', '12345678', 1, true);
insert into tb_usuario (nome, apelido, email, senha, avatar, is_ativo) values ('Donilo', 'Don616', 'donilo.pontes@bandtec.com.br', '12345678', 5, true);
insert into tb_usuario (nome, apelido, email, senha, avatar, is_ativo) values ('Hanna', 'H4nn4', 'hanna.santos@bandtec.com.br', '12345678', 4, true);
insert into tb_usuario (nome, apelido, email, senha, avatar, is_ativo) values ('Letícia', 'Let1ci4', 'leticia.costa@bandtec.com.br', '12345678', 3, true);
insert into tb_usuario (nome, apelido, email, senha, avatar, is_ativo) values ('Igor', '1g0r', 'igor.gsouza@bandtec.com.br', '12345678', 7, true);
insert into tb_usuario (nome, apelido, email, senha, avatar, is_ativo) values ('Matheus', 'M4th3us', 'matheus.cantero@bandtec.com.br', '12345678', 10, true);
insert into tb_usuario (nome, apelido, email, senha, avatar, is_ativo) values ('Lucas', 'Lacerda', 'lucas.lacerda@bandtec.com.br', '12345678', 8, true);




insert into tb_topico (nome, descricao) values ('Flutter', 'Um kit de desenvolvimento que blá blá');
insert into tb_topico (nome, descricao) values ('Java', 'Um kit de desenvolvimento que blá blá');
insert into tb_topico (nome, descricao) values ('React', 'Um kit de desenvolvimento que blá blá');
insert into tb_topico (nome, descricao) values ('JS', 'Um kit de desenvolvimento que blá blá');
insert into tb_topico (nome, descricao) values ('Html', 'Um kit de desenvolvimento que blá blá');
insert into tb_topico (nome, descricao) values ('Php', 'Um kit de desenvolvimento que blá blá');
insert into tb_topico (nome, descricao) values ('Spring', 'Um kit de desenvolvimento que blá blá');
insert into tb_topico (nome, descricao) values ('Laravel', 'Um kit de desenvolvimento que blá blá');
insert into tb_topico (nome, descricao) values ('Mysql', 'Um kit de desenvolvimento que blá blá');
insert into tb_topico (nome, descricao) values ('Css', 'Um kit de desenvolvimento que blá blá');


insert into tb_postagem ( titulo, conteudo, id_usuario, id_topico) values ('Como posso resolver este bug no java?', 'Como posso resolver est', 4 , 1);
insert into tb_postagem ( titulo, conteudo, id_usuario, id_topico) values ('Não consigo aprender REACT', 'xxxxxxxxxxxxxx', 5 , 1);
insert into tb_postagem ( titulo, conteudo, id_usuario, id_topico) values ('Me ajude com o Git!', 'xxxxxxxxxxxxxx', 6 , 1);
insert into tb_postagem ( titulo, conteudo, id_usuario, id_topico) values ('O que é nullpointerexception?', 'xxxxxxxxxxxxxx', 7 , 1);
insert into tb_postagem ( titulo, conteudo, id_usuario, id_topico) values ('Como faz para fazer delete sem where?', 'xxxxxxxxxxxxxx', 8 , 1);
insert into tb_postagem ( titulo, conteudo, id_usuario, id_topico) values ('O que é memória RAM?', 'xxxxxxxxxxxxxx', 9 , 1);
insert into tb_postagem ( titulo, conteudo, id_usuario, id_topico) values ('Duvidas com Laravel', 'cxxxxxxxxxxxxxx', 2 , 1);
insert into tb_postagem (titulo, conteudo, id_usuario, id_topico) values ('Não estou entendendo este código', 'xxxxxxxxxxxxxx', 8 , 1);
insert into tb_postagem ( titulo, conteudo, id_usuario, id_topico) values ('Quanto ganha um engenheiro DevOps?', 'xxxxxxxxxxxxxx', 7 , 1);
insert into tb_postagem (titulo, conteudo, id_usuario, id_topico) values ('Qual cor melhor pra esta tela?', 'xxxxxxxxxxxxxx', 5 , 1);
insert into tb_postagem ( titulo, conteudo, id_usuario, id_topico) values ('Onde posso estudar?', 'xxxxxxxxxxxxxx', 4 , 1);



insert into tb_pontuacao_postagem (id_postagem, id_usuario, pontuacao) values (1, 1, -1);
insert into tb_pontuacao_postagem (id_postagem, id_usuario, pontuacao) values (1, 2, 1);
insert into tb_pontuacao_postagem (id_postagem, id_usuario, pontuacao) values (1, 3, -1);
insert into tb_pontuacao_postagem (id_postagem, id_usuario, pontuacao) values (2, 1, 1);
insert into tb_pontuacao_postagem (id_postagem, id_usuario, pontuacao) values (2, 2, 1);
insert into tb_pontuacao_postagem (id_postagem, id_usuario, pontuacao) values (2, 3, -1);
insert into tb_pontuacao_postagem (id_postagem, id_usuario, pontuacao) values (3, 1, 1);
insert into tb_pontuacao_postagem (id_postagem, id_usuario, pontuacao) values (3, 2, 1);
insert into tb_pontuacao_postagem (id_postagem, id_usuario, pontuacao) values (3, 3, 1);

insert into tb_topico_has_seguidores (id_usuario, id_topico) values (1, 1);
insert into tb_topico_has_seguidores (id_usuario, id_topico) values (1, 2);
insert into tb_topico_has_seguidores (id_usuario, id_topico) values (1, 3);
insert into tb_topico_has_seguidores (id_usuario, id_topico) values (2, 1);
insert into tb_topico_has_seguidores (id_usuario, id_topico) values (2, 2);
insert into tb_topico_has_seguidores (id_usuario, id_topico) values (3, 1);

insert into tb_comentario (conteudo, id_usuario, id_postagem) values ('Comentário1 na postagem1', 1, 1);
insert into tb_comentario (conteudo, id_usuario, id_postagem) values ('Comentário2 na postagem1', 2, 1);
insert into tb_comentario (conteudo, id_usuario, id_postagem) values ('Comentário3 na postagem1', 3, 1);
insert into tb_comentario (conteudo, id_usuario, id_postagem) values ('Comentário1 na postagem2', 1, 2);
insert into tb_comentario (conteudo, id_usuario, id_postagem) values ('Comentário2 na postagem2', 2, 2);
insert into tb_comentario (conteudo, id_usuario, id_postagem) values ('Comentário1 na postagem3', 1, 3);
insert into tb_comentario (conteudo, id_usuario, id_postagem) values ('Comentário2 na postagem3', 2, 3);
insert into tb_comentario (conteudo, id_usuario, id_postagem) values ('Comentário3 na postagem3', 3, 3);
insert into tb_comentario (conteudo, id_usuario, id_postagem) values ('Comentário4 na postagem3', 3, 3);

insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (1, 1);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (1, 2);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (1, 3);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (2, 1);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (2, 2);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (2, 3);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (3, 1);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (3, 2);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (3, 3);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (4, 1);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (4, 2);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (4, 3);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (5, 1);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (5, 2);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (5, 3);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (6, 1);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (6, 2);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (6, 3);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (7, 1);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (7, 2);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (7, 3);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (8, 1);
insert into tb_pontuacao_comentario(id_comentario, id_usuario) values (8, 2);

insert into tb_resposta_duvida (resolvido, resolvido_em, id_resposta, id_postagem) values (1, '2022-10-22', 8, 3);
