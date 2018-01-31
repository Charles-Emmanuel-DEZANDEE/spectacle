USE SPECTACLE_DB;
GO

CREATE TABLE spectacle(
id INT not null,
titre VARCHAR(200) not null,
artiste VARCHAR(200) not null,
lieu Varchar(200),
date DATE not null,
places_disponible INT not null,
CONSTRAINT PK_ID_SPECTACLE PRIMARY KEY(id)
);
GO

CREATE TABLE client(
id INT not null,
nom VARCHAR(200) not null,
prenom VARCHAR(200) not null,
email VARCHAR(200) not null,
adresse TEXT not null,
code_postal CHAR(5) not null,
ville VARCHAR(200) not null,
CONSTRAINT PK_ID PRIMARY KEY(id)
);
GO

CREATE TABLE reservation(
code_reservation CHAR(20) not null,
spectacle_id INT not null,
client_id INT not null,
nombre_places INT not null,
date_reservation DATETIME not null,
CONSTRAINT PK_CODE_RESERVATION PRIMARY KEY(code_reservation),
CONSTRAINT FK_RESERVATION_SPECTACLE FOREIGN KEY(spectacle_id) REFERENCES spectacle(id),
CONSTRAINT FK_RESERVATION_CLIENT FOREIGN KEY(client_id) REFERENCES client(id)
);
GO