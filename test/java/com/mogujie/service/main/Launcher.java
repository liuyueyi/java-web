package com.mogujie.service.main;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;

public class Launcher {
    public static void main(String[] args) throws Exception {
        QueuedThreadPool boundedThreadPool = new QueuedThreadPool();
        boundedThreadPool.setMaxThreads(15);

        Server server = new Server();
        server.setThreadPool(boundedThreadPool);
        WebAppContext webContext = new WebAppContext("/Users/yihui/Project/Web/web/src/main/webapp", "/");
        webContext.setResourceBase("/Users/yihui/Project/Web/web/src/main/webapp");
        server.addHandler(webContext);

        Connector connector = new SelectChannelConnector();
        connector.setHost("localhost");
        connector.setPort(8080);
        connector.setMaxIdleTime(30000);
        connector.setStatsOn(false);
        server.addConnector(connector);

        server.setStopAtShutdown(true);
        server.setSendServerVersion(true);
        server.setSendDateHeader(true);
        server.setGracefulShutdown(1000);

        server.start();
        server.join();
    }
}
