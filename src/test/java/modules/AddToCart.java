package modules;

import baseSetup.ElementLocators;
import baseSetup.Operations;

public class AddToCart {

	boolean output=true;
	Operations operate = new Operations();
	ElementLocators element = new ElementLocators();

	public boolean giftcard() throws Exception {
		output = operate.sendElementByXpath(element.searchBox, "Gift Cards");
		//operate.screenshot("GiftCard");
		output = operate.clickElementByXpath(element.searchIcon);
		return output;
	}

	public boolean addToCart() throws Exception {
		output = operate.clickElementByXpath(element.giftCard);
		operate.switchTabs(1);
		output = operate.clickElementByXpath(element.addToCart);
		return output;
	}

	public boolean viewCart() throws Exception {
		output = operate.clickElementByXpath(element.cart);
		return output;
	}

	public boolean deleteCartItem() throws Exception {
		if (operate.getCount(element.deleteItem) >= 1)
			output = operate.clickElementByXpath(element.deleteItem);
		return output;
	}

}
