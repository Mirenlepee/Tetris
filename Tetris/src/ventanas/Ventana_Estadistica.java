package ventanas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BD.ConexionBD;
import BD.GestionBDUsuario;

public class Ventana_Estadistica extends JFrame{
	private JFrame ventana;
	private JTable tablaDatos;
	private TableModel modeloDatos;
	private static JLabel signInlbl;
	private static JButton btnReturn;
	
	public Ventana_Estadistica(int idJugador) {
		ventana = new JFrame("Estadística__");
		ventana.setSize(600, 250);
		ventana.setTitle("Statistics");
		setResizable(false);
		ventana.setLayout(new BorderLayout());
		
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
    
		actualizarEstadisticas(idJugador);
		 
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
	
	public void actualizarEstadisticas(int idJugador) {
        try {
            // Obtener puntos del jugador desde la base de datos
//            ArrayList<Integer> puntos = ConexionBD.obtenerPuntosPorJugador(idJugador);

            // Calcular estadísticas (por ejemplo, calcular el total de puntos)
//             int totalPuntos = calcularTotalPuntos(puntos);

            // Actualizar la tabla con los resultados
//            ((MiTableModel) modeloDatos).addRow(new Object[]{"Total points", totalPuntos});
     
        	
        	// Obtener estadísticas del jugador desde la base de datos
            // Supongamos que tu método de la base de datos se llama obtenerEstadisticasPorUsuario
            ArrayList<Integer> estadisticas = ConexionBD.obtenerEstadisticasPorUsuario(idJugador);

            // Actualizar la tabla con los resultados
            ((MiTableModel) modeloDatos).setEstadisticas(estadisticas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int calcularTotalPuntos(ArrayList<Integer> puntos) {
        // Cálculo del total de puntos a partir de la lista de puntos
        int total = 0;
        for (int punto : puntos) {
            total += punto;
        }
        return total;
    }
	
	
	private class MiTableModel implements TableModel{

		private final Class<?>[] CLASES_COLS = {String.class, Integer.class};
		private ArrayList<Object[]> filas = new ArrayList<>();
		private ArrayList<Integer> estadisticas = new ArrayList<>();
		 
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return CLASES_COLS[columnIndex];
		}

		public void setEstadisticas(ArrayList<Integer> estadisticas) {
            this.estadisticas = estadisticas;
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
	
	private void cargarEstadisticasEnTabla(int usuarioId) {
	    DefaultTableModel modelo = (DefaultTableModel) tablaDatos.getModel();
	    modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

	    try {
	        ArrayList<Integer> estadisticas = ConexionBD.obtenerEstadisticasPorUsuario(usuarioId);

	        modelo.addRow(new Object[]{"Time played", estadisticas.get(0)});
	        modelo.addRow(new Object[]{"Daily playtime", estadisticas.get(1)});
	        modelo.addRow(new Object[]{"Rounds played", estadisticas.get(2)});
	        modelo.addRow(new Object[]{"Max points", estadisticas.get(3)});
	        modelo.addRow(new Object[]{"Min points", estadisticas.get(4)});
	        modelo.addRow(new Object[]{"Total points", estadisticas.get(5)});
	        modelo.addRow(new Object[]{"Daily average points", estadisticas.get(6)});

	    } catch (SQLException e) {
	        e.printStackTrace();
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