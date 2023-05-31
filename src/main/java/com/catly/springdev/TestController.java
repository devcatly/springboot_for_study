package com.catly.springdev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
     @Autowired
     MemberRepository memberRepository;

     @GetMapping("/test")
     public List<Member> getAllMembers(){
         return memberRepository.findAll();
     }
}
