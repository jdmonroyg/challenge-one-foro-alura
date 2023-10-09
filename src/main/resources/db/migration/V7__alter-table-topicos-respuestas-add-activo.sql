alter table topicos add activo tinyint;
update topicos set activo=1;
alter table respuestas add activo tinyint;
