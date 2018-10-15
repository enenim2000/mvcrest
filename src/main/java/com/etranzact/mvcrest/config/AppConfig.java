package com.etranzact.mvcrest.config;

import com.etranzact.mvcrest.scheduler.BatchReportJobScheduler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

public class AppConfig extends ResourceConfig {
    public AppConfig() {
        packages("com.etranzact.mvcrest.controller");
        property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/jsp");
        register(JspMvcFeature.class);

        /*
         * Register batch report cron jobs
         */
        new BatchReportJobScheduler();
    }
}
