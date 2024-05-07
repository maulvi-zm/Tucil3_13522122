package com.tucil.backend;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;

import pkg.solution.Solution;

@RestController
public class Router {

    @CrossOrigin
    @RequestMapping(value = "/solve", method = RequestMethod.GET)
    public JsonNode processData(@RequestParam("start") String start,
                              @RequestParam("goal") String target,
                              @RequestParam("algorithm") String algorithm) {

        // Delete trailing whitespaces
        start = start.trim();
        target = target.trim();

        // Solve the problem
        Solution path = BackendApplication.solver.Solve(start, target, algorithm);

        // Return the solution as JSON
        return path.toJson();
    }

    @CrossOrigin
    @GetMapping("/")
    public String index() {
        return "Selamat datang di backend aplikasi Tucil!";
    }
}
