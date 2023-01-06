package cap_10_java_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Cap10T01 {
    public static void main(String[] args) {
        LocalDate mesqueVem = LocalDate.now().plusMonths(1);
        System.out.println(mesqueVem);

        LocalDate anoPassado = LocalDate.now().minusYears(1);
        System.out.println(anoPassado);

        LocalDateTime agora = LocalDateTime.now();
        System.out.println(agora);

        LocalTime agora2 = LocalTime.now();
        System.out.println(agora2);

        LocalDateTime todayMidDay = LocalDate.now().atTime(12,0);
        System.out.println(todayMidDay);

        LocalDate today = LocalDate.now();
        LocalDateTime todayAt = today.atTime(agora2);
        System.out.println(todayAt);
        }
}
