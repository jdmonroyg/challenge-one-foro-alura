create table if not exists topicos (

    id bigint not null auto_increment,
    titulo varchar(200) not null unique,
    mensaje varchar(10000) not null,
    fecha datetime not null,
    status varchar(50) not null,
    usuario_id bigint not null,
    curso_id bigint,
    primary key(id),
    constraint fk_topicos_usuario_id foreign key(usuario_id) references usuarios(id),
    constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id)
);