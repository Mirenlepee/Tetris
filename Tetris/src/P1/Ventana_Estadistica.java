package P1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;



public class Ventana_Estadistica extends AbstractTableModel{
	private JFrame ventana;
	private JTable tablaDatos;
	private AbstractTableModel modeloDatos;
	
	String[] cabeceras = {"Time played", "Daily playtime", "Rounds played", "Max points", "Min points", "Total points", "Daily average points"};
	Object[][] datos = {{"Dato 1"}, {"Dato 2"}, {"Dato 3"}, {"Dato 4"}, {"Dato 5"}, {"Dato 6"}, {"Dato 7"}};
    
    public Ventana_Estadistica() {
    	ventana = new JFrame("Estadística");
    	ventana.setSize(600, 800);
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
				// TODO Auto-generated method stub
				ventana.dispose();
			}
        	
        });
        
        ventana.add(pnlBoton, BorderLayout.SOUTH);
    	ventana.setVisible(true);
    }
    
    public void setDatos() {
//    	modeloDatos = new MiTableModel(cabeceras, datos);
//    	tablaDatos = new JTable(modeloDatos);
//    	JScrollPane scrollPane = new JScrollPane(tablaDatos);
//        ventana.add(scrollPane, BorderLayout.CENTER);
    }
    

    public void MiTableModel(String[] cabeceras, Object[][] datos) {
        this.cabeceras = cabeceras;
        this.datos = datos;
    }
    public int getRowCount() {
        return datos.length;
    }

    public int getColumnCount() {
        return cabeceras.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return datos[rowIndex][columnIndex];
    }
    
    public String getColumnName(int column) {
        return cabeceras[column];
    }
    
//    public void setValueAt(Object value, int row, int col) {
//        datos[row][col] = value;
//        fireTableCellUpdated(row, col);
//    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // Permitir la edición de todas las celdas
    }

    
//    private class MiTableModel implements TableModel{
//
//		private final Class<?>[] CLASES_COLS = {String.class, Integer.class};
//		@Override
//		public Class<?> getColumnClass(int columnIndex) {
//			// TODO Auto-generated method stub
//			return CLASES_COLS[columnIndex];
//		}
//
//		@Override
//		public int getColumnCount() {
//			// TODO Auto-generated method stub
//			return 2;
//		}
//
//		@Override
//		public int getRowCount() {
//			// TODO Auto-generated method stub
//			return 7;
//		}
//		
//		
//		@Override
//		public String getColumnName(int columnIndex) {
//			// TODO Auto-generated method stub
//			return cabeceras[columnIndex];
//		}
//		
//		@Override
//		public Object getValueAt(int rowIndex, int columnIndex) {
//			// TODO Auto-generated method stub
//			return cabeceras[rowIndex];
//			
//		}
//		
//		@Override
//		public boolean isCellEditable(int rowIndex, int columnIndex) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//
//		@Override
//		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//			// TODO Auto-generated method stub
//			switch(columnIndex) {
//			
//			}
//		}
//		
//		
//		ArrayList<TableModelListener> listaEsc = new ArrayList<>();
//
//		@Override
//		public void addTableModelListener(TableModelListener l) {
//			// TODO Auto-generated method stub
//			listaEsc.add(l);
//			
//		}
//		
//		@Override
//		public void removeTableModelListener(TableModelListener l) {
//			// TODO Auto-generated method stub
//			listaEsc.remove(l);
//			
//		}
//	
//		//DefaultTableModel lo hace así
//		public void fireTableChanged(TableModelEvent e) {
//			for(TableModelListener l: listaEsc) {
//				l.tableChanged(e);
//			}
//		}
//    }

}
