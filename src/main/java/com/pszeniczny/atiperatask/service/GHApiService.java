package com.pszeniczny.atiperatask.service;

import com.pszeniczny.atiperatask.model.ResponseModel;
import reactor.core.publisher.Flux;

public interface GHApiService {

    Flux<ResponseModel> getUserRepositories(String username);
}
