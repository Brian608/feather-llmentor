package org.feather.llm.rag.controller;

import org.feather.llm.rag.router.QueryRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.controller
 * @className: RagRouterController
 * @author: feather
 * @description:
 * @since: 2026-05-19 8:43 PM
 * @version: 1.0
 */
@RestController
@RequestMapping("/rag/router")
public class RagRouterController {

    @Autowired
    private QueryRouteService queryRouteService;

    @GetMapping("/route")
    public String route(String query) {
        return queryRouteService.route(query);
    }


}
