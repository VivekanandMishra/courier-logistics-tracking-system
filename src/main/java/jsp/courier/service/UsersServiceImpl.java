package jsp.courier.service;

import java.util.List;

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

        UsersResponse userResponse = new UsersResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setUsername(savedUser.getUsername());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setRole(savedUser.getRole());   
//         userResponse.setEnabled(savedUser.isEnabled()); 

        ResponseStructure<UsersResponse> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("User saved successfully");
        response.setData(userResponse);

        return response;
    }
    

	@Override
	public ResponseStructure<UsersResponse> getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseStructure<List<UsersResponse>> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseStructure<UsersResponse> updateUser(Long id, UsersRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseStructure<String> deleteUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}