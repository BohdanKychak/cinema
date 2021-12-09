package com.cinema.app.utils;

public final class Constants {
    public static final int MIN_MONEY_RANDOM = 100;
    public static final int MAX_MONEY_RANDOM = 5000;
    public static final double PRICE_OF_SESSION = 200.00;
    public static final int MIN_SEATS_HALL = 1;
    public static final int MAX_SEATS_HALL = 40;
    public static final int HALL = 1;
    public static final long CINEMA_ID = 12;
    public static final long MAX_GENERATION_CODE = 999_999_999;
    public static final long MIN_GENERATION_CODE = 100_000_000;
    public static final int HOUR_START = 9;
    public static final int HOUR_FINISH = 22;

    public static final String USER = "user";
    public static final String ADMIN = "admin";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String PASSWORD2 = "password2";
    public static final String BANK_ACCOUNT = "bankAccount";
    public static final String ROLE = "role";

    public static final String ID = "id";
    public static final String MAX_ID = "maxId";
    public static final String MOVIE = "movie";
    public static final String MOVIE_TITLE = "movieTitle";
    public static final String AGE = "age";
    public static final String SESSION_TIME = "sessionTime";
    public static final String PRICE = "price";
    public static final String FREE_PLACES = "freePlaces";
    public static final String HALL_ID = "hallId";
    public static final String PLACE = "place";
    public static final String MONEY = "money";
    public static final String SCHEDULE_PAGE = "schedulePage";
    public static final String POSITION = "position";
    public static final String PAGE_SIZE = "pageSize";
    public static final String TOTAL = "total";
    public static final String SORT = "sort";
    public static final String SORT_ORDER = "sortOrder";
    public static final String FILTER_BY_AGE = "filterAge";
    public static final String DEFAULT_SORT = "s.sessionTime";
    public static final String DEFAULT_SORT_ORDER = "ASC";
    public static final String EMPTY = "";
    public static final String LANG = "lang";
    public static final String COOKIE_LOCALE = "cookieLocale";
    public static final String COOKIE_LOCALE_FILTER = "CookieLocaleFilter";
    public static final String STATUS_CODE = "statusCode";
    public static final String REQUEST_URI = "requestUri";
    public static final String SERVLET_NAME = "servletName";
    public static final String THROWABLE_NAME = "throwableName";
    public static final String THROWABLE_MESSAGE = "throwableMessage";
    public static final String PURCHASED = "purchased";

    public static final String SQL_LOGIN = "SELECT login FROM account WHERE role='%s';";
    public static final String SQL_PASSWORD = "SELECT password FROM account WHERE role='%s';";
    public static final String SQL_REGISTRATION = "INSERT INTO account (login, password, role) VALUES (?,?,?);";
    public static final String SQL_ADD_ACCOUNT = "INSERT INTO bankAccount (accountNumber, money, userId) VALUES (?,?,?);";
    public static final String SQL_DELETE_USER = "DELETE FROM account WHERE login= ?;";
    public static final String SQL_SCHEDULE = "SELECT s.id, m.movieTitle, m.age, s.sessionTime, s.price, s.freePlaces, s.hallId " +
            "FROM schedule s LEFT JOIN movie m ON m.id=s.movieId " +
            "WHERE s.sessionTime > '%s' %s ORDER BY %s %s LIMIT %d OFFSET %d;";
    public static final String SQL_MOVIE_TITLE = "SELECT movieTitle FROM movie;";
    public static final String SQL_SESSION_ID = "SELECT id FROM schedule WHERE sessionTime > '%s';";
    public static final String SQL_ADD_TO_SCHEDULE = "INSERT INTO schedule(movieId, sessionTime, price, freePlaces, hallId) VALUES (?,?, " + PRICE_OF_SESSION + ", " + MAX_SEATS_HALL + ", " + HALL + ");";
    public static final String SQL_CANCEL_SESSION = "DELETE FROM schedule WHERE id=?;";
    public static final String SQL_MOVIE_ID = "SELECT id FROM movie WHERE movieTitle= '%s';";
    public static final String SQL_MAX_USER_ID = "SELECT max(id) as maxId FROM account;";
    public static final String SQL_PLACE = "SELECT place FROM sessionSeats WHERE sessionId='%s';";
    public static final String SQL_SEAT_RESERVATION = "INSERT INTO sessionSeats(userId, sessionId, place, hallId, purchaseCode) VALUES (?, ?, ?, " + HALL + ", ?);";
    public static final String SQL_USER_ID = "SELECT id FROM account WHERE login='%s';";
    public static final String SQL_GET_BALANCE = "SELECT money FROM bankAccount WHERE userId=%s;";
    public static final String SQL_MONEY_TRANSACTIONS = "UPDATE bankAccount SET money=? WHERE userId=?;";
    public static final String SQL_GET_FREE_PLACES = "SELECT freePlaces FROM schedule WHERE id=%s;";
    public static final String SQL_NEW_NUMBER_OF_SEATS = "UPDATE schedule SET freePlaces=? WHERE id=?;";
    public static final String SQL_TOTAL = "SELECT count(*) as total FROM schedule WHERE sessionTime > '%s' %s;";
    public static final String SQL_MOVIE_ID_AGE = "SELECT id FROM movie %s;";
    public static final String SQL_FILTER_BY_AGE = "WHERE age='%s+'";
    public static final String SQL_ADDITIONAL_FILTER = "AND m.age='%s+'";

