public class User {
    private String name;
    private String email;
    private String password;
    private int saldo;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.saldo = 0;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    } 

    public void setUsername(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (authenticate(oldPassword)) {
            setPassword(newPassword);
        } else {
            System.out.println("Old password is incorrect.");
        }
    }
    

}
