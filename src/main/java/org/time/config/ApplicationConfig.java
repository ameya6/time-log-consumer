package org.time.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.time.utils.LocalDateTimeAdapter;

import java.time.LocalDateTime;


@Configuration
@Log4j2
public class ApplicationConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()  ).create();
    }
}
