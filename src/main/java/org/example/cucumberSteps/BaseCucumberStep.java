package org.example.cucumberSteps;

import org.example.pages.PageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseCucumberStep {
    protected static final PageManager pages = new PageManager();
    protected static final Logger log = LogManager.getLogger("CucumberTest");
}