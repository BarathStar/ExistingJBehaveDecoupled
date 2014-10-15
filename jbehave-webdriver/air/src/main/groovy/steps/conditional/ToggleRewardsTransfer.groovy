package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import util.ToggleJmx
import state.RewardsTransferOnOrOff


class ToggleRewardsTransfer {

  private boolean rewardsTransferOn

  RewardsTransferOnOrOff rewardsTransferToggle

  @BeforeStories
  def rewardsTransferOn() {
    String rewardsTransferToggle = System.getProperty("CURRENCY_TRANSFER")
    if (rewardsTransferToggle != null) {
        new ToggleJmx().toggleOn('CURRENCY_TRANSFER', 'WEB')
        new ToggleJmx().toggleOn('CURRENCY_TRANSFER', 'SERVICE')
        rewardsTransferOn = true;
        this.rewardsTransferToggle.state = true
    }
  }

  @AfterStories
  def toggle_off_if_on() {
    if (rewardsTransferOn) {
      new ToggleJmx().toggleOff('CURRENCY_TRANSFER', 'WEB')
      new ToggleJmx().toggleOff('CURRENCY_TRANSFER', 'SERVICE')
      rewardsTransferToggle.state == false;
    }
  }

}
