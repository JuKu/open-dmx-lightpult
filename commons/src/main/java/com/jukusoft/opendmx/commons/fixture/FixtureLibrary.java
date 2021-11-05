package com.jukusoft.opendmx.commons.fixture;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * the fixture library.
 *
 * @author Justin Kuenzel
 */
public class FixtureLibrary {

	/**
	 * the fixture directory system property key.
	 */
	private static final String DIR_PROPERTY_KEY = "fixtureDir";

	/**
	 * a map with all fixtures (key: manufacturer, value: list with all fixtures of this manufacturers).
	 */
	private Map<String, List<FixtureLibEntry>> fixtures = new HashMap<>();

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
	 * load all fixtures from fixture directory.
	 */
	public void loadAllFixtures() throws IOException {
		this.fixtures.clear();

		Files.find(getFixtureDirPath(),
						Integer.MAX_VALUE,
						(filePath, fileAttr) -> fileAttr.isRegularFile() && filePath.endsWith(".fixture"))
				.forEach(path -> {
					try {
						loadFixtureInternal(path);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
	}

	/**
	 * ,load the fixture from the path and save it into memory.
	 *
	 * @param path path to .fixture file
	 * @throws IOException if file could not be read
	 */
	public void loadFixtureInternal(Path path) throws IOException {
		File file = path.toFile();

		if (!file.exists()) {
			throw new FileNotFoundException("fixture file does not exists: " + file.getAbsolutePath());
		}

		FixtureLibEntry fixture = loadFromFile(file);
		String manufacturer = fixture.getManufacturer();

		if (!this.fixtures.containsKey(manufacturer)) {
			this.fixtures.put(manufacturer, new ArrayList<>());
		}

		this.fixtures.get(manufacturer).add(fixture);
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

	/**
	 * get fixture directory with all .fixture files.
	 *
	 * @return directory with all fixture files
	 */
	private Path getFixtureDirPath() {
		String dirPath = "data/fixtures";

		if (System.getProperty(DIR_PROPERTY_KEY) != null) {
			dirPath = System.getProperty(DIR_PROPERTY_KEY);
		}

		File fixtureDir = new File(dirPath);

		//create directory, if not exists
		if (!fixtureDir.exists()) {
			fixtureDir.mkdirs();
		}

		return fixtureDir.toPath();
	}

}
