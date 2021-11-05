package com.jukusoft.opendmx.commons.fixture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 * the fixture library.
 *
 * @author Justin Kuenzel
 */
public class FixtureLibrary {

	/**
	 * save the fixture data of one fixture.
	 *
	 * @param file the tarhet file
	 * @param fixture the fixture to save
	 */
	public void save(File file, FixtureLibEntry fixture) {
		//Gson gson = new Gson();
		Gson gson = createGson();

		String jsonString = gson.toJson(fixture);
	}

	/**
	 * load fixture from file.
	 *
	 * @param file file path of fixture file
	 * @return fixture entry
	 * @throws IOException if file could not be read
	 */
	public FixtureLibEntry loadFromFile(File file) throws IOException {
		String jsonString = Files.readString(file.toPath());

		FixtureLibEntry fixtureLibEntry = createGson().fromJson(jsonString, FixtureLibEntry.class);
		return fixtureLibEntry;
	}

	/**
	 * create Gson instance.
	 *
	 * @return gson instance
	 */
	private Gson createGson() {
		//Gson gson = new Gson();
		return new GsonBuilder().setPrettyPrinting().create();
	}

}
