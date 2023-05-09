package com.tutorialsninja.testsuite.homepage;

import com.tutorialsninja.customlisteners.CustomListeners;
import com.tutorialsninja.pages.ComponentsPage;
import com.tutorialsninja.pages.DesktopPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LaptopAndNoteBookPage;
import com.tutorialsninja.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class TopMenuTest extends BaseTest {

    HomePage homePage;
    DesktopPage desktopPage;
    LaptopAndNoteBookPage laptopAndNoteBookPage;
    ComponentsPage componentsPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        desktopPage = new DesktopPage();
        laptopAndNoteBookPage = new LaptopAndNoteBookPage();
        componentsPage = new ComponentsPage();
    }

    @Test(groups = {"sanity", "regression"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        //click on desktop dropdown
        homePage.mouseHoverAndClickOnDesktop();
        //select show all desktops
        homePage.selectMenu("Desktops");
        String expectedDesktop = "Desktops";
        String actualDesktop = desktopPage.getDesktopText();
        //verify if user is navigated to the correct page
        Assert.assertEquals(actualDesktop, expectedDesktop, "Desktop not displayed");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        //click on laptops and notebooks dropdown
        homePage.mouseHoverAndClickOnLaptopsAndNotebooks();
        //select show all laptops and notebooks
        homePage.selectMenu("Laptops & Notebooks");
        String expectedLaptopsAndNotebooks = "Laptops & Notebooks";
        String actualLaptopsAndNotebooks = laptopAndNoteBookPage.getLaptopAndNoteBookText();
        //verify if user is navigated to the correct page
        Assert.assertEquals(actualLaptopsAndNotebooks, expectedLaptopsAndNotebooks, "Laptops And Notebooks not displayed");
    }

    @Test(groups = {"regression"})
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        //click on components dropdown
        homePage.mouseHoverAndClickOnComponents();
        //select show all components
        homePage.selectMenu("Components");
        String expectedComponents = "Components";
        String actualComponents = componentsPage.getComponentsText();
        //verify if user is navigated to the correct page
        Assert.assertEquals(actualComponents, expectedComponents, "Components Page not displayed");
    }
}
