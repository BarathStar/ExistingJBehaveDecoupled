package util


class NumberToWords {
    private static final List<String> numNames = ["zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"]

    private static final List<String> tensNames = ["",
            "ten",
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"]

    private static String convertLessThanTwenty(int number) {
        if (number < 0) throw new IllegalArgumentException("$number is too small")
        if (number > 20) throw new IllegalArgumentException("$number is too big")
        return numNames.get(number)
    }

    private static String convertLessThanOneHundred(int number) {
        if (number > 99) throw new IllegalArgumentException("$number is too big")

        if (number < 20)
            return convertLessThanTwenty(number)

        int tens = number / 10;
        int remainder = number % 10;

        String tensString = tensNames.get(tens);
        String remainderString = (remainder == 0) ? '' : " " + convertLessThanTwenty(remainder);

        return tensString + remainderString;
    }

    private static String convertLessThanOneThousand(int number) {
        if (number > 999) throw new IllegalArgumentException("$number is too big")

        if (number < 100)
            return convertLessThanOneHundred(number);

        int hundreds = number / 100;
        int remainder = number % 100;

        String remainderString = (remainder == 0) ? '' : " " + convertLessThanOneHundred(remainder);
        String hundredsString = convertLessThanTwenty(hundreds) + " hundred"

        return hundredsString + remainderString;
    }

    private static String convertLessThanOneMillion(int number) {
        if (number > 999999) throw new IllegalArgumentException("$number is too big")

        if (number < 1000)
            return convertLessThanOneThousand(number);

        int thousands = number / 1000;
        int remainder = number % 1000;

        String remainderString = (remainder == 0) ? '' : " " + convertLessThanOneThousand(remainder);
        String thousandsString = convertLessThanOneThousand(thousands) + " thousand";

        return thousandsString + remainderString;
    }


    private static String convertLessThanOneBillion(int number) {
        if (number > 999999999) throw new IllegalArgumentException("$number is too big")

        if (number < 1000000)
            return convertLessThanOneMillion(number);

        int millions = number / 1000000;
        int remainder = number % 1000000;

        String remainderString = (remainder == 0) ? '' : " " + convertLessThanOneMillion(remainder);
        String millionsString = convertLessThanOneThousand(millions) + " million";

        return millionsString + remainderString;
    }

    public static String convert(int number) {
        return convertLessThanOneBillion(number);
    }

}
