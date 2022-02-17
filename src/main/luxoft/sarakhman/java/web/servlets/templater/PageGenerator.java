package web.servlets.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;


public class PageGenerator {
    public static PageGenerator pageGenerator;
    private final Configuration configuration;

    public PageGenerator() {
        configuration = new Configuration(new Version(2, 3, 21));
    }

    public static PageGenerator instance() {
        if (pageGenerator == null) {
            pageGenerator = new PageGenerator();
        }
        return pageGenerator;
    }

    public String getPage(String string) {
        return getPage(string, Collections.emptyMap());
    }

    public String getPage(String path, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {

            Template template = configuration.getTemplate(path);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }
}
