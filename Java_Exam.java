package mephi.java_exam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Java_Exam {

    public static void main(String[] args) throws IOException {
        
        ArrayList<Integer> bad_sen_list = new ArrayList<>();
        ArrayList<String> bad_sys_list = new ArrayList<>();
        ArrayList<Date> DT = new ArrayList<>();
        
        //Установка стандартных инженерных систем
        ArrayList<Sensor> sen_list = new ArrayList<>();
        Sensor gas_press = new Sensor("Газопровод", "Датчик давления", 10, 5,15);
        sen_list.add(gas_press);
        Sensor gas_loss = new Sensor("Газопровод", "Датчик утечки газа", 0, 0,0);
        sen_list.add(gas_loss);
        
        Sensor boiler_temp = new Sensor("Котел", "Датчик температуры", 80, 50,120);
        sen_list.add(boiler_temp);
        Sensor boiler_gas = new Sensor("Котел", "Датчик утечки газа", 0, 0,0);
        sen_list.add(boiler_gas);
        
        Sensor pump_press = new Sensor("Насосная станция", "Датчик давления", 10, 5,20);
        sen_list.add(pump_press);
        Sensor pump__hum = new Sensor("Насосная станция", "Датчик влажности", 100, 80,150);
        sen_list.add(pump__hum);
        Sensor pumpr_vib = new Sensor("Насосная станция", "Датчик вибрации", 20, 10,30);
        sen_list.add(pumpr_vib);
        Sensor pump_enrg = new Sensor("Насосная станция", "Датчик энергопотребления", 3000, 2000,3500);
        sen_list.add(pump_enrg);
        
        Sensor cond_temp = new Sensor("Кондиционер", "Датчик температуры", 25, 16,40);
        sen_list.add(cond_temp);
        Sensor cond_vib = new Sensor("Кондиционер", "Датчик вибрации", 20, 10,30);
        sen_list.add(cond_vib);
        Sensor cond_noise = new Sensor("Кондиционер", "Датчик шума", 40, 20,80);
        sen_list.add(cond_noise);
        Sensor cond_enrg = new Sensor("Кондиционер", "Датчик энергопотребления", 2000, 1500,3000);
        sen_list.add(cond_enrg);
        
        Sensor trans_temp = new Sensor("Трансформатор", "Датчик температуры", 30,20,80);
        sen_list.add(trans_temp);
        Sensor trans_enrg = new Sensor("Трансформатор", "Датчик потребляемой энергии", 1000, 700,2000);
        sen_list.add(trans_enrg);
        Sensor trans_noise = new Sensor("Трансформатор", "Датчик шума", 30, 15,50);
        sen_list.add(trans_noise);
        Sensor trans_vib = new Sensor("Трансформатор", "Датчик вибрации", 20, 10,30);
        sen_list.add(trans_vib);
        
        Sensor heat_temp = new Sensor("Отопление", "Датчик температуры", 50, 30,60);
        sen_list.add(heat_temp);
        Sensor heat_press = new Sensor("Отопление", "Датчик давления", 10, 7,15);
        sen_list.add(heat_press);
        Sensor heat_hum = new Sensor("Отопление", "Датчик утечки жидкости", 0, 0,0);
        sen_list.add(heat_hum);
        
        ExcelWork file = new ExcelWork();
                
        Data_Gen S = new Data_Gen();
        S.Gen(sen_list, bad_sen_list, bad_sys_list,DT,file);
                
        GUI gui = new GUI("Система Умный дом", sen_list, bad_sen_list, bad_sys_list,DT,file);
        gui.setVisible(true);
    }
}