package com.sebprunier.nantes_quizz;

import com.sebprunier.nantes_quizz.photos.PhotoQuizzEndpoint;
import net.codestory.http.WebServer;
import net.codestory.http.templating.ModelAndView;

public class App {

    public static final int PORT = 8080;

    public static void main(String[] args) {
        new WebServer()
                .configure(routes -> {
                    routes.add(PhotoQuizzEndpoint.class);
                    routes.get("/about", (context) -> ModelAndView.of("index"));
                })
                .start(PORT);
    }

}
