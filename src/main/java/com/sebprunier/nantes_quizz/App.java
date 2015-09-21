package com.sebprunier.nantes_quizz;

import net.codestory.http.WebServer;
import net.codestory.http.templating.ModelAndView;

public class App {

    public static void main(String[] args) {
        new WebServer()
                .configure(routes -> {
                    routes.get("/about", (context) -> ModelAndView.of("index"));
                })
                .start();
    }

}
