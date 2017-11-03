#!/usr/bin/env bash
function func1() {
    LOGFILE=$(getProperty crawlerlog_7)
    echo ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" >> ${LOGFILE}
    FECHA="$(date +%Y_%m_%d-%H_%M)"
    FICHERO_DESCARGADO="$(echo $(getProperty datos_en_crudo_temporales_4)"ListadoProductos_"${FECHA}".xls")"
    wget -O $FICHERO_DESCARGADO $(getProperty autorizados_mapama_6) 2>> ${LOGFILE}
    if [ $? -eq 0 ]; then
        FECHAEJECUCION="$(date)"
        echo ${FECHAEJECUCION}": Se ha descargado el fichero con los productos autorizados" >> ${LOGFILE}
        libreoffice --convert-to xls  --outdir $(getProperty crudo_espania_autorizados_5) $FICHERO_DESCARGADO 2>> ${LOGFILE}
        if [ $? -eq 0 ]; then
            FECHAEJECUCION="$(date)"
            echo ${FECHAEJECUCION}": Se ha convertido el fichero viejo" >> ${LOGFILE}
            FICHERO_CONVERTIDO="$(echo $(getProperty crudo_espania_autorizados_5)"ListadoProductos_"${FECHA}".xls")"
            rm $FICHERO_DESCARGADO 2>> ${LOGFILE}
            if [ $? -eq 0 ]; then
                FECHAEJECUCION="$(date)"
                echo ${FECHAEJECUCION}": Se ha borrado el fichero viejo" >> ${LOGFILE}
                return 0;
            else
                echo ${FECHAEJECUCION}": ERROR: No se ha borrado el fichero viejo" >> ${LOGFILE}
                return -3;
            fi
        else
            echo ${FECHAEJECUCION}": ERROR: No se ha convertido el fichero viejo" >> ${LOGFILE}
            return -2;
        fi
    else
        echo ${FECHAEJECUCION}": ERROR: No se ha descargado el fichero con los productos autorizados" >> ${LOGFILE}
        return -1;
    fi

}

function getProperty() {

PROPERTY_FILE=/home/catalin/Escritorio/TFG/TFG/Scripts/fichero_configuracion.properties

if [ $# -ne 1 ]; then
	echo "Uso: getProperty <nombre_propiedad>"
else
	PROP_KEY=$1
	PROP_VALUE=`cat $PROPERTY_FILE | grep $PROP_KEY | cut -d'=' -f2`
	echo $PROP_VALUE
fi
}

func1
