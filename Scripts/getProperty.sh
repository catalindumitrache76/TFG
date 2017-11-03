#!/usr/bin/env bash
PROPERTY_FILE=/home/catalin/Escritorio/TFG/TFG/Scripts/fichero_configuracion.properties


if [ $# -ne 1 ]; then
	echo "Uso: ./fichero_configuracion <nombre_propiedad>"
else
	PROP_KEY=$1
	PROP_VALUE=`cat $PROPERTY_FILE | grep $PROP_KEY | cut -d'=' -f2`
	echo $PROP_VALUE
fi

