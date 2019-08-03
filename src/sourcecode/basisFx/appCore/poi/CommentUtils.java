package basisFx.appCore.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class CommentUtils {

    public static void setComment(HSSFWorkbook wb, HSSFSheet spreadsheet, Cell cell  ) throws IOException {

        String out = System.getProperty("user.dir") + "/" + "src/res/res/img/resized.jpg";
        File f = new File(out);
        byte[] picData;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int b;

        FileInputStream fis = new FileInputStream(out);
        if (f.exists()) {

            while ((b = fis.read()) != -1) {
                baos.write(b);
            }
            picData = IOUtils.toByteArray(fis);

            fis.close();


            BufferedImage bim= ImageIO.read(f);
            int height = bim.getHeight();
            int width = bim.getWidth();

            int rows=height/20;

            HSSFCreationHelper creationHelper = wb.getCreationHelper();
            HSSFPatriarch drawingPatriarch = spreadsheet.createDrawingPatriarch();

            int picIndex = wb.addPicture(baos.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG);

            int c = cell.getColumnIndex();
            int r = cell.getRowIndex();

            HSSFClientAnchor anchor = creationHelper.createClientAnchor();
//            anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);

//            anchor.setDx1(175);
//            anchor.setDx2(75);
//            anchor.setDy1(50);
//            anchor.setDy2(50);
            anchor.setCol1(c+1);
            anchor.setCol2(c + 3);
            anchor.setRow1(r);
            anchor.setRow2(r + rows);

            float heightInPoints = anchor.getAnchorHeightInPoints(spreadsheet);
            int columnWidth = spreadsheet.getColumnWidth(cell.getColumnIndex());
            int columnWidth1 = spreadsheet.getColumnWidth(cell.getColumnIndex() + 1);
            int columnWidth2 = spreadsheet.getColumnWidth(cell.getColumnIndex() + 2);


//            HSSFComment comment = drawingPatriarch.createCellComment(anchor);
            HSSFComment comment = drawingPatriarch.createCellComment(anchor);
            comment.setBackgroundImage(picIndex); // set picture as background image

            cell.setCellComment(comment);


        }
    }}
