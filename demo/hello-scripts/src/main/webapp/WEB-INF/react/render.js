// https://github.com/sdeleuze/spring-framework/blob/SPR-12266/spring-webmvc/src/test/resources/org/springframework/web/servlet/view/script/react/render.js

function render(template, model) {
    // Create a real Javascript Object from the model Map
    var data = {};
    for(var k in model) data[k]=model[k];
    var element = React.createElement(eval(template), data);
    // Should use React.renderString in production
    return React.renderToStaticMarkup(element);
}

function renderJsx(template, model) {
    var jsTemplate = JSXTransformer.transform(template).code;
    return render(jsTemplate, model);
}