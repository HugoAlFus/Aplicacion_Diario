package es.cheste.backend.dto;

import java.util.Objects;

public class UserUpdatePasswordDTO {

    private String email;
    private String oldPassword;
    private String newPassword;

    public UserUpdatePasswordDTO() {
        super();
    }

    public UserUpdatePasswordDTO(String email, String oldPassword, String newPassword) {
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdatePasswordDTO that = (UserUpdatePasswordDTO) o;
        return Objects.equals(email, that.email);
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserUpdatePasswordDTO{");
        sb.append("email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
