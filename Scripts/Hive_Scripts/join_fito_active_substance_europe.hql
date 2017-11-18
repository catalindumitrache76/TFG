-- Query que hace el JOIN entre la columna sustancia_activa_europea.name y fitosanitario.formulado y se queda con:
--    - fitosanitario.numregistro
--    - fitosanitario.nombrecomercial
--    - fitosanitario.titular
--    - fitosanitario.formulado
--    - sustancia_activa_europea.real_id (el id de la sustancia activa correspondiente a ese formulado)

-- Esto es posible porque el JOIN lo realiza truncando el formulado y quedándose con el primer string que encuentra,
-- que generalmente se corresponde con el la sustancia activa.

WITH form_split AS (SELECT UPPER(SPLIT(fci.formulado,'[\(1234567890 ]')[0]) formuladosplitted, fci.numregistro,
fci.nombrecomercial, fci.titular, fci.formulado FROM fitosanitario_con_id fci), name_full AS
(SELECT UPPER(sae.name) name, sae.real_id FROM sustancia_activa_europa sae)
SELECT form_split.numregistro, form_split.nombrecomercial, form_split.titular, form_split.formulado,
name_full.real_id FROM form_split, name_full WHERE form_split.formuladosplitted=name_full.name;
