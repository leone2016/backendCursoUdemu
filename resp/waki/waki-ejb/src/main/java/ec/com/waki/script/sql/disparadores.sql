/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  leoz3
 * Created: 03-nov-2018
 */



/* 
 * PARA ACTUALIZAR LOS ARTICULOS CON ESTADO DISPONIBLE = 23
 * ESTE DISPARADOR SE ACTIVA CADA SE SE HAGA ALGÚN CAMBIO EN LA TABLA STOCK_ARTICULO
 * 
 */
CREATE OR REPLACE FUNCTION UPDATE_INVENTARY()
  RETURNS trigger AS
$BODY$
BEGIN
    PERFORM  * from inventario(new.articulo);
RETURN NULL;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION UPDATE_INVENTARY()
  OWNER TO postgres;


CREATE TRIGGER UPDATE_INVENTARY
after  INSERT OR UPDATE ON stock_articulo
FOR EACH ROW
EXECUTE PROCEDURE UPDATE_INVENTARY();
