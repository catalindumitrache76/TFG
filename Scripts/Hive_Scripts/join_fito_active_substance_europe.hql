-- Query que hace el JOIN entre la columna sustancia_activa_europea.name y fitosanitario.formulado y se queda con:
--    - fitosanitario.numregistro
--    - fitosanitario.nombrecomercial
--    - fitosanitario.titular
--    - fitosanitario.formulado
--    - sustancia_activa_europea.real_id (el id de la sustancia activa correspondiente a ese formulado)

-- Esto es posible porque el JOIN lo realiza truncando el formulado y quedándose con el primer string que encuentra,
-- que generalmente se corresponde con el la sustancia activa.

WITH form_split AS (SELECT UPPER(SPLIT(fci.formulado,'[\(1234567890 ]')[0]) formuladosplitted, fci.id, fci.numregistro,
fci.nombrecomercial, fci.titular, fci.formulado FROM tfghivedb.fitosanitario_con_id fci), name_full AS
(SELECT UPPER(sae.name) name, sae.real_id FROM tfghivedb.sustancia_activa_europa sae)
SELECT form_split.id, form_split.numregistro, form_split.nombrecomercial, form_split.titular, form_split.formulado,
name_full.real_id FROM form_split, name_full WHERE form_split.formuladosplitted=name_full.name;


-- PROBLEMAS:
--    - Hay fitosanitarios_con_id que tienen mas de una sustancia activa y la query solo reconoce la primera palabra.
--    - Hay fitosanitarios_con_id cuyas sustancias activas están en español y la query no los reconoce.
