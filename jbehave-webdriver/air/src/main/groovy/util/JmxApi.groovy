package util

import javax.management.remote.JMXConnectorFactory
import javax.management.remote.JMXServiceURL


class JmxApi {

    private static final def HOST_PROPERTY = "domainToTest"
    private static final def WEB_PORT_PROPERTY = "jmxPort"
    private static final def SVC_PORT_PROPERTY = "serviceJmxPort"
    private static final def DEFAULT_HOST = "local.swacorp.com"
    private static final def WEB_DEFAULT_PORT = "9660"
    private static final def SVC_DEFAULT_PORT = "8988"
    protected def TIER

    protected def getMBeanServerConnection() {
        def serverUrl = 'service:jmx:rmi:///jndi/rmi://' + jmxHostAndPort() + '/jmxrmi'
        JMXConnectorFactory.connect(new JMXServiceURL(serverUrl)).MBeanServerConnection
    }

    private def jmxHostAndPort() {
        final String hostProperty = System.getProperty(HOST_PROPERTY, DEFAULT_HOST)
        final String portProperty = TIER == "WEB" ? System.getProperty(WEB_PORT_PROPERTY, WEB_DEFAULT_PORT) : System.getProperty(SVC_PORT_PROPERTY, SVC_DEFAULT_PORT)

        return hostProperty + ':' + portProperty
    }

}
