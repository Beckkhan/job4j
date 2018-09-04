package ru.job4j.transfer;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1
 * @since 04.09.2018
 * */
public class TransferTest {
    @Test
    public void whenAddUser() {
        Transfer transfer = new Transfer();
        User user = new User("A", "353535");
        Account account = new Account(50, "FIRST");
        transfer.addUser(user);
        transfer.addAccountToUser("353535", account);
        List<Account> result = transfer.getUserAccounts("353535");
        assertTrue(transfer.getDatabase().get(user).equals(result));
        assertThat(result.size(), is(1));
    }
    @Test
    public void whenDeleteUser() {
        Transfer transfer = new Transfer();
        User user = new User("B", "353535");
        transfer.addUser(user);
        transfer.deleteUser(user);
        assertNull(transfer.getDatabase().get(user));
    }
    @Test
    public void whenDeleteAccount() {
        Transfer transfer = new Transfer();
        User user = new User("C", "232323");
        Account first = new Account(50, "Dow");
        Account second = new Account(30, "Msk");
        transfer.addUser(user);
        transfer.addAccountToUser("232323", first);
        transfer.addAccountToUser("232323", second);
        transfer.deleteAccountFromUser("232323", first);
        List<Account> result = transfer.getUserAccounts("232323");
        assertTrue(transfer.getDatabase().get(user).equals(result));
        assertThat(result.size(), is(1));
    }
    @Test
    public void whenUserTransferAnotherThenTrue() {
        Transfer transfer = new Transfer();
        String passportSrc = "232323";
        String passportDest = "353535";
        User src = new User("A", passportSrc);
        User dest = new User("B", passportDest);
        Account srcAcc = new Account(50, "NY");
        Account destAcc = new Account(10, "TOK");
        transfer.addUser(src);
        transfer.addUser(dest);
        transfer.addAccountToUser(passportSrc, srcAcc);
        transfer.addAccountToUser(passportDest, destAcc);
        boolean result = transfer.transferMoney(passportSrc, "NY", passportDest, "TOK", 20);
        assertThat(result, is(true));
    }
    @Test
    public void whenSrcDoesNotHaveEnoughMoneyThenFalse() {
        Transfer transfer = new Transfer();
        String passportSrc = "232323";
        String passportDest = "353535";
        User src = new User("A", passportSrc);
        User dest = new User("B", passportDest);
        Account srcAcc = new Account(20, "NY");
        Account destAcc = new Account(10, "TOK");
        transfer.addUser(src);
        transfer.addUser(dest);
        transfer.addAccountToUser(passportSrc, srcAcc);
        transfer.addAccountToUser(passportDest, destAcc);
        boolean result = transfer.transferMoney(passportSrc, "NY", passportDest, "TOK", 30);
        assertThat(result, is(false));
    }
    @Test
    public void whenWrongSrcPassportThenFalse() {
        Transfer transfer = new Transfer();
        String passportSrc = "232323";
        String passportDest = "353535";
        User src = new User("A", passportSrc);
        User dest = new User("B", passportDest);
        Account srcAcc = new Account(20, "NY");
        Account destAcc = new Account(10, "TOK");
        transfer.addUser(src);
        transfer.addUser(dest);
        transfer.addAccountToUser(passportSrc, srcAcc);
        transfer.addAccountToUser(passportDest, destAcc);
        boolean result = transfer.transferMoney("000178", "NY", passportDest, "TOK", 30);
        assertThat(result, is(false));
    }
    @Test
    public void whenTransferIsSuccessfulAndBalanceIsValidThenCorrectBalance() {
        Transfer transfer = new Transfer();
        String passportSrc = "232323";
        String passportDest = "353535";
        User src = new User("A", passportSrc);
        User dest = new User("B", passportDest);
        Account srcAcc = new Account(50, "NY");
        Account destAcc = new Account(10, "TOK");
        transfer.addUser(src);
        transfer.addUser(dest);
        transfer.addAccountToUser(passportSrc, srcAcc);
        transfer.addAccountToUser(passportDest, destAcc);
        transfer.transferMoney(passportSrc, "NY", passportDest, "TOK", 20);
        List<Account> result = transfer.getUserAccounts(passportDest);
        assertThat(result.get(0).getValue(), is(30.0));
    }
}