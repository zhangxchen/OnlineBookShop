package com.atguigu.pojo;

public class Manager {
    private Integer managerId;
    private String managerPassword;
    private String managerName;
    private String managerEmail;

    public Manager() {

    }

    public Manager(final Integer managerId, final String managerPassword, final String managerName,
            final String managerEmail) {
        this.managerId = managerId;
        this.managerPassword = managerPassword;
        this.managerName = managerName;
        this.managerEmail = managerEmail;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(final Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(final String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(final String managerName) {
        this.managerName = managerName;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(final String managerEmail) {
        this.managerEmail = managerEmail;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", managerPassword='" + managerPassword + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerEmail='" + managerEmail + '\'' +
                '}';
    }
}
