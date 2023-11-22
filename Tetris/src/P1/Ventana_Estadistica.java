package P1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		 tablaDatos = new JTable();
		 
//		 ((DefaultTableModel) modeloDatos).addColumn("");
//		 ((DefaultTableModel) modeloDatos).addColumn("");
//		 ((DefaultTableModel) modeloDatos).addRow(new Object[]{"Time played"});
//		 ((DefaultTableModel) modeloDatos).addRow(new Object[]{"Daily playtime"});
//		 ((DefaultTableModel) modeloDatos).addRow(new Object[]{"Rounds played"});
//		 ((DefaultTableModel) modeloDatos).addRow(new Object[]{"Max points"});
//		 ((DefaultTableModel) modeloDatos).addRow(new Object[]{"Min points"});
//		 ((DefaultTableModel) modeloDatos).addRow(new Object[]{"Total points"});
//		 ((DefaultTableModel) modeloDatos).addRow(new Object[]{"Daily average points"});
		
		 ventana.add(new JScrollPane(tablaDatos), BorderLayout.CENTER);
		 setDatos();
    
		 ventana.add(pnlBoton, BorderLayout.SOUTH);
		 ventana.setVisible(true);
	}
	
	public void setDatos() {
		Object[][] datos = {{"Time played"}, {"Daily playtime"}, {"Rounds played"}, {"Max points"}, {"Min points"}, {"Total points"}, {"Daily average points"}};
		modeloDatos = new MiTableModel();
		tablaDatos.setModel(modeloDatos);
		for(Object[] d: datos) {
			((MiTableModel) modeloDatos).addRow(d);
		}
		// Establecer ceros en la segunda columna (por el momento)
	    for (int i = 0; i < modeloDatos.getRowCount(); i++) {
	        modeloDatos.setValueAt(0, i, 1);
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

//public class Ventana_Estadistica extends AbstractTableModel{
//	private JFrame ventana;
//	private JTable tablaDatos;
//	private AbstractTableModel modeloDatos;
//	
//	String[] cabeceras = {"Time played", "Daily playtime", "Rounds played", "Max points", "Min points", "Total points", "Daily average points"};
//	Object[][] datos = {{"Dato 1"}, {"Dato 2"}, {"Dato 3"}, {"Dato 4"}, {"Dato 5"}, {"Dato 6"}, {"Dato 7"}};
//    
//    public Ventana_Estadistica() {
//    	ventana = new JFrame("Estadística");
//    	ventana.setSize(600, 800);
//    	ventana.setTitle("Estadística");
//    	ventana.setLayout(new BorderLayout());
//
//        JLabel signInlbl = new JLabel("Statistics");
//        signInlbl.setFont(new Font("Cambria", Font.BOLD, 24));
//
//        JPanel pnlLbl = new JPanel();
//        pnlLbl.add(signInlbl, BorderLayout.CENTER);
//        ventana.add(pnlLbl, BorderLayout.NORTH);
//        
//        JPanel pnlBoton = new JPanel();
//        
//        JButton btnReturn = new JButton("Return");
//        pnlBoton.add(btnReturn);
//        btnReturn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				ventana.dispose();
//			}
//        	
//        });
//        
//        ventana.add(pnlBoton, BorderLayout.SOUTH);
//    	ventana.setVisible(true);
//    }
//    
//    public void setDatos() {
////    	modeloDatos = new MiTableModel(cabeceras, datos);
////    	tablaDatos = new JTable(modeloDatos);
////    	JScrollPane scrollPane = new JScrollPane(tablaDatos);
////        ventana.add(scrollPane, BorderLayout.CENTER);
//    }
//    
//
//    public void MiTableModel(String[] cabeceras, Object[][] datos) {
//        this.cabeceras = cabeceras;
//        this.datos = datos;
//    }
//    public int getRowCount() {
//        return datos.length;
//    }
//
//    public int getColumnCount() {
//        return cabeceras.length;
//    }
//
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        return datos[rowIndex][columnIndex];
//    }
//    
//    public String getColumnName(int column) {
//        return cabeceras[column];
//    }
//    
////    public void setValueAt(Object value, int row, int col) {
////        datos[row][col] = value;
////        fireTableCellUpdated(row, col);
////    }
//
//    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        return true; // Permitir la edición de todas las celdas
//    }

    
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

//public class Ventana_Estadistica extends JFrame {
//    private JLabel lblTitle;
//    private JLabel lblTotalTime;
//    private JLabel lblDailyTime;
//    private JLabel lblGamesPlayed;
//    private JLabel lblMaxPoints;
//    private JLabel lblMinPoints;
//    private JLabel lblTotalPoints;
//    private JLabel lblDailyAverage;
//    private JButton btnBack;
//
//
//    public Ventana_Estadistica() {
//        setSize(600, 800);
//        setTitle("Estadística");
//        setLayout(new GridLayout(9, 2));
//
//        lblTitle = new JLabel("Estadísticas");
//
//        lblTitle.setFont(new Font("Cambria", Font.BOLD, 24));
//        lblTotalTime = new JLabel("Total Time: ");
//        lblDailyTime = new JLabel("Daily Time: ");
//        lblGamesPlayed = new JLabel("Games Played: ");
//        lblMaxPoints = new JLabel("Max Points: ");
//        lblMinPoints = new JLabel("Min Points: ");
//        lblTotalPoints = new JLabel("Total Points: ");
//        lblDailyAverage = new JLabel("Daily Average: ");
//        btnBack = new JButton("Back");
//
//      
//        JTextField txtTotalTime = new JTextField();
//        txtTotalTime.setEditable(false);
//
//        JTextField txtDailyTime = new JTextField();
//        txtDailyTime.setEditable(false);
//
//        JTextField txtGamesPlayed = new JTextField();
//        txtGamesPlayed.setEditable(false);
//
//        JTextField txtMaxPoints = new JTextField();
//        txtMaxPoints.setEditable(false);
//
//        JTextField txtMinPoints = new JTextField();
//        txtMinPoints.setEditable(false);
//
//        JTextField txtTotalPoints = new JTextField();
//        txtTotalPoints.setEditable(false);
//
//        JTextField txtDailyAverage = new JTextField();
//        txtDailyAverage.setEditable(false);
//
//        btnBack.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//            }
//        });
//
//        add(lblTitle);
//        add(new JLabel());
//        add(lblTotalTime);
//        add(txtTotalTime);
//        add(lblDailyTime);
//        add(txtDailyTime);
//        add(lblGamesPlayed);
//        add(txtGamesPlayed);
//        add(lblMaxPoints);
//        add(txtMaxPoints);
//        add(lblMinPoints);
//        add(txtMinPoints);
//        add(lblTotalPoints);
//        add(txtTotalPoints);
//        add(lblDailyAverage);
//        add(txtDailyAverage);
//        add(btnBack);
//        
//        txtTotalTime.setText("Texto de Prueba");
//        
//        setVisible(true);
//    }
//}


