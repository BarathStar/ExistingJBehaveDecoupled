package pages

import java.text.MessageFormat
import java.lang.reflect.Array

/**
 * this is the artifact index page - used to provide access to per-story artifacts (log, screenshots, video)
 * A pojo approach to page building may soon get unwieldy but we will stick with it for now
 *
 */
final public class ArtifactIndex {
    public static final MessageFormat basicPage = new MessageFormat("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> <html> {0} {1} </html>")
    public static final MessageFormat headTag = new MessageFormat("<head> {0} {1} </head>")
    public static final MessageFormat h1Tag = new MessageFormat("<h1> {0} </h1>")
    public static final MessageFormat h2Tag = new MessageFormat("<h2> {0} </h2>")
    public static final MessageFormat titleTag = new MessageFormat("<title> {0} </title>")
    public static final MessageFormat bodyTag = new MessageFormat("<body> {0} </body>")
    public static final MessageFormat anchorTag = new MessageFormat("<a href={0} > {1} </a>")
    public static final String breakTag = "<br />"
    public static final String logLink = "<a href={0} > {1} </a>";
    public static final String buildUrl = System.getenv().get("BUILD_URL");

    private static final String emptyContent = "<h1> Empty Content </h1>";
    public static final String screenShotHdrValue = "Screen shots : "
    public static final String videoLabel = "Story Video"
    private String titleValue = {"ArtifactIndex"}
    private List<String> pageContent = new ArrayList<String>();
    private ScreenshotGallery screenshotGallery = null;

    public ArtifactIndex() {/* nothing here yet */}
    public ArtifactIndex(String title){
        this()
        titleValue = title
        addcontentH1(logLink);
    }

    final public void addTitle(String s){
        this.titleValue = s;
    }
    final public void addcontentH1(String h1) {
        String[] formatArgs = Array.newInstance(String.class, 2);
        formatArgs[0] = h1
        String pageH1 = h1Tag.format(formatArgs,new StringBuffer(),null).toString()
        pageContent.add(new StringBuilder(pageH1).toString())
    }
    final public void addcontentH2(String h2) {
        String[] formatArgs = Array.newInstance(String.class, 2);
        formatArgs[0] = h2
        String pageH2 = h2Tag.format(formatArgs,new StringBuffer(),null).toString()
        pageContent.add(new StringBuilder(pageH2).toString())
    }
    final public void addImageGalleryItem(String targetHref){
        if(screenshotGallery == null){
            screenshotGallery = new ScreenshotGallery();
        }
        screenshotGallery.addImage(targetHref)

    }
    final public String createContentLink(String targetHref, String linkText){
        String[] formatArgs = Array.newInstance(String.class, 2);
        formatArgs[0] = targetHref
        formatArgs[1] = linkText
        return anchorTag.format(formatArgs,new StringBuffer(),null).toString()
    }
    final public void addContentLink(String targetHref, String linkText){
        pageContent.add(new StringBuilder(createContentLink(targetHref, linkText)).append(breakTag).toString())
    }

    final private String buildContent() {
        StringBuilder content = new StringBuilder("")
        if (!pageContent.isEmpty()) {
        }
        Iterator<String> contentIterator = pageContent.iterator();
        try {
            content.append(contentIterator.next())
            while (contentIterator.hasNext()) {
                content.append(contentIterator.next())
            }
        } catch (Exception e) {
            e.printStackTrace()
            throw e
        }

        if (screenshotGallery != null) {
            content.append(screenshotGallery.toString());
        }
        return content.toString()
    }

    @Override
    final public String toString(){
        String[] formatArgs = Array.newInstance(String.class, 3);
        formatArgs[0] = titleValue;
        String pageTitle = titleTag.format(formatArgs, new StringBuffer(), null).toString()
        formatArgs[0] = pageTitle
        formatArgs[1] = ScreenshotGallery.imageGalleryStyle
        String pageHead = headTag.format(formatArgs, new StringBuffer(), null).toString()
        StringBuilder bodyContent =  new StringBuilder(buildContent())
        formatArgs[0] = bodyContent.toString()
        String pageBody = bodyTag.format(formatArgs, new StringBuffer(), null)
        formatArgs[0] = pageHead
        formatArgs[1] = pageBody
        String newPage = basicPage.format(formatArgs, new StringBuffer(), null).toString()
        return newPage

    }

