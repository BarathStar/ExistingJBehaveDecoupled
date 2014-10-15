package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories

import state.RewardsTransferHistoryOnOrOff
import util.ToggleJmx

class ToggleRewardTransferHistory {

  private boolean rewardsTransferHistoryOn
  def RewardsTransferHistoryOnOrOff rewardsTransferHistoryOnOrOff

  @BeforeStories
  def rewardsTransferHistoryOn() {

    String rewardsTransferHistoryToggle = System.getProperty("CURRENCY_TRANSFER_HISTORY")

      if (rewardsTransferHistoryToggle != null) {

        def ToggleJmx toggleJmx = new ToggleJmx()

        toggleJmx.toggleOn('CURRENCY_TRANSFER_HISTORY', 'WEB')
        toggleJmx.toggleOn('CURRENCY_TRANSFER_HISTORY', 'SERVICE')
        rewardsTransferHistoryOn = true;
        rewardsTransferHistoryOnOrOff.state = true;
    }
  }

  @AfterStories
  def toggle_off_if_on() {

    if (rewardsTransferHistoryOn) {

      def ToggleJmx toggleJmx = new ToggleJmx()

      toggleJmx.toggleOff('CURRENCY_TRANSFER_HISTORY', 'WEB')
      toggleJmx.toggleOff('CURRENCY_TRANSFER_HISTORY', 'SERVICE')
      rewardsTransferHistoryOnOrOff.state = false;
    }
  }

}
