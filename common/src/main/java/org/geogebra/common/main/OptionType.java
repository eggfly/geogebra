package org.geogebra.common.main;

/**
 * Option panel types
 */
public enum OptionType {
	// Order matters for the selection menu. A separator is placed after
	// OBJECTS and SPREADSHEET to isolate the view options
	OBJECTS, euclidean, euclidean2, euclidean_FOR_PLANE, euclidean3D,

	CAS, SPREADSHEET, LAYOUT, DEFAULTS, ALGEBRA, GLOBAL
}