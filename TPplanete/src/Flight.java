import java.time.LocalDateTime;

public class Flight {
    private final Aeroport departure;
    private final Aeroport arrival;
    private final String airlineName;
    private final String airLineCode;
    private final int number;
    private final LocalDateTime arrivalTime;

    public Aeroport getDeparture() {
        return departure;
    }

    private final LocalDateTime departureTime;

    public Aeroport getArrival() {
        return arrival;
    }

    public Flight(Aeroport departure, Aeroport arrival, String airlineName,
                  String airLineCode, int number, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.departure = departure;
        this.arrival = arrival;
        this.airlineName = airlineName;
        this.airLineCode = airLineCode;
        this.number = number;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "departure=" + departure +
                ", arrival=" + arrival +
                ", airline='" + airlineName + '\'' +
                ", number=" + number +
                ", IATA=" + airLineCode +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}