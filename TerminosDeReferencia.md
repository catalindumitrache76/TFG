# Fitosanitarios

## Propósito de este documento

* Indicar la motivación y los objetivos del proyecto.
* Describir en líneas generales el alcance del proyecto.
* Indicar cualquier dependencia y limitación ya conocida.
* Identificar los recursos requeridos para la siguiente fase del TFG.
* Justificar y asegurar el apoyo para llevar a cabo la siguiente fase del TFG.

## Aprobación

| Persona | Rol | Fecha |
|---------|-----|-------|
| Francisco J. Lopez-Pellicer | Director | TBD

## Motivación

La [Directiva 2009/128/CE](http://eur-lex.europa.eu/LexUriServ/LexUriServ.do?uri=OJ:L:2009:309:0071:0086:es:PDF) establece el marco de la actuación comunitaria para conseguir un uso sostenible de los productos fitosanitarios. Esta Directiva implica, por ejemplo, la obligacin del registro del uso de productos fitosanitarios. Un ejemplo del desarrollo de esta Directiva es el [Cuaderno de Explotación](http://www.mapama.gob.es/es/prensa/noticias/el-ministerio-de-agricultura-alimentaci%C3%B3n-y-medio-ambiente-aprueba-un-modelo-armonizado-de-cuaderno-de-explotaci%C3%B3n-de-los-productos-fitosanitarios--/tcm7-311275-16). Este cuaderno aglutina de manera ordenada y armonizada todos los elementos que deberán registrar los titulares de las explotaciones agrícolas, con el objetivo de facilitar el cumplimiento de la Directiva. 

Actualmente, hay varias empresas que ofren aplicaciones (p. ej. [aGROSLab](http://www.cuadernoexplotacion.es/)) que implementan el Cuaderno de Explotación. Un valor añadido que suelen incorporar estas aplicaciones es una base de datos con información acerca de los productos fitosanitarios autorizados. El problema al que se enfrentan estas empresas, no solo en España, es que esta información no se publica de forma uniforme en toda la Unión Europea. Es decir, hay al menos un publicador por país miembro, la información publicada es heterogénea y los formatos normalmente son dificilmente accesibles. Además, a nivel Europeo, hay una [base de datos de referencia](http://ec.europa.eu/food/plant/pesticides/eu-pesticides-database/public/?event=homepage&language=EN) de las restricciones más o menos comunes en el uso de productos fitosantiarios. Dado que no hay un estándar de publicación establecido, una consecuencia adicional de esta situación es que es complicado verificar si un producto tratado con una serie de productos fitosanitarios en un pais miembro puede ser exportado a otro, ya que la normativa fitosanitaria aplicable puede diferir. 

Es razonable integrar todas estas fuentes en una única fuente para su consumo posterior por las aplicaciones, e, incluso, directamente por los agricultores. Sin embargo, nos enfrentamos a los siguentes retos:
* *Datos*. La información sobre productos fitosanitarios no está habitualmente disponible en un formato estructurado. Es decir, la solución debe poder trabajar con datos en formatos no estructurados.
* *Esquema*. No existe un esquema de referencia compartido para integrar la información de productos fitosantitarios de diferentes países, pudiendo darse el caso de la existencia de varios esquemas distintos en función del caso de uso. Es decir, la solución debe poder reconfigurar el esquema de integración con facilidad. 
* *Procesado*. Derivado del reto anterior, los datos no se pueden procesar y almacenar directamente en el esquema de integración. Es decir, los datos deben guardarse en el formato original y ser procesados bajo demanda teniendo en cuenta las caractersticas especfcias de cada fuente.
* *Almacenamiento*. No se dispone de un presupuesto para invertir en un gran sistema de almacenamiento que permita almacenar los datos en formato original. ES decir, toda solución deberá ser de código abierto y poder ejecutar sobre hardware de bajo coste.
* *Agilidad*. Por todo lo anterior, La solución debe poder evolucionar con facilidad. Es decir, cualquier desarrollador que utilice la solución debe poder reconfigurar rápidamente el esquema común y los servicios que exponen dicho esquema para su uso.

## Objetivos

El proyecto tiene como objetivo principal desarrollar una solución relacionada con la integración de información sobre productos fitosantiarios:

- Recolectar periódicamente y almacenar los datos publicados sobre productos fitosanitarios de diversas fuentes; estos se almacenarán en su formato original hasta que sean necesitados.
- Monitorizar y gestionar los procesos de recolección de datos y su ciclo de vida.
- Facilitar a los desarrolladores que, en función de las necesidades de un escenario de aplicación, puedan implementar rápidamente un modelo de integración y los procesos para poblarlo.
- Facilitar a los desarrolladores que, en función de las necesidades de una aplicación, puedan implementar rápidamente una serie de servicios sobre un modelo de integración.
- Definir e implentar un escenario de aplicación de ejemplo que demuestre la validez de la estrategia de integración seguida. 

Esencialmente la mayor parte de los objetivos anterioremente expresados se corresponderían con caractersticas de un [Data Lake](https://martinfowler.com/bliki/DataLake.html). 

## Alcance de la solución

**Debe tener**: Son aquellos requisitos críticos para que el trabajo realizado durante un periodo de tiempo determinado (en nuestro caso desde ahora hasta junio 2017) sea considerado un éxito (TFG aprobado). Si uno de estos requisitos no se incluye, el proyecto debera ser considerado un fallo (no se puede presentar el TFG o el TFG es suspendido). **Debe tener** en [MoSCoW](https://en.wikipedia.org/wiki/MoSCoW_method) es **MUST** que puede ser considerado un acrónimo de **M**inimum **U**sable **S**ubse**T**. En ese sentido se puede entender como la unión de los requisitos del [producto mínimo viable](https://en.wikipedia.org/wiki/Minimum_viable_product) con los requisitos legales (p.ej. documentación en forma de memoria de TFG, cumplimiento LOPD, etc.) y de seguridad (en el sentido de robustez y calidad de la solución) obligatorios o acordados. 

**Debería tener**: Son aquellos que son importantes pero no necesarios para ser realizados durante el periodo de tiempo determinado. Pueden posponerse para ser realizado en el siguiente periodo. Son vitales pero no vitales, si no se implementan la solución es viable pero es doloroso no hacerlo (no se alcanzara un notable en el TFG, por ejemplo). 

**Podrá tener**: Son aquellos que comparados con los anteriores son los menos deseados o tienen menor impacto. Hay que tenerlos controlados ya que sólo se podrán entregar si se dan las mejores condiciones (por ejemplo, el proyecto va más rápido de los esperado). Si hay algún riesgo en la entrega del proyecto estos requisitos serían los primeros en ser descartados. Si no, el proyecto cubrira todos sus objetivos (p. ej. se tendra un sobresaliente o matrícula en el TFG) 

**No tendra**: Son aquellos que no van a ser entregados durante el periodo considerado. Se mantienen en esta lista de alcance para clarificar el alcance de la solución. Esto evita que informalmente sean introducidos ms tarde. El objetivo es ayudar a mantener el foto en la solución.



## Requisitos de alto nivel

{| 
| Celda 1, Fila 1 
| Celda 2, Fila 1 
|- 
| Celda 1, Fila 2 
| Celda 2, Fila 2 
|}


