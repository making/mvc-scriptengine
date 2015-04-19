# https://github.com/sdeleuze/spring-framework/blob/SPR-12266/spring-webmvc/src/test/resources/org/springframework/web/servlet/view/script/python/render.py

from string import Template

def render(template, model):
	s = Template(template)
	return s.substitute(model)