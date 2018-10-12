package com.drugsearch.serenity.utils;

import net.serenitybdd.core.SerenitySystemProperties;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import com.google.common.base.Function;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class SeleniumUtils {
	private static final long DEFAULT_POLLING_MILLISECONDS = 1000;

	public static String getBaseURL() {
		
		return System.getProperty("webdriver.base.url");
	}
	
	
	
	
	public static void scrollToElement(WebDriver driver, WebElement el) {
		System.out.println("scrolling to element " + ": " + el);
		highlightElement(driver, el);
		((JavascriptExecutor) driver)
				.executeScript("window.scrollTo(" + el.getLocation().getX() + "," + el.getLocation().getY() + ");");

		try {
			Thread.sleep(250);
		} catch (Exception t) {
		}
	}

	public static void safeclearandtype(WebDriver driver, WebElement element, String text) {
		try {
			highlightElement(driver, element);
			element.clear();
			element.sendKeys(text);
		} catch (Exception ex) {
			System.out.println("Inside safe clear and type-" + element + " at [" + driver.getTitle() + "]");

		}

	}

	

	public static boolean verify_url(WebDriver driver, String url) {
		System.out.println("Current url=" + driver.getCurrentUrl());
		return driver.getCurrentUrl().contains(url);
	}


	public static boolean verify_title(WebDriver driver, String title) {

		System.out.println("Current title =" + driver.getTitle());
		System.out.println(driver.getTitle().contains(title));
		return driver.getTitle().contains(title);
	}


	public static boolean verify_title_equals(WebDriver driver, String title) {

		System.out.println("Current title =" + driver.getTitle());
		return driver.getTitle().equals(title);
	}

	
	public static boolean verify_url_equals(WebDriver driver, String url) {
		System.out.println("Current url=" + driver.getCurrentUrl());
		return driver.getCurrentUrl().equals(url);
	}


	public static void safeclick(WebDriver driver, WebElement element) {
		try {
			(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(element));
			highlightElement(driver, element);
			element.click();
		} catch (StaleElementReferenceException sere) {
			element.click();
		} catch (TimeoutException toe) {
			System.out.println("Element identified by " + element.toString() + " was not clickable after 10 seconds");
		}
	}
	
	
	


	public static void scrollIntoElementView(WebElement element, WebDriver driver) {
		try {

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		} catch (StaleElementReferenceException e) {

			System.out.println("Element with " + element + "is not attached to the page document");
		}

	}

	
	public static void highlightElement(WebDriver driver, WebElement element) {

		String attributevalue = "border:3px solid red;";
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String getattrib = element.getAttribute("style");
		executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted - " + e);
		}
		executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);
	}



	
	

}
