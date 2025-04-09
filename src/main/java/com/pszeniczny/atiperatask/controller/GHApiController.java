package com.pszeniczny.atiperatask.controller;

import com.pszeniczny.atiperatask.exceptions.types.InvalidAcceptHeaderException;
import com.pszeniczny.atiperatask.model.ResponseModel;
import com.pszeniczny.atiperatask.service.GHApiService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/atiperatask")
public class GHApiController {

    private final GHApiService ghApiService;

    public GHApiController(GHApiService ghApiService) {
        this.ghApiService = ghApiService;
    }

    @RequestMapping(path = "{username}", method = RequestMethod.GET, produces = "application/json")
    public Flux<ResponseModel> getUserRepositories(@PathVariable("username") String username, @RequestHeader("Accept") String acceptHeader) {
        if ("application/xml".equals(acceptHeader)) {
            throw new InvalidAcceptHeaderException("XML format is not supported");
        }
        return ghApiService.getUserRepositories(username);
    }
}
