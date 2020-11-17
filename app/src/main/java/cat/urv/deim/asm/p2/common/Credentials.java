package cat.urv.deim.asm.p2.common;

public class Credentials {
    String mail;
    String username;
    String token;
    String password;

    public Credentials(String mail,String username,String token,String password){
        this.mail=mail;
        this.username=username;
        this.token=token;
        this.password=password;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "mail='" + mail + '\'' +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
