/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package study;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Yahya
 */
public class Main {
    static Scanner reader=new Scanner(System.in);
    static ArrayList<term> terms=new ArrayList<term>();
    static String listFileName=new String();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        String inputTemp=new String();
        System.out.println("Study or create?");
        inputTemp=reader.nextLine();
        if(inputTemp.charAt(0)==('s')){
            study();
        }
        else if(inputTemp.charAt(0)==('c')){
            create();
        }
        else{
            imprt();
        }
    }
    public static void study(){
        int currentGroupStudied=0;
        int groupsStudied=0;
        int cardsStudiedinGroup=0;
        int b=0;
        int overallWrongs=0;
        int overallCorrects=0;
        double overallProficiency=0;
        String inputTemp=new String();
        char inputChar;
        boolean done;
        do{
            
            cardsStudiedinGroup=0;
            b=cardsStudiedinGroup+(groupsStudied*5);
            overallWrongs=0;
            overallCorrects=0;
            overallProficiency=0;
            inputTemp=new String();
            inputChar='c';
            do{
                if((b>5)&&(groupsStudied!=currentGroupStudied)){
                    b=0;
                    currentGroupStudied++;
                }
                done=false;
                System.out.println(terms.get(b).toString(term.frontInt));
                System.out.println("Ready? If so type yes.");
                inputTemp=reader.nextLine();
                System.out.println(terms.get(b).toString(term.backInt));
                System.out.println("If you got it correct, type f. If you didn't, type j.");
                inputTemp=reader.nextLine();
                inputChar=inputTemp.charAt(0);
                switch(inputChar){
                    case 'f': case 'F':{
                        terms.get(b).correct();
                        overallCorrects++;
                        break;
                    }
                    case 'j': case 'J':{
                        terms.get(b).wrong();
                        overallWrongs++;
                        break;
                        
                    }
                    case 'd': case 'D':{
                        done=true;
                        groupsStudied=terms.size()+1;
                        break;
                    }
                    default:{
                        terms.get(b).wrong();
                        overallWrongs++;
                        break;
                    }
                }
                
                b++;
                overallProficiency=term.getProf(overallWrongs, overallCorrects, currentGroupStudied);

                
                System.out.println("Prof= "+overallProficiency);
            }
            while((overallProficiency<1)||(done==true));
            groupsStudied++;
        }
        while((groupsStudied*5)<terms.size());
        
        fileIO.save(overallWrongs+","+overallCorrects+","+b, listFileName);
        
    }
    public static void create() {
        // TODO code application logic here
        String inputTempFront=new String();
        String inputTempBack=new String();
        int i=0;
        System.out.println("Type the term, front and back, in the following format:");
        System.out.println("'front'<ENTER/RETURN>");
        System.out.println("'back'<ENTER/RETURN>");
        System.out.println("You may begin now. When finished, type *DONE* for both front and back and hit enter/return");
        do{
        System.out.println("Front:");
        inputTempFront=reader.nextLine();
        System.out.println("Back:");
        inputTempBack=reader.nextLine();
        terms.add(new term(inputTempFront,inputTempBack));
        i++;
        }
        while((!inputTempFront.equals("*DONE*"))&&(!inputTempBack.equals("*DONE*")));
        String outputSave=new String();
        for(int card=0;card<terms.size();card++){
            outputSave=outputSave+""+terms.get(card).toString()+";";
        }
        listFileName=terms.get(0).toString(term.frontInt)+""+Math.random();
        fileIO.save(outputSave, listFileName);
        study();
    }
    public static void imprt(){
        System.out.println("Input file name:");
        String fileName=reader.nextLine();
        String inputText=new String();
        inputText=fileIO.read(fileName);
        String inputTempFront=new String();
        String inputTempBack=new String();
        int lastcomma=0;
        int lastsemi=0;
        int card=0;
        for(int i=0;i<inputText.length();i++){
            if(inputText.charAt(i)==(',')){
                inputTempFront=inputText.substring(lastsemi,i);
                lastcomma=i;
            }
            if(inputText.charAt(i)==(';')){
                inputTempBack=inputText.substring(lastcomma,i);
                lastsemi=i;
                terms.add(new term(inputTempFront,inputTempBack));
                card++;
            }
        }
        study();
    }

}
