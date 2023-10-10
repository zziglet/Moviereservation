package reservation;

import member.Member;
import movie.Movie;

public interface ReservationService {
    void createReservation(Member member);
    void cancelReservation(Member member);

}
