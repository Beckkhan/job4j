package ru.job4j.transfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс Transfer с методами для провеения операций
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1
 * @since 04.09.2018
 * */
public class Transfer {
    /**
     * Коллекция пользователей и счетов.
     */
    private Map<User, List<Account>> database = new HashMap<>();

    public Map<User, List<Account>> getDatabase() {
        return database;
    }

    /**
     * Добавление User
     */
    public void addUser(User user) {
        if (user != null) {
            this.database.putIfAbsent(user, new ArrayList<Account>());
        }
    }

    /**
     * Удаление User
     */
    public void deleteUser(User user) {
        this.database.remove(user);
    }

    /**
     * Добавление счета для пользователя
     */
    public void addAccountToUser(String passport, Account account) {
        if (passport != null && account != null) {
            for (User user : database.keySet()) {
                if (user.getPassport().equals(passport)) {
                    this.database.get(user).add(account);
                    break;
                }
            }
        }
    }

    /**
     * Удаление счета пользователя
     */
    public void deleteAccountFromUser(String passport, Account account) {
        if (passport != null & account != null) {
            for (User user : database.keySet()) {
                if (user.getPassport().equals(passport)) {
                    this.database.get(user).remove(account);
                    break;
                }
            }
        }
    }

    /**
     * Получение списка счетов пользователя
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> userAccounts = null;
        if (passport != null) {
            for (User user : database.keySet()) {
                if (user.getPassport().equals(passport)) {
                    userAccounts = this.database.get(user);
                    break;
                }
            }
        }
        return userAccounts;
    }

    /**
     * Метод для перечисления денег с одного счёта на другой счёт
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        if (srcPassport != null && srcRequisite != null
                && destPassport != null && dstRequisite != null && amount > 0) {
            Account srcAccount = this.findAccountByPassportAndRequisites(srcPassport, srcRequisite);
            Account destAccount = this.findAccountByPassportAndRequisites(destPassport, dstRequisite);
            if (srcAccount != null && destAccount != null && srcAccount.getValue() > amount) {
                srcAccount.setValue(srcAccount.getValue() - amount);
                destAccount.setValue(destAccount.getValue() + amount);
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод поиска счета по данным паспорта пользователя и реквизитам счета
     */
    private Account findAccountByPassportAndRequisites(String srcPassport, String srcRequisite) {
        Account result = null;
        List<Account> userAccounts = this.getUserAccounts(srcPassport);
        if (srcPassport != null && srcRequisite != null && userAccounts != null) {
            for (Account account : userAccounts) {
                if (account.getRequisites().equals(srcRequisite)) {
                    result = account;
                    break;
                }
            }
        }
        return result;
    }
}
