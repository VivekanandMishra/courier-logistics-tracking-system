package jsp.courier.service;

import java.util.List;

import jsp.courier.dto.ResponseStructure;
import jsp.courier.dto.UsersRequest;
import jsp.courier.dto.UsersResponse;
import jsp.courier.entity.Users;

public interface UsersService {

    ResponseStructure<UsersResponse> saveUser(Users user);

    ResponseStructure<UsersResponse> getUserById(Long id);

    ResponseStructure<List<UsersResponse>> getAllUsers();

    ResponseStructure<UsersResponse> updateUser(Long id, UsersRequest request);

    ResponseStructure<String> deleteUser(Long id);
}