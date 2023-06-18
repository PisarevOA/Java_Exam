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
    }
    
    public void set_Sens_name(String SN){
       this.Sens_name = SN;
    }
    public String get_Sens_name(){
        return this.Sens_name;
    }
    
    
    public void set_Sys_name(String SN){
       this.Sys_name = SN;
    }
    
    public String get_Sys_name(){
        return this.Sys_name;
    }
}
