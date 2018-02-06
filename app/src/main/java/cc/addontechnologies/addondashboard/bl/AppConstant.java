package cc.addontechnologies.addondashboard.bl;

/**
 * Created by sathishbabur on 1/30/2018.
 */

public class AppConstant {
    public static final String URL_FEED="http://addonservicemanager.azurewebsites.net/";
    public static final String API_FEED="api/Reports";
    public static String getAPIUrl()
    {
        return URL_FEED+API_FEED;
    }

}
