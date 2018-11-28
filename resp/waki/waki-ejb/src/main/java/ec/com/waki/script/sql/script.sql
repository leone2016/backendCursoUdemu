/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  leoz3
 * Created: 01-nov-2018
 */

copy articulo (estado, nombre, descripcion_corta, descripcion_larga,color, codigo_importacion, empresa, fecha_registro, usuario_logeado, tipo ) 
from 'E:/pedido.csv' delimiter ';' csv header; --importar

copy descripcion_articulo (estado, articulo, descripcion, detalle ) 
from 'E:/detalle_articulo.csv' delimiter ';' csv header; --importar 

TO 'E:\pedido.csv' delimiters ';' WITH CSV HEADER; -- exportar 

