drop database if exists colegio;
create database colegio;
use colegio;
create table cursos(
    id int primary key auto_increment,
    titulo varchar(20) not null,
    profesor varchar(20) not null,
    dia varchar(20),
    turno varchar(20)
);
create table alumnos(
    id int primary key auto_increment,
    nombre varchar(20),
    apellido varchar(20),
    edad int,
    curso int
);