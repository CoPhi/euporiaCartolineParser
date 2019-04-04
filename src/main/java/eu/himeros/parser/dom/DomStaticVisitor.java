/*
 * Copyright (C) 2019 CoPhiLab
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

package eu.himeros.parser.dom;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.dom4j.Branch;
import org.dom4j.Node;
import org.dom4j.dom.DOMElement;
import org.dom4j.dom.DOMText;

/**
 *
 * @author federico
 */
public interface DomStaticVisitor {

    public static String getNodeName(ParseTree tree) {
        String name = tree.getClass().getName();
        name = name.substring(name.lastIndexOf('.') + 1);
        name = name.lastIndexOf('$') == -1 ? name : name.substring(name.lastIndexOf('$') + 1);
        name = name.replaceAll("Context$", "");
        try {
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
        } catch (Exception ex) {
            name = name.substring(0, 1);
        }
        return name;
    }

    public static Node visitTerminal(TerminalNode node) {
        String text = node.getText().trim();
        return new DOMText(text);
    }

    public static Node visitChildren(RuleNode node, ParseTreeVisitor<Node> visitor) {
        Node result = new DOMElement(DomStaticVisitor.getNodeName(node));
        for (int i = 0; i < node.getChildCount(); i++) {
            if (!shouldVisitNextChild(node, result)) {
                break;
            }
            ParseTree c = node.getChild(i);
            Node childResult = c.accept(visitor);
            result = aggregateResult(result, childResult);
        }
        return result;
    }

    public static boolean shouldVisitNextChild(RuleNode node, Node currentResult) {
        return true;
    }

    public static Node aggregateResult(Node aggregate, Node nextResult) {
        if (aggregate == defaultResult() || !(aggregate instanceof Branch)) {
            return nextResult;
        }
        if (nextResult == defaultResult()) {
            return aggregate;
        }
        ((Branch) aggregate).add(nextResult);
        return aggregate;
    }

    public static Node defaultResult() {
        return null;
    }
}
