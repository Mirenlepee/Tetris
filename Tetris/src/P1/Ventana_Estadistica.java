package P1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class Ventana_Estadistica{
private JFrame ventana;
private JTable tablaDatos;
private TableModel modeloDatos;

public Ventana_Estadistica() {
ventana = new JFrame("Estadística__");
ventana.setSize(600, 250);
ventana.setTitle("Estadística");
ventana.setLayout(new BorderLayout());

JLabel signInlbl = new JLabel("Statistics");
signInlbl.setFont(new Font("Cambria", Font.BOLD, 24));

JPanel pnlLbl = new JPanel();
pnlLbl.add(signInlbl, BorderLayout.CENTER);
ventana.add(pnlLbl, BorderLayout.NORTH);

JPanel pnlBoton = new JPanel();

JButton btnReturn = new JButton("Return");
pnlBoton.add(btnReturn);
btnReturn.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
ventana.dispose();
}
       
});
tablaDatos = new JTable();
ventana.add(new JScrollPane(tablaDatos), BorderLayout.CENTER);
setDatos();
   
ventana.add(pnlBoton, BorderLayout.SOUTH);
ventana.setVisible(true);
}

public void setDatos() {
modeloDatos = new MiTableModel();
tablaDatos.setModel(modeloDatos);
Object[][] datos = {{"Time played", 0}, {"Daily playtime", 0}, {"Rounds played", 0},
           {"Max points", 0}, {"Min points", 0}, {"Total points", 0}, {"Daily average points", 0}};

   for (Object[] d : datos) {
       ((MiTableModel) modeloDatos).addRow(d);
   }
}

private class MiTableModel implements TableModel{

private final Class<?>[] CLASES_COLS = {String.class, Integer.class};
private ArrayList<Object[]> filas = new ArrayList<>();
@Override
public Class<?> getColumnClass(int columnIndex) {
// TODO Auto-generated method stub
return CLASES_COLS[columnIndex];
}

@Override
public int getColumnCount() {
// TODO Auto-generated method stub
return 2;
}

@Override
public int getRowCount() {
// TODO Auto-generated method stub
return filas.size();
}

private String[] cabeceras = {"Statistics", "Values"};

@Override
public String getColumnName(int columnIndex) {
// TODO Auto-generated method stub
return cabeceras[columnIndex];
}

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
// TODO Auto-generated method stub
return filas.get(rowIndex)[columnIndex];

}

public void addRow(Object[] rowData) {
            filas.add(rowData);
            TableModelEvent event = new TableModelEvent(this, filas.size() - 1, filas.size() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
            fireTableChanged(event);
        }

@Override
public boolean isCellEditable(int rowIndex, int columnIndex) {
// TODO Auto-generated method stub
return false;
}

@Override
public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
// TODO Auto-generated method stub
switch(columnIndex) {
case 1:
// aquí se pondrá el dato en la segunda columna y en la fila correspondiente.
break;

}
}

ArrayList<TableModelListener> listaEsc = new ArrayList<>();

@Override
public void addTableModelListener(TableModelListener l) {
// TODO Auto-generated method stub
listaEsc.add(l);

}

@Override
public void removeTableModelListener(TableModelListener l) {
// TODO Auto-generated method stub
listaEsc.remove(l);

}

//DefaultTableModel lo hace así
public void fireTableChanged(TableModelEvent e) {
for(TableModelListener l: listaEsc) {
l.tableChanged(e);
}
}
}

}