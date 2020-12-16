package org.example.controller;

import org.example.model.Comment;
import org.example.model.User;
import org.example.dao.UserJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/public/getUserComments", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getUserComments(@RequestParam("firstname") String firstname,
                                                         @RequestParam("lastname") String lastname) {
        List<Comment> foundComments = userJDBCTemplate.getCommentsByUserName(firstname, lastname);
        if (foundComments == null) {
            foundComments = new ArrayList<>();
        }
        return ResponseEntity.ok(foundComments);
    }

    @RequestMapping(value = "/public/getProductComments", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getProductComments(@PathVariable("product") String productName) {
        List<Comment> foundComments = userJDBCTemplate.getCommentsByProductName(productName);
        if (foundComments == null) {
            foundComments = new ArrayList<>();
        }
        return ResponseEntity.ok(foundComments);
    }
}
