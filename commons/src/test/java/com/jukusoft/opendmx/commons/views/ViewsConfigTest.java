package com.jukusoft.opendmx.commons.views;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ViewsConfigTest {

    @Test
    public void testLoadConfig() throws IOException {
        ViewsConfig config = new ViewsConfig();
        config.loadConfig(new File("../data/console/generic/views.json"));

        assertFalse(config.getViews().isEmpty());
    }

}
