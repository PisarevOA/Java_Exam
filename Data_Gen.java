package mephi.Java_Exam;
import java.util.ArrayList;

public class Data_Gen {
    
    public void Data_Gen(ArrayList<Sensor> sen_list, int a){
        //фиксация времени сбора данных: set get методы и вызов
        Gen(sen_list);
        a = 1;
    }
    
    
    public void Gen(ArrayList<Sensor> sen_list){
        int d = 0;//переменный диапазон допустимых значений для датчика
        int s = 0;//переменная случайной генерации - определяет будет ли нештатный случай
        double l = 0; //переменная выброса - причина неисправности (больше/меньше штатного значения)
        
        for(int i=0; i<sen_list.size(); i++){
            s = (int) (1 + Math.random()*100);
            //System.out.println(s);
            
            if(sen_list.get(i).get_lim1()!=sen_list.get(i).get_lim2()){//небинарные датчики
                
                if (s < 95){ //штатные данные
                    d = sen_list.get(i).get_lim2() - sen_list.get(i).get_lim1();
                    sen_list.get(i).set_value((int) (sen_list.get(i).get_lim1() + Math.random() * (d)));
                }
                else{
                    l = Math.random(); 
                    if (l<0.5){
                        sen_list.get(i).set_value((int)(sen_list.get(i).get_lim1()- Math.random()*sen_list.get(i).get_lim1()));
                    }
                    else{
                        sen_list.get(i).set_value((int)(sen_list.get(i).get_lim2()+ Math.random()*sen_list.get(i).get_lim2()));
                    }
                }
            }
            else{//бинарные датчики
                if (s < 95){ //штатные данные
                    sen_list.get(i).set_value(0);
                }
                else{
                    sen_list.get(i).set_value(1);
                }
            }
        }
    }
}
