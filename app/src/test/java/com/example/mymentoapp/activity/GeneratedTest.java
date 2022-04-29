package com.example.mymentoapp.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class GeneratedTest {

    private AndroidDriver<AndroidElement> driver = null;
    private DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeEach
    public void setUp() throws MalformedURLException {
        dc.setCapability("testName", "1");
        dc.setCapability("accessKey", "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51IjoxNzIyNjMzMiwieHAucCI6MTcyMjYzMzEsInhwLm0iOjE2NTExNjgwNzg3NTksImV4cCI6MTk2NjUyODI3NCwiaXNzIjoiY29tLmV4cGVyaXRlc3QifQ.UmDOXHy8LvRUo3UWO42-EiCCZhOhnKbKncRUOUgVbM8");
        dc.setCapability("deviceQuery", "@serialnumber='4838473235363098'");
        driver = new AndroidDriver<>(new URL("https://cloud.seetest.io/wd/hub"), dc);
    }

    @Test
    public void generatedMethod() {
        driver.findElementByXPath("//*[@text='Name: Flavia Cojocaru']");
        driver.findElementByXPath("//*[@text='My courses']");
    }
}
