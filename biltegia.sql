DROP DATABASE IF EXISTS biltegia;
CREATE DATABASE biltegia;

USE biltegia;

CREATE TABLE langilea (
	nan				CHAR(9),
	izena			VARCHAR(16) NOT NULL,
	abizena			VARCHAR(16),
	erabiltzailea	VARCHAR(16) NOT NULL UNIQUE,
	pass			CHAR(70) NOT NULL,
	rol				VARCHAR(16),
	
	CONSTRAINT PK_LANGILEA PRIMARY KEY (nan)
);

CREATE TABLE hornitzailea (
	cif			CHAR(9),
	izena		VARCHAR(16) NOT NULL,
	telefonoa	CHAR(9) NOT NULL,
	helbidea	VARCHAR(64),
	
	CONSTRAINT PK_HORNITZAILEA PRIMARY KEY (cif)
);

CREATE TABLE bezeroa (
	idBezeroa	INT AUTO_INCREMENT,
	cif			CHAR(9),
	izena		VARCHAR(16) NOT NULL,
	telefonoa 	CHAR(9) NOT NULL,
	
	CONSTRAINT PK_BEZEROA PRIMARY KEY (idBezeroa)
);

CREATE TABLE kutxa_mota (
	motaId			TINYINT UNSIGNED,
	lehengaiaId		CHAR(2),
	deskribapena	VARCHAR(64) NOT NULL,
	prezioa			DECIMAL(4,2)NOT NULL,
	argazkia		VARCHAR(128),
	
	CONSTRAINT PK_KUTXA_MOTA PRIMARY KEY (motaId, lehengaiaId)
);

CREATE TABLE eskaera_hornitzailea (
	eskaeraId		SMALLINT UNSIGNED AUTO_INCREMENT,
	jasotze_data	DATE NOT NULL,
	arduraduna		CHAR(9),
	hornitzailea	CHAR(9),
	
	CONSTRAINT PK_ESKAERA_HORNITZAILE PRIMARY KEY (eskaeraId)
);

CREATE TABLE lehengaia (
	lehengaiaId		CHAR(2),
	deskribapena	VARCHAR(64) NOT NULL,
    img             MEDIUMBLOB,
	
	CONSTRAINT PK_LEHENGAIA PRIMARY KEY (lehengaiaId)
);

CREATE TABLE intzidentzia (
	sortze_data		DATE,
	sortze_ordua	TIME,
	tenperatura		DECIMAL(4,2) NOT NULL,
	
	CONSTRAINT PK_INTZIDENTZIA PRIMARY KEY (sortze_data,sortze_ordua)
);

CREATE TABLE tenperatura (
	sortze_ordua	TIME,
	tenperatura		DECIMAL(4,2) NOT NULL,
	
	CONSTRAINT PK_TENPERATURA PRIMARY KEY (sortze_ordua)
) ENGINE = Memory;

CREATE TABLE tenperatura_BatazBesteko (
	sortze_data		DATE,
	sortze_ordua	TIME,
	tenperatura		DECIMAL(4,2) NOT NULL,
	
	CONSTRAINT PK_TENPERATURA_BATAZBESTEKO PRIMARY KEY (sortze_data,sortze_ordua)
);

CREATE TABLE eskaera_onartzeko (
	eskaeraId		INT UNSIGNED AUTO_INCREMENT,
	sortze_data		DATE NOT NULL,
	helbidea		VARCHAR(64) NOT NULL,
	bezeroa			INT,
	
	CONSTRAINT PK_ESKAERA_ONARTZEKO PRIMARY KEY (eskaeraId)
);

CREATE TABLE eskaera_onartuta (
	eskaeraId		INT UNSIGNED,
	sortze_data		DATE NOT NULL,
	helbidea		VARCHAR(64) NOT NULL,
	bezeroa			INT,
	arduraduna 		CHAR(9),
	
	CONSTRAINT PK_ESKAERA_ONARTUTA PRIMARY KEY (eskaeraId)
);

CREATE TABLE lerroak_eskaera_hornitzaile (
	eskaeraId		SMALLINT UNSIGNED,
	lehengaiaId		CHAR(2),
	pezioa			DECIMAL(4,2) NOT NULL,
	pisua			DECIMAL(5,2) NOT NULL,
	
	CONSTRAINT PK_LERROAK_ESKAERA_HORNITZAILE PRIMARY KEY (eskaeraId,lehengaiaId)
);

CREATE TABLE lerroak_onartuta (
	motaId			TINYINT UNSIGNED,
	lehengaiaId		CHAR(2),
	eskaeraId		INT UNSIGNED,
	pezioa			DECIMAL(4,2) NOT NULL,
	kopurua			TINYINT UNSIGNED NOT NULL,
	
	CONSTRAINT PK_LERROAK_ONARTUTA PRIMARY KEY (motaId, eskaeraId, lehengaiaId)
);

