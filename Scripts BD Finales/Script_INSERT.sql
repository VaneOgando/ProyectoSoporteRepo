/*MARCA*/

/*MODELO*/

/*ESTADO*/
INSERT INTO ESTADO VALUES (estado_seq.nextval, 'Disponible', 'S');
INSERT INTO ESTADO VALUES (estado_seq.nextval, 'Asignado', 'N');
INSERT INTO ESTADO VALUES (estado_seq.nextval, 'Fuera de inventario', 'S');
INSERT INTO ESTADO VALUES (estado_seq.nextval, 'En reparacion', 'S');
INSERT INTO ESTADO VALUES (estado_seq.nextval, 'Averiado', 'S');


/*CATEGORIA*/
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Asignacion', 'historial');
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Devolucion', 'historial');
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Creacion', 'historial');
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Modificacion', 'historial');
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Fuera de inventario', 'historial');
INSERT INTO CATEGORIA VALUES (categoria_seq.nextval, 'Cambio de estado', 'historial');


/*EQUIPO*/

/*ACCESORIO*/

/*HISTORIAL INVENTARIO*/
