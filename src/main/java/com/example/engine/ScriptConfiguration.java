package com.example.engine;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ScriptConfiguration {

    private final String engineName;
    private final List<Resource> scripts;
    private final String renderObject;
    private final String renderFunction;
    private final String viewBase;
    private final Predicate<String> supports;
    private final boolean reload;

    public ScriptConfiguration(String engineName, List<Resource> scripts, String renderObject,
            String renderFunction, String viewBase, Predicate<String> supports, boolean reload) {
        this.engineName = engineName;
        this.scripts = scripts;
        this.renderObject = renderObject;
        this.renderFunction = renderFunction;
        this.viewBase = viewBase;
        this.supports = supports;
        this.reload = reload;
    }

    public String getEngineName() {
        return engineName;
    }

    public List<Resource> getScripts() {
        return scripts;
    }

    public String getRenderObject() {
        return renderObject;
    }

    public String getRenderFunction() {
        return renderFunction;
    }

    public String getViewBase() {
        return viewBase;
    }

    public Predicate<String> getSupports() {
        return supports;
    }

    public boolean isReload() {
        return reload;
    }

    public static class Builder {

        private String engineName;
        private List<Resource> scripts;
        private String renderObject;
        private String renderFunction;
        private String viewBase;
        private Predicate<String> supports;
        private boolean reload = false;

        public Builder engineName(String engineName) {
            this.engineName = engineName;
            return this;
        }

        public Builder scripts(Resource... scripts) {
            this.scripts = Arrays.asList(scripts);
            return this;
        }

        public Builder renderObject(String renderObject) {
            this.renderObject = renderObject;
            return this;
        }

        public Builder renderFunction(String renderFunction) {
            this.renderFunction = renderFunction;
            return this;
        }

        public Builder viewBase(String viewBase) {
            this.viewBase = viewBase;
            return this;
        }

        public Builder supports(Predicate<String> supports) {
            this.supports = supports;
            return this;
        }

        public Builder reload(boolean reload) {
            this.reload = reload;
            return this;
        }

        public ScriptConfiguration build() {
            return new ScriptConfiguration(engineName, scripts, renderObject,
                    renderFunction, viewBase, supports, reload);
        }
    }

}
