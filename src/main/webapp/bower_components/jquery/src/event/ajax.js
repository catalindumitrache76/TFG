define( [
	"../core",
	"../event"
], function( jQuery ) {

"use strict";

// Attach a bunch of functions for handling com.unizar.phytoscheme.processes.common_methods AJAX events
jQuery.each( [
	"ajaxStart",
	"ajaxStop",
	"ajaxComplete",
	"ajaxError",
	"ajaxSuccess",
	"ajaxSend"
], function( i, type ) {
	jQuery.fn[ type ] = function( fn ) {
		return this.on( type, fn );
	};
} );

} );
