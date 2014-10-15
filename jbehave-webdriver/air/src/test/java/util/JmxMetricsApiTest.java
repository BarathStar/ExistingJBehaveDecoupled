package util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.management.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * These unit tests are rather lame but they will go red
 * if the associated methods have their jmx bean keys changed or other method mischief
 */
@RunWith(JUnit4.class)
public class JmxMetricsApiTest {
    MBeanServerConnection mockMBeanServerConnection = null;
    JmxMetricsApi jmxMetricsApi = null;

    @Test
    public void retrieveCreateReservationMetricValue() throws Exception {
        mySetup("Metrics:type=CEBS.ReservationManagement,scope=createReservation,name=totalRequest", 1, null);
        int expectedValue = 1;
        int actualValue = jmxMetricsApi.retrieveCreateReservationMetricValue();

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void retrieveGetConfirmationGroupMetricValue() throws Exception {
        mySetup("Metrics:type=MULE.Purchase,scope=getConfirmationGroup,name=totalRequest", 1, null);
        int expectedValue = 1;
        int actualValue = jmxMetricsApi.retrieveGetConfirmationGroupMetricValue();
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void isOn() throws Exception {
        mySetup("com.swacorp.service.environmentManagementService.features:name=COLLECT_METRICS", 0, "isAvailable");
        Assert.assertFalse((Boolean) jmxMetricsApi.isSvcMetricsOn("COLLECT_METRICS"));
    }

    @Test
    public void isOff() throws Exception {
        mySetup("com.swacorp.service.environmentManagementService.features:name=COLLECT_METRICS", 0, "isAvailable");
        Assert.assertTrue((Boolean) jmxMetricsApi.isOff("COLLECT_METRICS"));
    }

    @Test
    public void resetServiceMetrics() throws Exception {
        mySetup("com.swacorp.common.logging:name=MetricsUtility", 1, "resetMetrics");
        int expectedValue = 0;
        jmxMetricsApi.resetServiceMetrics();

        mySetup("Metrics:type=CEBS.ReservationManagement,scope=createReservation,name=totalRequest", 0, null);
        int actualValue = jmxMetricsApi.retrieveCreateReservationMetricValue();
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void resetWebMetrics() throws Exception {
        mySetup("com.swacorp.common.logging:name=MetricsUtility", 1, "resetMetrics");
        int expectedValue = 0;
        jmxMetricsApi.resetWebMetrics();

        mySetup("Metrics:type=CEBS.ReservationManagement,scope=createReservation,name=totalRequest", 0, null);
        int actualValue = jmxMetricsApi.retrieveCreateReservationMetricValue();
        Assert.assertEquals(expectedValue, actualValue);
    }

    private void mySetup(String s, int i, String s1) throws Exception {
        ObjectName testObjectName = new ObjectName(s);

        MBeanOperationInfo mockMBeanOperationInfo = mock(MBeanOperationInfo.class);
        when(mockMBeanOperationInfo.getSignature()).thenReturn(new MBeanParameterInfo[]{});
        when(mockMBeanOperationInfo.getName()).thenReturn(s1);

        MBeanInfo mockMBeanInfo = mock(MBeanInfo.class);
        when(mockMBeanInfo.getOperations()).thenReturn(new MBeanOperationInfo[]{mockMBeanOperationInfo});

        mockMBeanServerConnection = mock(MBeanServerConnection.class);
        when(mockMBeanServerConnection.getMBeanInfo(testObjectName)).thenReturn(mockMBeanInfo);
        when(mockMBeanServerConnection.getAttribute(testObjectName, "Count")).thenReturn(Integer.valueOf(i));

        when(mockMBeanServerConnection.invoke(testObjectName, "resetMetrics", new Object[0], new String[0])).thenReturn(Integer.valueOf(0));

        when(mockMBeanServerConnection.invoke(testObjectName, "toggle", new Object[0], new String[0])).thenReturn(Integer.valueOf(0));

        jmxMetricsApi = new JmxMetricsApi() {
            @Override
            protected MBeanServerConnection getMBeanServerConnection(){
                return mockMBeanServerConnection;
            }
        };
    }
}
