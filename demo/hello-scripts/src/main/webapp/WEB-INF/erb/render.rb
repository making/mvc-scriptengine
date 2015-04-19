require 'erb'
require 'ostruct'
require 'java'
# https://github.com/sdeleuze/spring-framework/blob/SPR-12266/spring-webmvc/src/test/resources/org/springframework/web/servlet/view/script/erb/render.rb
# Renders an ERB template against a hashmap of variables.
# template should be a Java InputStream
def render(template, variables)
  context = OpenStruct.new(variables).instance_eval do
    variables.each do |k, v|
      instance_variable_set(k, v) if k[0] == '@'
    end

    def partial(partial_name, options={})
      new_variables = marshal_dump.merge(options[:locals] || {})
      Java::Pavo::ERB.render(partial_name, new_variables)
    end

    binding
  end
  ERB.new(template).result(context);
end