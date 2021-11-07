package com.jukusoft.opendmx.commons.views;

/**
 * a view represents a perspective in the UI and a button to switch into this perspective.
 *
 * @author Justin Kuenzel
 */
public class View {

    private final String key;
    private final String title;
    private final String description;

    public View(String key, String title, String description) {
        this.key = key;
        this.title = title;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
