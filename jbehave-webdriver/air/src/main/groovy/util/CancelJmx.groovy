package util

import javax.management.remote.JMXConnectorFactory as JmxFactory
import javax.management.remote.JMXServiceURL as JmxUrl

import javax.naming.ServiceUnavailableException
import org.apache.commons.lang.StringUtils

class CancelJmx {

    private static final def HOST_PROPERTY = "domainToTest"
    private static final def SVC_PORT_PROPERTY = "serviceJmxPort"
    private static final def DEFAULT_HOST = "local.swacorp.com"
    private static final def SVC_DEFAULT_PORT = "8988"

    // This is something of a hack to list these here but
    // we need to know the mapping from web to svc and it is
    // not standard across all the environments.
    private static final def WEB_TO_SERVER_NAME_MAP = [
            "ecom-dev-web-a.swacorp.com" : "ecom-dev-svc-a",
            "ecom-dev-web-b.swacorp.com" : "ecom-dev-svc-b",
            "ecom-dev-web-c.swacorp.com" : "ecom-dev-svc-c",
            "ecom-dev-web-d.swacorp.com" : "ecom-dev-svc-d",
            "ecom-dev-web-e.swacorp.com" : "ecom-dev-svc-e",
            "ecom-dev-f.swacorp.com" : "ecom-dev-svc-f",
            "ecom-dev-g.swacorp.com" : "ecom-dev-svc-g",
            "ecom-dev-h.swacorp.com" : "ecom-dev-svc-h",
            "ecom-dev-i.swacorp.com" : "ecom-dev-svc-i",
            "ecom-dev-j.swacorp.com" : "ecom-dev-svc-j",
            "ecom-itst-web-a.swacorp.com" : "ecom-itst-svc-a-1",
            "ecom-itst-web-b.swacorp.com" : "ecom-itst-svc-a-2",
            "ecom-itst-web-c.swacorp.com" : "ecom-itst-svc-a-3",
            "ecom-itst-web-d.swacorp.com" : "ecom-itst-svc-a-4",
            "ecom-itst-web-e.swacorp.com" : "ecom-itst-svc-a-5",
            "southwest-ftst-a.swacorp.com" : "ecom-qa-svc-a-1",
            "southwest-ftst-b.swacorp.com" : "ecom-qa-svc-a-2",
            "southwest-ftst-c.swacorp.com" : "ecom-qa-svc-a-3",
            "southwest-ftst-d.swacorp.com" : "ecom-qa-svc-a-4",
            "southwest-ftst-e.swacorp.com" : "ecom-qa-svc-a-5"
            ]

    private static final def WEB_TO_SERVER_PORT_MAP = [
        "ecom-dev-web-a.swacorp.com" : "13001",
        "ecom-dev-web-b.swacorp.com" : "13002",
        "ecom-dev-web-c.swacorp.com" : "13003",
        "ecom-dev-web-d.swacorp.com" : "13004",
        "ecom-dev-web-e.swacorp.com" : "13005",
        "ecom-dev-f.swacorp.com" : "13006",
        "ecom-dev-g.swacorp.com" : "13007",
        "ecom-dev-h.swacorp.com" : "13008",
        "ecom-dev-i.swacorp.com" : "13009",
        "ecom-dev-j.swacorp.com" : "13010",
        "ecom-itst-web-a.swacorp.com" : "13001",
        "ecom-itst-web-b.swacorp.com" : "13002",
        "ecom-itst-web-c.swacorp.com" : "13003",
        "ecom-itst-web-d.swacorp.com" : "13004",
        "ecom-itst-web-e.swacorp.com" : "13005",
        "southwest-ftst-a.swacorp.com" : "13001",
        "southwest-ftst-b.swacorp.com" : "13002",
        "southwest-ftst-c.swacorp.com" : "13003",
        "southwest-ftst-d.swacorp.com" : "13004",
        "southwest-ftst-e.swacorp.com" : "13005"
    ]

    def synchronized String cancel(String recordLocator) {
        try {
            def mbeanServer = getMBeanServer()
            def mbean = new GroovyMBean(mbeanServer, "com.swacorp.service:name=SimplePNRCancel")
            def argList = new ArrayList()
            argList.add(recordLocator)
            argList = argList.toArray()
            mbean.invokeMethod('cancel', argList)
        }
        catch (ServiceUnavailableException serviceException) {
            println "RMI to JMX not setup: ${serviceException.message}"
            serviceException.printStackTrace()
            throw serviceException
        }
    }

   def getMBeanServer() {
       def hostProperty = getProperty(HOST_PROPERTY, DEFAULT_HOST)
       def portProperty = getProperty(SVC_PORT_PROPERTY, SVC_DEFAULT_PORT)
       def serverUrl = jmxServerUrl(hostProperty, portProperty)
       JmxFactory.connect(new JmxUrl(serverUrl)).MBeanServerConnection
    }

    def String jmxServerUrl(String hostProperty, String portProperty) {
        def mappedServiceName = getServiceJmxName(hostProperty)
        def jmxHostPort = jmxHostAndPort(hostProperty, portProperty)
        def rmiName = "jmxrmi"
        if (mappedServiceName != null) {
            rmiName = "server"
        }
        def serverUrl = "service:jmx:rmi:///jndi/rmi://${jmxHostPort}/${rmiName}"
        return serverUrl
    }

    def String jmxHostAndPort(String hostProperty, String portProperty) {
        def mappedServiceName = getServiceJmxName(hostProperty)
        if (mappedServiceName == null || mappedServiceName.isEmpty()) {
            mappedServiceName = hostProperty
        }
        def mappedServicePort = getServiceJmxPort(hostProperty)
        if (mappedServicePort == null || mappedServicePort.isEmpty()) {
            mappedServicePort = portProperty
        }
        return "${mappedServiceName}:${mappedServicePort}"
    }

    // Rather than getting fancy for now this is a simple name mapping.
    def String getServiceJmxName(String webServerName) {
        def newName =  WEB_TO_SERVER_NAME_MAP.get(webServerName)
        return newName
    }

    def String getServiceJmxPort(String webServerName) {
        def newPort =  WEB_TO_SERVER_PORT_MAP.get(webServerName)
        return newPort
    }

    def String getProperty(String propertyName, String defaultValue) {
        String propertyValue = System.getProperty(propertyName)
        return StringUtils.isNotBlank(propertyValue) ? propertyValue : defaultValue
    }

}
