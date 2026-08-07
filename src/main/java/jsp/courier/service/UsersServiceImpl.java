package jsp.courier.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.courier.dto.ResponseStructure;
import jsp.courier.dto.UsersRequest;
import jsp.courier.dto.UsersResponse;
import jsp.courier.entity.Users;
import jsp.courier.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public ResponseStructure<UsersResponse> saveUser(Users user) {

        Users savedUser = usersRepository.save(user);

        UsersResponse userResponse = mapToResponse(savedUser);

        ResponseStructure<UsersResponse> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("User saved successfully");
        response.setData(userResponse);

        return response;
    }

    @Override
    public ResponseStructure<UsersResponse> getUserById(Long id) {

        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UsersResponse userResponse = mapToResponse(user);

        ResponseStructure<UsersResponse> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("User fetched successfully");
        response.setData(userResponse);

        return response;
    }

    @Override
    public ResponseStructure<List<UsersResponse>> getAllUsers() {

        List<UsersResponse> users = usersRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ResponseStructure<List<UsersResponse>> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All users fetched successfully");
        response.setData(users);

        return response;
    }

    @Override
    public ResponseStructure<UsersResponse> updateUser(Long id, UsersRequest request) {

        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        // user.setEnabled(request.isEnabled());

        Users updatedUser = usersRepository.save(user);

        UsersResponse userResponse = mapToResponse(updatedUser);

        ResponseStructure<UsersResponse> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("User updated successfully");
        response.setData(userResponse);

        return response;
    }

    @Override
    public ResponseStructure<String> deleteUser(Long id) {

        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        usersRepository.delete(user);

        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("User deleted successfully");
        response.setData("User deleted");

        return response;
    }

    // Helper method
    private UsersResponse mapToResponse(Users user) {

        UsersResponse response = new UsersResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        // response.setEnabled(user.isEnabled());

        return response;
    }
}