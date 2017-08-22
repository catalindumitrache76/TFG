#!/usr/bin/env bash
LOGFILE="/home/catalin/Escritorio/TFG/TFG/Crawlers_Scripts/crawlerlog.log"
FECHA="$(date +%Y_%m_%d-%H_%M)"
FICHERO_DESCARGADO="$(echo "/home/catalin/Escritorio/TFG/Productos_fitosanitarios/España/Productos_autorizados/ListadoProductos_"${FECHA}".xls")"
wget -O $FICHERO_DESCARGADO http://www.mapama.gob.es/es/agricultura/temas/sanidad-vegetal/productos-fitosanitarios/registro/productos/ListadoProductos.asp 2>> ${LOGFILE}
if [ $? -eq 0 ]; then
    FECHAEJECUCION="$(date)"
    echo ${FECHAEJECUCION}": Se ha descargado el fichero con los productos autorizados" >> ${LOGFILE}
else
    echo ${FECHAEJECUCION}": ERROR: No se ha descargado el fichero con los productos autorizados" >> ${LOGFILE}
fi
libreoffice --convert-to ods  --outdir /home/catalin/Escritorio/TFG/Productos_fitosanitarios/España/Productos_autorizados $FICHERO_DESCARGADO 2>> ${LOGFILE}
if [ $? -eq 0 ]; then
    FECHAEJECUCION="$(date)"
    echo ${FECHAEJECUCION}": Se ha convertido el fichero viejo" >> ${LOGFILE}
else
    echo ${FECHAEJECUCION}": ERROR: No se ha convertido el fichero viejo" >> ${LOGFILE}
fi
FICHERO_CONVERTIDO="$(echo "/home/catalin/Escritorio/TFG/Productos_fitosanitarios/España/Productos_autorizados/ListadoProductos_"${FECHA}".ods")"
rm $FICHERO_DESCARGADO 2>> ${LOGFILE}
if [ $? -eq 0 ]; then
    FECHAEJECUCION="$(date)"
    echo ${FECHAEJECUCION}": Se ha borrado el fichero viejo" >> ${LOGFILE}
else
    echo ${FECHAEJECUCION}": ERROR: No se ha borrado el fichero viejo" >> ${LOGFILE}
fi
/home/catalin/Escritorio/TFG/HADOOP/hadoop-2.8.0/bin/hadoop fs -put $FICHERO_CONVERTIDO /user/TFG/Datos_en_crudo/España/Productos_autorizados 2>> ${LOGFILE}
if [ $? -eq 0 ]; then
    FECHAEJECUCION="$(date)"
    echo ${FECHAEJECUCION}": Se ha copiado el fichero en Hadoop. " >> ${LOGFILE}
else
    echo ${FECHAEJECUCION}": ERROR: No se ha copiado el fichero en Hadoop. Código: "$? >> ${LOGFILE}
fi
