package mephi.java_exam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
//import javax.swing.event.TreeSelectionEvent;
//import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

public class GUI extends JFrame{
    private final JTree tree;
    private final DefaultMutableTreeNode rootTree;
    
    private final JButton DataButton = new JButton("Получить данные");
    private final JButton New_Sensor = new JButton("Добавить датчик");
   // private final JButton Report = new JButton("Получить 5 значений");
    
    private final JScrollPane scroll;
    private final JPanel contents = new JPanel();
    //ArrayList<String> sys_list = new ArrayList<>();


    //элементы для диалогового окна добавления датчика
    Dialog New_sens_dialog = new Dialog(this);
    JTextField Sys_nm = new JTextField("", 1);
    JTextField Sens_nm = new JTextField("",1);
    JTextField vl = new JTextField("",1);
    JTextField l1 = new JTextField("",1);
    JTextField l2 = new JTextField("",1);
    JButton add = new JButton("Добавить");
    JButton cancel = new JButton("Отмена");

    private final JLabel labell = new JLabel("Название ИС");
    private final JLabel label2 = new JLabel("Название датчика");
    private final JLabel label3 = new JLabel("Нормальное значение");
    private final JLabel label4 = new JLabel("Нижняя граница");
    private final JLabel label5 = new JLabel("Верхняя граница");
    private final JLabel label6 = new JLabel("Подтвердите данные:");
    private final JLabel labelF = new JLabel("");
    
    public GUI(String name, ArrayList<Sensor> sen_list, ArrayList<Integer> bad_sen_list,
                            ArrayList<String> bad_sys_list,ArrayList<Date> DT,
                            ExcelWork file) {
        super(name);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setBounds(200,200,300,500);
        
        this.rootTree = new DefaultMutableTreeNode("Инженерные системы");
        this.tree = new JTree(rootTree);        
        tree.expandPath(new TreePath(rootTree.getPath()));
        
        
        this.scroll = new JScrollPane(tree);
        scroll.setSize(8000, 200);
        
        InitSysNodes(sen_list); //вызов метода формирования дерева стандартных ИС
        
        setContentPane(contents);
        contents.add(scroll);
        contents.add(DataButton);
        contents.add(New_Sensor);
        
        //Функционал кнопки "Получит данные"
        DataButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            
            Data_Gen S = new Data_Gen();
            try {
                S.Gen(sen_list, bad_sen_list, bad_sys_list,DT,file);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            rootTree.removeAllChildren();
            InitSysNodes(sen_list);
            scroll.updateUI();
            tree.updateUI();
            tree.expandPath(new TreePath(rootTree.getPath()));
            
            tree.setCellRenderer(new DefaultTreeCellRenderer(){
                @Override
                public Component getTreeCellRendererComponent(JTree tree,Object value,
                                boolean sel,boolean expanded,boolean leaf,int row,boolean hasFocus){
                    JLabel node = (JLabel)super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
                    node.setForeground(node.getText().contains("!")? Color.RED:Color.BLACK);
                    return node;
                    }
            });     
        });
        
        //Функционал кнопки "Добавить датчик"
        New_sens_dialog.setBounds(300,300,500,150);
        New_sens_dialog.setName("добавление нового датчика");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,5,1,1));
            
        New_sens_dialog.add(panel);
        panel.add(labell);            
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(Sys_nm);
        panel.add(Sens_nm);
        panel.add(vl);
        panel.add(l1);
        panel.add(l2);
        panel.add(labelF);
        panel.add(label6);
        panel.add(add);
        panel.add(cancel);
            
        New_Sensor.addActionListener((var evt) -> {
            Sensor K = new Sensor(Sys_nm.getText(), Sens_nm.getText(),3, 2,5); 
            New_sens_dialog.setVisible(true);
            
            add.addActionListener((var e1) -> {
                
                try {
                    Sensor NS = new Sensor(Sys_nm.getText(), Sens_nm.getText(), 
                                    Integer.parseInt(vl.getText()), Integer.parseInt(l1.getText()),Integer.parseInt(l2.getText()));
                    New_sens_dialog.setVisible(false);
   
                    System.out.println(K.get_Sens_name());
                    if (!NS.get_Sens_name().equals(sen_list.get(sen_list.size()-1).get_Sens_name())){
                        
                        sen_list.add(NS);
                        rootTree.removeAllChildren();
                        InitSysNodes(sen_list);
                        //System.out.println(NS.get_Sens_name());             
                        scroll.updateUI();
                        tree.updateUI();
                        tree.expandPath(new TreePath(rootTree.getPath())); 
                    }      
                    
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,"Пожалуйста, введите натуральное число",
                                                "Некорректный ввод данных",JOptionPane.PLAIN_MESSAGE);
                }
            });
            
            cancel.addActionListener((var e1) -> {
                New_sens_dialog.setVisible(false);
            });
                   
    
        });
        
