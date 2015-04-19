/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.engine;

import java.io.InputStream;
import javax.servlet.ServletContext;

public interface Resource {

    InputStream getInputStream();

    String getPath();

    public static Resource classpath(String path) {
        return new ClassPathResource(path);
    }

    public static Resource servletContext(ServletContext servletContext, String path) {
        return new ServletContextResource(servletContext, path);
    }

    static class ClassPathResource implements Resource {

        private final String path;

        public ClassPathResource(String path) {
            this.path = path;
        }

        @Override
        public InputStream getInputStream() {
            return getClassLoader().getResourceAsStream(path);
        }

        @Override
        public String getPath() {
            return path;
        }

    }

    static class ServletContextResource implements Resource {

        private final ServletContext servletContext;
        private final String path;

        public ServletContextResource(ServletContext servletContext, String path) {
            this.servletContext = servletContext;
            this.path = path;
        }

        @Override
        public InputStream getInputStream() {
            return this.servletContext.getResourceAsStream(path);
        }

        @Override
        public String getPath() {
            return path;
        }

    }

    static ClassLoader getClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception ignored) {
        }
        if (cl == null) {
            try {
                cl = Resource.class.getClassLoader();
            } catch (Exception ignored) {
            }
        }
        if (cl == null) {
            cl = ClassLoader.getSystemClassLoader();
        }
        return cl;
    }

}
