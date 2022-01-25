package work.szczepanskimichal.project13snippetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WebAppConfig implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(WebAppConfig.class, args);
    }

//    redirect from specific address to specific view - walkaround
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/urs adress").setViewName("view address");
//        registry.addViewController("/403").setViewName("403");
//    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(getRoleConverter());e
//
//    }



}
