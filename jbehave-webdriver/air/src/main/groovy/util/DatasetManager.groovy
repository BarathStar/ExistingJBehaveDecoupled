/*
 * Copyright (c) 2011, Southwest Airlines Co.  All rights reserved.
 */
package util
import org.ho.yaml.Yaml
import domain.RapidRewardsAccount
import domain.Guardian
/**
 * This class is created to centralize tasks related to dataset yaml files.
 */
class DatasetManager {

    static final private String RR_DEFAULT_ACCOUNT = "RR_Default_Account"
    static final private String DATA_PATH = "/src/main/resources/data/"

    static RapidRewardsAccount loadAccount(String fileName) {
        if(fileName == null){
            fileName = RR_DEFAULT_ACCOUNT
        }
        RapidRewardsAccount rrAccount
        rrAccount = Yaml.loadType(new File(getAirDirectory(), DATA_PATH+"${fileName}.yml"), RapidRewardsAccount)
        return rrAccount
    }

    private static File getAirDirectory() {
        new File(System.getProperty("user.dir"))
    }

    static Guardian loadGuardian(String guardianType) {
        Guardian guardian
        guardian = Yaml.loadType(new File(getAirDirectory(), System.getProperty("java.io.yaml.home", DATA_PATH) + "UM/${guardianType}.yml"),Guardian)
        return guardian
    }
}