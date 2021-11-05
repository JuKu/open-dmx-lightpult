package com.jukusoft.opendmx.editor.utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Version {

    private String versionName = "n/a";
    private String buildNumber = "n/a";
    private String buildTime = "n/a";
    private String gitCommit = "n/a";

    public Version(String versionName, String buildNumber, String buildTime, String gitCommit) {
        this.versionName = versionName;
        this.buildNumber = buildNumber;
        this.buildTime = buildTime;
        this.gitCommit = gitCommit;
    }

    protected Version() {
        //
    }

    public String getVersionName() {
        return versionName;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public String getGitCommit() {
        return gitCommit;
    }

    public static Version getCurrentVersion() {
        try {
            //read version information from resource file
            ClassLoader classLoader = Version.class.getClassLoader();
            //File versionFile = new File(classLoader.getResource("version/version.json").getFile());

            String content = getResourceFileAsString("version/version.json");//Files.readString(versionFile.toPath());
            JSONObject json = new JSONObject(content);

            return new Version(json.getString("version"), json.getString("build.number"), json.getString("build.time"), json.getString("git.build.hash"));
        } catch (IOException e) {
            return new Version();
        }
    }

    protected static String getResourceFileAsString(String fileName) throws IOException {
        //see also: https://stackoverflow.com/questions/6068197/utils-to-read-resource-text-file-to-string-java

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }

}
