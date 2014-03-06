## What it does
Report is an api that allows developers to implement pastebin support for logs in their bukkit plugins.

## How to implement pastebin into your own code

1. Copy the files in <a href="https://github.com/Carlgo11/report/tree/master/src/main/java/com/carlgo11/report">src/main/java/com.carlgo11.report</a> into your project
* Add a boolean in your config.yml with the name `report-log: true`.
* In the code where you want to get the pastebin link do `String pastebin = Pastebin.makePaste("Report topic", this)` If you're calling the method outside of your main class replace __"this"__ with __"Plugin"__, from <a href="http://jd.bukkit.org/rb/doxygen/d1/db6/interfaceorg_1_1bukkit_1_1plugin_1_1Plugin.html">org.bukkit.plugin.Plugin</a>;)
* If you want the pure pastebin link (without http://pastebin.com/) do `String purelink = pastebin.replace("http://pastebin.com/", "");`
* To give the user the link simply do `player.sendMessage(pastebin);`. Of cource you can add more text to the message.
* When a user is given the link and he/she clicks it they will be redirected to a captcha page. After completing the captcha the pastebin will be created.<br>
*(If you have entered a user key the pastebin will be created by that user without having the user to enter a login or password. Note that they will not be able to edit any other pastebins created by the user, neither can they edit the user settings)*
* If you want to print out the link to a sender do `sender.sendMessage(pastebin)`.

## Entering an api-user-key
A userkey is needed when you want all pastebins to be created by your pastebin account.
That can help you to understand what people often need help with.

1. Go to http://pastebin.com/api#1 and get your devkey.
* When you have your devkey go to http://pastebin.com/api/api_user_key.html and enter in your credentials. You will then get your api-user-key.
* Go to the pastebin class and enter your api-user-key in the `token` string.

## Permitted use

You may use the code in this project and this example for your own project, as long as you give credit to the authors.
For more information of usage of this project please read https://github.com/Carlgo11/report/blob/master/LICENSE.md.
