package util

import javax.naming.ServiceUnavailableException

class JmxCacheApi extends JmxApi {

    private def reloadCache() {
        try {
            def mbeanServer = getMBeanServerConnection()
            def mbean = new GroovyMBean(mbeanServer, "com.swacorp.service.StationInfoCacheManager")
            def operation = 'reloadCache'
            mbean.invokeMethod(operation, new ArrayList().toArray())
        } catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            throw serviceException
        }
    }

}
