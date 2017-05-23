package en.edu.whu.listener; /**
 * Created by wuwenan on 11/05/2017.
 */

import en.edu.whu.entity.User;
import en.edu.whu.util.SessionUtil;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

@WebListener()
public class MyHttpSessionListener implements HttpSessionListener {

    private int userNumber = 0;

    // Public constructor is required by servlet spec
    public MyHttpSessionListener() {
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
        userNumber++;
        //将计数变量放入ServletContext对象中，ServletContext对象即jsp内置的application对象
        se.getSession().getServletContext().setAttribute("userNumber", userNumber);

    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
        userNumber--;

        //从application范围中取出用户列表对象
        ArrayList<User> userList = null;
        userList = (ArrayList<User>) se.getSession().getServletContext().getAttribute("userList");
        //若用户列表中有该用户，则从用户列表对象中删除
        if (SessionUtil.getUserBySessionId(userList, se.getSession().getId()) != null) {
            userList.remove(SessionUtil.getUserBySessionId(userList, se.getSession().getId()));
        }

    }
}
