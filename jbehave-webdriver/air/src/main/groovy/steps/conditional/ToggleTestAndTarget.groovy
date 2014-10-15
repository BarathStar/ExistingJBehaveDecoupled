package steps.conditional

import util.ToggleJmx
import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories

class ToggleTestAndTarget {

    private boolean testAndTargetOff

    @BeforeStories
    def testAndTargetOff() {
      String testAndTargetToggle = System.getProperty("TEST_AND_TARGET_OFF")
      if (testAndTargetToggle != null) {
          new ToggleJmx().toggleOff('TEST_AND_TARGET', 'WEB')
          testAndTargetOff = true;
      }
    }

    @AfterStories
    def testAndTargetOnIfOff() {
      if (testAndTargetOff) {
        new ToggleJmx().toggleOn('TEST_AND_TARGET', 'WEB')
      }
    }


}
