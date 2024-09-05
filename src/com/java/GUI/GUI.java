package com.java.GUI;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;
import util.JdbcUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yjl~
 * @version 1.0
 */
public class GUI extends JFrame{
    private static final Integer WIDTH=600;
    private static final Integer HEIGHT=400;

    public GUI()   {

        setTitle("登录界面");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon imageIcon = new ImageIcon("D:\\Develop\\Java\\Java代码\\IDEA_Java_Project\\GUI\\src\\com\\java\\GUI\\bg.png");
        JLabel jblBg = new JLabel(imageIcon);//可插入背景图片
        jblBg.setBounds(0,0,WIDTH,HEIGHT);
        jblBg.setLayout(null);
        this.add(jblBg);

        //账号
        JLabel uid = new JLabel("账号");
        uid.setBounds(170,120,50,30);
        uid.setFont(new Font("PingFang",Font.BOLD,17));
        uid.setForeground(Color.WHITE);
        jblBg.add(uid);
        //账号输入框
        JTextField uidInput = new JTextField();
        uidInput.setBounds(240, 120, 160, 30);
        jblBg.add(uidInput);

        //密码
        JLabel upwd=new JLabel("密码");
        upwd.setBounds(170, 180, 50, 30);
        upwd.setFont(new Font("PingFang",Font.BOLD,17));
        upwd.setForeground(Color.WHITE);
        jblBg.add(upwd);
        //密码输入框
        JPasswordField upwdInput = new JPasswordField();
        upwdInput.setBounds(240, 180, 160, 30);
        jblBg.add(upwdInput);

        //登录按钮
        JButton btnLogin=new JButton("登录");
        btnLogin.setBounds(260, 250, 100, 30);
        btnLogin.setBackground(Color.BLACK);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("PingFang",Font.BOLD,17));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getInputUid=uidInput.getText();
                String getInputUpwd=upwdInput.getText();
                Connection conn=null;
                PreparedStatement pstmt=null;
                ResultSet rs=null;
                try {
                    conn= JdbcUtils.getConnection();
                    String sql="SELECT * FROM userinfo WHERE name =? AND password =?";
                    pstmt=conn.prepareStatement(sql);
                    pstmt.setString(1,getInputUid);
                    pstmt.setString(2,getInputUpwd);
                    rs= pstmt.executeQuery();
                    //创建弹窗
                    JDialog dialog = new JDialog();
                    dialog.setLocationRelativeTo(null);
                    dialog.setSize(200,200);
                    if(rs.next()){
                        dialog.add(new JLabel("登录成功！"));
                    }else{
                        dialog.add(new JLabel("登录失败！"));
                    }
                    dialog.setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    JdbcUtils.release(conn,pstmt,rs);
                }
            }
        });
        jblBg.add(btnLogin);

        //创建和定义注册按钮
        JButton jButton = new JButton("注册");
        jButton.setBounds(260, 290, 100, 30);//设置注册按钮的位置
        jButton.setBackground(Color.BLACK);
        jButton.setForeground(Color.WHITE);
        jButton.setFont(new Font("PingFang",Font.BOLD,17));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RgsterFrame();
            }
        });
        jblBg.add(jButton);//将按钮进行添加

        setVisible(true);
    }
    //主函数只需运行构造函数即可即可
    public static void main(String[] args) {
        new GUI();//直接创建对象进行调用
    }
}

//创建一个RgsterFrame类
class RgsterFrame extends JFrame {

    private static final Integer WIDTH=600;
    private static final Integer HEIGHT=400;

    public RgsterFrame() {

        setTitle("注册界面");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon imageIcon = new ImageIcon("D:\\Develop\\Java\\Java代码\\IDEA_Java_Project\\GUI\\src\\com\\java\\GUI\\bg.png");
        JLabel jblBg = new JLabel(imageIcon);//可插入背景图片
        jblBg.setBounds(0,0,WIDTH,HEIGHT);
        jblBg.setLayout(null);
        this.add(jblBg);

        //账号
        JLabel uid = new JLabel("账号");
        uid.setBounds(170,120,50,30);
        uid.setBounds(170,120,50,30);
        uid.setFont(new Font("PingFang",Font.BOLD,17));
        uid.setForeground(Color.WHITE);
        jblBg.add(uid);
        //账号输入框
        JTextField uidInput = new JTextField();
        uidInput.setBounds(240, 120, 160, 30);
        jblBg.add(uidInput);

        //密码
        JLabel upwd=new JLabel("密码");
        upwd.setBounds(170, 180, 50, 30);
        upwd.setBounds(170, 180, 50, 30);
        upwd.setFont(new Font("PingFang",Font.BOLD,17));
        upwd.setForeground(Color.WHITE);
        jblBg.add(upwd);
        //密码输入框
        JPasswordField upwdInput = new JPasswordField();
        upwdInput.setBounds(240, 180, 160, 30);
        jblBg.add(upwdInput);


        //创建提交按钮
        JButton jButton = new JButton("提交");
        jButton.setBounds(260, 220, 100, 30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //获取输入的账号和密码
                String getInputUid = uidInput.getText();
                String getInputUpwd=upwdInput.getText();

                JDialog jDialog = new JDialog();
                jDialog.setLocationRelativeTo(null);
                jDialog.setSize(200,200);
                String tip;
                if (checkRgster(getInputUid, getInputUpwd)) {
                    tip = "注册成功";
                } else {
                    tip = "注册失败！用户名重复！";
                }
                jDialog.add(new JLabel(tip));
                jDialog.setVisible(true);
            }
        });
        jblBg.add(jButton);
        setVisible(true);
    }
    
    //创建注册验证函数
    public boolean checkRgster(String getInputUid, String getInputUpwd) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try {
            conn = JdbcUtils.getConnection();
            String sql="INSERT INTO userinfo(name, password) VALUES (?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,getInputUid);
            pstmt.setString(2,getInputUpwd);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("用户名重复了！！！");
        }finally {
            JdbcUtils.release(conn,pstmt,rs);
        }
        System.out.println("这句被执行了");
        return false;
    }
}