    public static final String UTF8 = "UTF-8";
    public static final String PASSWORD_TERMS = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
    public static final String BANK_ACCOUNT_TERMS = "\\d{8,}";
    public static final String NUMERIC_TERMS = "\\d{1,}";
    public static final String LIST_TERMS = "(([\\d])(\\,\\s)?)*";
    public static final String DATA_TIME_TERMS = "yyyy-MM-dd HH:mm";

    public static final String UNKNOWN = "Unknown";
    public static final String MESSAGE = "message";
    public static final String MESSAGE2 = "message2";
    public static final String MESSAGE_SEATS_FREE = "free";
    public static final String MESSAGE_SEATS_TAKEN = "taken";
    public static final String MESSAGE_REGISTRATION = "registration";
    public static final String MESSAGE_ACCOUNT = "account";
    public static final String MESSAGE_EMPTY = "empty";
    public static final String ERROR_PARAMETER_INVALID = "Parameter %s is invalid";
    public static final String ERROR_CONNECTION = "Cannot obtain a connection from pool";
    public static final String ERROR_PUT_BACK = "Connection not in the used array";

    public static final String ERROR_EXCEPTION = "javax.servlet.error.exception";
    public static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";
    public static final String ERROR_SERVLET_NAME = "javax.servlet.error.servlet_name";
    public static final String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";

    public static final String LIST = "list";
    public static final String LOGIN_USER = "loginUser";
    public static final String REDIRECT_ID = "redirectId";
    public static final String LOGIN_REDIRECT_ID = "/login?redirectId=";
    public static final String BEGINNING_FILTER_TOTAL = "AND (";
    public static final String MIDDLE_FILTER_TOTAL = " movieId=%d OR";
    public static final String END_FILTER_TOTAL = ")";

    public static final String URL_ANY = "/*";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGOUT = "/logout";
    public static final String URL_INFO = "/userInfo";
    public static final String URL_USER = "/userMenu";
    public static final String URL_ADMIN = "/adminMenu";
    public static final String URL_SCHEDULE = "/schedule";
    public static final String URL_REGISTRATION = "/registration";
    public static final String URL_PURCHASE = "/purchase";
    public static final String URL_PURCHASED = "/purchased";
    public static final String URL_CREATE_ACCOUNT = "/createAccount";
    public static final String URL_SCHEDULE_CHANGES = "/scheduleChanges";
    public static final String URL_APP_EXCEPTION_HANDLER = "/appExceptionHandler";

    public static final String JSP_INDEX = "/index.jsp";
    public static final String JSP_ACCESS_DENIED = "/accessDenied.jsp";
    public static final String JSP_LOGIN = "/WEB-INF/views/loginView.jsp";
    public static final String JSP_INFO = "/WEB-INF/views/userInfoView.jsp";
    public static final String JSP_USER = "/WEB-INF/views/userPageView.jsp";
    public static final String JSP_ADMIN = "/WEB-INF/views/adminPageView.jsp";
    public static final String JSP_SCHEDULE = "/WEB-INF/views/scheduleView.jsp";
    public static final String JSP_REGISTRATION = "/WEB-INF/views/registrationView.jsp";
    public static final String JSP_PURCHASE = "/WEB-INF/views/purchaseView.jsp";
    public static final String JSP_PURCHASED = "/WEB-INF/views/purchasedView.jsp";
    public static final String JSP_CONGRATULATIONS = "/cinema/congratulations.jsp";
    public static final String JSP_DONE = "/cinema/done.jsp";
    public static final String JSP_CREATE_ACCOUNT = "/WEB-INF/views/createAccountView.jsp";
    public static final String JSP_SCHEDULE_CHANGES = "/WEB-INF/views/scheduleChangesView.jsp";
    public static final String JSP_APP_EXCEPTION_HANDLER = "/WEB-INF/views/appExceptionHandler.jsp";
}
