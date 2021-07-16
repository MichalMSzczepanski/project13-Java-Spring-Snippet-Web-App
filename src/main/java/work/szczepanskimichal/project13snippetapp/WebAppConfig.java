package work.szczepanskimichal.project13snippetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WebAppConfig implements WebMvcConfigurer {

//    main application method
    public static void main(String[] args) {
        SpringApplication.run(WebAppConfig.class, args);
    }

//    redirect from specific address to specific view - walkaround
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/urs adress").setViewName("view address");
//        registry.addViewController("/403").setViewName("403");
//    }

// converter settings
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(getBookConverter());
//
//    }
//
//    @Bean
//    public BookConverter getBookConverter() {
//        return new BookConverter();
//    }

}
