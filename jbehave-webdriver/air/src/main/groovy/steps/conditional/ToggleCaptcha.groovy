package steps.conditional

import util.ToggleJmx
import org.jbehave.core.annotations.AfterStories
import org.jbehave.core.annotations.BeforeStories
import state.ToggleCaptchaOff

class ToggleCaptcha {

  private boolean toggleCaptchaOff

  def ToggleCaptchaOff toggleCaptcha;

  @BeforeStories
  def captchaOff() {
    String CaptchaOffToggle = System.getProperty("CAPTCHA_OFF")
    if (CaptchaOffToggle != null) {
        new ToggleJmx().toggleOff('CAPTCHA_WIDGET', 'WEB')
        new ToggleJmx().toggleOff('CAPTCHA_WIDGET', 'SERVICE')
        toggleCaptchaOff = true;
        toggleCaptcha.state = true;
    }
  }

  @AfterStories
  def toggle_on_if_off() {
    if (toggleCaptchaOff) {
      new ToggleJmx().toggleOn('CAPTCHA_WIDGET', 'WEB')
      new ToggleJmx().toggleOn('CAPTCHA_WIDGET', 'SERVICE')
      toggleCaptcha.state = false;
    }
  }

}
