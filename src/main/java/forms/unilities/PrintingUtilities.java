package forms.unilities;

import java.awt.*;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;

public class PrintingUtilities {
    public void print(JTable jTable, String name) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("pdf/"+name+".pdf")));

            document.open();
            PdfContentByte cb = writer.getDirectContent();

            cb.saveState();
            Graphics2D g2 = cb.createGraphicsShapes(jTable.getWidth(),jTable.getHeight());

            Shape oldClip = g2.getClip();
            g2.clipRect(0, 0, jTable.getWidth(), jTable.getHeight());

            jTable.print(g2);
            g2.setClip(oldClip);

            g2.dispose();
            cb.restoreState();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        document.close();
    }
}