    public void addStoryLogLink (String logPath, String text ) {
        String logLocation = logPath;
        if(buildUrl != null){
            logLocation = new StringBuilder(buildUrl).append("artifact/").append(logPath.substring(logPath.indexOf("commerce-project"))).toString();
        }
        String hdrLink = MessageFormat.format(pageContent.remove(0), logLocation, titleValue != null ? text+titleValue : text).toString()
        pageContent.add(0,hdrLink)
    }

    public static final class ScreenshotGallery {
        public static final String DEFAULT_IMG_WIDTH = "690";
        public static final String DEFAULT_IMG_HEIGHT = "880";
        public static final String DEFAULT_IMG_ALT = "\'step title here\'";
        public static final MessageFormat divTag = new MessageFormat("<div id={0}> {1} </div>");
        public static final MessageFormat ulTag = new MessageFormat("<ul class={0} > {1} </ul>")
        public static final MessageFormat liTag = new MessageFormat("<li> {0} </li>")
        public static final MessageFormat imgTag = new MessageFormat("<img src={0} width={1} height={2} alt={3} />")
        public static final MessageFormat spanTag = new MessageFormat("<span class={0}> {1} </span>")

        private List<String> screenImageItems = new ArrayList<String>();

        public final addImage(String hrefImageLink) {
            String[] formatArgs = Array.newInstance(String.class, 8);
            formatArgs[0] = hrefImageLink;
            formatArgs[1] = DEFAULT_IMG_WIDTH;
            formatArgs[2] = DEFAULT_IMG_HEIGHT;
            formatArgs[3] = DEFAULT_IMG_ALT;
            String imageItem = imgTag.format(formatArgs, new StringBuffer(), null).toString();
            formatArgs[0] = imageItem;
            String galleryItem = liTag.format(formatArgs, new StringBuffer(), null).toString();
            screenImageItems.add(galleryItem);
        }
        public final String toString() {
            if(screenImageItems.isEmpty()) return

            String[] formatArgs = Array.newInstance(String.class, 4);
            StringBuilder galleryContentBuilder = new StringBuilder(screenImageItems.remove(0))
            Iterator<String> i = screenImageItems.iterator()
            while(i.hasNext()) {
                galleryContentBuilder.append(i.next())
            }
            String screenShotListItems = galleryContentBuilder.toString();
            formatArgs[0] = "\'slides\'"
            formatArgs[1] = screenShotListItems;
            galleryContentBuilder = new StringBuilder(ulTag.format(formatArgs, new StringBuffer(), null).toString())
            formatArgs[0] = "\'arrow previous\'"
            formatArgs[1] = ""
            galleryContentBuilder.append(spanTag.format(formatArgs, new StringBuffer(), null))
            formatArgs[0] = "\'arrow next\'"
            formatArgs[1] = ""
            galleryContentBuilder.append(spanTag.format(formatArgs,new StringBuffer(), null))

            formatArgs[0] = "\'slideshow\'"
            formatArgs[1] = galleryContentBuilder.toString()
            String galleryDivContainer = divTag.format(formatArgs, new StringBuffer(), null);

            return new StringBuilder(galleryDivContainer).append(imageGalleryScripts).toString()
        }

/*
* scripts and style are here so that we don't have dependency issues;
*/
        public static final String imageGalleryScripts = "<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js\"></script>\n" +
                "<script type=\"text/javascript\">//<![CDATA[\n" +
                "\$(window).load(function(){\n" +
                "\t\n" +
                "\n" +
                "\tvar slides = \$('#slideshow li'),\n" +
                "\t\tcurrent = 0,\n" +
                "\t\tslideshow = {width:0,height:0};\n" +
                "\n" +
                "\tsetTimeout(function(){\n" +
                "\t\t\$('#slideshow .arrow').click(function(){\n" +
                "\t\t\tvar li\t\t\t= slides.eq(current),\n" +
                "\t\t\t\tcanvas\t\t= li.find('canvas'),\n" +
                "\t\t\t\tnextIndex\t= 0;\n" +
                "\n" +
                "\t\t\tif(\$(this).hasClass('next')){\n" +
                "\t\t\t\tnextIndex = current >= slides.length-1 ? 0 : current+1;\n" +
                "\t\t\t}\n" +
                "\t\t\telse {\n" +
                "\t\t\t\tnextIndex = current <= 0 ? slides.length-1 : current-1;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\tvar next = slides.eq(nextIndex);\n" +
                "\t\t\t\n" +
                "\t\t\tcurrent=nextIndex;\n" +
                "\t\t\tnext.addClass('slideActive').show();\n" +
                "\t\t\tli.removeClass('slideActive').hide();\n" +
                "\t\t});\n" +
                "\t\t\n" +
                "\t},100);\n" +
                "\n" +
                "\t\n" +
                "});\n" +
                "\n" +
                "//]]></script> ";
        public static final String imageGalleryStyle = "<style type=\"text/css\">\n" +
                "*{\n" +
                "\tmargin:0;\n" +
                "\tpadding:0;\n" +
                "}\n" +
                "\n" +
                "body{\n" +
                "\tcolor:#eee;\n" +
                "\tbackground:url('http://swa-build-artifacts.s3.amazonaws.com/bg.jpg') repeat-x #0c0d10;\n" +
                "\tfont:13px \"Lucida Sans Unicode\",Arial,Helvetica,sans-serif;\n" +
                "}\n" +
                "a {\n" +
                "\tcolor:white;\n" +
                "}\n" +
                "\n" +
                "#slideshow{\n" +
                "\tbackground-color:#F5F5F5;\n" +
                "\tborder:1px solid #FFFFFF;\n" +
                "\theight:"+Integer.toString(Integer.valueOf(DEFAULT_IMG_HEIGHT)+20)+"px;\n" +
                "\tmargin:32px auto 0;\n" +
                "\tposition:relative;\n" +
                "\twidth:"+Integer.toString(Integer.valueOf(DEFAULT_IMG_WIDTH)+110)+"px;\n" +
                "\t\n" +
                "\t-moz-box-shadow:0 0 22px #111;\n" +
                "\t-webkit-box-shadow:0 0 22px #111;\n" +
                "\tbox-shadow:0 0 22px #111;\n" +
                "}\n" +
                "\n" +
                "#slideshow ul{\n" +
                "\tleft:54px;\n" +
                "\tlist-style:none outside none;\n" +
                "\toverflow:hidden;\n" +
                "\tposition:absolute;\n" +
                "\ttop:10px;\n" +
                "\theight:"+DEFAULT_IMG_HEIGHT+"px;\n" +
                "\twidth:"+DEFAULT_IMG_WIDTH+"px;\n" +
                "}\n" +
                "\n" +
                "#slideshow li{\n" +
                "\tposition:absolute;\n" +
                "\tdisplay:none;\n" +
                "\tz-index:10;\n" +
                "}\n" +
                "\n" +
                "#slideshow li:first-child{\n" +
                "\tdisplay:block;\n" +
                "\tz-index:1000;\n" +
                "}\n" +
                "\n" +
                "#slideshow .slideActive{\n" +
                "\tz-index:1000;\n" +
                "}\n" +
                "\n" +
                "#slideshow canvas{\n" +
                "\tdisplay:none;\n" +
                "\tposition:absolute;\n" +
                "\tz-index:100;\n" +
                "}\n" +
                "\n" +
                "#slideshow .arrow{\n" +
                "\theight:86px;\n" +
                "\twidth:60px;\n" +
                "\tposition:absolute;\n" +
                "\tbackground:url('http://swa-build-artifacts.s3.amazonaws.com/arrows.png') no-repeat;\n" +
                "\ttop:50%;\n" +
                "\tmargin-top:-43px;\n" +
                "\tcursor:pointer;\n" +
                "\tz-index:5000;\n" +
                "}\n" +
                "\n" +
                "#slideshow .previous{ background-position:left top;left:0;}\n" +
                "#slideshow .previous:hover{ background-position:left bottom;}\n" +
                "\n" +
                "#slideshow .next{ background-position:right top;right:0;}\n" +
                "#slideshow .next:hover{ background-position:right bottom;}\n" +
                "\n" +
                "\n" +
                "</style>";
    }
}