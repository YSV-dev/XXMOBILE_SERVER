package com.uralkali.common.models.dto.filters;

/**
 * Фильтр для выбора пользователей OEBS.
 * @author brzsmg
 */
public class OebsUsersFilter extends SortingFilter {

    private Long userId = null;
    private String userName = null;
    private String fullName = null;

    //sorting=[USER_NAME, FULL_NAME]

    public OebsUsersFilter() { }

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
}
