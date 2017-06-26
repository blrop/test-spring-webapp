package ru.mai.dep806.mvcapp.controllers;

import com.mongodb.Mongo;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.UnknownHostException;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Контроллер для главной страницы приложения.
 */
@Controller
public class WelcomeController {

    private int visitorCount = 0;

    @RequestMapping("/index.html")
    public String index(Model model) {
        visitorCount += 5;
        model.addAttribute("visitorCount", visitorCount);

        MongoOperations mongoOps = null;
        try {
            mongoOps = new MongoTemplate(new Mongo(), "database");
            Person aPerson = mongoOps.findOne(new Query(where("name").is("Joe")), Person.class);
            int age = aPerson.getAge();

            model.addAttribute("age", age);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "WEB-INF/jsp/index.jsp";
    }

}