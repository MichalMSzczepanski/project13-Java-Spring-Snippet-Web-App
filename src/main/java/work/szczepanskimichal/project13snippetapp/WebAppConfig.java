package work.szczepanskimichal.project13snippetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import work.szczepanskimichal.project13snippetapp.role.RoleConverter;

import javax.validation.Validator;

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

//    validator & messaging settings
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

// converter settings
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getRoleConverter());

    }

    @Bean
    public RoleConverter getRoleConverter() {
        return new RoleConverter();
    }

}
