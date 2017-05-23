package en.edu.whu.util;

import en.edu.whu.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuwenan on 11/05/2017.
 */
public class SessionUtil {
    public static Object getUserBySessionId(ArrayList<User> userList, String SessionIdString) {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (SessionIdString.equals(user.getSessionIdString())) {
                return user;
            }
        }
        return null;
    }
}
