package jim.kats;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Main {
    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring.xml"));

        emailService.sendEmail("test@miu.edu", "tewali");
    }
}
