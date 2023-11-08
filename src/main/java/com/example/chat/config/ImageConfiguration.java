package com.example.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ImageConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/chat/**")
                .addResourceLocations("file:///Users/isangmin/chat_project/");
        //mac 여기경로까지를 localhost:8080/에 추가해주었기 때문에
        // localhost:8080/(여긴 컨트롤러에서 chat/room)로 만들거 고려함 chat/images/20231104/43480740836560.png
        // 나중에 viewResolver 생각중(size 조절등)

    }
}
