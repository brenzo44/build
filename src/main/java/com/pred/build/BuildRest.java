package com.pred.build;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class BuildRest {
    
    @Autowired
    private ItemRepo repo;
    @Autowired
    private BuildRepo bRepo;

    private Build build = new Build("default");

    @GetMapping("/")
    public String defaultReturn() {
        return "Welcome to the item builder application!";
    }
    @GetMapping("/item/{name}")
    public ResponseEntity<Item> getItem(@PathVariable String name) {
        Item item = repo.findOneItem(name);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(item);
    }
    @GetMapping("/build")
    public Build getBuild() {
        return build;
    }
    @PutMapping("/build/{name}")
    public ResponseEntity<?> addItem(@PathVariable String name) {
        if(build.getNumItems() >= 6){
            return ResponseEntity.badRequest().body("Too many items! Only 6 can be added!");
        }
        Item item = repo.findOneItem(name);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        build.addItem(item);
        return ResponseEntity.ok().body(build);
    }
    @PutMapping("/build/remove/{name}")
    public ResponseEntity<Build> removeItem(@PathVariable String name) {
        Item item = repo.findOneItem(name);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        //build.removeItem(item);
        //return ResponseEntity.ok().body(build);
        else if (build.removeItem(item)) {
            return ResponseEntity.ok().body(build);
        }
        else{
            return ResponseEntity.notFound().build();}
    }
    @GetMapping("/build/totals")
    public Map<String, Integer> getTotals() {
        return build.getTotals();
    }
    @PostMapping("/build/save")
    public ResponseEntity<?> saveBuild() {
        if (build.getNumItems() < 6) {
            return ResponseEntity.badRequest().body("Not enough items! 6 are required!");
        }
        bRepo.save(build);
        return ResponseEntity.ok().body("Success.");
    }
    @PostMapping("item/save")
    public ResponseEntity<?> saveItem(@RequestBody Item item) {
        repo.save(item);
        return ResponseEntity.ok().body("Success.");
    }

}
