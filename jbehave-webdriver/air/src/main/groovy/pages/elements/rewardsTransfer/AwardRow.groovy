package pages.elements.rewardsTransfer

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import static java.lang.Integer.parseInt

class AwardRow {

    private WebElement awardRow
    private int rowIndex

    private AwardRow(WebElement awardRow) {
        this.awardRow = awardRow
        this.rowIndex = getRowIndex()
    }

    private static def getAllRows(container) {
        container.findElements(By.className("rrTransferAwardsSection"))
    }

    public static AwardRow findAwardRowByName(awardName, container) {
        def rowElement = getAllRows(container).find { it.findElement(By.className("awardName")).text == awardName }
        return rowElement != null ? new AwardRow(rowElement) : null
    }

    public static AwardRow findSelectedAwardRow(container) {
        def rowElement = getAllRows(container).find { it.getAttribute("class").contains("rewardsTransferEntryDisplayHighlightBox") }
        return rowElement != null ? new AwardRow(rowElement) : null
    }

    private int getRowIndex() {
        String id = awardRow.getAttribute("id")
        return parseInt(id.replace("awardRow", ""))
    }

    public void select() {
        awardRow.findElement(By.id("awardRadioButton" + rowIndex)).click()
    }

    public String getAwardTransferValueText() {
        awardRow.findElement(By.className("awardTransferValue")).text
    }

}
