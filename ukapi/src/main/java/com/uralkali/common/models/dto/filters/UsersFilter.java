package com.uralkali.common.models.dto.filters;

public class UsersFilter extends SortingFilter {

    private boolean isSearchLock = false;

    private Long userId = null;
    private String userName = null;
    private String fullName = null;
    private Boolean companyExists = null;

    //sorting=[USER_NAME, FULL_NAME]

    public UsersFilter() { }

    public boolean isSearchLock() {
        return isSearchLock;
    }

    public void setSearchLock(boolean searchLock) {
        isSearchLock = searchLock;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getCompanyExists() {
        return companyExists;
    }

    public void setCompanyExists(Boolean companyExists) {
        this.companyExists = companyExists;
    }
}
