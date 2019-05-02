$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Background.feature");
formatter.feature({
  "line": 1,
  "name": "TestNgSuite",
  "description": "",
  "id": "testngsuite",
  "keyword": "Feature"
});
formatter.before({
  "duration": 31377781542,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Back to Back",
  "description": "",
  "id": "testngsuite;back-to-back",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "Message displayed Login Successfully",
  "keyword": "And "
});
formatter.match({
  "location": "TestSteps.message_displayed_Login_Successfully()"
});
formatter.result({
  "duration": 1524463710,
  "status": "passed"
});
formatter.after({
  "duration": 183015024,
  "status": "passed"
});
formatter.uri("TestNgSuite.feature");
formatter.feature({
  "line": 1,
  "name": "TestNgSuite",
  "description": "",
  "id": "testngsuite",
  "keyword": "Feature"
});
formatter.before({
  "duration": 24365066197,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Login Application",
  "description": "",
  "id": "testngsuite;login-application",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "User enter \"mngr189249\" and \"Ubydase\"",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "mngr189249",
      "offset": 12
    },
    {
      "val": "Ubydase",
      "offset": 29
    }
  ],
  "location": "TestSteps.user_enters_and(String,String)"
});
formatter.result({
  "duration": 6350168108,
  "status": "passed"
});
formatter.after({
  "duration": 145469532,
  "status": "passed"
});
formatter.before({
  "duration": 12492544459,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "New Customer creation",
  "description": "",
  "id": "testngsuite;new-customer-creation",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "User enter \"mngr189249\" and \"Ubydase\"",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "User use new customers from the Application",
  "rows": [
    {
      "cells": [
        "cunames",
        "dobs",
        "addrs",
        "cit",
        "st",
        "pin",
        "pno",
        "mail",
        "pass"
      ],
      "line": 9
    },
    {
      "cells": [
        "chinrnaefr",
        "090801978",
        "xxxxx",
        "chinnai",
        "tamil",
        "604301",
        "7656891573",
        "chindrgdddtg@gmail.com",
        "99838chinna"
      ],
      "line": 10
    },
    {
      "cells": [
        "chindngfss",
        "090801978",
        "xxxxx",
        "chinnasi",
        "tamsil",
        "604301",
        "7656898523",
        "chineys@gmail.com",
        "99570chinna"
      ],
      "line": 11
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "mngr189249",
      "offset": 12
    },
    {
      "val": "Ubydase",
      "offset": 29
    }
  ],
  "location": "TestSteps.user_enters_and(String,String)"
});
formatter.result({
  "duration": 2555136640,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.user_use_new_customer_from_the_Application(DataTable)"
});
formatter.result({
  "duration": 89989553,
  "error_message": "org.openqa.selenium.UnhandledAlertException: unexpected alert open: {Alert text : User or Password is not valid}\n  (Session info: chrome\u003d73.0.3683.103)\n  (Driver info: chromedriver\u003d72.0.3626.69 (3c16f8a135abc0d4da2dff33804db79b849a7c38),platform\u003dWindows NT 6.2.9200 x86) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 26 milliseconds: null\nBuild info: version: \u00272.53.1\u0027, revision: \u0027a36b8b1cd5757287168e54b817830adce9b0158d\u0027, time: \u00272016-06-30 19:26:09\u0027\nSystem info: host: \u0027Krish\u0027, ip: \u0027192.168.0.103\u0027, os.name: \u0027Windows 8\u0027, os.arch: \u0027x86\u0027, os.version: \u00276.2\u0027, java.version: \u00271.8.0_141\u0027\nDriver info: org.openqa.selenium.remote.RemoteWebDriver\nCapabilities [{mobileEmulationEnabled\u003dfalse, timeouts\u003d{implicit\u003d0, pageLoad\u003d300000, script\u003d30000}, hasTouchScreen\u003dfalse, platform\u003dWIN8, acceptSslCerts\u003dfalse, goog:chromeOptions\u003d{debuggerAddress\u003dlocalhost:63214}, acceptInsecureCerts\u003dfalse, webStorageEnabled\u003dtrue, browserName\u003dchrome, takesScreenshot\u003dtrue, javascriptEnabled\u003dtrue, setWindowRect\u003dtrue, unexpectedAlertBehaviour\u003dignore, applicationCacheEnabled\u003dfalse, rotatable\u003dfalse, networkConnectionEnabled\u003dfalse, chrome\u003d{chromedriverVersion\u003d72.0.3626.69 (3c16f8a135abc0d4da2dff33804db79b849a7c38), userDataDir\u003dC:\\Users\\Kris\\AppData\\Local\\Temp\\scoped_dir5152_11586}, takesHeapSnapshot\u003dtrue, pageLoadStrategy\u003dnormal, databaseEnabled\u003dfalse, handlesAlerts\u003dtrue, version\u003d73.0.3683.103, browserConnectionEnabled\u003dfalse, proxy\u003d{}, nativeEvents\u003dtrue, webdriver.remote.sessionid\u003d8463d427718e32a23ecd326e023dd31a, locationContextEnabled\u003dtrue, cssSelectorsEnabled\u003dtrue}]\nSession ID: 8463d427718e32a23ecd326e023dd31a\n*** Element info: {Using\u003dxpath, value\u003d//a[contains(text(),\u0027New Customer\u0027)]}\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\r\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\r\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:206)\r\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:164)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:678)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:363)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:500)\r\n\tat org.openqa.selenium.By$ByXPath.findElement(By.java:361)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:355)\r\n\tat StepDefinition.TestSteps.user_use_new_customer_from_the_Application(TestSteps.java:81)\r\n\tat ✽.Then User use new customers from the Application(TestNgSuite.feature:8)\r\n",
  "status": "failed"
});
formatter.after({
  "duration": 132070396,
  "status": "passed"
});
});