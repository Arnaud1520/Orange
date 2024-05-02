drop database if exists orange_efrei;
create database orange_efrei;
use orange_efrei;

create table client (
    idclient int(3) not null auto_increment,
    nom varchar(50),
    prenom varchar(50),
    adresse varchar(50),
    email varchar(50),
    primary key (idclient)
);

create table produit (
    idproduit int(3) not null auto_increment,
    designation varchar (50),
    prixAchat float,
    dateAchat date,
    categorie enum("Téléphone", "Informatique", "Télévision"),
    idclient int (3) not null,
    primary key (idproduit),
    foreign key (idclient) references client (idclient)
);

create table technicien (
    idtechnicien int(3) not null auto_increment,
    nom varchar(50),
    prenom varchar(50),
    specialite varchar(50),
    dateEmbauche date,
    primary key (idtechnicien)
);

create table intervention (
    idinter int(3) not null auto_increment,
    description text,
    prixInter float,
    dateInter date,
    idproduit int(3) not null,
    idtechnicien int(3) not null,
    primary key (idinter),
    foreign key (idproduit) references produit(idproduit),
    foreign key (idtechnicien) references technicien(idtechnicien)

);