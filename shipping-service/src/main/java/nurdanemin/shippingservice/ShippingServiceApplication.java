package nurdanemin.shippingservice;

import nurdanemin.commonpackage.utils.constants.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        Paths.ConfigurationBasePackage, Paths.Shipping.ServiceBasePackage})
public class ShippingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShippingServiceApplication.class, args);
    }

}