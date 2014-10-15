package util

import org.junit.Assert
import org.junit.Ignore
import org.junit.Test


public class YSlowBeaconRequestTest {

    @Test
    @Ignore
    public void testGetRequestedRelativePath_ShouldReturnUrl_WhenCalledOnHomepage() {
        YSlowBeaconRequest ySlowBeaconRequest = new YSlowBeaconRequest(null) {
            @Override protected String getContent() {
                return '{"u":"http%3A%2F%2Fvip4.swacorp.com%2F"}'
            }
        }

        String expectedRelativeUrl = "/"
        String returnedRelativeUrl = ySlowBeaconRequest.getRequestedRelativePath()

        Assert.assertEquals(expectedRelativeUrl, returnedRelativeUrl)
    }

    @Test
    @Ignore
    public void getArchiveFile_shouldGenerateFilenameForFlightPage() {
        YSlowBeaconRequest ySlowBeaconRequest = new YSlowBeaconRequest(null) {
            @Override protected String getContent() {
                return '{"u":"https%3A%2F%2Fecom-dev-g.swacorp.com%2Freservations%2Fbook-reservations.html%3Fdisc%3D0%253A1%253A1304538628.336000%253A779%25406FC2B7C1964BA214FBA068950B59F0BC5375116C%26ss%3D0%26int%3D%26companyName%3D%26cid%3D"}'
            }
        }
        File file = ySlowBeaconRequest.getArchiveFile()

        // TODO: figure out the counter!
        Assert.assertEquals("0-reservations.book-reservations.html.json", file.name);
    }


    @Test
    @Ignore
    public void getArchiveFile_shouldGenerateFilenameForHomepage() {
        YSlowBeaconRequest ySlowBeaconRequest = new YSlowBeaconRequest(null) {
            @Override protected String getContent() {
                return '{"u":"https%3A%2F%2Fecom-dev-g.swacorp.com%2F"}'
            }

        }
        File file = ySlowBeaconRequest.getArchiveFile()

        // TODO: figure out the counter!
        Assert.assertEquals("1-homepage.json", file.name);
    }

    @Test
    @Ignore
    public void getArchiveFile_shouldGenerateFilenameForUrlWithTrailingSlash() {
        YSlowBeaconRequest ySlowBeaconRequest = new YSlowBeaconRequest(null) {
            @Override protected String getContent() {
                return '{"u":"http%3A%2F%2Fecom-dev-g.swacorp.com%2Fflight%2F"}'
            }

        }
        File file = ySlowBeaconRequest.getArchiveFile()

        // TODO: figure out the counter!
        Assert.assertEquals("2-flight.json", file.name);
    }

    @Test
    @Ignore
    public void getArchiveFile_shouldGenerateFilenameForUrlWithJsession() {
        YSlowBeaconRequest ySlowBeaconRequest = new YSlowBeaconRequest(null) {
            @Override protected String getContent() {
                return '{"u":"http%3A%2F%2Fecom-dev-g.swacorp.com%2Freservations%2Fbook-reservations.html;jsessionid=vhrg2xom75io"}'
            }

        }
        File file = ySlowBeaconRequest.getArchiveFile()

        // TODO: figure out the counter!
        Assert.assertEquals("3-reservations.book-reservations.html.json", file.name);
    }


}