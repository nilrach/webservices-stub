package com.eminence.webservices.stub;


import com.atlassian.jira.rest.client.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

class JiraStub implements ServiceStub {

    private final Map<String,Project> projects;
    private final ObjectMapper objectMapper;

    JiraStub() {
        projects = new HashMap<>();
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(NON_NULL);
        createDummyProjects();
    }

    public String getResponseFor(HttpServletRequest httpServletRequest) {
        try {
            String httpMethod = httpServletRequest.getMethod();
            String requestURI = httpServletRequest.getRequestURI();
            if("GET".equals(httpMethod) && requestURI.endsWith("project")) {
                List<BasicProject> basicProjects = new ArrayList<>();
                for(String key : projects.keySet()) {
                    Project project = projects.get(key);
                    basicProjects.add(new BasicProject(project.getUri(), project.getKey(), project.getName()));
                }
                return objectMapper.writeValueAsString(basicProjects);
            }

        } catch (Exception e) {

        }
        return null;
    }

    private void createDummyProjects() {
        try {
            BasicUser basicUser = new BasicUser(new URI(""),"stubJiraUser","stub jira user description");
            List<Version> versions = new ArrayList<>();
            Version version = new Version(new URI(""),Long.valueOf(11),"latest","latest version",false,false,null);
            versions.add(version);
            List<BasicComponent> basicComponents = new ArrayList<>();
            BasicComponent basicComponent = new BasicComponent(new URI(""),null,"stubComponent",null);
            basicComponents.add(basicComponent);

            Project project = new Project(new URI(""),"StubProject1","Stub Project1","Stub Project1 Description",
                    basicUser,new URI(""),versions,basicComponents,null,null);
            projects.put("StubProject1",project);

            project = new Project(new URI(""),"StubProject2","Stub Project2","Stub Project2 Description",
                    basicUser,new URI(""),versions,basicComponents,null,null);
            projects.put("StubProject2",project);
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }

    }
}
