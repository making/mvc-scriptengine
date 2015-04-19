https://github.com/sdeleuze/spring-framework/blob/SPR-12266/spring-webmvc/src/test/resources/org/springframework/web/servlet/view/script/handlebars/render.js
function render(template, model) {
    var compiledTemplate = Handlebars.compile(template);
    return compiledTemplate(model);
}