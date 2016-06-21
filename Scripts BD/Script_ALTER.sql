ALTER TABLE ACCESORIO ADD fkEstado 		INT NOT NULL;
ALTER TABLE ACCESORIO ADD fkCategoria	INT NOT NULL; 
ALTER TABLE ACCESORIO ADD fkModelo		INT; 

ALTER TABLE ACCESORIO ADD CONSTRAINT acc_fkEstado 		FOREIGN KEY (fkEstado)		REFERENCES ESTADO (idEstado);
ALTER TABLE ACCESORIO ADD CONSTRAINT acc_fkCategoria	FOREIGN KEY (fkCategoria)	REFERENCES CATEGORIA (idCategoria);
ALTER TABLE ACCESORIO ADD CONSTRAINT acc_fkModelo		FOREIGN KEY (fkModelo)		REFERENCES MODELO (idModelo);


ALTER TABLE CATEGORIA ADD CONSTRAINT tipoCategoriaCheck CHECK (tipoCategoria IN ('accesorio', 'historial'));

/*	
ALTER TABLE DOCUMENTO ADD fkHistorialInv INT NOT NULL;	

ALTER TABLE DOCUMENTO ADD CONSTRAINT doc_fkHistorialInv	FOREIGN KEY (fkHistorialInv)	REFERENCES HISTORIALINVENTARIO (idHistorialInv);
*/

ALTER TABLE EQUIPO ADD fkModelo INT NOT NULL;
ALTER TABLE EQUIPO ADD fkEstado INT NOT NULL;

ALTER TABLE EQUIPO ADD CONSTRAINT equ_fkModelo		FOREIGN KEY (fkModelo)		REFERENCES MODELO (idModelo);
ALTER TABLE EQUIPO ADD CONSTRAINT equ_fkEstado		FOREIGN KEY (fkEstado)		REFERENCES ESTADO (idEstado);


ALTER TABLE HISTORIALINVENTARIO ADD fkCategoria INT NOT NULL;
ALTER TABLE HISTORIALINVENTARIO ADD fkEquipo 	VARCHAR(22);
ALTER TABLE HISTORIALINVENTARIO ADD fkAccesorio INT;

ALTER TABLE HISTORIALINVENTARIO ADD CONSTRAINT hist_fkCategoria		FOREIGN KEY (fkCategoria)	REFERENCES CATEGORIA (idCategoria);
ALTER TABLE HISTORIALINVENTARIO ADD CONSTRAINT hist_fkEquipo		FOREIGN KEY (fkEquipo)		REFERENCES EQUIPO (numSerie);
ALTER TABLE HISTORIALINVENTARIO ADD CONSTRAINT hist_fkAccesorio		FOREIGN KEY (fkAccesorio)	REFERENCES ACCESORIO (idAccesorio);


ALTER TABLE MODELO ADD fkMarca INT NOT NULL;

ALTER TABLE MODELO ADD CONSTRAINT mod_fkMarca	FOREIGN KEY (fkMarca)	REFERENCES MARCA (idMarca);
