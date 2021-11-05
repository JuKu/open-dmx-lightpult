package com.jukusoft.opendmx.commons.fixture;

import java.util.Map;

/*
 * an entry for the fixture library (entry means one spot, dimmer or one moving head).
 *
 * @author Justin Kuenzel
 */
public class FixtureLibEntry {

	/**
	 * short name of the fixture, e.q. "MH-X25".
	 */
	private String shortName;

	/**
	 * long name of the fixture, e.q. "<Manufacturer> <Model>".
	 */
	private String longName;

	/**
	 * the manufacturer of the headlight.
	 */
	private String manufacturer;

	/**
	 * modes of headlight.
	 */
	private Map<String,FixtureMode> modes;

	// TODO: add palettes

	/**
	 * default constructor.
	 *
	 * @param shortName short name of the headlight.
	 * @param longName long name of the headlight.
	 * @param manufacturer manufacturer of the headlight.
	 */
	public FixtureLibEntry(String shortName, String longName, String manufacturer) {
		this.shortName = shortName;
		this.longName = longName;
		this.manufacturer = manufacturer;
	}

	public String getShortName() {
		return shortName;
	}

	public String getLongName() {
		return longName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public Map<String, FixtureMode> getModes() {
		return modes;
	}

}
