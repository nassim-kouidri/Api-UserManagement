package com.nassim.usermanagement.Controller;

import com.nassim.usermanagement.DTO.ErrorResponse;
import com.nassim.usermanagement.DTO.Request.UserRequest;
import com.nassim.usermanagement.DTO.Response.UserResponse;
import com.nassim.usermanagement.Model.UserModel;
import com.nassim.usermanagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    public  UserService userService;

    @GetMapping("/allUsers")
    public List<UserModel> findAllUser() {
        return userService.findByAll();
    }

//    @GetMapping("/user/{id}")
//    public Optional<UserModel> findById(@PathVariable("id") Long id){
//        return userService.findById(id);
//    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Optional<UserModel> user = userService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        ErrorResponse errorResponse = new ErrorResponse("L'utilisateur n'existe pas dans la bdd");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addUser")
    public UserModel saveUser(@RequestBody UserModel userModel) {
        return userService.saveUser(userModel);
    }

//    @PutMapping(path="/updateUser/{id}")
//    public UserModel updateUser(@PathVariable("id") Long id, @RequestBody UserModel userModel){
//        Optional<UserModel> e =userService.findById(id);
//        if(e.isPresent()) {
//            UserModel currentUser = e.get();
//
//            String name = userModel.getName();
//            if(name != null) {
//                currentUser.setName(name);
//            }
//
//            String address = userModel.getAddress();
//            if(address != null) {
//                currentUser.setAddress(address);
//            }
//
//            userService.saveUser(currentUser);
//            return currentUser;
//        } else {
//            return null;
//        }
//    }

    @PutMapping(path = "/updateUser/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody UserModel userModel) {
        Optional<UserModel> e = userService.findById(id);
        if (e.isPresent()) {
            UserModel currentUser = e.get();

            Field[] fields = currentUser.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(userModel);
                    if (value != null) {
                        field.set(currentUser, value);
                    }
                } catch (IllegalAccessException exception) {
                    exception.printStackTrace();
                }
            }

            userService.saveUser(currentUser);
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("L'utilisateur n'existe pas dans la bdd");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND) ;
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id")Long id){
         userService.deleteUser(id);
    }


    @PostMapping("/res")
    public UserResponse saveUserResponse(@RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

//    @PutMapping("/res/{id}")
//    public UserResponse updateUserResponse(@RequestBody UserRequest userRequest, @PathVariable("id") Long id) {
//        return userService.updateUserWithDTO(userRequest, id);
//    }

    @PutMapping("/newUpdateUser/{id}")
    public ResponseEntity<?> updateUserResponse(@RequestBody UserModel userModel, @PathVariable("id") Long id) {
        Optional<UserModel> e = userService.findById(id);
        if(e.isPresent()){
            return new ResponseEntity<>(userService.updateUserWithoutDTO(userModel, id), HttpStatus.OK);
        }
//        ErrorResponse errorResponse = new ErrorResponse("L'utilisateur n'existe pas dans la bdd");
        String messageError = "l'utilisateur n'existe pas";
        return new ResponseEntity<>(messageError, HttpStatus.NOT_FOUND) ;

    }

    @GetMapping("/helloworld")
    public String  hello() {
        return userService.HelloWord();
    }
}
