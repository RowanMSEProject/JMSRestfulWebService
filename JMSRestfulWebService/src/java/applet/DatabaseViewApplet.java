/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JTextArea;

/**
 *
 * @author mse
 */
public abstract class DatabaseViewApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
    }

    abstract public void refresh(String name, String message)
            throws MalformedURLException, IOException;

    public void refresh(String name, String message,
            String service, String command,
            String URL, JTextArea textarea)
            throws MalformedURLException, IOException {
        if (name.equals(service) && message.equals(command)) {
            URL oracle = new URL(URL);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            textarea.setText("");
            while ((inputLine = in.readLine()) != null) {
                textarea.append(inputLine + "\n");
            }
            in.close();
        }
    }

}
