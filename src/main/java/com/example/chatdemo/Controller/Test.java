package com.example.chatdemo.Controller;

import com.example.chatdemo.Model.Content;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Test {
    @PostMapping("/hello")
    public ResponseEntity<?> tes(@RequestBody Content content){

        return ResponseEntity.ok(content);
    }
}
