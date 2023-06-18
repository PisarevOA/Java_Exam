package mephi.Java_Exam;

import java.util.ArrayList;

public class Java_Exam {

    public static void main(String[] args) {
        
        //Установка стандартных инженерных систем
        ArrayList<Sensor> sen_list = new ArrayList<>();
        Sensor gas_press = new Sensor("Газопровод", "Датчик давления", 20, 10,30);
        sen_list.add(gas_press);
        Sensor gas_loss = new Sensor("Газопровод", "Датчик утечки газа", 20, 10,30);
        sen_list.add(gas_loss);
        
        Sensor boiler_temp = new Sensor("Котел", "Датчик температуры", 20, 10,30);
        sen_list.add(boiler_temp);
        Sensor boiler_gas = new Sensor("Котел", "Датчик газа", 20, 10,30);
        sen_list.add(boiler_gas);
        
        Sensor pump_press = new Sensor("Насосная станция", "Датчик давления", 20, 10,30);
        sen_list.add(pump_press);
        Sensor pump__hum = new Sensor("Насосная станция", "Датчик влажности", 20, 10,30);
        sen_list.add(pump__hum);
        Sensor pumpr_vib = new Sensor("Насосная станция", "Датчик вибрации", 20, 10,30);
        sen_list.add(pumpr_vib);
        Sensor pump_enrg = new Sensor("Насосная станция", "Датчик энергопотребления", 20, 10,30);
        sen_list.add(pump_enrg);
        
        Sensor cond_temp = new Sensor("Кондиционер", "Датчик температуры", 20, 10,30);
        sen_list.add(cond_temp);
        Sensor cond_vib = new Sensor("Кондиционер", "Датчик вибрации", 20, 10,30);
        sen_list.add(cond_vib);
        Sensor cond_noise = new Sensor("Кондиционер", "Датчик шума", 20, 10,30);
        sen_list.add(cond_noise);
        Sensor cond_enrg = new Sensor("Кондиционер", "Датчик энергопотребления", 20, 10,30);
        sen_list.add(cond_enrg);
        
        Sensor trans_temp = new Sensor("Трансформатор", "Датчик температуры", 20, 10,30);
        sen_list.add(trans_temp);
        Sensor trans_enrg = new Sensor("Трансформатор", "Датчик потребляемой энергии", 20, 10,30);
        sen_list.add(trans_enrg);
        Sensor trans_noise = new Sensor("Трансформатор", "Датчик шума", 20, 10,30);
        sen_list.add(trans_noise);
        Sensor trans_vib = new Sensor("Трансформатор", "Датчик вибрации", 20, 10,30);
        sen_list.add(trans_vib);
        
        Sensor heat_temp = new Sensor("Отопление", "Датчик температуры", 20, 10,30);
        sen_list.add(heat_temp);
        Sensor heat_press = new Sensor("Отопление", "Датчик давления", 20, 10,30);
        sen_list.add(heat_press);
        Sensor heat_hum = new Sensor("Отопление", "Датчик влажности", 20, 10,30);
        sen_list.add(heat_hum);
        
        GUI gui = new GUI("Система Умный дом", sen_list);
        gui.setVisible(true);
    }
}
