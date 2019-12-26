package com.carlgo11.report;

import org.bukkit.plugin.Plugin;

import java.io.*;

public class Report {

    private transient Pastebin pastebin;
    private transient Plugin plugin;

    public Report(Plugin parent, String user_key, String dev_key) {
        this.pastebin = new Pastebin(user_key, dev_key);
        this.plugin = parent;

    }

    public void makeReport() {
        String log = "Log reporting has been disabled by the user.";
        String config = "Config reporting has been disabled by the user.";

        if (plugin.getConfig().getBoolean("report.report-log")) {
            log = this.latestlog();
        }
        if (plugin.getConfig().getBoolean("report.report-config")) {
            config = this.config(plugin);
        }

        String body = String.format("Config:\n %s\nLog:\n%s", config, log);
        try {
            pastebin.makePaste(plugin.getName(), body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String config(Plugin plugin) {
        BufferedReader br = null;
        StringBuilder txt = new StringBuilder();
        String configPath = "" + plugin.getDataFolder() + File.separatorChar + "config.yml";
        try {
            String line;
            br = new BufferedReader(new FileReader(configPath));
            while ((line = br.readLine()) != null) {
                txt.append(line);
                txt.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return txt.toString();
    }

    private String latestlog() {
        BufferedReader br = null;
        StringBuilder txt = new StringBuilder();
        try {
            String line;
            br = new BufferedReader(new FileReader("logs" + File.separatorChar + "latest.log"));
            while ((line = br.readLine()) != null) {
                txt.append(line);
                txt.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return txt.toString();
    }
}
