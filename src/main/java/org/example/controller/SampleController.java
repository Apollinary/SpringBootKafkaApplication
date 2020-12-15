package org.example.controller;

import org.example.User;
import org.example.dao.UserJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleController {

    @Autowired
    UserJDBCTemplate userJDBCTemplate;

    @RequestMapping(value = "/public/getUsers", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(@RequestParam("firstname") String firstname,
                                               @RequestParam("lastname") String lastname) {
        List<User> foundUsers = userJDBCTemplate.getUserByName(firstname, lastname);
        if (foundUsers == null) {
            foundUsers = new ArrayList<>();
        }
        return ResponseEntity.ok(foundUsers);
    }
}
