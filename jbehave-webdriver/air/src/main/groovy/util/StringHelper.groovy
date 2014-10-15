package util

class StringHelper {

    final static String alphabet="abcdefghijklmnopqrstuvwxyz"
    static Random charSelector = new Random()

    public static String getRandomString(int length = 3) {
        def randomString = ""
        length.times {
            randomString += alphabet.getAt(charSelector.nextInt(alphabet.length()))
        }
        return randomString.toString()
    }
}