CREATE TABLE lerroak_onartzeko (
	motaId			TINYINT UNSIGNED,
	lehengaiaId		CHAR(2),
	eskaeraId		INT UNSIGNED,
	pezioa			DECIMAL(4,2) NOT NULL,
	kopurua			TINYINT UNSIGNED NOT NULL,
	
	CONSTRAINT PK_LERROAK_ONARTZEKO PRIMARY KEY (motaId, eskaeraId, lehengaiaId)
);

CREATE TABLE kutxa (
	kutxaId				INT UNSIGNED AUTO_INCREMENT,
	motaId				TINYINT UNSIGNED,
	lehengaiaId			CHAR(2),
	jatorrizko_eskaera	SMALLINT UNSIGNED,
	
	CONSTRAINT PK_KUTXA PRIMARY KEY (kutxaId, motaId, lehengaiaId)
);

CREATE TABLE kutxa_salduta (
	kutxaId				INT UNSIGNED,
	motaId				TINYINT UNSIGNED,
	lehengaiaId			CHAR(2),
	jatorrizko_eskaera	SMALLINT UNSIGNED,
	saldutako_eskaera	INT UNSIGNED,
	
	CONSTRAINT PK_KUTXA_SALDUTA PRIMARY KEY (kutxaId, motaId, lehengaiaId)
);

ALTER TABLE eskaera_hornitzailea 
ADD CONSTRAINT FK_LANGILEA_ESKAERA_HORNITZAILEA FOREIGN KEY (arduraduna) REFERENCES langilea (nan),
ADD CONSTRAINT FK_HORNITZAILEA_ESKAERA_HORNITZAILEA FOREIGN KEY (hornitzailea) REFERENCES hornitzailea (cif);

ALTER TABLE eskaera_onartuta 
ADD CONSTRAINT FK_BEZEROA_ESKAERA_ONARTUTA FOREIGN KEY (bezeroa) REFERENCES bezeroa (idBezeroa),
ADD CONSTRAINT FK_LANGILEA_ESKAERA_ONARTUTA FOREIGN KEY (arduraduna) REFERENCES langilea (nan);

ALTER TABLE eskaera_onartzeko 
ADD CONSTRAINT FK_BEZEROA_ESKAERA_ONARTZEKO FOREIGN KEY (bezeroa) REFERENCES bezeroa (idBezeroa);

ALTER TABLE kutxa_mota
ADD CONSTRAINT FK_LEHENGAIA_KUTXA_MOTA FOREIGN KEY (lehengaiaId) REFERENCES lehengaia (lehengaiaId);

ALTER TABLE kutxa
ADD CONSTRAINT FK_KUTXA_MOTA_KUTXA FOREIGN KEY (motaId, lehengaiaId) REFERENCES kutxa_mota (motaId, lehengaiaId);

ALTER TABLE kutxa_salduta
ADD CONSTRAINT FK_KUTXA_MOTA_KUTXA_SALDUTA FOREIGN KEY (motaId, lehengaiaId) REFERENCES kutxa_mota (motaId, lehengaiaId),
ADD CONSTRAINT FK_ESKAERA_ONARTUTA_KUTXA_SALDUTA FOREIGN KEY (saldutako_eskaera) REFERENCES eskaera_onartuta (eskaeraId);

ALTER TABLE lerroak_onartuta
ADD CONSTRAINT FK_KUTXA_MOTA_LERROAK_ONARTUTA FOREIGN KEY (motaId, lehengaiaId) REFERENCES kutxa_mota (motaId, lehengaiaId),
ADD CONSTRAINT FK_ESKAERA_LERROAK_ONARTUTA FOREIGN KEY (eskaeraId) REFERENCES eskaera_onartuta (eskaeraId);

ALTER TABLE lerroak_onartzeko
ADD CONSTRAINT FK_KUTXA_MOTA_LERROAK_ONARTZEKO FOREIGN KEY (motaId, lehengaiaId) REFERENCES kutxa_mota (motaId, lehengaiaId),
ADD CONSTRAINT FK_ESKAERA_LERROAK_ONARTZEKO FOREIGN KEY (eskaeraId) REFERENCES eskaera_onartzeko (eskaeraId);

ALTER TABLE lerroak_eskaera_hornitzaile
ADD CONSTRAINT FK_HORNITZAILEA_LERROAK_ESKAERA_HORNITZAILE FOREIGN KEY (eskaeraId) REFERENCES eskaera_hornitzailea (eskaeraId),
ADD CONSTRAINT FK_LEHENGAIA_LERROAK_ESKAERA_HORNITZAILE FOREIGN KEY (lehengaiaId) REFERENCES lehengaia (lehengaiaId);