//        tree.addTreeSelectionListener(new TreeSelectionListener() {
//            @Override
//            public void valueChanged(TreeSelectionEvent e) {
//               System.out.println("Selection is " + e.getPath());
//               str = e.getPath().toString();
//               for(int i =0; i< sen_list.size(); i++){
//                   if(str.contains(sen_list.get(i).get_Sys_name())){
//                       System.out.println(sen_list.get(i).get_Sens_name());
//                   }
//               }
//    
//            }
//        });

        tree.setCellRenderer(new DefaultTreeCellRenderer(){
            @Override
            public Component getTreeCellRendererComponent(JTree tree,Object value,
                    boolean sel,boolean expanded,boolean leaf,int row,boolean hasFocus){
                JLabel node = (JLabel)super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
                node.setForeground(node.getText().contains("!")? Color.RED:Color.BLACK);
                return node;
            }
        });

    }

    private void InitSysNodes(ArrayList<Sensor> sen_list) {
        
        DefaultMutableTreeNode varNode = null;//создание переменной ветви, которая в цикле заменяется на названия систем
        DefaultMutableTreeNode var1 = null;
        DefaultMutableTreeNode var2 = null;
        String message = "";
        //System.out.println("LA");
        for (int i=0; i< sen_list.size(); i++) {
            if (i==0){
                varNode = new DefaultMutableTreeNode(sen_list.get(i).get_Sys_name());
                rootTree.add(varNode);
                tree.expandPath(new TreePath(varNode.getPath()));
                if (sen_list.get(i).get_value() > sen_list.get(i).get_lim2()){
                    var1 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value() + "!");
                    varNode.add(var1);
                    message = message + sen_list.get(i).get_Sens_name() + ": \nПоказания больше верхней границы!\n\n";
                    
                }else{
                    if (sen_list.get(i).get_value() < sen_list.get(i).get_lim1()){
                        var1 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value() + "!");
                        varNode.add(var1);
                        if (sen_list.get(i).get_value() != -1){
                            message = message + sen_list.get(i).get_Sens_name() + ": \nПоказания меньше нижней границы\n\n!";
                        }else{
                            message = message + sen_list.get(i).get_Sens_name() + ": \nОтключен!\n\n";
                        }
                    }else{//штатная ситуация
                    var1 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value());
                    varNode.add(var1);
                        
                    }
                }
            }
            else{
                if (!sen_list.get(i).Sys_name.equals(sen_list.get(i-1).get_Sys_name())) {
                    varNode = new DefaultMutableTreeNode(sen_list.get(i).get_Sys_name());
                    rootTree.add(varNode);
                    
                    if (sen_list.get(i).get_value() > sen_list.get(i).get_lim2() ){
                        var1 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value() + "!");
                        varNode.add(var1);
                        message = message + sen_list.get(i).get_Sens_name() + ": \n Показания больше верхней границы!\n\n";
                    }else{
                        if (sen_list.get(i).get_value() < sen_list.get(i).get_lim1()){
                            var1 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value()+ "!");
                            varNode.add(var1);
                            
                            if (sen_list.get(i).get_value() != -1){
                                message = message + sen_list.get(i).get_Sens_name() + ": \nПоказания меньше нижней границы!\n\n";
                            }else{
                                message = message + sen_list.get(i).get_Sens_name() + ": \nОтключен!\n\n";
                            }
                        }else{
                                var1 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value());
                                varNode.add(var1);
                                //System.out.println(sen_list.get(i).get_value());
                        }
                    }
                }
                else{
                    if (sen_list.get(i).get_value() > sen_list.get(i).get_lim2() && sen_list.get(i).get_value() > 1){
                        var2 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value() + "!");
                        varNode.add(var2);
                        message = message + sen_list.get(i).get_Sens_name() + ": \nПоказания больше верхней границы!\n\n";
                    }
                    else{
                        if (sen_list.get(i).get_value() < sen_list.get(i).get_lim1() && sen_list.get(i).get_value() != 1){
                            var2 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value() + "!");
                            varNode.add(var2);
                            
                            if (sen_list.get(i).get_value() != -1){
                                message = message + sen_list.get(i).get_Sens_name() + ": \nПоказания меньше нижней границы!\n\n";
                            }else{
                                message = message + sen_list.get(i).get_Sens_name() + ": \nОтключен!\n\n";
                            }
                        }
                        else{
                            if (sen_list.get(i).get_value() == 1){
                                var2 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value() + "!");
                                varNode.add(var2);
                                message = message + sen_list.get(i).get_Sens_name() + ": Утечка!\n\n";
                            }else{
                                var2 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value());
                                varNode.add(var2);
                            }
                        }
                    }
                        
                }        
            }
            //sys_list.add(sen_list.get(i).get_Sys_name());
        }
        if (!message.equals("")){
            JOptionPane.showMessageDialog(null,message,"Отклонение!",JOptionPane.PLAIN_MESSAGE);
        }

    }
}
