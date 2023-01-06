package cap_10_java_time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Cap10T02 {
    public static void main(String[] args) {

        // criando data atual e add 1 mes a ela
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        System.out.println(nextMonth);

        LocalDate lastYear = LocalDate.now().minusYears(1);
        System.out.println(lastYear);

        /*
         * LocalDate armazena irformaçao de data apenas sem horas e timezone
         * para armazenar horas utilizamos o LocalTime
         * para exibir data e hora usamos o LocalDateTime
         */
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        // usando o atTime do LocalDate para settar uma hora especifica
        LocalDateTime todayAt = LocalDate.now().atTime(12, 0);
        System.out.println(todayAt);

        // unindo LocalDate com LocalTime com o atTime

        LocalDate dayDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        LocalDateTime todayNow = dayDate.atTime(nowTime);
        System.out.println(todayNow);

        // Criando um Zone data time, que armazena informações de Zone
        ZonedDateTime zonedDateTime1 = todayNow.atZone(ZoneId.of("America/Sao_Paulo"));
        System.out.println(zonedDateTime1);

        LocalDate oldYearDate = LocalDate.now().withYear(1980);
        System.out.println(oldYearDate.getDayOfMonth());

        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
        System.out.println(LocalDate.now().isAfter(tomorrowDate));
        System.out.println(LocalDate.now().isBefore(tomorrowDate));
        System.out.println(LocalDate.now().isEqual(tomorrowDate));

        ZonedDateTime tokyo = ZonedDateTime
                .of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
        ZonedDateTime saoPaulo = ZonedDateTime
                .of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));

                tokyo = tokyo.plusHours(12);
        System.out.println(tokyo.isEqual(saoPaulo));

        Locale pt = new Locale("pt");
        System.out.println(Month.DECEMBER.firstMonthOfQuarter().getDisplayName(TextStyle.SHORT, pt));
        System.out.println(Month.DECEMBER.plus(1));
        System.out.println(Month.DECEMBER.minus(1));

        LocalDateTime nowLocalTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String result = nowLocalTime.format(formatter);
        LocalDate resultDate = LocalDate.parse(result, formatter);
        System.out.println(resultDate);


        LocalDate agora = LocalDate.now();
        LocalDate outraData =LocalDate.of(1989, Month.JANUARY, 25);


        LocalTime agora2 = LocalTime.now();
        LocalTime outraHora = agora2.plusHours(2);


        long days = ChronoUnit.DAYS.between(outraData,agora);
        Period period = Period.between(agora, outraData);
        period =period.negated();
        
        Duration duration = Duration.between(agora2, outraHora);

        System.out.println(days);
        System.out.println(period.getYears());
        System.out.println(duration.toHours());
    }
}
