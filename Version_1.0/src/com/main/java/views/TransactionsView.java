// src/views/TransactionsView.java
package com.main.java.views;

import javax.swing.*;
import java.awt.BorderLayout; // 添加这行导入语句

public class TransactionsView extends BaseView {
    @Override
    public String getViewName() {
        return "Transactions";
    }

    @Override
    protected void initUI() {
        setLayout(new BorderLayout()); // 使用 BorderLayout 布局
        add(new JLabel("Transactions Management", SwingConstants.CENTER));
    }
}