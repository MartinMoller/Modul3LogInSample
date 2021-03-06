/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.OrderErrorException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin
 */
public class CreateOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws OrderErrorException {
        try {
            
        
        HttpSession session = request.getSession();
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int height = Integer.parseInt(request.getParameter("height"));
        int userId = (int) session.getAttribute("userId");
        int status = 0;
        boolean door = Boolean.parseBoolean(request.getParameter("door"));
        boolean window = Boolean.parseBoolean(request.getParameter("window"));

        LogicFacade.createOrder(length, width, height, userId, status, door, window);
        
        session.setAttribute("message", "Dit legeo hus er nu bestilt! "
                + " Gå ind på ordre siden for at se din stykliste!");

        return "order";
        }
        catch(Exception e){
            throw new OrderErrorException(e.getMessage());
        }
    }

}
