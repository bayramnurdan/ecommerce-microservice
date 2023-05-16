package nurdanemin.commonpackage.configuration.mappers;

import nurdanemin.commonpackage.utils.mappers.ModelMapperManager;
import nurdanemin.commonpackage.utils.mappers.ModelMapperService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper getModelMapper() {

        return new ModelMapper();
    }

    @Bean
    public ModelMapperService getModelMapperService(ModelMapper mapper) {

        return new ModelMapperManager(mapper);
    }
}


