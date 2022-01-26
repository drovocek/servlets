package edu.volkov.mvc.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    public static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    public static String getJspPath(String jspName) {
        return JSP_FORMAT.formatted(jspName);
    }
}
