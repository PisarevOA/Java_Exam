package mephi.java_exam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class GUI extends JFrame{
    private final JTree tree;
    private final DefaultMutableTreeNode rootTree;
    
    private final JButton DataButton = new JButton("Получить данные");
    private final JButton New_Sensor = new JButton("Добавить датчик");
    
    private final JScrollPane scroll;
    private final JPanel contents = new JPanel();

    public GUI(String name, ArrayList<Sensor> sen_list, ArrayList<Integer> bad_sen_list,
                            ArrayList<String> bad_sys_list,ArrayList<ArrayList<Integer> > Data_matrix,
                            ExcelWork file) {
        super(name);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setBounds(200,200,300,500);
        
        this.rootTree = new DefaultMutableTreeNode("Инженерные системы");
        this.tree = new JTree(rootTree);
        this.scroll = new JScrollPane(tree);
     
        InitSysNodes(sen_list); //вызов метода формирования дерева стандартных ИС
        
        setContentPane(contents);
        contents.add(scroll);
        contents.add(DataButton);
        contents.add(New_Sensor);
        
        //DataButton.setName("DataButton");
        //New_Sensor.setName("1");
        DataButton.addActionListener((java.awt.event.ActionEvent evt) -> {
           
            
            Data_Gen S = new Data_Gen();
            try {
                S.Gen(sen_list, bad_sen_list, bad_sys_list,Data_matrix,file);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            //rootTree.removeAllChildren();
            System.out.println(sen_list.get(1).get_value());
            InitSysNodes(sen_list);
            
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
        //System.out.println("LA");
        for (int i=0; i< sen_list.size(); i++) {
            if (i==0){
                varNode = new DefaultMutableTreeNode(sen_list.get(i).get_Sys_name());
                rootTree.add(varNode);
                
                if (sen_list.get(i).get_value() > sen_list.get(i).get_lim2()){
                    var1 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value() + "!");
                    varNode.add(var1);
                    JOptionPane.showMessageDialog(null,sen_list.get(i).get_Sens_name() + " Показания больше верхней границы!","Отклонение!",JOptionPane.PLAIN_MESSAGE);
                }else{
                    if (sen_list.get(i).get_value() < sen_list.get(i).get_lim1()){
                        var1 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value() + "!");
                        varNode.add(var1);
                        if (sen_list.get(i).get_value() != -1){
                            JOptionPane.showMessageDialog(null,sen_list.get(i).get_Sens_name() + " Показания меньше нижней границы!","Отклонение!",JOptionPane.PLAIN_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null,sen_list.get(i).get_Sens_name() + " Датчик отключен!","Отклонение!",JOptionPane.PLAIN_MESSAGE);
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
                        JOptionPane.showMessageDialog(null,sen_list.get(i).get_Sens_name() + " Показания больше верхней границы!","Отклонение!",JOptionPane.PLAIN_MESSAGE);
                    }else{
                        if (sen_list.get(i).get_value() < sen_list.get(i).get_lim1()){
                            var1 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value()+ "!");
                            varNode.add(var1);
                            if (sen_list.get(i).get_value() != -1){
                                JOptionPane.showMessageDialog(null,sen_list.get(i).get_Sens_name() + " Показания меньше нижней границы!","Отклонение!",JOptionPane.PLAIN_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(null,sen_list.get(i).get_Sens_name() + " Датчик отключен!","Отклонение!",JOptionPane.PLAIN_MESSAGE);
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
                        JOptionPane.showMessageDialog(null,sen_list.get(i).get_Sens_name() + " Показания больше верхней границы!","Отклонение!",JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        if (sen_list.get(i).get_value() < sen_list.get(i).get_lim1() && sen_list.get(i).get_value() != 1){
                            var2 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value() + "!");
                            varNode.add(var2);
                            if (sen_list.get(i).get_value() != -1){
                                JOptionPane.showMessageDialog(null,sen_list.get(i).get_Sens_name() + " Показания меньше нижней границы!","Отклонение!",JOptionPane.PLAIN_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(null,sen_list.get(i).get_Sens_name() + " Датчик отключен!","Отклонение!",JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                        else{
                            if (sen_list.get(i).get_value() == 1){
                                var2 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value() + "!");
                                varNode.add(var2);
                                JOptionPane.showMessageDialog(null,sen_list.get(i).get_Sens_name() + " Утечка!","Отклонение!",JOptionPane.PLAIN_MESSAGE);
                            }else{
                                var2 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name() + " " + sen_list.get(i).get_value());
                                varNode.add(var2);
                            }
                        }
                    }
                        
                }        
            }    
        }

    }
}
