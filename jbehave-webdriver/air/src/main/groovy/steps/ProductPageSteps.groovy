/* Copyright (c) 2013, Southwest Airlines Co.  All rights reserved.*/
package steps

import org.jbehave.core.annotations.When
import pages.ProductPage

public class ProductPageSteps {

    ProductPage productPage

    @When("I go to the Products page")
    def openProductPage() {
        productPage.openProductPage()
    }
}
