@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;
    private String plate;
    
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private LocalDateTime entryTime;
}
