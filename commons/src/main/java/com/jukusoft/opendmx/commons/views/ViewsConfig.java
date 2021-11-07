package com.jukusoft.opendmx.commons.views;

import com.jukusoft.opendmx.commons.utils.Dirs;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * this class contains the values for the views from the config files in data/console/generic/views.json .
 *
 * @author Justin Kuenzel
 */
@Configuration
public class ViewsConfig {

    /**
     * a list with all views (perspectives) in the UI (and their buttons).
     */
    private List<View> views = new ArrayList<>();

    /**
     * default constructor.
     */
    public ViewsConfig() {
        try {
            loadConfig(new File(Dirs.DATA + "console/generic/views.json"));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load console ViewsConfig");
        }
    }

    /**
     * load the config from the json file.
     *
     * @param file path to json file
     */
    protected void loadConfig(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalStateException("config file does not exists: " + file.getAbsolutePath());
        }

        //cleanup old views
        this.views.clear();

        String jsonString = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        JSONObject json = new JSONObject(jsonString);

        //parse views
        for (String key : json.keySet()) {
            JSONObject json1 = json.getJSONObject(key);
            String title = json1.getString("title");
            String description = json1.getString("description");

            View view = new View(key, title, description);
            this.views.add(view);
        }
    }

    /**
     * get views.
     *
     * @return views / UI perspectives
     */
    public List<View> getViews() {
        return Collections.unmodifiableList(this.views);
    }

}
