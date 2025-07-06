package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

@Disabled("Удалить аннотацию после реализации всех методов")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyOnAlreadyBookedSeatThenGetException() {
        Account accountFirst = new AccountCinema();
        Account accountSecond = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        cinema.buy(accountFirst, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(accountSecond, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenAddSameSessionTwiceThenGetException() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        assertThatThrownBy(() -> cinema.add(session))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyOnPastDateThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, -1);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }
}