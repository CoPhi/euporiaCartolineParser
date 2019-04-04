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

package eu.himeros.parser.io;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author federico
 */
public class DomOutputter {
    public static void println(Node node, PrintStream printStream){
        StringWriter sw=new StringWriter();
        OutputFormat outputFormat=OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter=new XMLWriter(sw,outputFormat);
        try{
            xmlWriter.write(node);
        }catch(IOException ex){
            ex.printStackTrace(System.err);
        }
        printStream.println(sw.toString());
    }
    
    public static void println(ParseTree tree, Parser parser, PrintStream printStream){
        //Show AST in console
        printStream.println(tree.toStringTree(parser));
    }
    
}
