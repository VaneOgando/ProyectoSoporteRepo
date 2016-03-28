/*MARCA*/

INSERT INTO MARCA VALUES (marca_seq.nextval, 'LENOVO');
INSERT INTO MARCA VALUES (marca_seq.nextval, 'TOSHIBA');
INSERT INTO MARCA VALUES (marca_seq.nextval, 'APPLE');
INSERT INTO MARCA VALUES (marca_seq.nextval, 'ASUS');
INSERT INTO MARCA VALUES (marca_seq.nextval, 'HP');
INSERT INTO MARCA VALUES (marca_seq.nextval, 'DELL');
INSERT INTO MARCA VALUES (marca_seq.nextval, 'GENIUS');



/*MODELO*/
INSERT INTO MODELO VALUES (modelo_seq.nextval,'ThinkPad X250', (SELECT idMarca FROM MARCA WHERE marca = 'LENOVO') );
INSERT INTO MODELO VALUES (modelo_seq.nextval,'ThinkPad E450', (SELECT idMarca FROM MARCA WHERE marca = 'LENOVO') );

INSERT INTO MODELO VALUES (modelo_seq.nextval,'Satellite C45-A4113WL', (SELECT idMarca from MARCA WHERE marca = 'TOSHIBA') );
INSERT INTO MODELO VALUES (modelo_seq.nextval,'Satellite C55-B5218KM', (SELECT idMarca from MARCA WHERE marca = 'TOSHIBA') );

INSERT INTO MODELO VALUES (modelo_seq.nextval,'MacBook', (SELECT idMarca from MARCA WHERE marca = 'APPLE') );
INSERT INTO MODELO VALUES (modelo_seq.nextval,'MacBook Pro', (SELECT idMarca from MARCA WHERE marca = 'APPLE') );

INSERT INTO MODELO VALUES (modelo_seq.nextval,'X453MA', (SELECT idMarca from MARCA WHERE marca = 'ASUS') );
INSERT INTO MODELO VALUES (modelo_seq.nextval,'K555LA', (SELECT idMarca from MARCA WHERE marca = 'ASUS') );

INSERT INTO MODELO VALUES (modelo_seq.nextval,'ProBook 5330M', (SELECT idMarca from MARCA WHERE marca = 'HP') );
INSERT INTO MODELO VALUES (modelo_seq.nextval,'ENVY 15-3090la', (SELECT idMarca from MARCA WHERE marca = 'HP') );

INSERT INTO MODELO VALUES (modelo_seq.nextval,'Inspiron 15 serie 7000', (SELECT idMarca from MARCA WHERE marca = 'DELL') );
INSERT INTO MODELO VALUES (modelo_seq.nextval,'XPS 13', (SELECT idMarca from MARCA WHERE marca = 'DELL') );

INSERT INTO MODELO VALUES (modelo_seq.nextval,'Netscroll 120', (SELECT idMarca from MARCA WHERE marca = 'GENIUS') );
INSERT INTO MODELO VALUES (modelo_seq.nextval,'Xscroll', (SELECT idMarca from MARCA WHERE marca = 'GENIUS') );


/*ESTADO*/
INSERT INTO ESTADO VALUES (estado_seq.nextval, 'Disponible', 'S');
INSERT INTO ESTADO VALUES (estado_seq.nextval, 'Asignado', 'N');
INSERT INTO ESTADO VALUES (estado_seq.nextval, 'Fuera de inventario', 'S');
INSERT INTO ESTADO VALUES (estado_seq.nextval, 'En reparacion', 'S');


/*CATEGORIA*/
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Asignacion', 'historial');
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Devolucion', 'historial');
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Creacion', 'historial');
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Modificacion', 'historial');
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Fuera de inventario', 'historial');
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Cambio de estado', 'historial');

INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Mouse', 'accesorio');
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Impresora', 'accesorio');


