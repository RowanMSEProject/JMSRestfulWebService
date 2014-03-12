/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package applet;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JApplet;

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

    public void refresh(String name, String message) throws MalformedURLException, IOException {
        
    }
    
}
