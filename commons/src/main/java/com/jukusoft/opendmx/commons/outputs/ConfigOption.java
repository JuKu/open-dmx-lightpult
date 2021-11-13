package com.jukusoft.opendmx.commons.outputs;

/*
 * This class represents a single config option for the output controller.
 *
 * @author Justin Kuenzel
 */
public class ConfigOption {

	public enum CONFIG_TYPE {
		STRING,
		INTEGER,
		DOUBLE,
		BOOLEAN
	}

	/**
	 * the key of the option
	 */
	private String key;

	/**
	 * the title of the config option (e.q. ip address).
	 */
	private String title;

	/**
	 * the data type.
	 */
	private CONFIG_TYPE dataType;

	/**
	 * default constructor.
	 *
	 * @param key key of the option
	 * @param title title of the option
	 * @param dataType datatype of the option
	 */
	public ConfigOption(String key, String title, CONFIG_TYPE dataType) {
		this.key = key;
		this.title = title;
		this.dataType = dataType;
	}

}
