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

package eu.himeros.parser.euporiacartoline;

import eu.himeros.parser.graphics.Visualizer;
import eu.himeros.parser.EuporiaCartolineLexer;
import eu.himeros.parser.EuporiaCartolineParser;
import eu.himeros.parser.io.DomOutputter;
import eu.himeros.parser.io.FileInputter;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.dom4j.Node;

/**
 *
 * @author federico
 */
public class Main {
    
    public Node parse(String text) throws Exception {
        EuporiaCartolineLexer lexer = new EuporiaCartolineLexer(CharStreams.fromString(text));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EuporiaCartolineParser parser = new EuporiaCartolineParser(tokens);
        EuporiaCartolineVisitor sdmv = new EuporiaCartolineVisitor();
        ParseTree tree = parser.doc();
        Node result = sdmv.visit(tree);
        DomOutputter.println(result, System.out);
        DomOutputter.println(tree, parser, System.out);
        Visualizer.visualize("Cartoline", parser, tree);
        return result;
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.parse(FileInputter.fileToString(args[0]));
    }
}
