package com.swacorp.dotcom.webscenarios.air.config;

public class Domains {
	public static String getDotcomDomain() {
		return "http://" + System.getProperty("domainToTest", "local.swacorp.com");
	}

	public static String getSwabizDomain() {
		return "http://" + System.getProperty("swabizDomainToTest", "swabiz.swacorp.com");
	}

    public static String getSecureSwabizDomain() {
		return "https://" + System.getProperty("swabizDomainToTest", "swabiz.swacorp.com");
	}

    // domainToTest doesn't always match the machine that is running the service tier, so if you
    // run into that situation, you can specify the service tier machine with the expectationsDomain
    // System property.
    public static String getExpectationsDomain() {
        String expectationsDomain = System.getProperty("expectationsDomain");
        if (expectationsDomain != null) {
            return "http://" + expectationsDomain;
        } else {
            return getDotcomDomain();
        }
    }
}
