package util;

import org.junit.Assert;
import org.junit.Test;

public class NumberToWordsTest {
    @Test
    public void test_convert_one() {
        Assert.assertEquals("one", NumberToWords.convert(1));
    }

    @Test
    public void test_convert_twelve() {
        Assert.assertEquals("twelve", NumberToWords.convert(12));
    }

    @Test
    public void test_convert_forty() {
        Assert.assertEquals("forty", NumberToWords.convert(40));
    }

    @Test
    public void test_convert_forty_five() {
        Assert.assertEquals("forty five", NumberToWords.convert(45));
    }

    @Test
    public void test_convert_one_hundred() {
        Assert.assertEquals("one hundred", NumberToWords.convert(100));
    }

    @Test
    public void test_convert_one_hundred_forty_five() {
        Assert.assertEquals("one hundred forty five", NumberToWords.convert(145));
    }

    @Test
    public void test_convert_two_hundred_forty_five() {
        Assert.assertEquals("two hundred forty five", NumberToWords.convert(245));
    }

    @Test
    public void test_convert_one_thousand() {
        Assert.assertEquals("one thousand", NumberToWords.convert(1000));
    }

    @Test
    public void test_convert_nine_hundred_ninety_nine() {
        Assert.assertEquals("nine hundred ninety nine", NumberToWords.convert(999));
    }

    @Test
    public void test_convert_one_hundred_forty_five_thousand_two_hundred_forty_five() {
        Assert.assertEquals("one hundred forty five thousand two hundred forty five", NumberToWords.convert(145245));
    }

    @Test
    public void test_convert_nine_hundred_ninety_nine_thousand_nine_hundred_ninety_nine() {
        Assert.assertEquals("nine hundred ninety nine thousand nine hundred ninety nine", NumberToWords.convert(999999));
    }

    @Test
    public void test_convert_one_million() {
        Assert.assertEquals("one million", NumberToWords.convert(1000000));
    }

    @Test
    public void test_convert_one_million_nine_hundred_ninety_nine_thousand_nine_hundred_ninety_nine() {
        Assert.assertEquals("one million nine hundred ninety nine thousand nine hundred ninety nine", NumberToWords.convert(1999999));
    }

    @Test
    public void test_convert_nine_hundred_ninety_nine_million_nine_hundred_ninety_nine_thousand_nine_hundred_ninety_nine() {
        Assert.assertEquals("nine hundred ninety nine million nine hundred ninety nine thousand nine hundred ninety nine", NumberToWords.convert(999999999));
    }

}
