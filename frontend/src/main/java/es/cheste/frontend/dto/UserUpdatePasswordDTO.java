package es.cheste.frontend.dto;

import java.util.Objects;

public class UserUpdatePasswordDTO {

    private String oldPassword;
    private String newPassword;

    public UserUpdatePasswordDTO() {
        super();
    }

    public UserUpdatePasswordDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdatePasswordDTO that = (UserUpdatePasswordDTO) o;
        return Objects.equals(oldPassword, that.oldPassword) && Objects.equals(newPassword, that.newPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oldPassword, newPassword);
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
        sb.append("oldPassword='").append(oldPassword).append('\'');
        sb.append(", newPassword='").append(newPassword).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
