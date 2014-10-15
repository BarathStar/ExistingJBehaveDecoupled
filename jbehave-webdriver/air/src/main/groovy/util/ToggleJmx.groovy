package util

import javax.management.remote.JMXConnectorFactory as JmxFactory
import javax.management.remote.JMXServiceURL as JmxUrl

import javax.naming.ServiceUnavailableException
import org.apache.commons.lang.StringUtils

class ToggleJmx {

    private static final def HOST_PROPERTY = "domainToTest"
    private static final def WEB_PORT_PROPERTY = "jmxPort"
    private static final def SVC_PORT_PROPERTY = "serviceJmxPort"
    private static final def DEFAULT_HOST = "local.swacorp.com"
    private static final def WEB_DEFAULT_PORT = "9660"
    private static final def SVC_DEFAULT_PORT = "8988"
    private def TIER

    def getMBeanServer() {
        def serverUrl = 'service:jmx:rmi:///jndi/rmi://' + jmxHostAndPort() + '/jmxrmi'
        JmxFactory.connect(new JmxUrl(serverUrl)).MBeanServerConnection
    }

    def jmxHostAndPort() {
        final String hostProperty = getProperty(HOST_PROPERTY, DEFAULT_HOST)
        final String portProperty = TIER == "WEB" ? getProperty(WEB_PORT_PROPERTY, WEB_DEFAULT_PORT) : getProperty(SVC_PORT_PROPERTY, SVC_DEFAULT_PORT)

        return hostProperty + ':' + portProperty
    }
    def int retrieveCreateReservationMetricValue() {
        try {
            def mbeanServer  = getMBeanServer()
            String[] domains = mbeanServer.getDomains()
            def mbean        = new GroovyMBean(mbeanServer, "Metrics:type=CEBS.ReservationManagement,scope=createReservation,name=totalRequest")
            return mbean.getProperty("Count")
        } catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    def toggle(toggleName) {
        try {
            def mbeanServer = getMBeanServer()
            def mbean = new GroovyMBean(mbeanServer, "com.swacorp.service.environmentManagementService.features:name=" + toggleName)
            def operation = 'toggle'
            mbean.invokeMethod(operation, new ArrayList().toArray())
        } catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    def isOn(toggleName) {
        def mbeanServer = getMBeanServer();
        def mbean = new GroovyMBean(mbeanServer, "com.swacorp.service.environmentManagementService.features:name=" + toggleName)
        Boolean.valueOf(mbean.invokeMethod('isAvailable', new ArrayList().toArray()))
    }

    def isOn(toggleName, def tier) {
        TIER = tier
        isOn(toggleName)
    }

    def isOff(toggleName) {
        !isOn(toggleName)
    }

    String getProperty(String propertyName, String defaultValue) {
        String propertyValue = System.getProperty(propertyName)
        return StringUtils.isNotBlank(propertyValue) ? propertyValue : defaultValue
    }

    def toggleOn(def toggleName, def tier) {
        TIER = tier
        if (!isOn(toggleName)) {
            toggle(toggleName)
        }
    }

    def toggleOff(def toggleName, def tier) {
        TIER = tier
        if (isOn(toggleName)) {
            toggle(toggleName)
        }
    }

    def maintenanceToggles(def operation, def parameter) {
        TIER = "WEB"
        try {
            def mbeanServer = getMBeanServer()
            def mbean = new GroovyMBean(mbeanServer, "com.swacorp.dotcom:name=MaintenanceMode")
            def argList = new ArrayList()
            argList.add(parameter)
            argList = argList.toArray()
            mbean.invokeMethod(operation, argList)
        } catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    def resetServiceMetricsForTier(String tierName){
        TIER = tierName
        try {
            def mbeanServer = getMBeanServer()
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

    def reloadRoutingCacheForServiceTier() {
        TIER = "SERVICE"
        try {
            def mbeanServer = getMBeanServer()
            def mbean = new GroovyMBean(mbeanServer, "com.swacorp.service:name=RoutingInfoCacheManager")
            def argList = new Object[0]
            def operation = "reloadCache"
            mbean.invokeMethod(operation, argList)
        }
        catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    def reloadRoutingCacheForWebTier() {
        TIER = "WEB"
        try {
            def mbeanServer = getMBeanServer()
            def mbean = new GroovyMBean(mbeanServer, "com.swacorp.dotcom:name=RoutingInfoCacheJMXManager")
            def argList = new Object[0]
            def operation = "reloadCache"
            mbean.invokeMethod(operation, argList)
        }
        catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    def reloadStationCacheForServiceTier() {
        TIER = "SERVICE"
        try {
            def mbeanServer = getMBeanServer()
            def mbean = new GroovyMBean(mbeanServer, "com.swacorp.service:name=StationInfoCacheManager")
            def argList = new Object[0]
            def operation = "reloadCache"
            mbean.invokeMethod(operation, argList)
        }
        catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    def reloadStationCacheForWebTier() {
        TIER = "WEB"
        try {
            def mbeanServer = getMBeanServer()
            def mbean = new GroovyMBean(mbeanServer, "com.swacorp.dotcom:name=StationInfoCacheJMXManager")
            def argList = new Object[0]
            def operation = "reloadCache"
            mbean.invokeMethod(operation, argList)
        }
        catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    def reloadRoutesCacheForWebTier() {
        TIER = "WEB"
        try {
            def mbeanServer = getMBeanServer()
            def mbean = new GroovyMBean(mbeanServer, "com.swacorp.dotcom:name=RouteCacheJMXManager")
            def argList = new Object[0]
            def operation = "reloadCache"
            mbean.invokeMethod(operation, argList)
        }
        catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

    def reloadRoutesCacheForServiceTier() {
        TIER = "SERVICE"
        try {
            def mbeanServer = getMBeanServer()
            def mbean = new GroovyMBean(mbeanServer, "com.swacorp.service:name=RouteCacheClobService")
            def argList = new Object[0]
            def operation = "reloadCache"
            mbean.invokeMethod(operation, argList)
        }
        catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

}
