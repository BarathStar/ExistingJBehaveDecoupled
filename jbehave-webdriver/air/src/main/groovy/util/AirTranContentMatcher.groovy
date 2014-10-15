package util

import java.util.regex.Matcher

/**
 * Detect any AirTran content in a string.
 */
class AirTranContentMatcher {

    def private static final AIR_TRAN_CITIES = [
            'Akron', 'Canton', 'Allentown', 'Aruba', 'Asheville', 'Atlanta', 'Atlantic City', 'Bloomington', 'Normal',
            'Branson', 'Cancun', 'Charlotte', 'Ft. Worth', 'Dayton', 'Des Moines', 'Flint', 'Grand Rapids', 'GulfPort',
            'Biloxi', 'Harrisburg', 'Huntsville', 'Key West', 'Knoxville', 'Lexington', 'Memphis', 'Miami', 'Moline',
            'Quad Cities', 'Montego Bay', 'Nassau', 'Newport News', 'Pensacola', 'Portland', 'Punta Cana', 'Richmond',
            'Rochester', 'San Juan', 'Sarasota', 'Bradenton', 'Tunica', 'Washington', 'White Plains', 'Wichita' ]

    def private static final AIR_TRAN_STATION_CODES = [
            'CAK', 'ABE', 'AUA', 'AVL', 'ATL', 'ACY', 'BMI', 'BKG', 'CUN', 'CLT', 'DFW', 'DAY', 'DSM', 'FNT', 'GRR',
            'GPT', 'MDT', 'HSV', 'EYW', 'TYS', 'LEX', 'MEM', 'MIA', 'MLI', 'MBJ', 'NAS', 'PHF', 'PNS', 'PWM', 'PUJ',
            'RIC', 'ROC', 'SJU', 'SRQ', 'UTM', 'DCA', 'HPN', 'ICT' ]

    def private static any(def collection) {
        '(' + collection.collect {it.replaceAll('\\s+', '\\\\s*')}.join('|') + ')'
    }

    def private static nonSubstring(def collection) {
        collection.collect { "\\b$it\\b" }
    }

    def private static final AIR_TRAN_REGEX = [
            'Air Tran Text':         /(?im)^.*?(air\s*tran).*/,
            'Air Tran City':         /(?im)^.*${any(AIR_TRAN_CITIES)}.*/,
            'Air Tran Station Code': /(?im)^.*${any(nonSubstring(AIR_TRAN_STATION_CODES))}.*/
    ]

    /**
     * @param source to look for Air Tran content in.
     * @return A collection of all matched content. This may be used as expected in a boolean context because Groovy
     * evaluates non-empty collections as TRUE and empty collections as FALSE.
     */
    def matches(String source) {
        def result = new Matches()
        AIR_TRAN_REGEX.each { regex ->
            def matcher = (source =~ regex.value)
            while (matcher.find()) {
                result.add(regex.key, matcher, source)
            }
        }
        result
    }

    /**
     * Collection of AirTran content matches. Generates a textual report of all AirTran content found, including
     * the category, matched text, and contextual clues as to its location in the source.
     */
    public class Matches extends ArrayList {
        def add(String category, Matcher matcher, String source) {
            add(new Match(category, matcher, source))
        }

        def report() {
            if (empty) {
                "No Air Tran content found."
            }
            else {
                def b = new StringBuilder()
                each {
                    b.append("${it.category()} \"${it.content()}\" found in \"${it.context()}\"\n")
                }
                b.deleteCharAt(b.length() - 1)
                b.toString()
            }
        }

        /**
         * AirTran content match.
         */
        public class Match {
            def private category
            def private content
            def private matchStartingIndex
            def private source

            def Match(def category, def matcher, def source) {
                this.category = category
                this.content = matcher.group(1)
                this.matchStartingIndex = matcher.start(1)
                this.source = source
            }

            def category() {
                category
            }

            def content() {
                content
            }

            def context() {
                context(25)
            }

            def context(def range) {
                def start = withLowerBound(matchStartingIndex - range, 0)
                def end = withUpperBound(matchStartingIndex + content.length() + range, source.length() - 1)
                source[start..end].trim()
            }

            def private withLowerBound(def value, def lowerBound) {
                value >= lowerBound ? value : lowerBound
            }

            def private withUpperBound(def value, def upperBound) {
                value <= upperBound ? value : upperBound
            }
        }
    }
}
