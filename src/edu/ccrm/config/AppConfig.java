package edu.ccrm.config;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public class AppConfig {

    // The single, static instance of the AppConfig class.
    private static final AppConfig INSTANCE = new AppConfig();

    private final Properties properties;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * This is a key part of the Singleton pattern.
     */
    private AppConfig() {
        properties = new Properties();
        // In a real application, you would load this from a .properties file.
        // For this project, we'll use default hardcoded values.
        properties.setProperty("data.folder", "data");
        properties.setProperty("backup.folder", "backups");
        properties.setProperty("students.csv.name", "students.csv");
        properties.setProperty("courses.csv.name", "courses.csv");
        properties.setProperty("max.credits.per.semester", "18");
    }

    /**
     * Returns the single instance of the AppConfig class.
     *
     * @return The singleton AppConfig instance.
     */
    public static AppConfig getInstance() {
        return INSTANCE;
    }

    /**
     * Retrieves a configuration property by its key.
     *
     * @param key The key of the property.
     * @return The value of the property as a String.
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * A convenient method to get a property as an integer.
     *
     * @param key The key of the property.
     * @return The integer value, or a default value (0) if parsing fails.
     */
    public int getIntProperty(String key) {
        try {
            return Integer.parseInt(properties.getProperty(key));
        } catch (NumberFormatException e) {
            System.err.println("Warning: Could not parse integer for property '" + key + "'.");
            return 0; // Default value
        }
    }

    /**
     * Provides the base path for data files.
     *
     * @return The Path object for the data directory.
     */
    public Path getDataPath() {
        return Paths.get(getProperty("data.folder"));
    }

    /**
     * Provides the base path for backup files.
     *
     * @return The Path object for the backup directory.
     */
    public Path getBackupPath() {
        return Paths.get(getProperty("backup.folder"));
    }
}
