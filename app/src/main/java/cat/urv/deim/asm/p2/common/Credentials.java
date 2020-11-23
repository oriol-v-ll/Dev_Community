package cat.urv.deim.asm.p2.common;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credentials that = (Credentials) o;
        return Objects.equals(mail, that.mail) &&
                Objects.equals(username, that.username) &&
                Objects.equals(token, that.token) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, username, token, password);
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;
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
