
-- Crea una tabla si no existe para almacenar la información de las sustancias activas europeas
-- que no han sido mapeadas con los productos fitosanitarios autorizados de españa

CREATE TABLE IF NOT EXISTS tfghivedb.mismatch_sustancia_fito_sustancia
(
  id int,
  real_id string,
  tipo string,
  language string,
  name string
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\\;';

-- Si la tabla existe y ha sido creada, la trunca para poder insertar información nueva.
TRUNCATE TABLE tfghivedb.mismatch_sustancia_fito_sustancia;

-- Almacena la información de las sustancias activas europeas que no han sido mapeadas con
-- los productos fitosanitarios autorizados de españa
INSERT INTO TABLE tfghivedb.mismatch_sustancia_fito_sustancia
select * from tfghivedb.sustancia_activa_europa where real_id not in
(select activesubstanceid from tfghivedb.fitosanitario_sustancia_activa_europa);
