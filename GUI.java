package mephi.Java_Exam;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class GUI extends JFrame{
    private final JTree tree;
    private final DefaultMutableTreeNode rootTree;
    
    private final JButton DataButton = new JButton("Получить данные");
    private final JButton New_Sensor = new JButton("Добавить датчик");
    
    private final JScrollPane scroll;
    private final JPanel contents = new JPanel();

    public GUI(String name, ArrayList<Sensor> sen_list) {
        super(name);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setBounds(200,200,300,500);
        
        this.rootTree = new DefaultMutableTreeNode("Инженерные системы");
        this.tree = new JTree(rootTree);
        this.scroll = new JScrollPane(tree);
     
        initSysNodes(sen_list); //вызов метода формирования дерева стандартных ИС
        
        setContentPane(contents);
        contents.add(scroll);
        contents.add(DataButton);
        contents.add(New_Sensor);
  
    }

    private void initSysNodes(ArrayList<Sensor> sen_list) {
        
        DefaultMutableTreeNode varNode = null;//создание переменной ветви, которая в цикле заменяется на названия систем
        DefaultMutableTreeNode var1 = null;
        DefaultMutableTreeNode var2 = null;
        
        for (int i=0; i< sen_list.size(); i++) {
            if (i==0){
                varNode = new DefaultMutableTreeNode(sen_list.get(i).get_Sys_name());
                rootTree.add(varNode);
                var1 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name());
                varNode.add(var1);
            }
            else{
                if (!sen_list.get(i).Sys_name.equals(sen_list.get(i-1).get_Sys_name())) {
                    varNode = new DefaultMutableTreeNode(sen_list.get(i).get_Sys_name());
                    rootTree.add(varNode);
                    var1 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name());
                    varNode.add(var1);
                    //initBookNodes(varNode, books);
                }
                else{
                    var2 = new DefaultMutableTreeNode(sen_list.get(i).get_Sens_name());
                    varNode.add(var2);
                }        
            }    
        }
    }

}
