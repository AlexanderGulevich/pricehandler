/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basisFx.appCore.utils;

import basisFx.appCore.settings.StylesPathes;
import javafx.scene.Scene;
import javafx.scene.text.Font;

import java.io.File;
import java.net.URI;

public class FontHandler {

        private static FontHandler instanse;
        private static Scene scene;
        private static StylesPathes stylesPathe;

    public static FontHandler getInstanse(){

        if(instanse==null) {
            instanse=new FontHandler();
        }else{
             return instanse;
        }
        return instanse;
    }


    public void loadFontToScene(){

        URI uri=null;
        File folder=null;
        File[] listOfFiles=null;

        String path = System.getProperty("user.dir") + "/"+"src/res/res/fonts";
        path = path.replace("\\","/");
        folder = new File(path);
        listOfFiles = folder.listFiles();
//
            boolean directory = folder.isDirectory();
            boolean absolute = folder.isAbsolute();
            boolean file = folder.isFile();
            boolean hidden = folder.isHidden();


        addtStylesheetsToSceen(listOfFiles);


    }

    private void addtStylesheetsToSceen( File[] listOfFiles) {
        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                String fileName = listOfFile.getName();
                File parentFolder = listOfFile.getParentFile();


                Font.loadFont(PathToFile.getPath("/src/res/res/fonts/"+ fileName),15d);


            }
        }
    }


}
