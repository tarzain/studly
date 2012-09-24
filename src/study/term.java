/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package study;

/**
 *
 * @author Yahya
 */
public class term {
    public String front=new String();
    public String back=new String();
    public double proficiency;
    public int wrongs;
    public int corrects;
    public int count;
    public static int frontInt=0;
    public static int backInt=1;
    public static int bothInt=2;
    public term(String frontInp, String backInp){
        front=frontInp;
        back=backInp;
        proficiency=0;
        wrongs=0;
        corrects=0;
        count=0;
    }
    public void wrong(){
        wrongs++;
        count++;
    }
    public void correct(){
        corrects++;
        count++;
    }
    public void setFront(String inpFront){
        front=inpFront+"";

    }
    public void setBack(String inpBack){
        back=inpBack+"";
    }

    public String toString(int side){
        switch(side){
            case 0:{
                return front;
            }
            case 1:{
                return back;
            }
            case 2:{
                return front+""+back;
            }
            default:{
                return front+", "+back;
            }
        }
    }
    public String toString(){
        return front+", "+back;
    }
    public double getProficiency(){
        if((wrongs==0)&&(corrects!=0)){
            proficiency=1;
        }
        else if(corrects==0){
            proficiency=0;
        }
        else{
            proficiency=corrects/wrongs;
        }
        return proficiency;
    }
    public static double getProf(int wrong, int correct,int timesCurrentGroupStudied){

        if(timesCurrentGroupStudied==0){
            return 0;
        }
        else{
            if((wrong==0)&&(correct!=0)){
                return 1;
            }
            else if(correct==0){
                return 0;
            }
            else{
                return (correct/wrong);
            }
        }

    }
    public int getCount(){
        return count;
    }

}
