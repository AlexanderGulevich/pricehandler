package basisFx.appCore.utils;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class ImgResize {
            public static void mainr( )
            {
                String in = (System.getProperty("user.dir") + "/"+"src/res/res/img/gagarin.jpg").replace("\\","/");
                String out = System.getProperty("user.dir") + "/"+"src/res/res/img/resized.jpg";


                resize(in,out,480);

            }

            public static void   resize(String sourceFile, String ResizedFile, int width)
            {
                try
                {
                    File f = new File(sourceFile);
                    if (!f.exists())  Registry.windowFabric.infoWindow("File doesn’t exist") ;

                    BufferedImage bim=ImageIO.read(new FileInputStream(sourceFile));
                    Image resizedImg=bim.getScaledInstance(width,-1,Image.SCALE_FAST);
                    int scaled_height=resizedImg.getHeight(null);

                    BufferedImage rBimg=new BufferedImage(width,scaled_height,bim.getType());

                    Graphics2D g=rBimg.createGraphics();
                    g.drawImage(resizedImg,0,0,null);
                    g.dispose();

                    ImageIO.write(rBimg,ResizedFile.substring(ResizedFile.indexOf(".")+1),new FileOutputStream(ResizedFile));
                }
                catch (Exception e)
                {
                    Registry.windowFabric.infoWindow(e.getMessage()) ;
                }
                Registry.windowFabric.infoWindow("\"Picture Resized Successfully\"") ;
            }
        }
