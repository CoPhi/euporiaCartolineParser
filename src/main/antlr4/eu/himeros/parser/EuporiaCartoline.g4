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

grammar EuporiaCartoline;

doc: figure+;
figure: head figDesc;
head: HEAD_ATTR text;
figDesc: FIG_DESC_ATTR text;
text: SPAN+;
HEAD_ATTR: 'titolo:';
FIG_DESC_ATTR: 'descrizione:';
SPAN: [\u0021-\uFFFF]+;
WS: (' '|'\n'|EOF)->skip;
