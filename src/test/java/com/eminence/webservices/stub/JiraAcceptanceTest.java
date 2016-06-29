package com.eminence.webservices.stub;


import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.atlassian.jira.rest.client.domain.BasicProject;
import com.atlassian.jira.rest.client.domain.User;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import com.eminence.webservices.ServiceType;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URI;

import static com.eminence.webservices.ServiceType.*;


public class JiraAcceptanceTest {
    private static final String JIRA_URL = "http://localhost:8081";
    private static final String JIRA_ADMIN_USERNAME = "admin";
    private static final String JIRA_ADMIN_PASSWORD = "admin";
    private static JiraRestClient jiraRestClient;

    @BeforeClass
    public static void getJiraRestClient() throws Exception {
            HttpTestServer server = new HttpTestServer(JIRA, 8081);
            server.start();

            JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
            URI uri = new URI(JIRA_URL);
            jiraRestClient = factory.createWithBasicHttpAuthentication(uri, JIRA_ADMIN_USERNAME, JIRA_ADMIN_PASSWORD);
    }

    @Test
    public void shouldBeAbleToGetAllProjects() throws Exception {
        Promise<Iterable<BasicProject>> promise = jiraRestClient.getProjectClient().getAllProjects();
        Iterable<BasicProject> projects = promise.claim();

        projects.forEach(p -> System.out.println(p.getKey()));
    }

    @Test
    @Ignore
    public void shouldBeAbleToAddUserAndGetItBack() throws Exception {
        Promise<User> promise = jiraRestClient.getUserClient().getUser("admin");
        User user = promise.claim();
    }
}