/*EQUIPO*/
INSERT INTO EQUIPO VALUES ('2UA449P120', 'TP-250', 'Intel Core i5-5200U', '8GB', '1TB', 'Windows 7 Professional', 'Pantalla 12,5". 2 Puertos USB 3.0. Cámara de 720p', '07/21/2015', (SELECT idModelo FROM MODELO WHERE modelo = 'ThinkPad X250'), (SELECT idEstado FROM ESTADO WHERE estado = 'Disponible'));
INSERT INTO EQUIPO VALUES ('1TO420IO10', 'Sat-C45', 'Intel Celeron 1037U', '4GB', '500 GB', 'Windows 8.1', 'Pantalla 14". 2 Puertos USB 2.0 y 1 HDMI. Cámara web y microfono integrado', '04/16/2015', (SELECT idModelo FROM MODELO WHERE modelo = 'Satellite C45-A4113WL'), (SELECT idEstado FROM ESTADO WHERE estado = 'Asignado'));
INSERT INTO EQUIPO VALUES ('8MK318R497', 'HP-533-M', 'Intel Core i3 -2310M', '4GB', '500 GB', 'Windows 7 Profesional', 'Pantalla 13.3". 2 Puertos USB, HDMI y VGA. Unidad de CD. Cámara web y wifi', '01/09/2014', (SELECT idModelo FROM MODELO WHERE modelo = 'ProBook 5330M'), (SELECT idEstado FROM ESTADO WHERE estado = 'Fuera de inventario'));


/*ACCESORIO*/
INSERT INTO ACCESORIO VALUES (accesorio_seq.nextval, NULL, 'Mouse optico Netscroll 120', 'Color negro. Puerto USB. Comoda adaptacion para ambas manos', '08/17/2015', (SELECT idEstado FROM ESTADO WHERE estado = 'Disponible'), (SELECT idCategoria FROM CATEGORIA WHERE categoria = 'Mouse' AND tipoCategoria = 'accesorio'), (SELECT idModelo FROM MODELO WHERE modelo = 'Netscroll 120') );
INSERT INTO ACCESORIO VALUES (accesorio_seq.nextval, NULL, 'Mouse Alambrico Xscroll', 'Ratón óptico de alta precisión con rueda Buttons 3 Port PS/2', '09/18/2015', (SELECT idEstado FROM ESTADO WHERE estado = 'Fuera de inventario'), (SELECT idCategoria FROM CATEGORIA WHERE categoria = 'Mouse' AND tipoCategoria = 'accesorio'), (SELECT idModelo FROM MODELO WHERE modelo = 'Xscroll') );

/*HISTORIAL INVENTARIO*/
INSERT INTO HISTORIALINVENTARIO VALUES (histInv_seq.nextval, '04/20/2015', 'Creacion del equipo Sat-C45', 0000065, 12345678, NULL, (SELECT idCategoria FROM CATEGORIA WHERE categoria = 'Creacion'), (SELECT numSerie FROM EQUIPO WHERE equipo = 'Sat-C45'), NULL );
INSERT INTO HISTORIALINVENTARIO VALUES (histInv_seq.nextval, '04/28/2015', 'Asignacion del equipo Sat-C45', 000097, 12345678, 87654321, (SELECT idCategoria FROM CATEGORIA WHERE categoria = 'Asignacion'), (SELECT numSerie FROM EQUIPO WHERE equipo = 'Sat-C45'), NULL );

INSERT INTO HISTORIALINVENTARIO VALUES (histInv_seq.nextval, '09/19/2015', 'Creacion del accesorio Mouse Alambrico Xscroll', 0000322, 12345678, NULL, (SELECT idCategoria FROM CATEGORIA WHERE categoria = 'Creacion'), NULL, (SELECT idAccesorio FROM ACCESORIO WHERE accesorio = 'Mouse Alambrico Xscroll'));
INSERT INTO HISTORIALINVENTARIO VALUES (histInv_seq.nextval, '10/04/2015', 'Asignacion del accesorio Mouse Alambrico Xscroll', 0000410, 12345678, 87654321, (SELECT idCategoria FROM CATEGORIA WHERE categoria = 'Asignacion'), NULL, (SELECT idAccesorio FROM ACCESORIO WHERE accesorio = 'Mouse Alambrico Xscroll'));
INSERT INTO HISTORIALINVENTARIO VALUES (histInv_seq.nextval, '01/08/2016', 'Se devuelve el mouse por no funcionar producto de un golpe', NULL, 12345678, 87654321, (SELECT idCategoria FROM CATEGORIA WHERE categoria = 'Devolucion'), NULL, (SELECT idAccesorio FROM ACCESORIO WHERE accesorio = 'Mouse Alambrico Xscroll'));
INSERT INTO HISTORIALINVENTARIO VALUES (histInv_seq.nextval, '01/08/2016', 'El accesorio queda inutilizado', NULL, 12345678, NULL, (SELECT idCategoria FROM CATEGORIA WHERE categoria = 'Fuera de inventario'), NULL, (SELECT idAccesorio FROM ACCESORIO WHERE accesorio = 'Mouse Alambrico Xscroll'));