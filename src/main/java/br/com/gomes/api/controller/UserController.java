package br.com.gomes.api.controller;

import br.com.gomes.api.domain.User;
import br.com.gomes.api.domain.dto.UserDTO;
import br.com.gomes.api.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok().body(userService.findAll().stream().
                map(x ->  mapper.map(x, UserDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto){


        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(userService.createUser(dto).getId()).toUri())
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id,@RequestBody UserDTO dto){
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.map(userService.update(dto), UserDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id){
        userService.delete(id);
        return ResponseEntity.notFound().build();
    }
}









