package com.eminence.webservices.stub;

import com.eminence.webservices.ServiceType;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpTestServer {
    private int httpPort;
    private Server server;
    private String requestBody;
    private String responseBody;
    private ServiceStub serviceStub;

    public HttpTestServer(ServiceType serviceType, int httpPort) {
        this.httpPort = httpPort;
        serviceStub = getServiceStubFor(serviceType);
    }

    public void start() throws Exception {
        initServerConfiguration();
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    private ServiceStub getServiceStubFor(ServiceType serviceType) {
        switch (serviceType) {
            case TEAMCITY:
                return new TeamCityStub();
            case JIRA:
                return new JiraStub();
        }
        return null;
    }

    private void initServerConfiguration() {
        server = new Server(httpPort);
        server.setHandler(getServiceRequestHandler());
    }

    private Handler getServiceRequestHandler() {
        return new AbstractHandler() {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws IOException, ServletException {
                serviceStub.updateResponse(httpServletRequest, httpServletResponse);
                baseRequest.setHandled(true);
            }
        };
    }

}
