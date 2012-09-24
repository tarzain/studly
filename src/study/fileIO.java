/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package study;
import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Yahya
 */
public class fileIO {
public static void save(String input,String fileName){
		try{
		FileWriter outFile=new FileWriter(fileName, false);
		PrintWriter out=new PrintWriter(outFile);
		//print to file
		out.write(input);
		out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}
public static String read(String fileName){
        String textRead= new String();
        try{
            FileReader reader=new FileReader(fileName);
            BufferedReader readerB=new BufferedReader(reader);
            textRead=readerB.readLine();

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return textRead;
    }
}
