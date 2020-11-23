package cat.urv.deim.asm.p2.common;

import java.util.Objects;

public class ValidarUsuario {
    String version;
    String timestamp;
    String method;
    String status;
    String message;
    String userAgent;

    public ValidarUsuario(String version, String timestamp, String method, String status, String message, String userAgent){
        this.version=version;
        this.timestamp=timestamp;
        this.method=method;
        this.status=status;
        this.message=message;
        this.userAgent=userAgent;
    }

    @Override
    public String toString() {
        return "ValidarUsuario{" +
                "version='" + version + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidarUsuario that = (ValidarUsuario) o;
        return Objects.equals(version, that.version) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(method, that.method) &&
                Objects.equals(status, that.status) &&
                Objects.equals(message, that.message) &&
                Objects.equals(userAgent, that.userAgent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, timestamp, method, status, message, userAgent);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
