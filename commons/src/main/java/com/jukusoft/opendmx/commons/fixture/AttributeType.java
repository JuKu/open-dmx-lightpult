package com.jukusoft.opendmx.commons.fixture;

/*
 * the attribute types.
 *
 * @author Justin Kuenzel
 */
public enum AttributeType {

	POSITION("pos"),

	DIMMER("dim"),

	SHUTTER("shutter"),

	FOCUS("focus"),

	ZOOM("zoom"),

	MOVEMENT_SPEED("movSpeed"),

	COLOR_RAD("colR"),

	COLOR("col"),

	GOBO("gobo"),

	GOBO_ROT("goboRot"),

	PRISM("prism"),

	FUNCTION("func"),

	CONTROL("control");

	private final String shortName;

	AttributeType(String shortName) {
		this.shortName = shortName;
	}

}
