# Report

## What it does
Report is an api that allows developers to implement pastebin support for logs in their bukkit plugins.

## How to implement into your own code

1. Copy the src/main/java/api files into your project
2. Add a boolean in your config.yml with the name `share-log: true`
3. In the code you want to get the pastebin link do `String pastebin = Pastebin.makePaste(Report.Main(this), "topic name", "text");` If you're calling the method outside of your main class replace `this` with your main class.)
4. If you want to print out the link to a sender do `sender.sendMessage(pastebin)`.
5. If you want the pure pastebin link (without http://pastebin.com/) do `String purelink = pastebin.replace("http://pastebin.com/", "");`
6. When a user is given the link and he/she clicks it they will be redirected to a captcha page. After completing the captcha the pastebin will be created. If you have entered a user key the pastebin will be created by that user without having the user to enter a login or password. Note that they will not be able to edit any other pastebins created by the user, neither can they edit the user settings.

## Entering a userkey
1. Go to http://pastebin.com/api#1 and get your devkey.
2. When you have your devkey go to http://pastebin.com/api/api_user_key.html and enter in your credentials. You will then get your userkey
2. Go to the pastebin class and enter your userkey in the `token` string.
* This is only nessesary when you want all pastebins to be created by your pastebin account.
