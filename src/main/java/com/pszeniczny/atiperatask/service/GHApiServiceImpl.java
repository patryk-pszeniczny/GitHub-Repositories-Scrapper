package com.pszeniczny.atiperatask.service;

import com.pszeniczny.atiperatask.exceptions.types.ResourceNotFoundException;
import com.pszeniczny.atiperatask.model.BranchInfo;
import com.pszeniczny.atiperatask.model.RepositoryInfo;
import com.pszeniczny.atiperatask.model.ResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GHApiServiceImpl implements GHApiService {

    private final WebClient webClient;

    public GHApiServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Value("${github.api.url}")
    private String GITHUB_API_URL;

    @Override
    public Flux<ResponseModel> getUserRepositories(String username) {
        return webClient.get()
                .uri( GITHUB_API_URL + "/users/" + username + "/repos")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new ResourceNotFoundException("User repositories not found.")))
                .bodyToFlux(RepositoryInfo.class)
                .flatMap(repositoryInfo -> getBranchesForRepository(repositoryInfo)
                        .map(branches -> new ResponseModel(repositoryInfo.name(), repositoryInfo.owner().login(), branches))
                );
    }

    private Mono<List<BranchInfo>> getBranchesForRepository(RepositoryInfo repositoryInfo) {
        return webClient.get()
                .uri(GITHUB_API_URL + "/repos/" + repositoryInfo.owner().login() + "/" + repositoryInfo.name() + "/branches")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new ResourceNotFoundException("Branches not found for repository: " + repositoryInfo.name())))
                .bodyToFlux(BranchInfo.class)
                .collectList();
    }

}
