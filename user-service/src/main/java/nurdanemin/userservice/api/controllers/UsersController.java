package nurdanemin.userservice.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.userservice.business.abstracts.UserService;
import nurdanemin.userservice.business.dto.request.create.CreateAddressRequest;
import nurdanemin.userservice.business.dto.request.create.CreateUserRequest;
import nurdanemin.userservice.business.dto.request.update.UpdateUserRequest;
import nurdanemin.userservice.business.dto.response.create.CreateUserResponse;
import nurdanemin.userservice.business.dto.response.get.GetAllUsersResponse;
import nurdanemin.userservice.business.dto.response.get.GetUserResponse;
import nurdanemin.userservice.business.dto.response.update.UpdateUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/")
public class UsersController {
    private final UserService service;


    @GetMapping
    public List<GetAllUsersResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetUserResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse add(@RequestBody CreateUserRequest request){
        return service.createUser(request);
    }

    @PutMapping("{id}")
    public UpdateUserResponse update(@PathVariable  UUID id, @RequestBody UpdateUserRequest request){
        return  service.updateUser(id, request);
    }

    @PutMapping("/add/address/{userId}")
    public void addAddresstoUser(@PathVariable  UUID userId, @RequestBody CreateAddressRequest addressRequest){
        service.addAddressForUser(userId, addressRequest);
    }




    @DeleteMapping("/delete-address-from-user/{userId}")
    public  void deleteAdDressForUser(@RequestParam UUID addressId, @PathVariable UUID userId){
        service.deleteAddressFromUser(addressId, userId);

    }
}
