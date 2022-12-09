package com.robbit.askid.test;

import com.robbit.askid.Controllers.AuthorizationController;
import org.junit.Assert;

public class AuthorizationControllerTest {

    @org.junit.Test
    public void getMd5() {
        String correctPassword = "32316a4f55bb533b0c12855c2e48b211";
        String noCorrectPasswordOne = "b2b092b0c2c114da98ad1a1229501c53";
        String noCorrectPasswordTwo = "aac94780b0160a825b738df1bd17e0ea";

        String result = AuthorizationController.getMd5("global123");
        Assert.assertEquals(noCorrectPasswordTwo, result);

    }
}