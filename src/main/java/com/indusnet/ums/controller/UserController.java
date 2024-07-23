package com.indusnet.ums.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.indusnet.ums.util.ObjectIdTypeAdapter;
import com.indusnet.ums.common.MessageTypeConst;
import com.indusnet.ums.common.LoggingResponseModel;
import com.indusnet.ums.common.ResponseModel;
import com.indusnet.ums.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.indusnet.ums.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ums/")
@Slf4j
public class UserController {

	
	@Autowired
    Gson gson;

    @Autowired
    IUserService service;

    Gson customGson = new GsonBuilder().registerTypeHierarchyAdapter(ObjectId.class, new ObjectIdTypeAdapter()).create();

    //POST

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> register(@RequestBody String request){

        LoggingResponseModel msgStart = LoggingResponseModel.builder()
                .message("Start add register")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgStart));
        ResponseModel response = service.add(customGson.fromJson(request, UserModel.class));
        HttpStatus status = response.getStatusCode() != null ? response.getStatusCode() : HttpStatus.NOT_FOUND;

        LoggingResponseModel msgEnd = LoggingResponseModel.builder()
                .message("End Add register")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgEnd));

        return new ResponseEntity<ResponseModel>(response, status);
    }
    
    //GET by id

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<String> getById(@PathVariable("id") String id){
                return new ResponseEntity<String>(service.get(id),HttpStatus.OK);       
         }
    //GET by name
    @GetMapping(value="/name/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
       public ResponseEntity<String> getByName(@PathVariable("name") String name){
        return new ResponseEntity<String>(service.getName(name),HttpStatus.OK);
       }

    //GET by email
    @GetMapping(value="/email/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getByEmail(@PathVariable("email") String email){
     return new ResponseEntity<String>(service.getEmail(email),HttpStatus.OK);
    }

    //GET by password
    @GetMapping(value="/password/{password}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getByPassword(@PathVariable("password") String password){
     return new ResponseEntity<String>(service.getPassword(password),HttpStatus.OK);
    }


    //GET all users

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
         public ResponseEntity<String> getAllUsers() {
             String usersJson = service.getAllUsers();
             return new ResponseEntity<>(usersJson, HttpStatus.OK);
         }

    //UPDATE

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
         public ResponseEntity<ResponseModel> update(@RequestBody String request) {
             LoggingResponseModel msgStart = LoggingResponseModel.builder()
                     .message("Start update user")
                     .messageTypeId(MessageTypeConst.SUCCESS)
                     .build();
     
             log.info(gson.toJson(msgStart));
             
             ResponseModel response = service.update(customGson.fromJson(request, UserModel.class));
             HttpStatus status = response.getStatusCode() != null ? response.getStatusCode() : HttpStatus.NOT_FOUND;
     
             LoggingResponseModel msgEnd = LoggingResponseModel.builder()
                     .message("End update user")
                     .messageTypeId(MessageTypeConst.SUCCESS)
                     .build();
     
             log.info(gson.toJson(msgEnd));
             return new ResponseEntity<>(response, status);
         }
     
         
        //DELETE
             
        @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<ResponseModel> delete(@PathVariable("id") String id) {
        LoggingResponseModel msgStart = LoggingResponseModel.builder()
                .message("Start delete user")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgStart));
        ResponseModel response = service.delete(id);
        HttpStatus status = response.getStatusCode() != null ? response.getStatusCode() : HttpStatus.NOT_FOUND;

        LoggingResponseModel msgEnd = LoggingResponseModel.builder()
                .message("End delete user")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgEnd));
        return new ResponseEntity<>(response, status);
        }

         
         
         
}

