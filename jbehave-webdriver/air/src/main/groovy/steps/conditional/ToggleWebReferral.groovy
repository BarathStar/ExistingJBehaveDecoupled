package steps.conditional

import util.ToggleJmx
import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import state.WebReferralOnOrOff

class ToggleWebReferral {

  private boolean webReferralToggleOn

  def WebReferralOnOrOff webReferral;

  @BeforeStories
  def webReferralOn() {
    String webRefToggle = System.getProperty("WEBREF_TOGGLE")
    if (webRefToggle != null) {
        new ToggleJmx().toggleOn('WEB_REFERRAL', 'WEB')
        new ToggleJmx().toggleOn('WEB_REFERRAL', 'SERVICE')
        webReferralToggleOn = true;
        webReferral.state = true;
    }

  }

  @AfterStories
  def toggle_off_if_on() {
    if (webReferralToggleOn) {
      new ToggleJmx().toggleOff('WEB_REFERRAL', 'WEB')
      new ToggleJmx().toggleOff('WEB_REFERRAL', 'SERVICE')
    }
  }

}
