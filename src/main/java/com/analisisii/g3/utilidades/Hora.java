package com.analisisii.g3.utilidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author gian_
 */
public class Hora implements Runnable {

    private JLabel lbl;

    public Hora(JLabel lbl) {
        this.lbl = lbl;
    }

    @Override
    public void run() {
        while (true) {
            String dt = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:ss").format(LocalDateTime.now());
            lbl.setText(dt);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
