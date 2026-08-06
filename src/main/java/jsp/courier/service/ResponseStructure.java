package jsp.courier.service;
import jsp.courier.dto.UsersResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class ResponseStructure<T> {

	    private int status;
	    private String message;
	    private T data;
		public void setData(UsersResponse usersResponse) {
			// TODO Auto-generated method stub
			
		}

	}
	
