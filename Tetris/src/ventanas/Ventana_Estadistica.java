package ventanas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BD.GestionBDUsuario;

public class Ventana_Estadistica extends JFrame{
	private JFrame ventana;
	private JTable tablaDatos;
	private TableModel modeloDatos;
	private static JLabel signInlbl;
	private static JButton btnReturn;
	private String emailUsuario;
	static GestionBDUsuario base;
	
	public Ventana_Estadistica() {
		ventana = new JFrame("Estadística__");
		ventana.setSize(600, 250);
		ventana.setTitle("Statistics");
		setResizable(false);
		ventana.setLayout(new BorderLayout());
		
		emailUsuario = JOptionPane.showInputDialog(ventana, "Ingresa tu correo electrónico:", "Ingresar Correo Electrónico", JOptionPane.PLAIN_MESSAGE);
		
		signInlbl = new JLabel("Statistics");
		signInlbl.setFont(new Font("Cambria", Font.BOLD, 24));
		
		JPanel pnlLbl = new JPanel();
		pnlLbl.add(signInlbl, BorderLayout.CENTER);
		ventana.add(pnlLbl, BorderLayout.NORTH);
		
		JPanel pnlBoton = new JPanel();
		
		btnReturn = new JButton("Return");
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
    
		actualizarEstadisticas(emailUsuario);
		 
		ventana.add(pnlBoton, BorderLayout.SOUTH);
		ventana.setVisible(true);
		
		
		if(Ventana_Idioma.getIdiomaSeleccionado()!=null) {
            if(Ventana_Idioma.getIdiomaSeleccionado()=="Español") {
            	cambiarTextosEspañol();	
            }else if(Ventana_Idioma.getIdiomaSeleccionado()=="Français") {
            	cambiarTextosFrances();
            }else if(Ventana_Idioma.getIdiomaSeleccionado()=="Deutsch") {
            	cambiarTextosAleman();
            }
        }
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


	public void actualizarEstadisticas(String email) {
		base = new GestionBDUsuario(); 
	    ResultSet resultado = base.obtenerEstadisticasPorUsuario(email);

	    if (resultado != null) {
	        try {
	            while (resultado.next()) {
	                int timePlayed = resultado.getInt("timePlayed");
	                int dailyPlayTime = resultado.getInt("dailyPlayTime");
	                int roundsPlayed = resultado.getInt("roundsPlayed");
	                int maxPoints = resultado.getInt("maxPoints");
	                int minPoints = resultado.getInt("minPoints");
	                int totalPoints = resultado.getInt("totalPoints");
	                int dailyAveragePoints = resultado.getInt("dailyAveragePoints");

	                // Llena los datos en la tabla
	                ((MiTableModel) modeloDatos).setEstadisticas(
	                    new ArrayList<>(Arrays.asList(
	                        new Object[]{"Time played", timePlayed},
	                        new Object[]{"Daily playtime", dailyPlayTime},
	                        new Object[]{"Rounds played", roundsPlayed},
	                        new Object[]{"Max points", maxPoints},
	                        new Object[]{"Min points", minPoints},
	                        new Object[]{"Total points", totalPoints},
	                        new Object[]{"Daily average points", dailyAveragePoints}
	                    ))
	                );
	            }
	        } catch (SQLException e) {
	            // Maneja la excepción, por ejemplo, muestra un mensaje de error
	            e.printStackTrace();
	        } finally {
	            try {
	                resultado.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}	
	private class MiTableModel implements TableModel{

		private final Class<?>[] CLASES_COLS = {String.class, Integer.class};
		private ArrayList<Object[]> filas = new ArrayList<>();
		 
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return CLASES_COLS[columnIndex];
		}

		 public void setEstadisticas(ArrayList<Object[]> estadisticas) {
		        this.filas = estadisticas;
		        fireTableDataChanged();
		    }

		 public void fireTableDataChanged() {
			 fireTableChanged(getTableModelEvent());
		 }

		 private TableModelEvent getTableModelEvent() {
			 return new TableModelEvent(this, TableModelEvent.HEADER_ROW, TableModelEvent.HEADER_ROW,
					 TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE);
		 }

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public int getRowCount() {
			return filas.size();
		}
		
		private String[] cabeceras = {"Statistics", "Values"};

		@Override
		public String getColumnName(int columnIndex) {
			return cabeceras[columnIndex];
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return filas.get(rowIndex)[columnIndex];
			
		}
		
		public void addRow(Object[] rowData) {
            filas.add(rowData);
            TableModelEvent event = new TableModelEvent(this, filas.size() - 1, filas.size() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
            fireTableChanged(event);
        }
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			switch(columnIndex) {
			case 1:
				// aquí se pondrá el dato en la segunda columna y en la fila correspondiente.
				break;
			}
		}
		
		ArrayList<TableModelListener> listaEsc = new ArrayList<>();

		@Override
		public void addTableModelListener(TableModelListener l) {
			listaEsc.add(l);	
		}
		
		@Override
		public void removeTableModelListener(TableModelListener l) {
			listaEsc.remove(l);
		}
	
		//DefaultTableModel lo hace así
		public void fireTableChanged(TableModelEvent e) {
			for(TableModelListener l: listaEsc) {
				l.tableChanged(e);
			}
		}
	}
	
	public static void cambiarTextosEspañol() {
	     signInlbl.setText("Estadística");
	     btnReturn.setText("Volver");
	}
	public static void cambiarTextosIngles() {
	     signInlbl.setText("Statistics");
	     btnReturn.setText("Return");
	}
	public static void cambiarTextosFrances() {
	    signInlbl.setText("Statistique");
	    btnReturn.setText("Retour");
	}
	public static void cambiarTextosEuskara() {
	     signInlbl.setText("Estatistikak");
	     btnReturn.setText("Itzuli");
	}
	public static void cambiarTextosAleman() {
		signInlbl.setText("Statistik");
		btnReturn.setText("Zurück");
	}
}