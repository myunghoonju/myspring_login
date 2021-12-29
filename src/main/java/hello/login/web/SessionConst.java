package hello.login.web;

public enum SessionConst {

    LOGIN_MEMBER("loginMember"),
    VIP_MEMBER("vipMember");

    private final String sessionName;

    SessionConst(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionName() {
        return sessionName;
    }
}
