package com.jukusoft.opendmx.commons.fixture;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * junit tests for fixture library.
 *
 * @author Justin Kuenzel
 */
public class FixtureLibraryTest {

	@BeforeAll
	protected static void beforeAll() {
		//set system property, so another fixture directory is used
		System.setProperty(FixtureLibrary.DIR_PROPERTY_KEY, "./junit/fixtures");

		new File("./junit/fixtures").mkdirs();
	}

	@AfterAll
	protected static void afterAll() {
		//
	}

	@Test
	public void testLoadAllFixtures() throws IOException {
		FixtureLibrary fixtureLibrary = new FixtureLibrary();

		//first, cleanup old test fixture
		File oldFixture = new File(fixtureLibrary.getFixtureDirPath().toFile(), "test.fixture");

		if (oldFixture.exists()) {
			oldFixture.delete();
		}

		assertEquals(0, fixtureLibrary.countLoadedFixtures());

		fixtureLibrary.loadAllFixtures();
		assertEquals(0, fixtureLibrary.countLoadedFixtures());

		//create new fixture
		FixtureLibEntry fixture = new FixtureLibEntry("test", "test", "test", 1);
		fixtureLibrary.save(new File(fixtureLibrary.getFixtureDirPath().toFile(), "test.fixture"), fixture);

		//load fixtures again
		fixtureLibrary.loadAllFixtures();
		assertEquals(1, fixtureLibrary.countLoadedFixtures());
	}

}
