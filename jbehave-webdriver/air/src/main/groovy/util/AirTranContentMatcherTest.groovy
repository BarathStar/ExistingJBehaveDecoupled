package util;

import junit.framework.TestCase
import org.junit.Test
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.greaterThan;

public class AirTranContentMatcherTest extends TestCase {

    private AirTranContentMatcher matcher = new AirTranContentMatcher()

    public void test_should_not_detect_any_mention_of_air_tran_unless_its_present() throws Exception {
        assertThat(matcher.matches(pageWith('no content from that new airline')).size(), is(0))
    }

    public void test_should_detect_any_mention_of_air_tran() throws Exception {
        [ 'airtran', 'Air Tran', 'air tran', 'Air Tran', 'aiR Tran', 'AIRTRAN', '<span class="airTran">' ].each {
            def page = pageWith(it)
            assertThat("'$page' should have been detected", matcher.matches(page).size(), is(greaterThan(0)))
        }
    }

    public void test_should_detect_an_air_tran_station_name() throws Exception {
        [ 'Memphis', 'Charlotte', 'montego Bay', 'MONTEGOBAY', "Montego\t Bay"].each {
            def page = pageWith(it)
            assertThat("'$page' should have been detected", matcher.matches(page).size(), is(greaterThan(0)))
        }
    }

    public void test_should_detect_an_air_tran_station_code() throws Exception {
        [ 'SRQ', 'DSM', 'srq'].each {
            def page = pageWith(it)
            assertThat("'$page' should have been detected", matcher.matches(page).size(), is(greaterThan(0)))
        }
    }

    public void test_should_not_detect_an_air_tran_station_code_when_it_is_a_substring() throws Exception {
        [ 'SRQDSM', 'srqdsm' ].each {
            def page = pageWith(it)
            assertThat("'$page' should not have been detected", matcher.matches(page).size(), is(0))
        }
    }

    public void test_should_report_the_any_air_tran_text_matched_category() throws Exception {
        def page = pageWith('something surrounding Air\n Tran content')
        assertThat(matcher.matches(page).first().category(), is('Air Tran Text'))
    }

    public void test_should_report_the_any_air_tran_city_matched_category() throws Exception {
        def page = pageWith('something surrounding Memphis content')
        assertThat(matcher.matches(page).first().category(), is('Air Tran City'))
    }

    public void test_should_report_the_any_air_tran_station_code_matched_category() throws Exception {
        def page = pageWith('something surrounding ATL content')
        matcher.matches(page)
        assertThat(matcher.matches(page).first().category(), is('Air Tran Station Code'))
    }

    public void test_should_report_any_air_tran_text_matched_content() throws Exception {
        def page = pageWith('something surrounding \nAir Tran content')
        matcher.matches(page)
        assertThat(matcher.matches(page).first().content(), is('Air Tran'))
    }

    public void test_should_report_any_air_tran_city_matched_content() throws Exception {
        def page = pageWith('something surrounding Montego Bay content')
        matcher.matches(page)
        assertThat(matcher.matches(page).first().content(), is('Montego Bay'))
    }

    public void test_should_report_any_air_tran_station_code_matched_content() throws Exception {
        def page = pageWith('something surrounding ATL content')
        matcher.matches(page)
        assertThat(matcher.matches(page).first().content(), is('ATL'))
    }

    public void test_should_build_report_for_all_groups() throws Exception {
        def page = pageWith('something surrounding ATL content and a little Air Tran for taste')
        assertThat(matcher.matches(page).report(), is(
                "Air Tran Text \"Air Tran\" found in \"ATL content and a little Air Tran for taste</body></html>\"\n" +
                "Air Tran Station Code \"ATL\" found in \"dy>something surrounding ATL content and a little Air\""))
    }

    public void test_should_not_have_leading_or_trailing_whitespace_in_the_context() throws Exception {
        def page = pageWith('something surrounding Montego Bay\n                               content')
        matcher.matches(page)
        assertThat(matcher.matches(page).first().context(), is('dy>something surrounding Montego Bay'))
    }

    private String pageWith(String content) {
        "<html><body>$content</body></html>"
    }
}