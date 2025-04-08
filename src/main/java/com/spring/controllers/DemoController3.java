package com.spring.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.components.CustomAPIResponse;
import com.spring.components.CustomException;
import com.spring.components.CustomException2;
import com.spring.components.ErrorResponse;
import com.spring.pojos.CropData;
import com.spring.serivces.CropService;
import com.spring.serivces.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Date;

@RestController
@RequestMapping("/api/")
public class DemoController3 {


    @Autowired
    private User user;

    @Autowired
    private CropService cropService;

    public ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/getUserDetails/")
    public ResponseEntity<CustomAPIResponse> getUserDetails(@RequestParam(name = "id") int id){
        System.out.println(" inside controller method");
        user.getUserDetailsV2();
        CustomAPIResponse response = new CustomAPIResponse();
        Link updateLink = WebMvcLinkBuilder.linkTo(DemoController3.class).slash("updateDetails").withRel("self").withType("Update");
        Link deleteLink = Link.of("/api/"+"/deleteUser").withRel("delete").withType("Post");
        response.add(updateLink);
        response.add(deleteLink);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/getCustomDetails/")
    public CustomAPIResponse getUserDetailsByCustomResponse(@RequestParam(name = "id") int id){
        System.out.println(" inside controller method");
        user.getUserDetailsV2();
        CustomAPIResponse response = new CustomAPIResponse();
        response.setMessage("Details Fetched Successfully");
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return response;
    }


    @GetMapping(value = "/getCropDetailsById/{id}")
    public ResponseEntity<String> getCropDetails(@PathVariable(name = "id") int id) throws JsonProcessingException {
        System.out.println(" fetching details for id "+id);
        CropData cropData = cropService.getCropData();
        String JsonString = objectMapper.writeValueAsString(cropData);
        return ResponseEntity.status(HttpStatus.OK).body(JsonString);
    }


    @GetMapping(value = "/exceptionExamples/")
    public void exceptionHandlerExample(){
       // throw new NullPointerException("throwing null pointer exception for testing");
        throw new CustomException("throwing custom Exception",HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/returnResponseEntity/")
    public ResponseEntity<?> returnResponseEntity(){
        try {
            throw new CustomException("customException was thrown",HttpStatus.BAD_REQUEST);
        }catch (CustomException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getHttpStatus().value(),e.getMessage(), new Date());
            return new ResponseEntity<>(errorResponse,e.getHttpStatus());
        }

    }



    @GetMapping("/responseStatusExample/")
    public void responseStatusExample(){
        throw new CustomException2(HttpStatus.BAD_REQUEST, "new exception thrown");
    }



//    @ResponseStatus(value = HttpStatus.OK, reason = "customException")
//    @ExceptionHandler({CustomException.class, NullPointerException.class})
//    public ResponseEntity<?>  handleCustomException(Exception e){
//
//        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(),e.getMessage()+" exception handled at controller level ", new Date());
//        return new ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
//    }

    @ResponseStatus(value = HttpStatus.OK, reason = "customException")
    @ExceptionHandler({CustomException.class, NullPointerException.class})
    public void  handleCustomException(HttpServletResponse response, Exception e) throws IOException {

        response.sendError(HttpStatus.FORBIDDEN.value(),e.getMessage());

    }

}
