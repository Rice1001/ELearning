package com.example.elearning;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RegisterActivityTest {

    @Test
    public void isEmail() {
        assertThat(RegisterActivity.isEmail("12330@"),is(true));
    }
}