package ma.skypay.bankingservice;

public interface AccountService {
    void deposit(int amount);
    void widthdraw(int amount);
    void printStatement();
}