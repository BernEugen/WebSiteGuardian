package com.berneugen.WebSiteGuardian.Model;

public class StatusesModel {

    public static final int OK_STATUS = 200;
    public static final int NOT_FOUND_STATUS = 404;
    public static final int FORBIDDEN_STATUS = 403;
    public static final int FAILED_STATUS = 500;
    public static final int TEMP_UNAVAILABLE_STATUS = 503;
    public static final int TIMEOUT_STATUS = 504;
    public static final int TIMEOUT_HTTP_CLIENT = 1000 * 4;
}
