package cn.aixan.computer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author aix QQ:32729842
 * @version 2022-09-07 17:37
 */
@Configuration
public class MyWebMvcConfigurationAdapter implements WebMvcConfigurer {
    @Value("${aixan.filePath}")
    private String filePath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/image/**")
                .addResourceLocations("file://" + filePath + "/");
    }
}
