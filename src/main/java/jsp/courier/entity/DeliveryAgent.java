package jsp.courier.entity;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "delivery_agents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "agent_code", unique = true, nullable = false)
    private String agentCode;

//    @NotBlank(message = "First name is required")
//    @Column(nullable = false)
    private String firstName;

//    @NotBlank(message = "Last name is required")
//    @Column(nullable = false)
    private String lastName;

    @Email(message = "Invalid email")
//    @Column(unique = true, nullable = false)
    private String email;

//    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
//    @Column(unique = true, nullable = false)
    private String phoneNo;

    @Column(nullable = false)
    private String vehicleType;

    @Column(unique = true, nullable = false)
    private String vehicleNumber;

    @Column(unique = true)
    private String drivingLicenseNo;

    private String currentLocation;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private AgentStatus status;

    @Column(nullable = false)
    private Boolean available;

    private LocalDate joiningDate;

    private Double salary;

//    @OneToMany(mappedBy = "deliveryAgent",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Shipment> shipments;
}