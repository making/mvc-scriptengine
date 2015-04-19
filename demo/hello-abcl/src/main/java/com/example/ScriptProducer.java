package com.example;

import com.example.engine.ScriptConfiguration;
import com.example.engine.ScriptViewEngine;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.script.ScriptEngineManager;
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
   public ScriptViewEngine abclScriptViewEngine() {
       log.info(() -> "create ScriptViewEngine for ABCL. This takes a few minutes ...! Please wait :P");
       ScriptConfiguration config = new ScriptConfiguration.Builder()
               .renderFunction("demo:render")
               .scripts(servletContext(servletContext, "/WEB-INF/abcl/install.lisp"),
                       servletContext(servletContext, "/WEB-INF/abcl/render.lisp"))
               .supports(view -> view.endsWith(".lisp"))
               .reload(false)
               .build();
       return new ScriptViewEngine(servletContext, new ScriptEngineManager().getEngineByExtension("lisp"), config);
   }
}
