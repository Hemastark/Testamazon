package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;
import baseSetup.Operations;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import modules.AddToCart;


public class AddToCartInterface {
	
	boolean value,value1;
	Operations operate = new Operations();
	AddToCart add = new AddToCart();
	
	@Given("^Navigating to Amazon Home page$")
	public void Navigating_to_Amazon_Home_page() throws IOException {
		value = operate.initialize_browser();
		value1 = operate.getURL("http://amazon.in/");
		Assert.assertEquals(value&value1,true);
	}
	
	@When("^Searching for Gift cards$")
	public void Searching_for_Gift_cards() throws Exception {
		value = add.giftcard();
		Assert.assertEquals(value, true);
	}
	
	@Then("^Adding the Gift cards to Cart$")
	public void Adding_the_Gift_cards_to_Cart() throws Exception {
		value = add.addToCart();
		Assert.assertEquals(value, true);
	}

	@When("^Navigating to Cart$")
	public void Navigating_to_Cart() throws Exception {
		value = add.viewCart();
		Assert.assertEquals(value, true);
	}
	
	@Then("^Removing the Items from Cart$")
	public void Removing_the_Items_from_Cart() throws Exception {
		value = add.deleteCartItem();
		Assert.assertEquals(value, true);
	}
	
//	@After
//	public void tearDown(Scenario scenario) {
//	    if (scenario.isFailed()) {
//	      final byte[] screenshot = operate.screenshotArray();
//	      String testName = scenario.getName();
//	      System.out.println("*************" + testName + "**************");
//	      scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
//	    }
//	}
}
