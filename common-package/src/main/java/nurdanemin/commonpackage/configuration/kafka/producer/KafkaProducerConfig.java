package nurdanemin.commonpackage.configuration.kafka.producer;

import nurdanemin.commonpackage.utils.kafka.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfig {
    @Bean
    KafkaProducer getKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        return new KafkaProducer(kafkaTemplate);
    }
}
