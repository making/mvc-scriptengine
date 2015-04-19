package com.example.engine;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.mvc.Models;
import javax.mvc.engine.Priorities;
import javax.mvc.engine.ViewEngine;
import javax.mvc.engine.ViewEngineContext;
import javax.mvc.engine.ViewEngineException;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;

@Priority(Priorities.DEFAULT)
public class ScriptViewEngine implements ViewEngine {

    private static final Logger log = Logger.getLogger(ScriptViewEngine.class.getName());

    private final ServletContext servletContext;
    private final ScriptConfiguration config;
    private final ScriptEngine scriptEngine;

    public ScriptViewEngine(ServletContext servletContext, ScriptConfiguration config) {
        this(servletContext, new ScriptEngineManager().getEngineByName(config.getEngineName()), config);        
    }
    
    public ScriptViewEngine(ServletContext servletContext, ScriptEngine scriptEngine, ScriptConfiguration config) {
        this.servletContext = servletContext;
        this.config = config;
        this.scriptEngine = scriptEngine;
        if (this.scriptEngine == null) {
            throw new IllegalStateException("scriptEngine is not found for " + config.getEngineName());
        }
        if (!this.config.isReload()) {
            loadScripts();
        }
    }
    
    private void loadScripts() {
        for (Resource script : this.config.getScripts()) {
            log.info(() -> "load " + script.getPath());
            try (Reader reader = new InputStreamReader(script.getInputStream())) {
                this.scriptEngine.eval(reader);
            } catch (IOException | ScriptException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean supports(String view) {
        return this.config.getSupports().test(view);
    }

    @Override
    public void processView(ViewEngineContext context) throws ViewEngineException {
        String viewBase = this.config.getViewBase();
        if (viewBase == null) {
            viewBase = ViewEngine.DEFAULT_VIEW_FOLDER;
        }
        try (PrintWriter writer = context.getResponse().getWriter();
                InputStream templateStream = this.servletContext.getResourceAsStream(viewBase + "/" + context.getView())) {
            if (this.config.isReload()) {
                loadScripts();
            }
            Models models = context.getModels();

            String template = copyToString(templateStream, StandardCharsets.UTF_8);
            Object html;
            if (this.config.getRenderObject() == null) {
                html = ((Invocable) this.scriptEngine).invokeFunction(this.config.getRenderFunction(), template, models);
            } else {
                Object self = this.scriptEngine.eval(this.config.getRenderObject());
                html = ((Invocable) this.scriptEngine).invokeMethod(self, this.config.getRenderFunction(), template, models);
            }
            writer.println(String.valueOf(html));
        } catch (Exception e) {
            log.log(Level.SEVERE, null, e);
            throw new ViewEngineException(e);
        }
    }

    private static String copyToString(InputStream in, Charset charset) throws IOException {
        StringBuilder out = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(in, charset);) {
            char[] buffer = new char[4096];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != -1) {
                out.append(buffer, 0, bytesRead);
            }
            return out.toString();
        }
    }
}
