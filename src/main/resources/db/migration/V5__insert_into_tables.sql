--População basica das tabelas para possibilitar as queries basicas do projeto.
-- =========================
-- USUARIO (login do sistema)
-- senha = 123456 (BCrypt)
-- =========================
INSERT INTO usuarios (login, senha)
VALUES (
    'admin',
    '$2a$12$zkKgm1I0OlTu4l5kq.wRk.5HlMl2la/zG/3m.5b06csUhzCIy2NZq'
);

-- =========================
-- AUTOR (quem cria tópicos)
-- =========================
INSERT INTO autores (nome, email, senha)
VALUES (
    'Autor Teste',
    'autor@forumhub.com',
    '$2a$10$7EqJtq98hPqEX7fNZaFWoOhi0VQzC2nFQ0gGk7l5K6p3G6Q9y1P7a'
);

-- =========================
-- CURSO
-- =========================
INSERT INTO cursos (nome, categoria, ativo)
VALUES (
    'Spring Boot',
    'Programação',
    true
);

-- =========================
-- TÓPICO
-- =========================
INSERT INTO topicos (titulo, mensagem, data_criacao, estado, autor_id, curso_id)
VALUES (
    'Dúvida sobre Spring Boot',
    'Como funciona o @RestController?',
    NOW(),
    'ATIVADO',
    1,
    1
);