/*CREAR TABLAS*/

CREATE TABLE ACCESORIO(

	idAccesorio 	INT 		NOT NULL,
	numSerie		VARCHAR(11),
	accesorio		VARCHAR(30)	NOT NULL,
	caracteristicas	VARCHAR(100),
	fechaCompra		DATE		NOT NULL,
	
	CONSTRAINT accesorio_pk PRIMARY KEY (idAccesorio)
);

CREATE TABLE CATEGORIA(

	idCategoria   INT 			NOT NULL,
	categoria	  VARCHAR(30)	NOT NULL,
	tipoCategoria VARCHAR(30)	NOT NULL,

	CONSTRAINT categoria_pk PRIMARY KEY (idCategoria)	
);

CREATE TABLE DOCUMENTO(

	idDocumento		INT 		NOT NULL,
	documento 		VARCHAR(30)	NOT NULL,
	archivo			BLOB		NOT NULL,
	
	CONSTRAINT documento_pk PRIMARY KEY (idDocumento)
);

CREATE TABLE EQUIPO(

	numSerie 		VARCHAR(11)	NOT NULL,
	equipo			VARCHAR(30)	NOT NULL,
	procesador		VARCHAR(30)	NOT NULL,
	memoriaRam		VARCHAR(30)	NOT NULL,
	discoDuro		VARCHAR(30)	NOT NULL,
	sistOperativo	VARCHAR(30)	NOT NULL,
	caracteristicas	VARCHAR(100),
	fechaCompra		DATE		NOT NULL,
	
	CONSTRAINT equipo_pk PRIMARY KEY (numSerie)
);

CREATE TABLE ESTADO(

	idEstado	INT			NOT NULL,
	estado		VARCHAR(30)	NOT NULL,
	
	CONSTRAINT estado_pk PRIMARY KEY (idEstado)
);

CREATE TABLE HISTORIALINVENTARIO(

	idHistorialInv	INT				NOT NULL,
	fechaGestion	DATE			NOT NULL,
	descripcion		VARCHAR(100)	NOT NULL,
	idIncidencia	NUMBER(7),
	respSoporte		NUMBER(8)		NOT NULL,
	usuarioAsig		NUMBER(8), 

	CONSTRAINT historialInv_pk PRIMARY KEY (idHistorialInv)
);

CREATE TABLE MARCA(

	idMarca 	INT 		NOT NULL,
	marca 		VARCHAR(30) NOT NULL,

	CONSTRAINT marca_pk PRIMARY KEY (idMarca)
);

CREATE TABLE MODELO(

	idModelo 	INT 		NOT NULL,
	modelo 		VARCHAR(30) NOT NULL,
	
	CONSTRAINT modelo_pk PRIMARY KEY (idModelo)
);


/*CREAR SECUENCIAS*/

CREATE SEQUENCE accesorio_seq;
CREATE SEQUENCE categoria_seq;
CREATE SEQUENCE documento_seq;
CREATE SEQUENCE estado_seq;
CREATE SEQUENCE histInv_seq;
CREATE SEQUENCE marca_seq;
CREATE SEQUENCE modelo_seq;


/*CREAR TRIGGERS*/

CREATE OR REPLACE TRIGGER accesorio_tri BEFORE INSERT ON ACCESORIO FOR EACH ROW

	BEGIN
		SELECT accesorio_seq.NEXTVAL
		INTO :new.idAccesorio
		FROM dual;
	END;			

/	
	
CREATE OR REPLACE TRIGGER categoria_tri BEFORE INSERT ON CATEGORIA FOR EACH ROW

	BEGIN
		SELECT categoria_seq.NEXTVAL
		INTO :new.idCategoria
		FROM dual;
	END;		

/	
	
CREATE OR REPLACE TRIGGER documento_tri BEFORE INSERT ON DOCUMENTO FOR EACH ROW

	BEGIN
		SELECT documento_seq.NEXTVAL
		INTO :new.idDocumento
		FROM dual;
	END;					

/	
	
CREATE OR REPLACE TRIGGER estado_tri BEFORE INSERT ON ESTADO FOR EACH ROW

	BEGIN
		SELECT estado_seq.NEXTVAL
		INTO :new.idEstado
		FROM dual;
	END;	

/
	
CREATE OR REPLACE TRIGGER histInv_tri BEFORE INSERT ON HISTORIALINVENTARIO FOR EACH ROW

	BEGIN
		SELECT histInv_seq.NEXTVAL
		INTO :new.idHistorialInv
		FROM dual;
	END;				

/
	
CREATE OR REPLACE TRIGGER marca_tri BEFORE INSERT ON MARCA FOR EACH ROW

	BEGIN
		SELECT marca_seq.NEXTVAL
		INTO :new.idMarca
		FROM dual;
	END;

/	
	
CREATE OR REPLACE TRIGGER modelo_tri BEFORE INSERT ON MODELO FOR EACH ROW

	BEGIN
		SELECT modelo_seq.NEXTVAL
		INTO :new.idModelo
		FROM dual;
	END;

/	