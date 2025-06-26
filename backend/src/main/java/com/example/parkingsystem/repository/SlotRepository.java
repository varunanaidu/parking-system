@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByFloor(int floor);
}