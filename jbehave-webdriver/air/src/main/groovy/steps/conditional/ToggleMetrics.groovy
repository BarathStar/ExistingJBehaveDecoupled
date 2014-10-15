package steps.conditional

import util.ToggleJmx

import javax.management.InstanceNotFoundException

/**
 * This toggle is manually set when a fault injection is done by the story
 */
class ToggleMetrics {

    private boolean metricsTogglesOn



    def metricsToggleOn() {
        try {
            new ToggleJmx().toggleOn('COLLECT_METRICS', 'SVC')
            metricsTogglesOn = true
        }
        catch (InstanceNotFoundException jmxInstanceNotFound) {
            // OK - fail quietly
        }
    }

    def toggleOffIfOn() {
        if (metricsTogglesOn) {
            new ToggleJmx().toggleOff('COLLECT_METRICS', 'SVC')
            metricsTogglesOn = false
        }
    }




}