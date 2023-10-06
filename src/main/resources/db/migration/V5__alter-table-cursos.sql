alter table cursos drop column categoria;
alter table cursos add column categoria varchar(100) not null;