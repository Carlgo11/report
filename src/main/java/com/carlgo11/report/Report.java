package com.carlgo11.report;

import org.bukkit.plugin.Plugin;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Report {

    private transient Pastebin pastebin;
    private transient Plugin plugin;
    private URL url = null;

    /**
     * @param parent   Plugin that requests the report.
     * @param user_key Pastebin API_USER_KEY. Can be left empty
     * @param dev_key  Pastebin API_DEV_KEY. Generate one at pastebin.com/api
     * @since 1.1
     */
    public Report(Plugin parent, String user_key, String dev_key) {
        this.pastebin = new Pastebin(user_key, dev_key);
        this.plugin = parent;

    }

    /**
     * Gather information and post to Pastebin
     *
     * @return Returns true if a response URL from pastebin was received.
     * @since 1.0
     */
    public boolean makeReport() {
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
            this.url = new URL(pastebin.makePaste(plugin.getName(), body));
            return true;
        } catch (UnsupportedEncodingException ignored) {
        } catch (MalformedURLException ignored) {
        }
        return false;
    }

    /**
     * Get the Pastebin URL of the report.
     *
     * @return returns a Pastebin URL.
     * @since 1.2
     */
    public URL getURL() {
        return this.url;
    }

    /**
     * Generates a String containing the config.yml of the plugin requesting the report.
     *
     * @param plugin Plugin requesting the report.
     * @return Returns a String containing the config.yml
     * @since 1.0
     */
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

    /**
     * Generates a String containing the log/latest.log of the server.
     *
     * @return returns a String containing the latest.log.
     * @since 1.0
     */
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
