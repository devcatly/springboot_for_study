package com.catly.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model){
        Person examplePesron = new Person();
        examplePesron.setId(1L);
        examplePesron.setName("홍길동");
        examplePesron.setAge(11);
        examplePesron.setHobbies(List.of("운동","독서"));

        model.addAttribute("person",examplePesron);
        model.addAttribute("today", LocalDate.now());

        return "example";

    }

    @Getter
    @Setter
    class Person{
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }


}
