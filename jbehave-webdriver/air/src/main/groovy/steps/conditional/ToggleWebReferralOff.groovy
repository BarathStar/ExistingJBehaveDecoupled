package steps.conditional

import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import state.WebReferralOnOrOff
import util.ToggleJmx

class ToggleWebReferralOff {

  private boolean webReferralToggleOff

  @BeforeStories
  def webReferralOff() {
    String webRefToggle = System.getProperty("WEBREFOFF_TOGGLE")
    if (webRefToggle != null) {
        new ToggleJmx().toggleOff('WEB_REFERRAL', 'WEB')
        new ToggleJmx().toggleOff('WEB_REFERRAL', 'SERVICE')
        webReferralToggleOff = true;
    }
  }

  @AfterStories
  def toggle_on_if_off() {
    if (webReferralToggleOff) {
      new ToggleJmx().toggleOn('WEB_REFERRAL', 'WEB')
      new ToggleJmx().toggleOn('WEB_REFERRAL', 'SERVICE')
    }
  }

}
