package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;


public class PageGenerator {
    public static PageGenerator pageGenerator;
    private final Configuration configuration;

    public PageGenerator() {
        configuration = new Configuration();
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

    public String getPage(String name, Map<String, Object> objects) {
        Writer stream = new StringWriter();
        try {

            Template template = configuration.getTemplate(name);
            template.process(objects, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }
}
