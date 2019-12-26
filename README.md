# Report-API
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

## Usage

To generate a report, call the Report class like this:
```JAVA
String api_key = ""; //Pastebin API key
String user_key = ""; //Pastebin User key (can be left blank)

Report report = new Report(plugin, user_key, api_key); //Call Report class
report.makeReport(); // Send the data away to Pastebin
```
