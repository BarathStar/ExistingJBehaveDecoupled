package util

import org.apache.commons.lang.StringUtils

import javax.naming.ServiceUnavailableException

class JmxMetricsApi extends JmxApi {

    def resetMetrics() {
        def toggleBean = new ToggleJmx()
        toggleBean.resetServiceMetrics()
        toggleBean.resetWebMetrics()
    }

    def int retrieveCreateReservationMetricValue() {
        try {
            def mbeanServer  = getMBeanServerConnection()
            def mbean        = new GroovyMBean(mbeanServer, "Metrics:type=CEBS.ReservationManagement,scope=createReservation,name=totalRequest")
            return mbean.getProperty("Count")
        } catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    int retrieveWebReservationMetricValue() {
        TIER = "WEB"
        try {
            def mbeanServer = getMBeanServerConnection()
            String[] domains = mbeanServer.getDomains()
            def mbean = new GroovyMBean(mbeanServer, "Metrics:type=MULE.purchase,scope=getConfirmationGroup,name=success")
            return mbean.getProperty("Count")
        } catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    def int retrieveGetConfirmationGroupMetricValue() {
        try {
            def mbeanServer  = getMBeanServerConnection()
            def mbean        = new GroovyMBean(mbeanServer, "Metrics:type=MULE.Purchase,scope=getConfirmationGroup,name=totalRequest")
            return mbean.getProperty("Count")
        } catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    private def toggle(toggleName) {
        try {
            def mbeanServer = getMBeanServerConnection()
            def mbean = new GroovyMBean(mbeanServer, "com.swacorp.service.environmentManagementService.features:name=" + toggleName)
            def operation = 'toggle'
            mbean.invokeMethod(operation, new ArrayList().toArray())
        } catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    def isSvcMetricsOn(toggleName) {
        def mbeanServer = getMBeanServerConnection();
        def mbean = new GroovyMBean(mbeanServer, "com.swacorp.service.environmentManagementService.features:name=" + toggleName)
        Boolean.valueOf(mbean.invokeMethod('isAvailable', new ArrayList().toArray()))
    }

    def isOff(toggleName) {
        !isSvcMetricsOn(toggleName)
    }

    private String getProperty(String propertyName, String defaultValue) {
        String propertyValue = System.getProperty(propertyName)
        return StringUtils.isNotBlank(propertyValue) ? propertyValue : defaultValue
    }

    def toggleOn(def toggleName, def tier) {
        TIER = tier
        if (!isSvcMetricsOn(toggleName)) {
            toggle(toggleName)
        }
    }

    def toggleOff(def toggleName, def tier) {
        TIER = tier
        if (isSvcMetricsOn(toggleName)) {
            toggle(toggleName)
        }
    }


    private def resetServiceMetricsForTier(String tierName){
        TIER = tierName
        try {
            def mbeanServer = getMBeanServerConnection()
            def mbean       = new GroovyMBean(mbeanServer, "com.swacorp.common.logging:name=MetricsUtility")
            def argList     = new Object[0]
            def operation   = "resetMetrics"
            mbean.invokeMethod(operation, argList)
        } catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    def resetServiceMetrics() {
        resetServiceMetricsForTier("SERVICE")
    }

    def resetWebMetrics() {
        resetServiceMetricsForTier("WEB")
    }


}
