package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CookiesServlet")
public class CookiesServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //Se supone el usuario esta visitando el sitio por primera vez
        boolean nuevoUsuario = true;
        
        //Obtenemos el arreglo de cookies
        //Esta informacion se envia al servidor
        Cookie[] cookies = request.getCookies();
        
        //Buscamos si ya existe una cookie que ya hallamos agregado anteriormente al navegador
        //La cookie se llama: visitanteRecurrente
        if(cookies != null){ //si el arreglo de cookies es diferente de null
            for(Cookie  c: cookies){ //foreach para recorrer cada uno de los elmentos
                if(c.getName().equals("visitanteRecurrente") && c.getValue().equals("si")){
                    //si ya existe la cookie significa que ya es un usuario recurrente
                    //por lo tanto significa que ya encontramos la cookie que ya andabamos buscando
                    nuevoUsuario = false;   
                    break;
                }
            }
        }
        
        
        //Esto es para verificar si el usuario es nuevo, pero ver si esta cookie ya estaba definida
        String mensaje = null;
        if(nuevoUsuario == true){
            Cookie visitanteCookie = new Cookie("visitanteRecurrente","si");
            //Si es nuevo el usuario lo agregamos para que ya no sea nuevo
            response.addCookie(visitanteCookie);
            mensaje = "Gracias por visitar nuestro sitio por primera vez";
        }
        else{
            //Si el usuario ya habia estado antes en el sitio
            mensaje = "Gracias por visitar NUEVAMENTE nuestro sitio";
        }
        
        
        
        /*Es posible tambien saber si el sitio ya ha sido visitado dentro de el navegador
            en herramientas para desarrollador, aplicaciones, localhost*/
        
        //Para mandar la respuesta de lo ocurrido, en caso ya halla sido visitado o no
        response.setContentType("text/HTML;charset=UTF-8");
        PrintWriter salida = response.getWriter();
        salida.print("Mensaje: "+mensaje);
        salida.close();
        
    }
}