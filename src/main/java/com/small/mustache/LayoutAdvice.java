package com.small.mustache;

import com.samskivert.mustache.Mustache;
import java.util.Map;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LayoutAdvice {
	@Autowired
	private Mustache.Compiler compiler;

	@ModelAttribute("layout")
	public Mustache.Lambda layout(Map<String, Object> model) {
		return new Layout(this.compiler);
	}

	@ModelAttribute("title")
	public Mustache.Lambda title(@ModelAttribute Layout layout) {
		return (frag, out) -> layout.setTitle(frag.execute());
	}

	@ModelAttribute("script")
	public Mustache.Lambda script(@ModelAttribute Layout layout) {
		return (frag, out) -> layout.setScript(frag.execute());
	}

	@ModelAttribute("css")
	public Mustache.Lambda css(@ModelAttribute Layout layout) {
		return (frag, out) -> layout.setCss(frag.execute());
	}
}
