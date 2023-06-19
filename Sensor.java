package mephi.Java_Exam;

public final class Sensor {
    
    public String Sys_name;
    public String Sens_name;
    public int value;
    public int lim1;
    public int lim2;
        
    public Sensor(String Sys_name, String Sens_name, int value, int lim1, int lim2){
        set_Sys_name(Sys_name);
        set_Sens_name(Sens_name);
        set_value(value);
        set_lim1(lim1);
        set_lim2(lim2);
    }
    
    public void set_Sys_name(String SN){
       this.Sys_name = SN;
    }
    
    public String get_Sys_name(){
        return this.Sys_name;
    }
    
    public void set_Sens_name(String SN){
       this.Sens_name = SN;
    }
    public String get_Sens_name(){
        return this.Sens_name;
    }
    
    public void set_value(int v){
       this.value = v;
    }
    public int get_value(){
        return this.value;
    }
    
    public void set_lim1(int v){
       this.lim1 = v;
    }
    public int get_lim1(){
        return this.lim1;
    }
    
    public void set_lim2(int v){
       this.lim2 = v;
    }
    public int get_lim2(){
        return this.lim2;
    }
}
