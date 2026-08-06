package jsp.courier.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.courier.dto.ResponseStructure;
import jsp.courier.dto.UsersRequest;
import jsp.courier.dto.UsersResponse;
import jsp.courier.entity.Users;
import jsp.courier.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<UsersResponse>> saveUser(@RequestBody Users user) {
        return ResponseEntity.ok(service.saveUser(user));
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<UsersResponse>>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<UsersResponse>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<UsersResponse>> updateUser(
            @PathVariable Long id,
            @RequestBody UsersRequest user) {

        return ResponseEntity.ok(service.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        service.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully");
    }
}