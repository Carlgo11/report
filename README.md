# Report-API
[![Build Status](https://img.shields.io/github/workflow/status/Carlgo11/report/Java%20CI?style=for-the-badge)](https://github.com/Carlgo11/report/actions)
[![License](https://img.shields.io/github/license/carlgo11/report?style=for-the-badge)](https://github.com/Carlgo11/report/blob/master/LICENSE.md)
![Bukkit](https://img.shields.io/badge/bukkit-v1.15.1-ab7b53?style=for-the-badge)

Upload server logs & plugin configurations for bug reports.

## Installation

Add this to your pom.xml:

```XML
<dependency>
  <groupId>com.carlgo11</groupId>
  <artifactId>report</artifactId>
  <version>1.1</version>
</dependency>
```

Run via command line:
```BASH
mvn install
```

import the Report class in your own class:
```JAVA
import com.carlgo11.report.Report;
```

Go to [Pastebin.com](https://pastebin.com) and create an account if you haven't already.  
Then go to [Pastebin API](https://pastebin.com/api#1), retrieve your API key and optionally your [User Key](https://pastebin.com/api/api_user_key.html).

### Plugin dependency
As your plugin will have to load after the Report-API has finished loading it's a good idea to make that clear in the `plugin.yml` of your plugin. This can be done through several ways.
  
 #### Hard dependency
The first one is to require Report-API to be present for your plugin do load:
```YAML
depend:
  - report-api
```

#### Soft dependency
If you'd rather have the report feature as an optional functionality you can use the soft depend tag:
```YAML
softdepend:
  - report-api
```
This way the server will try and load Report-API before your own plugin if Report-API is present in the plugins folder.
If not, it will just load your plugin normally.

## Usage

To generate a report, call the Report class like this:
```JAVA
String api_key = ""; //Pastebin API key
String user_key = ""; //Pastebin User key (can be left blank)

Report report = new Report(plugin, user_key, api_key); //Call Report class
report.makeReport(); //Send the data away to Pastebin
```

You can then send the URL to the user who sent the request.

```JAVA
URL url = report.getURL(); //Get the Pastebin URL
sender.sendMessage("Give this URL to the support agent: " + URL.toString);
```

### Optional code
It's a good idea to also check that the `makeReport()` function returns true and that the `getURL()` function returns a valid Pastebin URL.
```JAVA
String api_key = "";
String user_key = "";
try {
    Report report = new Report(plugin, user_key, api_key);
    if(report.makeReport()){
        URL url = report.getURL();
        if(url.toString().startsWith("https://pastebin.com/")){
            sender.sendMessage("Give this URL to the support agent: " + url.toString);
        }
    }
} catch (ClassNotFoundException e) {
    System.out.println("Report-API not installed!");
}
```


## Compile locally
To compile Report-API locally you need:
* Java JDK 8 or later
* Apache Maven

Then run:
```BASH
mvn install
```
_Depending on your OS and Maven setup you might need to replace `mvn` with `maven` in the example above_.

You should now have a Report-API JAR-file inside the `target` folder.
