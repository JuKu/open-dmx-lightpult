package com.jukusoft.opendmx.commons.views;

import com.jukusoft.opendmx.commons.utils.Dirs;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ViewsConfigTest {

    @Test
    public void testLoadConfig() throws IOException {
        Dirs.overrideDataDir("../data/");

        ViewsConfig config = new ViewsConfig();
        config.loadConfig(new File("../data/console/generic/views.json"));

        assertFalse(config.getViews().isEmpty());
    }

}
