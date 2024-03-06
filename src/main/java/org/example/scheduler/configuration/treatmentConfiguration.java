package org.example.scheduler.configuration;

import lombok.AccessLevel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


    @Configuration
    public class treatmentConfiguration {

        @Bean
        ModelMapper getModelMapper() {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true);
            return modelMapper;
        }

    }
