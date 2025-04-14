import com.invoice.entities.Ride;
import com.invoice.enums.RideType;
import com.invoice.services.InvoiceGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceGeneratorTest {

    @Test
    public void givenDistanceAndTime_shouldReturnFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Ride ride = new Ride(2.0, 5, RideType.NORMAL);
        double fare = invoiceGenerator.calculateFare(ride);
        assertEquals(25.0, fare); // (2 * 10) + (5 * 1) = 25
    }

    @Test
    public void givenShortDistanceAndTime_shouldReturnMinimumFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Ride ride = new Ride(0.1, 1, RideType.NORMAL);
        double fare = invoiceGenerator.calculateFare(ride);
        assertEquals(5.0, fare); // less than min, so Rs. 5
    }

    @Test
    public void givenMultipleRides_shouldReturnTotalFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Ride[] rides = {
                new Ride(2.0, 5, RideType.NORMAL),
                new Ride(0.1, 1, RideType.NORMAL)
        };
        double fare = invoiceGenerator.calculateFare(rides);
        assertEquals(30.0, fare); // 25 + 5
    }

}
