package com.small.mustache;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import java.io.IOException;
import java.io.Writer;
import lombok.Setter;

public class Layout implements Mustache.Lambda {
	private String body;
	@Setter
  private String title = "";
	@Setter
  private String script = "";
	@Setter
  private String css = "";

	private Mustache.Compiler compiler;

	public Layout(Mustache.Compiler compiler) {
		this.compiler = compiler;
	}

	@Override
	public void execute(Template.Fragment frag, Writer out) throws IOException {
		this.body = frag.execute();
		this.compiler.compile("{{>layout}}").execute(frag.context(), out);
	}
}
