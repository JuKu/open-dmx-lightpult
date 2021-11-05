package com.jukusoft.opendmx.commons.fixture;

/*
 * the attribute types.
 *
 * @author Justin Kuenzel
 */
public enum AttributeType {

	POSITION("pos"),

	MOVEMENT_SPEED("movSpeed"),

	COLOR_RAD("colR"),

	COLOR("col");

	private final String shortName;

	AttributeType(String shortName) {
		this.shortName = shortName;
	}

}
