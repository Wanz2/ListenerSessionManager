package en.edu.whu.listener; /**
 * Created by wuwenan on 11/05/2017.
 */

import en.edu.whu.entity.User;
import en.edu.whu.util.SessionUtil;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static en.edu.whu.util.SessionUtil.getUserBySessionId;

//需要通过request来获得用户的信息，包括sessionId，ip地址和第一次访问时间
@WebListener()
public class MyServletRequestListener implements ServletRequestListener {

    private ArrayList<User> userList;//在线用户列表

    // Public constructor is required by servlet spec
    public MyServletRequestListener() {
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        //从application对象中获得userList
        userList = (ArrayList<User>) servletRequestEvent.getServletContext().getAttribute("userList");

        //若第一次请求，则上下文中还没有userList，则新建一个userList
        if (userList == null) {
            userList = new ArrayList<>();
        }

        //将ServletRequest转换为HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        //获得sessionId
        String sessionIdString = request.getSession().getId();

        //若在线用户列表中没有该用户（即没有匹配的SessionId）则将其加入
        if (SessionUtil.getUserBySessionId(userList,sessionIdString) == null) {
            User user = new User();
            user.setSessionIdString(sessionIdString);
            user.setFirstTimeString(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
            user.setIpString(request.getRemoteAddr());
            userList.add(user);
        }

        //将用户列表对象放入application范围中
        servletRequestEvent.getServletContext().setAttribute("userList", userList);

    }
}
