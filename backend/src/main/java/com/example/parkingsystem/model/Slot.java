@Entity
public class Slot {
    @Id
    @GeneratedValue
    private Long id;

    private int floor;
    private int number;
    private boolean occupied;

    private Long vehicleId;
    }
