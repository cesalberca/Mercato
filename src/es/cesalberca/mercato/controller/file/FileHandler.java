package es.cesalberca.mercato.controller.file;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import es.cesalberca.mercato.controller.database.DBHandler;
import es.cesalberca.mercato.model.Item;
import es.cesalberca.mercato.model.Order;
import es.cesalberca.mercato.model.User;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.Date;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * Clase que se ocupa de las exportaciones a ficheros.
 * @author César Alberca
 */
public class FileHandler {
    
    /**
     * Exporta los pedidos de un usuario a html.
     * @param u Usuario del que se generarán los datos.
     * @throws FileNotFoundException Fichero no encontrado.
     */
    public void exportToHtml(User u) throws FileNotFoundException {
        DBHandler dbh = new DBHandler();
        PrintWriter pw = null;
        
        pw = new PrintWriter(u.getName() + ".html");
        pw.println("<!DOCTYPE html>");
        pw.println("<html>");
            pw.println("\t<head>");
                pw.println("\t\t<meta charset='UTF-8'>");
                pw.println("\t\t<title> Pedidos: "  + u.getName() + "</title>");
            pw.println("\t</head>");
            pw.println("\t<body>");
            pw.println("\t\t<h1>Usuario: " + u.getName() + "</h1>");
            pw.println("\t\t<table border='1' rules='all'>");

            pw.println("\t\t\t<tr>");
            pw.print("\t\t\t\t<th>");
            pw.print("Nombre");
            pw.println("</th>");
            pw.print("\t\t\t\t<th>");
            pw.print("Precio");
            pw.println("</th>");
            pw.print("\t\t\t\t<th>");
            pw.print("Categoría");
            pw.println("</th>");
            pw.println("\t\t\t</th>");
            
            for (Order order : u.getOrders()) {
                for (Item item : order.getItems()) {
                    pw.println("\t\t\t<tr>");
                    pw.print("\t\t\t\t<td>");
                    pw.print(item.getName());
                    pw.println("</td>");
                    pw.print("\t\t\t\t<td>");
                    pw.print(item.getPrize());
                    pw.println("</td>");
                    pw.print("\t\t\t\t<td>");
                    pw.print(item.getCategory().getName());
                    pw.println("</td>");
                    pw.println("\t\t\t</tr>");
                }   
            }
            pw.println("\t\t</table>");
            pw.println("\t\t<span>Precio total:" + u.getTotalPrizeOrders() + "€ </span>");
            pw.println("\t</body>");
        pw.println("</html>");
        pw.flush();
        pw.close();
    }
    
    /**
     * Función que exporta a pdf.
     * @param user Usuario del que se exportarán los pedidos.
     * @throws DocumentException Error al generar el documento.
     * @throws FileNotFoundException Fichero no encontrado o actualmente en uso.
     */
    public void exportToPdf(User user) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(user.getName() + ".pdf"));
        document.open();

        Chapter chapter = new Chapter(1);
        Chunk chunk = new Chunk(user.getName());
        chapter.add(chunk);
        createTable(chapter, user);
        chapter.add(new Paragraph("Precio total:" + user.getTotalPrizeOrders() + "€" ));
        document.add(chapter);

        document.close();
    }
    
    /**
     * Crea una tabla a partir de los pedidos del usuario.
     * @param section Sección donde se meterá la tabla.
     * @param u Usuario del que se sacará la tabla.
     * @throws BadElementException 
     */
    private static void createTable(Section section, User u) throws BadElementException {
        PdfPTable table = new PdfPTable(3);

        PdfPCell cell = new PdfPCell(new Phrase("Nombre"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Precio"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Categoría"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        table.setHeaderRows(1);
        
        for (Order order : u.getOrders()) {
            for (Item item : order.getItems()) {
                table.addCell(item.getName());
                table.addCell("" + item.getPrize());
                table.addCell(item.getCategory().getName());
            }
        }

        section.add(table);
    }
}
