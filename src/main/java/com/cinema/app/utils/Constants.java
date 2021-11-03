package com.cinema.app.utils;

public final class Constants {
    public static final int MIN_MONEY_RANDOM = 100;
    public static final int MAX_MONEY_RANDOM = 5000;

    public static final String USER = "user";
    public static final String ADMIN = "admin";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String BANK_ACCOUNT = "bankAccount";
    public static final String ROLE = "role";

    public static final String ID = "id";
    public static final String MOVIE_TITLE = "movieTitle";
    public static final String AGE = "age";
    public static final String POSTER = "poster";
    public static final String SESSION_DATE = "sessionDate";
    public static final String SESSION_TIME = "sessionTime";
    public static final String PRICE = "price";
    public static final String FREE_PLACES = "freePlaces";
    public static final String HALL_ID = "hallId";

    public static final String SQL_LOGIN = "select login from account where role='%s';";
    public static final String SQL_PASSWORD = "select password from account where role='%s';";
    public static final String SQL_REGISTRATION = "INSERT INTO account (login, password, role) VALUES (?,?,?);";
    public static final String SQL_ADD_ACCOUNT = "INSERT INTO bankAccount (accountNumber, money, userId) VALUES (?,?,?);";
    public static final String SQL_DELETE_USER = "DELETE FROM account WHERE login= ?;";
    public static final String SQL_SCHEDULE = " SELECT s.id, m.movieTitle, m.age, m.poster, s.sessionDate, s.sessionTime, s.price, s.freePlaces, s.hallId FROM schedule s LEFT JOIN movie m ON m.id=s.movieId;";
    public static final String SQL_MOVIE_TITLE = "SELECT movieTitle FROM movie;";
    public static final String SQL_ADD_TO_SCHEDULE = "INSERT INTO schedule(movieId, sessionDate, sessionTime, price, freePlaces, hallId) VALUES (?,?,?, 200.00, 40, 1);";
    public static final String SQL_CANCEL_SESSION = "DELETE FROM schedule WHERE id=?;";
    public static final String SQL_SESSION_EXISTS = "SELECT id FROM schedule;";
    public static final String SQL_MOVIE_ID = "SELECT id FROM movie WHERE movieTitle= '%s';";
    public static final String SQL_USER_ID = "SELECT id FROM account WHERE login= '%s';";

    public static final String PASSWORD_TERMS = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
    public static final String BANK_ACCOUNT_TERMS = "\\d{8,}";
    public static final String ID_TERMS = "\\d{1,}";
    public static final String DATA_TERMS = "yyyy-MM-dd";

    public static final String ERROR_MASSAGE = "errorMessage";
    public static final String ERROR_REGISTRATION = "Error. Such a user exists or the specified data does not meet the conditions of registration";
    public static final String ERROR_INVALID_FIELDS = "Invalid login or Password";
    public static final String ERROR_ID = "Error. The specified session ID does not exist";
    public static final String ERROR_MOVIE_SESSION = "Error. You can add a movie to the schedule no later than the day before it is shown";
    public static final String ERROR_EMPTY = "Error. One of the fields is empty";


    public static final String LIST = "list";
    public static final String LOGIN_USER = "loginUser";
    public static final String REDIRECT_ID = "redirectId";
    public static final String LOGIN_REDIRECT_ID = "/login?redirectId=";

    public static final String URL_ANY = "/*";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGOUT = "/logout";
    public static final String URL_INFO = "/userInfo";
    public static final String URL_USER = "/userMenu";
    public static final String URL_ADMIN = "/adminMenu";
    public static final String URL_SCHEDULE = "/schedule";
    public static final String URL_REGISTRATION = "/registration";
    public static final String URL_CREATE_ACCOUNT = "/createAccount";
    public static final String URL_SCHEDULE_CHANGES = "/scheduleChanges";

    public static final String JSP_INDEX = "/index.jsp";
    public static final String JSP_ACCESS_DENIED = "/WEB-INF/views/accessDeniedView.jsp";
    public static final String JSP_LOGIN = "/WEB-INF/views/loginView.jsp";
    public static final String JSP_INFO = "/WEB-INF/views/userInfoView.jsp";
    public static final String JSP_USER = "/WEB-INF/views/userPageView.jsp";
    public static final String JSP_ADMIN = "/WEB-INF/views/adminPageView.jsp";
    public static final String JSP_SCHEDULE = "/WEB-INF/views/scheduleView.jsp";
    public static final String JSP_REGISTRATION = "/WEB-INF/views/registrationView.jsp";
    public static final String JSP_CONGRATULATIONS = "/cinema/congratulations.jsp";
    public static final String JSP_CREATE_ACCOUNT = "/WEB-INF/views/createAccountView.jsp";
    public static final String JSP_SCHEDULE_CHANGES = "/WEB-INF/views/scheduleChangesView.jsp";
}