package com.tutorialsninja.testsuite.laptopsandnotebooks;

import com.tutorialsninja.customlisteners.CustomListeners;
import com.tutorialsninja.pages.*;
import com.tutorialsninja.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class LaptopsAndNotebooksTest extends BaseTest {

    HomePage homePage;
    LaptopAndNoteBookPage laptopAndNoteBookPage;
    MacBookPage macBookPage;
    ShoppingCartPage shoppingCartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        laptopAndNoteBookPage = new LaptopAndNoteBookPage();
        macBookPage = new MacBookPage();
        shoppingCartPage = new ShoppingCartPage();
        checkoutPage = new CheckoutPage();
    }

    @Test(groups = {"sanity", "regression"})
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        homePage.mouseHoverAndClickOnLaptopsAndNotebooks();
        homePage.selectMenu("Laptops & Notebooks");
        laptopAndNoteBookPage.selectPriceHighToLow("Price (High > Low)");
        Assert.assertEquals(laptopAndNoteBookPage.afterSorting(), laptopAndNoteBookPage.beforeSorting(), "products not sorted by Price High to Low");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        homePage.selectMenu("Laptops & Notebooks");
        laptopAndNoteBookPage.selectPriceHighToLow("Price (High > Low)");
        laptopAndNoteBookPage.clickOnMacbook();
        Assert.assertEquals(macBookPage.getTextFromMacBook(), "MacBook", "MacBook Product not display");
        macBookPage.clickOnAddToCart();
        Assert.assertTrue(macBookPage.isSuccessMessageAppearing(), "Message Doesn't Appear");
        macBookPage.clickOnShoppingCart();
        Assert.assertTrue(shoppingCartPage.isShoppingCartAppearing(), "Shopping Cart Doesn't Appear");
        Assert.assertEquals(shoppingCartPage.getProductName(), "MacBook", "Product Name Doesn't appear");
        shoppingCartPage.clearAndAddQuantity("2");
        shoppingCartPage.clickOnUpdate();
        Assert.assertTrue(shoppingCartPage.isSuccessMessageAppearing("Success: You have modified your shopping cart!"), "Cart not modified");
        Assert.assertEquals(shoppingCartPage.getTotalText(), "$1,204.00", "Total not matched");
        shoppingCartPage.clickOnCheckout();
        Thread.sleep(1000);
        Assert.assertEquals(checkoutPage.getCheckoutText(), "Checkout", "Checkout not displayed");
        Thread.sleep(1000);
        Assert.assertEquals(checkoutPage.getNewCustomerText(), "New Customer", "New Customer not displayed");
        checkoutPage.clickOnGuestCheckoutRadioButton();
        checkoutPage.clickOnContinueButton();
        checkoutPage.enterBillingDetailsFirstName("Hi");
        checkoutPage.enterBillingDetailsLastName("Patel");
        checkoutPage.enterBillingDetailsEmail("Patel" + getRandomAlphaNumericString(4) + "@gmail.com");
        Thread.sleep(500);
        checkoutPage.enterBillingDetailsTelephone("07654321234");
        checkoutPage.enterBillingDetailsAddress("11 Pinner Road");
        checkoutPage.enterBillingDetailsCity("Harrow");
        checkoutPage.enterBillingDetailsPostcode("HA61SY");
        checkoutPage.enterBillingDetailsCountry("United Kingdom");
        Thread.sleep(500);
        checkoutPage.enterBillingDetailsRegionOrState("Aberdeen");
        checkoutPage.clickOnContinueBillingButton();
        checkoutPage.enterComment("Nothing Specific.");
        Thread.sleep(1000);
        checkoutPage.clickOnAgreeToTermsAndConditions();
        checkoutPage.clickOnContinueCommentButton();
        Thread.sleep(1000);
        Assert.assertTrue(checkoutPage.isPaymentWarningAppearing(), "Payment Warning not displayed");
    }
}
