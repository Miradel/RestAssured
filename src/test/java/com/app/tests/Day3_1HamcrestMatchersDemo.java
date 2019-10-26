package com.app.tests;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class Day3_1HamcrestMatchersDemo {

    // Hamcrest library provide matchers (Assertion method)

    @Test
    public void test(){
        assertThat(4,equalTo(2*2));

        String st1 = "miradel";
        String st2 = "miradel";
        assertThat(st1,is("miradel"));
        assertThat(st1,is(st2));

        assertThat(st1,is(not("mira")));

        assertThat(st1,equalToIgnoringCase("Miradel"));

        assertThat(st1,equalToIgnoringWhiteSpace(" miradel"));

        // compare number
        assertThat(10,greaterThan(3));
        assertThat(10,lessThan(19));
        assertThat(10,lessThanOrEqualTo(10));

        // CMD+D ==> duplicate line

        assertThat(st1,notNullValue());

        List<String> list = Arrays.asList("one","two","three");
        assertThat(list, hasSize(3));

        assertThat(list,containsInAnyOrder("two","three","one"));
        assertThat(list,hasItem("one"));

        List<Integer> num = Arrays.asList(11,12,13);
        assertThat(num,everyItem(greaterThan(4)));
    }
}
