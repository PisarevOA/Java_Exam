package mephi.java_exam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Data_Gen {
    

    public void Data_Gen(ArrayList<Sensor> sen_list, ArrayList<Integer> bad_sen_list, ArrayList<String> bad_sys_list){
    }
    
    
    public void Gen(ArrayList<Sensor> sen_list, ArrayList<Integer> bad_sen_list,
                    ArrayList<String> bad_sys_list, ArrayList<Date> DT,
                    ExcelWork file) throws IOException{
        int d = 0;//переменный диапазон допустимых значений для датчика
        int s = 0;//переменная случайной генерации - определяет будет ли нештатный случай
        double l = 0; //переменная выброса - причина неисправности (больше/меньше штатного значения)
        
        ArrayList<Integer> var_line = new ArrayList<>();
        Date date = new Date();
        DT.add(date);

        for(int i=0; i<sen_list.size(); i++){
            s = (int) (1 + Math.random()*100);
            //System.out.println(s);
            if (!bad_sys_list.contains(sen_list.get(i).get_Sys_name())){//проверка, входит ли система в список сломанных
                
                //расчеты для случая "не входит": штатная генерация и обычный шанс проблемы
                if(sen_list.get(i).get_lim1()!=sen_list.get(i).get_lim2()){//небинарные датчики
                                
                    if (s >= 5 && s < 95 && !bad_sen_list.contains(i)){ //штатные данные
                        d = sen_list.get(i).get_lim2() - sen_list.get(i).get_lim1();
                        sen_list.get(i).set_value((int) (sen_list.get(i).get_lim1() + Math.random() * (d)));
                    }
                    else{
                        if(s > 95 && !bad_sen_list.contains(i)){//генерируется отклонение
                            l = Math.random(); 
                            if (l<0.5){
                                sen_list.get(i).set_value((int)(2 + sen_list.get(i).get_lim1()- Math.random()*sen_list.get(i).get_lim1()));
                            }
                            else{
                                sen_list.get(i).set_value((int)(2 + sen_list.get(i).get_lim2()+ Math.random()*sen_list.get(i).get_lim2()));
                            }
                            bad_sen_list.add(i);
                            bad_sys_list.add(sen_list.get(i).get_Sys_name());
                        }else{//s<6: датчик отключен
                            if(!bad_sen_list.contains(i)){
                                sen_list.get(i).set_value(-1);
                                bad_sen_list.add(i);
                                //bad_sys_list.add(sen_list.get(i).get_Sys_name());
                            }
                        }
                    
                }
            }
            else{//бинарные датчики
                
                if ((s >= 5 && s < 95) && !bad_sen_list.contains(i)){ //штатные данные
                    sen_list.get(i).set_value(0);
                }
                else{//наличие проблемы
                    if (!bad_sen_list.contains(i) && s >= 95){
                        sen_list.get(i).set_value(1);
                        bad_sen_list.add(i);
                        bad_sys_list.add(sen_list.get(i).get_Sys_name());
                    }else{
                        if(bad_sen_list.contains(i)){
                            sen_list.get(i).set_value(1);
                        }
                    }    
                }
            }
            }else{//случай, когда датчик входит в сломанную систему, изменены вероятности
                //вынесено в отдельный метод для удобства
                Gen30(i, sen_list,bad_sen_list,bad_sys_list);
            }
            var_line.add(sen_list.get(i).get_value()); //формирование массива показаний
        }
        
        file.saveData(sen_list, DT);
        
    }
    
    public void Gen30(int i, ArrayList<Sensor> sen_list, ArrayList<Integer> bad_sen_list, ArrayList<String> bad_sys_list){
        int s = (int) (1 + Math.random()*100); 
        //System.out.println("А30: " + s);
        if(sen_list.get(i).get_lim1()!=sen_list.get(i).get_lim2()){//небинарные датчики
                                
            if (s >= 7 && s < 85 && !bad_sen_list.contains(i)){ //штатные данные
                int d = sen_list.get(i).get_lim2() - sen_list.get(i).get_lim1();
                sen_list.get(i).set_value((int) (sen_list.get(i).get_lim1() + Math.random() * (d)));
            }
                else{
                    if(s > 85 && !bad_sen_list.contains(i)){//генерируется отклонение
                        double l = Math.random(); 
                        if (l<0.5){
                            sen_list.get(i).set_value((int)(2 + sen_list.get(i).get_lim1()- Math.random()*sen_list.get(i).get_lim1()));
                        }
                        else{
                            sen_list.get(i).set_value((int)(2 + sen_list.get(i).get_lim2()+ Math.random()*sen_list.get(i).get_lim2()));
                        }
                        bad_sen_list.add(i);
                        bad_sys_list.add(sen_list.get(i).get_Sys_name());
                    }else{//s<10: датчик отключен
                        if(!bad_sen_list.contains(i)){
                            sen_list.get(i).set_value(-1);
                            bad_sen_list.add(i);
                            bad_sys_list.add(sen_list.get(i).get_Sys_name());
                        }
                    }
                    
                    //System.out.println(sen_list.get(i).get_Sys_name());
                }
            }
            else{//бинарные датчики
                
                if ((s >= 7 && s < 85) && !bad_sen_list.contains(i)){ //штатные данные
                    sen_list.get(i).set_value(0);
                }
                else{//наличие проблемы
                    if (!bad_sen_list.contains(i) && s >= 85){
                        sen_list.get(i).set_value(1);
                        bad_sen_list.add(i);
                        //bad_sys_list.add(sen_list.get(i).get_Sys_name());
                    }else{
                        if(bad_sen_list.contains(i)){
                            sen_list.get(i).set_value(1);
                        }
                    }    
                }
            }       
    }
    

    
}