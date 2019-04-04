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

import eu.himeros.parser.dom.DomStaticVisitor;
import eu.himeros.parser.EuporiaCartolineBaseVisitor;
import eu.himeros.parser.EuporiaCartolineParser;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.dom4j.Branch;
import org.dom4j.Node;
import org.dom4j.dom.DOMText;

/**
 *
 * @author federico
 */
public class EuporiaCartolineVisitor extends EuporiaCartolineBaseVisitor<Node> implements DomStaticVisitor {

    @Override
    public Node visitText(EuporiaCartolineParser.TextContext ctx) {
        StringBuilder sb=new StringBuilder();
        ((Branch) visitChildren(ctx)).content().forEach((node) -> {
            sb.append(node.getText()).append(" ");
        });
        return new DOMText(sb.toString().trim());
    }

    @Override
    public Node visitTerminal(TerminalNode node) {
        if (node.getSymbol().getType() == EuporiaCartolineParser.SPAN) {
            return new DOMText(node.getText());
        } else {
            return new DOMText("");
        }
    }

    @Override
    public Node visitChildren(RuleNode node) {
        return DomStaticVisitor.visitChildren(node, this);
    }

    @Override
    public Node aggregateResult(Node aggregate, Node nextResult) {
        return DomStaticVisitor.aggregateResult(aggregate, nextResult);
    }

    @Override
    protected Node defaultResult() {
        return DomStaticVisitor.defaultResult();
    }
}
