/*
 * Copyright (C) 2019 CoPhiLab
 * Repository: <https://github.com/CoPhi>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.himeros.parser.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author federico
 */
public class Visualizer {

    public static void visualize(String title, Parser parser, ParseTree tree) {
        try {
            //show AST in GUI
            JFrame frame = new JFrame(title);
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
            viewer.setScale(2);//scale a little
            panel.add(viewer);
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setBounds(5, 5, 1195, 595);
            JPanel contentPane = new JPanel(null);
            contentPane.setPreferredSize(new Dimension(1200, 600));
            contentPane.add(scrollPane);
            frame.setContentPane(contentPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 630);
            frame.setVisible(true);
        } catch (HeadlessException ex) {
            ex.printStackTrace(System.err);
        }

    }

}
