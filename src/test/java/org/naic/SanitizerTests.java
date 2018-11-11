/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SanitizerTests {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sqlKeywordsNotAllowed() {
        String keyword = "select";
        boolean isValid = Sanitizer.SanitizeInput(keyword);
        assertThat(isValid, is(false));

        keyword = "drop";
        isValid = Sanitizer.SanitizeInput(keyword);
        assertThat(isValid, is(false));

        keyword = "alter";
        isValid = Sanitizer.SanitizeInput(keyword);
        assertThat(isValid, is(false));

        keyword = "delete";
        isValid = Sanitizer.SanitizeInput(keyword);
        assertThat(isValid, is(false));

        keyword = "exec";
        isValid = Sanitizer.SanitizeInput(keyword);
        assertThat(isValid, is(false));

    }

    @Test
    public void nameOrEmailOrPhoneAllowed() {
        String keyword = "german";
        boolean isValid = Sanitizer.SanitizeInput(keyword);
        assertThat(isValid, is(true));

        keyword = "germanlph@gmail.com";
        isValid = Sanitizer.SanitizeInput(keyword);
        assertThat(isValid, is(true));

        keyword = "(913) 375-8802";
        isValid = Sanitizer.SanitizeInput(keyword);
        assertThat(isValid, is(true));
    }
}


