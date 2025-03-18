package com.main.java.UI;

import com.main.java.components.RoundedButton;
import com.main.java.components.RoundedPanel;
import com.main.java.constants.AppConstants;
import com.main.java.views.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FinanceTrackerUI extends JFrame {
    private String username;
    private JPanel mainContentPanel;
    private JButton currentNavButton;
    private JPanel sideBarPanel;

    public FinanceTrackerUI(String username) {
        this.username = username;
        setTitle("BuckBrainAI");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 使用带间距的 BorderLayout 布局，为元素提供边距
        setLayout(new BorderLayout(10, 10));

        // 顶部标题栏
        JPanel topBarPanel = createTopBarPanel();
        add(topBarPanel, BorderLayout.NORTH);

        // 左侧导航栏
        sideBarPanel = createSideBarPanel();
        add(sideBarPanel, BorderLayout.WEST);

        // 主内容区（使用 CardLayout 管理多个视图）
        mainContentPanel = new RoundedPanel(new CardLayout());
        // 添加各个视图面板到主内容区
        mainContentPanel.add(new DashboardView(), "Dashboard");
        mainContentPanel.add(new TransactionsView(), "Transactions");
        mainContentPanel.add(new InvestmentsView(), "Investments");
        mainContentPanel.add(new SettingsView(), "Settings");
        // 目前 Accounts 和 Credit Cards 页面尚未实现，先添加占位面板
        JPanel accountsPanel = new JPanel();
        accountsPanel.add(new JLabel("Accounts Page"));
        mainContentPanel.add(accountsPanel, "Accounts");
        JPanel creditCardsPanel = new JPanel();
        creditCardsPanel.add(new JLabel("Credit Cards Page"));
        mainContentPanel.add(creditCardsPanel, "Credit Cards");
        add(mainContentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createTopBarPanel() {
        // 使用 RoundedPanel 使标题栏带有圆角和背景色
        RoundedPanel topBar = new RoundedPanel(new BorderLayout());
        topBar.setBackground(AppConstants.BACKGROUND_COLOR);
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // 左侧显示 BuckBrainAI 标志
        JLabel titleLabel = new JLabel("BuckBrainAI");
        titleLabel.setFont(AppConstants.TITLE_FONT);
        titleLabel.setForeground(AppConstants.PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        topBar.add(titleLabel, BorderLayout.WEST);

        // 右侧：用户欢迎语 + 设置按钮 + 用户头像按钮
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        userPanel.setOpaque(false); // 背景透明，保持与 topBar 一致
        JLabel userNameLabel = new JLabel("Hello, " + username);
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userPanel.add(userNameLabel);
        // 设置按钮（齿轮图标）
        JButton settingsButton = createIconButton("⚙");
        userPanel.add(settingsButton);
        // 用户头像按钮（使用用户图标）
        JButton avatarButton = createIconButton("👤");
        userPanel.add(avatarButton);

        topBar.add(userPanel, BorderLayout.EAST);
        return topBar;
    }

    private JPanel createSideBarPanel() {
        // 使用 RoundedPanel 作为侧边导航栏面板
        RoundedPanel navPanel = new RoundedPanel(new GridLayout(7, 1, 0, 10));
        navPanel.setPreferredSize(new Dimension(220, 0));
        navPanel.setBackground(AppConstants.BACKGROUND_COLOR);
        navPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        // 导航选项
        String[] navItems = {"Dashboard", "Transactions", "Accounts", "Investments", "Credit Cards", "Settings"};
        for (String item : navItems) {
            JButton navButton = createNavButton(item);
            navPanel.add(navButton);
            // 默认选中 Dashboard
            if (item.equals("Dashboard")) {
                navButton.setBackground(AppConstants.PRIMARY_COLOR);
                navButton.setForeground(Color.WHITE);
                currentNavButton = navButton;
            }
        }
        return navPanel;
    }

    private JButton createNavButton(String text) {
        // 使用圆角按钮作为导航按钮
        RoundedButton button = new RoundedButton(text);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(AppConstants.BUTTON_FONT);
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        // 点击导航按钮时，切换相应视图，并更新按钮高亮状态
        button.addActionListener(e -> {
            // 切换 CardLayout 显示的视图
            CardLayout cl = (CardLayout) mainContentPanel.getLayout();
            cl.show(mainContentPanel, text);
            // 更新导航按钮高亮状态
            if (currentNavButton != null) {
                currentNavButton.setBackground(Color.WHITE);
                currentNavButton.setForeground(Color.BLACK);
            }
            button.setBackground(AppConstants.PRIMARY_COLOR);
            button.setForeground(Color.WHITE);
            currentNavButton = button;
        });
        return button;
    }

    private JButton createIconButton(String iconChar) {
        // 创建只含符号的圆角按钮（用于顶部图标按钮）
        RoundedButton button = new RoundedButton(iconChar);
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        // 根据不同符号绑定不同功能
        if (iconChar.equals("⚙")) {
            // 点击齿轮图标打开设置视图
            button.addActionListener(e -> {
                CardLayout cl = (CardLayout) mainContentPanel.getLayout();
                cl.show(mainContentPanel, "Settings");
                // 高亮侧边栏中的 Settings 按钮
                updateNavSelection(findNavButton("Settings"));
            });
        } else if (iconChar.equals("👤")) {
            // 点击用户头像图标打开 Accounts 视图
            button.addActionListener(e -> {
                CardLayout cl = (CardLayout) mainContentPanel.getLayout();
                cl.show(mainContentPanel, "Accounts");
                // 高亮侧边栏中的 Accounts 按钮
                updateNavSelection(findNavButton("Accounts"));
            });
        }
        return button;
    }

    private JButton findNavButton(String text) {
        // 在侧边导航面板中查找具有指定文本的按钮
        for (Component comp : sideBarPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                if (btn.getText().equals(text)) {
                    return btn;
                }
            }
        }
        return null;
    }

    private void updateNavSelection(JButton selectedButton) {
        if (selectedButton == null) return;
        // 重置当前选中按钮的样式
        if (currentNavButton != null) {
            currentNavButton.setBackground(Color.WHITE);
            currentNavButton.setForeground(Color.BLACK);
        }
        // 设置新的选中按钮样式
        selectedButton.setBackground(AppConstants.PRIMARY_COLOR);
        selectedButton.setForeground(Color.WHITE);
        currentNavButton = selectedButton;
    }
}
