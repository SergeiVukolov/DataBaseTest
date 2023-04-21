package com.a1qa.v1task.utils;

import aquality.selenium.browser.AqualityServices;
import com.a1qa.v1task.models.FieldsData;
import org.openqa.selenium.Cookie;

public class Authorization {
    private static FieldsData fieldsData = JsonHelper.getJsonData(JsonHelper.getValueFromJson("pathToFieldsData"), FieldsData.class);

    public static void sendTokenAsCookie(String token) {
        Cookie cookie = new Cookie(fieldsData.getFieldToken(), token);
        AqualityServices.getBrowser().getDriver().manage().addCookie(cookie);
    }

}
