package com.example.task.configuration;

import com.example.task.client.jsonplaceholder.AlbumsClient;
import com.example.task.client.jsonplaceholder.PostsClient;
import com.example.task.client.jsonplaceholder.UsersClient;
import com.example.task.client.jsonplaceholder.impl.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ClientConfig {

    private <T> T getDefaultClient(String apiUrl, Class<T> repositoryServiceClass) {
        WebClient client = WebClient.builder()
                .baseUrl(apiUrl)
                .exchangeStrategies(ExchangeStrategies
                        .builder()
                        .codecs(codecs -> codecs
                                .defaultCodecs()
                                .maxInMemorySize(500 * 1024 * 1024))
                        .build())
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(
                WebClientAdapter.create(client)).build();
        return factory.createClient(repositoryServiceClass);
    }


    private PostsRepositoryService postsRepositoryService() {
        return getDefaultClient(ApplicationConfig.API_BASE_URL, PostsRepositoryService.class);
    }

    private UsersRepositoryService usersRepositoryService() {
        return getDefaultClient(ApplicationConfig.API_BASE_URL, UsersRepositoryService.class);
    }

    private AlbumsRepositoryService albumsRepositoryService() {
        return getDefaultClient(ApplicationConfig.API_BASE_URL, AlbumsRepositoryService.class);
    }

    @Bean
    public PostsClient postsClient() {
        return new PostsClientImpl(postsRepositoryService());
    }

    @Bean
    public UsersClient usersClient() {
        return new UsersClientImpl(usersRepositoryService());
    }

    @Bean
    public AlbumsClient albumsClient() {
        return new AlbumsClientImpl(albumsRepositoryService());
    }

    @Bean
    CacheManager cacheManager() {
        return new CaffeineCacheManager();
    }
}
