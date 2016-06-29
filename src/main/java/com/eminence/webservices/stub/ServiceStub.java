package com.eminence.webservices.stub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.apache.commons.io.IOUtils.*;

public interface ServiceStub {

    default void updateResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        updateContenpetTyOf(httpServletResponse);
        updateStatusOf(httpServletResponse);
        write(getResponseFor(httpServletRequest), httpServletResponse.getOutputStream());
    }

    default void updateStatusOf(HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(SC_OK);
    }

    String getResponseFor(HttpServletRequest httpServletRequest);

    default void updateHeadersOf(HttpServletResponse httpServletResponse) {

    }

    default void updateContenpetTyOf(HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("application/json;charset=utf-8");
    }

}
