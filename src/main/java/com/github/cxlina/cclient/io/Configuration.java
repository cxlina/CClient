package com.github.cxlina.cclient.io;

import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;

public class Configuration {

    private YamlFile file;

    public Configuration(String path) {
        try {
            this.file = new YamlFile(path);
            if (!this.file.exists())
                this.file.createNewFile();
            this.file.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(String s, Object o) {
        try {
            this.file.set(s, o);
            this.file.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T get(Class<T> type, String path, T def) {
        if (!this.file.contains(path))
            this.set(path, def);
        return (T) this.file.get(path, def);
    }

    public YamlFile getFile() {
        return file;
    }
}
