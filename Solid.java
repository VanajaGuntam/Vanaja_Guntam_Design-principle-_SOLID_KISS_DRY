class StringLengthValidator {
    static greaterThan(value: string, length: number): boolean {
        if ( value.length > length)  {
            return true
        } else {
            throw new Error("String is not long enough.");
        }
    }
}

class UserBalanceValidator {
    static haveEnoughFunds(user: User, amount: number): boolean {
        return (user.getBalance() - amount) > 0;
    }
}

/**
 * INTERFACES
 */
interface IUserAccount {
    _id: string;
    name: string;
    balance: number;
}

/**
 * CLASSES
 */
class User implements IUserAccount {

    _id: string = '';
    name: string = '';
    balance: number = 0;

    constructor(name) {
        this._id = this._generateRandomID();
        this.setName(name);
    }

    private _generateRandomID() {
        return Math.random().toString(36).substring(7);
    }

    getId(): string {
        return this._id;
    }

    setName(name: string): User {
        StringLengthValidator.greaterThan(name, 2);
        this.name = name;
        return this;
    }

    getBalance(): number {
        return this.balance;
    }

    setBalance(amount: number): User {
        this.balance = amount;

        LoggerService.log("User " + this.getId() + " now has " + this.getBalance() );
        return this;
    }
}

class LoggerService {

    static log(message: string): string {
        message = (new Date()) + " :: " + message;
        console.log(message);
        return message;
    }

}

class AccountService {


    transfer(fromUser: User, toUser: User, amount: number): any {
        if (!UserBalanceValidator.haveEnoughFunds(fromUser, amount)) {
            LoggerService.log("User " + fromUser.getId() + " has not enough funds.");
            return {fromUser, toUser};
        }

        fromUser.setBalance(fromUser.getBalance() - amount);
        toUser.setBalance(toUser.getBalance() + amount);

        return {fromUser, toUser}
    }

    updateBalance(user: User, amount: number) : User {
        user.setBalance(user.getBalance() + amount);
        return user;
    }

}

const aService = new AccountService();
let u1 = new User("john");
let u2 = new User("bob");

u1 = aService.updateBalance(u1, 1000);
u2 = aService.updateBalance(u2, 500);

console.log( aService.transfer(u1, u2, 250) );
