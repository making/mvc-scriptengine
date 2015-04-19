package com.example;

import com.example.engine.ScriptConfiguration;
import com.example.engine.ScriptViewEngine;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import static com.example.engine.Resource.*;
import java.util.logging.Logger;

@ApplicationScoped
public class ScriptProducer {

    private static final Logger log = Logger.getLogger(ScriptProducer.class.getName());

    @Inject
    ServletContext servletContext;

    @Produces
    @ApplicationScoped
    public ScriptViewEngine reactScriptViewEngine() {
        log.info(() -> "create ScriptViewEngine for React.js");
        ScriptConfiguration config = new ScriptConfiguration.Builder()
                .engineName("js")
                .renderFunction("render")
                .scripts(
                        //classpath("/META-INF/resources/webjars/react/0.13.1/react.js"),
                        servletContext(servletContext, "/WEB-INF/react/react.js"),
                        servletContext(servletContext, "/WEB-INF/react/polyfill.js"),
                        servletContext(servletContext, "/WEB-INF/react/render.js"))
                .supports(view -> view.endsWith(".js"))
                .reload(false)
                .build();
        return new ScriptViewEngine(servletContext, config);
    }

    @Produces
    @ApplicationScoped
    public ScriptViewEngine reactJsxScriptViewEngine() {
        log.info(() -> "create ScriptViewEngine for React.js (JSX)");
        ScriptConfiguration config = new ScriptConfiguration.Builder()
                .engineName("js")
                .renderFunction("renderJsx")
                .scripts(
                        //classpath("/META-INF/resources/webjars/react/0.13.1/react.js"),
                        //classpath("/META-INF/resources/webjars/react/0.13.1/JSXTransformer.js"),
                        servletContext(servletContext, "/WEB-INF/react/react.js"),
                        servletContext(servletContext, "/WEB-INF/react/JSXTransformer.js"),
                        servletContext(servletContext, "/WEB-INF/react/polyfill.js"),
                        servletContext(servletContext, "/WEB-INF/react/render.js"))
                .supports(view -> view.endsWith(".jsx"))
                .reload(false)
                .build();
        return new ScriptViewEngine(servletContext, config);
    }

    @Produces
    @ApplicationScoped
    public ScriptViewEngine handlebarsScriptViewEngine() {
        log.info(() -> "create ScriptViewEngine for Handlebars");
        ScriptConfiguration config = new ScriptConfiguration.Builder()
                .engineName("js")
                .renderFunction("render")
                .scripts(servletContext(servletContext, "/WEB-INF/react/polyfill.js"),
                        servletContext(servletContext, "/WEB-INF/handlebars/handlebars.js"),
                        servletContext(servletContext, "/WEB-INF/handlebars/render.js"))
                .supports(view -> view.endsWith(".hbs"))
                .reload(false)
                .build();
        return new ScriptViewEngine(servletContext, config);
    }

    @Produces
    @ApplicationScoped
    public ScriptViewEngine mustacheScriptViewEngine() {
        log.info(() -> "create ScriptViewEngine for Mustache.js");
        ScriptConfiguration config = new ScriptConfiguration.Builder()
                .engineName("js")
                .renderObject("Mustache")
                .renderFunction("render")
                .scripts(servletContext(servletContext, "/WEB-INF/mustache/mustache.js"))
                .supports(view -> view.endsWith(".mst"))
                .reload(false)
                .build();
        return new ScriptViewEngine(servletContext, config);
    }

    @Produces
    @ApplicationScoped
    public ScriptViewEngine jythonScriptViewEngine() {
        log.info(() -> "create ScriptViewEngine for Jython");
        ScriptConfiguration config = new ScriptConfiguration.Builder()
                .engineName("jython")
                .renderFunction("render")
                .scripts(servletContext(servletContext, "/WEB-INF/jython/render.py"))
                .supports(view -> view.endsWith(".py.html"))
                .reload(false)
                .build();
        return new ScriptViewEngine(servletContext, config);
    }

    @Produces
    @ApplicationScoped
    public ScriptViewEngine erbScriptViewEngine() {
        log.info(() -> "create ScriptViewEngine for ERB (JRuby)");
        ScriptConfiguration config = new ScriptConfiguration.Builder()
                .engineName("jruby")
                .renderFunction("render")
                .scripts(servletContext(servletContext, "/WEB-INF/erb/render.rb"))
                .supports(view -> view.endsWith(".erb"))
                .reload(false)
                .build();
        return new ScriptViewEngine(servletContext, config);
    }
}
