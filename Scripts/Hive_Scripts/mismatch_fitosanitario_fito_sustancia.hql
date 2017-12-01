
-- Crea una tabla para almacenar la información de los productos fitosanitarios autorizados de España
-- que no han sido mapeados con las sustancias activas europeas

CREATE TABLE IF NOT EXISTS tfghivedb.mismatch_fitosanitario_fito_sustancia
(
  id int,
  numregistro string,
  nombrecomercial string,
  titular string,
  formulado string
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\\;';

-- Si la tabla existe y ha sido creada, la trunca para poder insertar información nueva.
TRUNCATE TABLE tfghivedb.mismatch_fitosanitario_fito_sustancia;

INSERT INTO TABLE tfghivedb.mismatch_fitosanitario_fito_sustancia
select * from tfghivedb.fitosanitario_con_id where numregistro not in
(select numregistro from tfghivedb.fitosanitario_sustancia_activa_europa);
